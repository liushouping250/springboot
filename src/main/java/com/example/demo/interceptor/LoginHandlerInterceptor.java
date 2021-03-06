package com.example.demo.interceptor;

import com.example.demo.config.global_exception.ResultCode;
import com.example.demo.config.global_exception.ResultUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 测试HandlerInterceptor 拦截器
 * @author: Mr.monster.liu
 * @create: 2021-11-04 16:26
 **/
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 预处理回调方法，实现处理器的预处理
     * 返回值：true表示继续流程；false表示流程中断，不会继续调用其他的拦截器或处理器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("开始拦截.........");
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isEmpty(authorization)){
            exceptionJson(response);
            return false;
        }
        return true;
    }

    /**
     * 后处理回调方法，实现处理器（controller）的后处理，但在渲染视图之前
     * 此时我们可以通过modelAndView对模型数据进行处理或对视图进行处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("postHandle.........");
    }
    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，
     * 如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，
     * 但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        System.out.println("afterCompletion.........");
    }

    private void exceptionJson(HttpServletResponse response) throws IOException {
        ResultUtil<Object> error = ResultUtil.error(ResultCode.HANDLER_EXCEPTION.getCode(), ResultCode.HANDLER_EXCEPTION.getMsg());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String json = new Gson().toJson(error);
        response.getWriter().print(json);
    }
}
