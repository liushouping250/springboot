package com.example.demo.interceptor;


import com.example.demo.config.global_exception.ResultCode;
import com.example.demo.config.global_exception.ResultUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @description: Filter 拦截器的demo
 * @author: Mr.monster.liu
 * @create: 2021-11-04 18:20
 **/
@Component
@WebFilter(urlPatterns="/**",filterName="loginFilter")
public class LoginFilter implements Filter {

    /**
     * 排除不拦截的url
     */
    private static final String[]  EXCLUDE_PATH_PATTERNS   = { "/stuInfo/getAllStuInfoA"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        //获取请求url地址，不拦截excludePathPatterns中的url
        String url = req.getRequestURI();
        if (!Arrays.asList(EXCLUDE_PATH_PATTERNS).contains(url)) {
            System.out.println("Filter开始拦截了................");
            //业务代码
            String authorization = req.getHeader("Authorization");
            if(StringUtils.isEmpty(authorization)){
                exceptionJson(res);
                return;
            }
        }
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter开始destroy................");
    }


    private void exceptionJson(HttpServletResponse response) throws IOException {
        ResultUtil<Object> error = ResultUtil.error(ResultCode.HANDLER_EXCEPTION.getCode(), ResultCode.HANDLER_EXCEPTION.getMsg());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String json = new Gson().toJson(error);
        response.getWriter().print(json);
    }
}
