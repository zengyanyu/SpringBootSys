package com.zengyanyu.system.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zengyanyu
 */
@Api(tags = "天地图历史影响 控制器")
@RestController
@RequestMapping("/tdt")
public class TdtController {

    /**
     * 天地图账号：
     * https://oauth.tianditu.gov.cn/
     * 1194314874@qq.com
     * Zeng$123456
     */
    private static final String TDT_SERVER_KEY = "33725e220df1831cdcaffabbea3b96d9";
    // 天地图WMTS基础地址
    private static final String TDT_BASE_URL = "https://t0.tianditu.gov.cn/img_w/wmts";

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
    @GetMapping("/history/tile")
    public byte[] getHistoryTile(
            @RequestParam String z,
            @RequestParam String y,
            @RequestParam String x,
            @RequestParam String time,
            HttpServletResponse response
    ) {
        // 1. 严格按照天地图WMTS标准格式拼接URL（参数顺序、大小写完全正确）
        String url = String.format(
                "%s?service=WMTS&request=GetTile&version=1.0.0&layer=img&style=default&tilematrixset=w&tilematrix=%s&tilerow=%s&tilecol=%s&time=%s&tk=%s",
                TDT_BASE_URL, z, y, x, time, TDT_SERVER_KEY
        );

        // 2. 设置正确的图片响应头
        response.setContentType("image/png");
        // 缓存1天，优化性能
        response.setHeader("Cache-Control", "max-age=86400");

        // 3. 后端请求天地图，返回瓦片给前端
        return restTemplate.getForObject(url, byte[].class);
    }
}