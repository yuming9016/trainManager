package org.mrj.trainmanager.interceptor;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mrj.trainmanager.common.util.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    //这是拦截器类，用于验证token
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");//拿到请求头对象header中Authorization字段对应的值
//        System.out.println(token);
        Map<String,Object> map = new HashMap<>();
        try {
            JwtUtils.checkToken(token);//使用工具验证令牌
            return true;//放行请求
            //根据不同异常进行不同处理
        } catch (TokenExpiredException e) {
            map.put("msg", "Token已经过期!!!");
        } catch (SignatureVerificationException e){
            map.put("msg", "签名错误!!!");
        } catch (AlgorithmMismatchException e){
            map.put("msg", "加密算法不匹配!!!");
        } catch (NullPointerException e){
            System.out.println("无token");
        }catch (Exception e) {
//            e.printStackTrace();
            map.put("msg", "无效token~~");
        }
        map.put("state", false);
        //map转为json
        String json=new ObjectMapper().writeValueAsString(map);
        //设置响应格式
        response.setContentType("application/json;charset=UTF-8");
        //返回到前台
        response.getWriter().println(json);


        return false;
    }
}
