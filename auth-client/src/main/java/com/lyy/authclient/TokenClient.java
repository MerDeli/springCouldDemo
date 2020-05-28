package com.lyy.authclient;

import dto.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "api-security")
public interface TokenClient {
    @RequestMapping(value = "/sso/getInfo", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowCredentials = "true")
    ApiResult getUserInfo(@RequestHeader("accessToken") String accessToken);
}
