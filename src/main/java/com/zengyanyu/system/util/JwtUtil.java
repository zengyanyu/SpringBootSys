package com.zengyanyu.system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {

    private JwtUtil() {
    }

    // 确保这个密钥在生产环境中安全存储和管理
    private static String secretKey = "your_secret_key";
    // 30天，单位为毫秒
    private static Long jwtExpiration = 2591999965L;

    /**
     * 生成token
     *
     * @param username
     * @return
     */
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * 创建token
     *
     * @param claims
     * @param subject
     * @return
     */
    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(DateUtils.getCurrentDate())
                .setExpiration(DateUtils.getDate(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 根据token获取用用户名
     *
     * @param token
     * @return
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 根据token获取token有效期
     *
     * @param token
     * @return
     */
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    /**
     * 判断token是否过期
     *
     * @param token
     * @return
     */
    public static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static Boolean validateToken(String token, String userAccount) {
        String username = extractUsername(token);
        return (username.equals(userAccount) && !isTokenExpired(token));
    }

}
