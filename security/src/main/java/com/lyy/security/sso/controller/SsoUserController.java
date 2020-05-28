//package com.lyy.security.sso.controller;
//
//import com.lyy.auth.entity.Account;
//import com.lyy.security.sso.service.UserService;
//import dto.ApiResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/sso")
//public class SsoUserController {
//    @Autowired
//    private UserService userService;
//    /* token存储器 */
//    @Autowired
//    private RedisTokenStore redisTokenStore;
//
//    @GetMapping("/getInfo")
//    @CrossOrigin(origins = "*", allowCredentials = "true")
//    public ResponseEntity<ApiResult> getUserInfo(@RequestHeader("accessToken") String accessToken){
//        try {
//            OAuth2Authentication oAuth2Authentication = redisTokenStore.readAuthentication(accessToken);
//            if (null == oAuth2Authentication) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(ApiResult.valueOf(HttpStatus.UNAUTHORIZED.value(), "无效TOKEN"));
//            }
//            Authentication authentication = oAuth2Authentication.getUserAuthentication();
//            String username = authentication.getName();
//            Account ssoAccount = userService.getUser(username);
//            if (null == ssoAccount) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(ApiResult.valueOf(HttpStatus.UNAUTHORIZED.value(), "无效TOKEN"));
//            }
//            ssoAccount.setPassword("");
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(ApiResult.success(ssoAccount));
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(ApiResult.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内容错误"));
//        }
//    }
//}
