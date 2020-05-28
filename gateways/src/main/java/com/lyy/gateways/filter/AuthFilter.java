package com.lyy.gateways.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyy.authclient.TokenClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import dto.ApiResult;
import feign.FeignException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

@Component
public class AuthFilter extends ZuulFilter {
    @Autowired
    private TokenClient tokenClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HashMap<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = (String) headerNames.nextElement();
            // 排除Cookie字段
            if (key.equalsIgnoreCase("Cookie")) {
                continue;
            }
            String value = request.getHeader(key);
            map.put(key, value);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.convertValue(map, JsonNode.class);
        String uri = request.getRequestURI();
        if(StringUtils.contains(uri,"/oauth") || StringUtils.contains(uri,"/secur")){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String accessToken = request.getHeader("accessToken");
        if(StringUtils.isBlank(accessToken)){
            failback(requestContext, HttpStatus.UNAUTHORIZED.value(),"未授权");
        }else {
            try{
                ApiResult result = tokenClient.getUserInfo(accessToken);
                HashMap userInfo = (HashMap)result.getData();
                String username = (String)userInfo.get("uername");
                if(StringUtils.isNotBlank(username)){
                    return null;
                }
                failback(requestContext, HttpStatus.METHOD_NOT_ALLOWED.value(), "无权限");
            }catch (FeignException fe){
                fe.printStackTrace();
                int status = fe.status();
                failback(requestContext, status, fe.getMessage());
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return null;
    }

    public void failback(RequestContext requestContext,int status,String msg){
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(status);
        requestContext.getResponse().setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> result = new HashMap<>();
        result.put("code",status);
        result.put("message",msg);
        try {
            requestContext.setResponseBody(objectMapper.writeValueAsString(result));
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

    }
}
