package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
public class MyFilter extends ZuulFilter {

    private static Logger logger =  LoggerFactory.getLogger(MyFilter.class);

    /*过滤其类型，前置过滤还是后置过滤*/
    @Override
    public String filterType() {
        return "pre";//返回字符串为pre，则为前置过滤，到请求目标方法前过滤
    }
    @Override
    public int filterOrder() {
        return 0;
    }

    //当前过滤是否生效，true生效
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //业务逻辑
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info(String.format("%s >>> %s",request.getMethod(),request.getRequestURI().toString()));

        Object accessToken = request.getParameter("token");
        if (accessToken==null){
            logger.warn("token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                currentContext.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        logger.info("ok");
        return null;
    }
}
