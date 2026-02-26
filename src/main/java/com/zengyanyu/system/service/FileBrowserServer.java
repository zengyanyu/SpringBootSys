package com.zengyanyu.system.service;

import org.springframework.http.HttpHeaders;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileBrowserServer {

    private static final int PORT = 8081;
    // 用于存放 CSS 等静态资源
    private static final String WEB_ROOT = "D:/";

    private final ServerSocket serverSocket;
    private final ExecutorService threadPool;

    public static void main(String[] args) throws IOException {
        FileBrowserServer server = new FileBrowserServer(PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.start();
    }

    public FileBrowserServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.threadPool = Executors.newCachedThreadPool();
    }

    public void start() {
        log("服务器启动，监听端口: " + PORT);
        log("请在浏览器中访问: http://localhost:" + PORT);

        while (!serverSocket.isClosed()) {
            try {
                Socket clientSocket = serverSocket.accept();
                threadPool.execute(new ClientHandler(clientSocket));
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    log("接受客户端连接时出错: " + e.getMessage());
                }
            }
        }
    }

    public void stop() {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
        }
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            // ignore
        }
        log("服务器已关闭。");
    }

    private class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                 BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream())) {

                String requestLine = in.readLine();
                if (requestLine == null) {
                    return;
                }
                log("请求: " + requestLine);

                String[] tokens = requestLine.split(" ");
                String method = tokens[0];
                String requestUri = tokens[1];

                // 忽略请求的其余部分
//                while (in.readLine() != null && !in.readLine().isEmpty()) ;

                if (!"GET".equals(method)) {
                    sendErrorResponse(out, dataOut, 405, "Method Not Allowed");
                    return;
                }

                // 路由处理
                if (requestUri.startsWith("/browse")) {
                    handleBrowse(requestUri, out, dataOut);
                } else if (requestUri.startsWith("/download")) {
                    handleDownload(requestUri, out, dataOut);
                } else if (requestUri.equals("/")) {
                    // 根路径，重定向到浏览 C:\
                    sendRedirect(out, dataOut, "/browse?path=" + WEB_ROOT);
                } else if (requestUri.startsWith("/webroot/")) {
                    // 提供静态资源 (CSS)
                    handleStaticFile(requestUri, out, dataOut);
                } else {
                    sendErrorResponse(out, dataOut, 404, "Not Found");
                }

            } catch (IOException e) {
                log("处理客户端请求时出错: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    log("关闭客户端套接字时出错: " + e.getMessage());
                }
            }
        }

        private void handleBrowse(String requestUri, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
            String path = getQueryParameter(requestUri, "path");
            if (path == null || path.isEmpty()) {
                sendRedirect(out, dataOut, "/browse?path=" + WEB_ROOT);
                return;
            }

            File dir = new File(path);

            // 安全检查：防止路径遍历和访问不存在的路径
            if (!dir.exists() || !dir.isDirectory()) {
                sendErrorResponse(out, dataOut, 404, "Directory Not Found");
                return;
            }

            File[] files = dir.listFiles();
            if (files == null) {
                sendErrorResponse(out, dataOut, 403, "Forbidden (Cannot list directory)");
                return;
            }

            // 排序：目录优先，然后按名称排序
            Arrays.sort(files, (f1, f2) -> {
                if (f1.isDirectory() && f2.isFile()) {
                    return -1;
                }
                if (f1.isFile() && f2.isDirectory()) {
                    return 1;
                }
                return f1.getName().compareToIgnoreCase(f2.getName());
            });

            String html = generateHtmlForDirectory(dir, files);
            byte[] htmlBytes = html.getBytes(StandardCharsets.UTF_8);
            sendResponse(out, dataOut, 200, "OK", "text/html; charset=UTF-8", htmlBytes);
        }

        private void handleDownload(String requestUri, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
            String path = getQueryParameter(requestUri, "path");
            if (path == null || path.isEmpty()) {
                sendErrorResponse(out, dataOut, 400, "Bad Request");
                return;
            }

            File file = new File(path);
            if (!file.exists() || !file.isFile()) {
                sendErrorResponse(out, dataOut, 404, "File Not Found");
                return;
            }

            String contentType = getContentType(file.getName());
            byte[] fileData = Files.readAllBytes(file.toPath());

            // 设置下载头
            Map<String, String> headers = new HashMap<>();
            headers.put(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\"");

            sendResponse(out, dataOut, 200, "OK", contentType, fileData, headers);
        }

        private void handleStaticFile(String requestUri, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
            String filePath = WEB_ROOT + requestUri.substring("/webroot".length());
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                sendErrorResponse(out, dataOut, 404, "Static File Not Found");
                return;
            }
            byte[] fileData = Files.readAllBytes(file.toPath());
            sendResponse(out, dataOut, 200, "OK", getContentType(file.getName()), fileData);
        }

        private String generateHtmlForDirectory(File dir, File[] files) throws UnsupportedEncodingException {
            StringBuilder sb = new StringBuilder();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 父目录链接
            File parent = dir.getParentFile();
            String parentLink = "";
            if (parent != null) {
                parentLink = String.format("<tr><td colspan=\"3\"><a href=\"/browse?path=%s\">.. (返回上级目录)</a></td></tr>", URLEncoder.encode(parent.getAbsolutePath(), "UTF-8"));
            }

            sb.append("<!DOCTYPE html>");
            sb.append("<html lang=\"zh-CN\"><head><meta charset=\"UTF-8\">");
            sb.append("<title>文件浏览器 - ").append(dir.getAbsolutePath()).append("</title>");
            sb.append("<link rel=\"stylesheet\" href=\"/webroot/style.css\">");
            sb.append("</head><body>");
            sb.append("<div class=\"container\">");
            sb.append("<h1>目录: ").append(dir.getAbsolutePath()).append("</h1>");
            sb.append("<table class=\"file-list\">");
            sb.append("<thead><tr><th>名称</th><th class=\"size\">大小</th><th class=\"modified\">修改时间</th></tr></thead>");
            sb.append("<tbody>");
            sb.append(parentLink);

            for (File file : files) {
                String name = file.getName();
                String path = URLEncoder.encode(file.getAbsolutePath(), "UTF-8");
                String icon = file.isDirectory() ? "[目录]" : "[文件]";
                String link = file.isDirectory() ? "/browse?path=" + path : "/download?path=" + path;
                String size = file.isDirectory() ? "-" : formatFileSize(file.length());
                String modified = dateFormat.format(new Date(file.lastModified()));

                sb.append("<tr>");
                sb.append("<td><span class=\"file-icon\">").append(icon).append("</span><a href=\"").append(link).append("\">").append(name).append("</a></td>");
                sb.append("<td class=\"size\">").append(size).append("</td>");
                sb.append("<td class=\"modified\">").append(modified).append("</td>");
                sb.append("</tr>");
            }

            sb.append("</tbody></table>");
            sb.append("</div>");
            sb.append("</body></html>");
            return sb.toString();
        }

        private String getQueryParameter(String uri, String key) {
            try {
                String query = new URI(uri).getQuery();
                if (query == null) {
                    return null;
                }
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    if (pair.length == 2 && key.equals(pair[0])) {
                        return URLDecoder.decode(pair[1], "UTF-8");
                    }
                }
            } catch (URISyntaxException | UnsupportedEncodingException e) {
                log("解析查询参数失败: " + e.getMessage());
            }
            return null;
        }

        private String formatFileSize(long bytes) {
            if (bytes < 1024) {
                return bytes + " B";
            }
            int exp = (int) (Math.log(bytes) / Math.log(1024));
            char pre = "KMGTPE".charAt(exp - 1);
            return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
        }

        private String getContentType(String fileName) {
            if (fileName.endsWith(".css")) {
                return "text/css";
            }
            if (fileName.endsWith(".js")) {
                return "application/javascript";
            }
            if (fileName.endsWith(".png")) {
                return "image/png";
            }
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                return "image/jpeg";
            }
            if (fileName.endsWith(".gif")) {
                return "image/gif";
            }
            if (fileName.endsWith(".txt")) {
                return "text/plain";
            }
            if (fileName.endsWith(".pdf")) {
                return "application/pdf";
            }
            // 默认为二进制流，强制下载
            return "application/octet-stream";
        }

        private void sendResponse(PrintWriter out, BufferedOutputStream dataOut, int statusCode, String statusText, String contentType, byte[] bodyData) throws IOException {
            sendResponse(out, dataOut, statusCode, statusText, contentType, bodyData, null);
        }

        private void sendResponse(PrintWriter out, BufferedOutputStream dataOut, int statusCode, String statusText, String contentType, byte[] bodyData, Map<String, String> extraHeaders) throws IOException {
            out.println("HTTP/1.1 " + statusCode + " " + statusText);
            out.println("Server: Java FileBrowserServer");
            out.println("Date: " + new Date());
            out.println("Content-Type: " + contentType);
            out.println("Content-Length: " + bodyData.length);
            if (extraHeaders != null) {
                for (Map.Entry<String, String> header : extraHeaders.entrySet()) {
                    out.println(header.getKey() + ": " + header.getValue());
                }
            }
            out.println(); // 空行，分隔头部和主体
            out.flush();
            dataOut.write(bodyData);
            dataOut.flush();
        }

        private void sendRedirect(PrintWriter out, BufferedOutputStream dataOut, String location) throws IOException {
            String body = "<h1>Redirecting...</h1>";
            byte[] bodyData = body.getBytes(StandardCharsets.UTF_8);
            out.println("HTTP/1.1 302 Found");
            out.println("Location: " + location);
            out.println("Content-Type: text/html");
            out.println("Content-Length: " + bodyData.length);
            out.println();
            out.flush();
            dataOut.write(bodyData);
            dataOut.flush();
        }

        private void sendErrorResponse(PrintWriter out, BufferedOutputStream dataOut, int statusCode, String message) throws IOException {
            String html = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>" + statusCode + " - " + message + "</title></head><body><h1>" + statusCode + " - " + message + "</h1></body></html>";
            byte[] htmlBytes = html.getBytes(StandardCharsets.UTF_8);
            sendResponse(out, dataOut, statusCode, message, "text/html; charset=UTF-8", htmlBytes);
        }
    }

    private static void log(String message) {
        System.out.println("[ " + java.time.LocalTime.now() + " ] " + message);
    }

}

