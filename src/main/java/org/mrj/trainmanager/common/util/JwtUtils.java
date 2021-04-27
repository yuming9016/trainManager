package org.mrj.trainmanager.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @作者 s.1228
 * @日期 2021/3/16 14:17
 * @Version 1.0
 */
@Slf4j
public class JwtUtils {

    //定义两个常量
    public static final long EXPIRE = 1000 * 60 * 60 * 24;//设置token的过期时间
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";//秘钥，可自己随便写，为了加密操作

    //根据id和Nickname生成token字符串
    public static String getJwtToken(String id, String name) {

        //构建jwt字符串
        String JwtToken = Jwts.builder()
                //配置jwt头信息，这是固定的
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")

                //设置字符串过期时间
                .setSubject("guli-user")//分类好像要改？
                .setIssuedAt(new Date())//得到当前时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//超过EXPIRE就过期了

                //设置token的用户信息部分，多个可以再添加
                .claim("id", id)
                .claim("nickname", name)

                //设置签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    //判断token是否存在与有效
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.hasLength(jwtToken))/* 判断是否为空 */ return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);//解析判断
        } catch (Exception e){
            log.error("JwtUtils#checkToken error",e);
            return false;
        }
        return true;
    }

    //判断token是否存在与有效(通过request在header里面把字符串得到，再做一次验证)
    public static boolean checkToken(HttpServletRequest request) {
        try{
            String jwtToken = request.getHeader("token");
            if (StringUtils.hasLength(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            log.error("JwtUtils#checkToken error",e);
            return false;
        }
        return true;
    }

    //根据token字符串获取token字符串里的用户信息（id）
    public static String getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.hasLength(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);//得到值
        Claims claims = claimsJws.getBody();//得到字符串中的主体部分
        return (String)claims.get("id");//还可以是getname
    }
}
