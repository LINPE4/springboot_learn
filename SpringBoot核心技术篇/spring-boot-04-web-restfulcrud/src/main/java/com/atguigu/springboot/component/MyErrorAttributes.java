package com.atguigu.springboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

//给容器中加入我们自己定义的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    //返回值的map就是页面和json能获取的所有字段
//    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace, WebRequest request) {
//        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
//        map.put("company","atguigu");
//
//        //我们的异常处理器携带的数据
//        Map<String,Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);
//        map.put("ext",ext);
//        return map;
//    }

    //返回值的map就是页面和json能获取的所有字段
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","atguigu");
        //我们的异常处理器携带的数据
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        map.put("ext",ext);
        return map;
    }
}
