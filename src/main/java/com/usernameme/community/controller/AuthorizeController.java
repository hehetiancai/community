package com.usernameme.community.controller;

import com.usernameme.community.dto.AccessTokenDTO;
import com.usernameme.community.dto.GithubUser;
import com.usernameme.community.mapper.UserMapper;
import com.usernameme.community.model.User;
import com.usernameme.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: AuthorizeController
 * @Author: hhtc
 * @Date: 2020/2/21 0021 上午 10:06
 * @Version: V
 */
@Controller
public class AuthorizeController {
    //定义githubProvider
    @Autowired
    private GithubProvider githubProvider;

    //定义可变化地址
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clientsevret;
    @Value("${github.redirect.uri}")
    private String redirecturi;

    //注入userMapper
    @Autowired
    private UserMapper userMapper;
    private String token;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,

                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsevret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirecturi);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //github登录成功以后，获取用户信息生成token
            User user = new User();
            String token = UUID.randomUUID().toString();
            //把token  set到user对象中，将user保存到数据库中
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //并且把token放到cookie中
            response.addCookie(new Cookie("token",token));

            return "redirect:/";
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }

    }
}
