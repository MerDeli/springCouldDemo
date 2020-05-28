package com.lyy.authclient;

import dto.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "api-auth")
public interface AuthClient {

    @RequestMapping(value = "/sso/getUser", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", allowCredentials = "true")
    ApiResult getUser(@RequestParam("username") String username);

}
