package com.zengyanyu.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "天地图控制器")
@RestController
public class TdtController {

    @Value("${tianditu.wmts-url}")
    private String tdtUrl;

    @Value("${tianditu.key}")
    private String tdtKey;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 天地图历史影像瓦片代理接口
     *
     * @param z        缩放级别（tilematrix）
     * @param y        瓦片行号（tilerow）
     * @param x        瓦片列号（tilecol）
     * @param time     历史时间（格式YYYYMMDD，如20220101）
     * @param response 响应对象
     * @return 瓦片图片字节流
     */
    @ApiOperation("天地图历史影像瓦片代理接口")
    @GetMapping("/tdt/history/tile")
    public byte[] getHistoryTile(@RequestParam String z, @RequestParam String y, @RequestParam String x, @RequestParam String time, HttpServletResponse response) {
        // 1. 拼接天地图官方WMTS请求URL（100%符合标准格式）
        String url = String.format("%s?service=WMTS&request=GetTile&version=1.0.0&layer=img&style=default&tilematrixset=w&tilematrix=%s&tilerow=%s&tilecol=%s&time=%s&tk=%s", tdtUrl, z, y, x, time, tdtKey);

        // 2. 设置响应头，返回图片格式
        response.setContentType("image/png");
        // 缓存1天，优化性能
        response.setHeader("Cache-Control", "max-age=86400");

        // 3. 后端请求天地图，返回瓦片给前端
        return restTemplate.getForObject(url, byte[].class);
    }

}