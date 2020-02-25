package com.usernameme.community.controller;

import com.usernameme.community.dto.AccessTokenDTO;
import com.usernameme.community.dto.GithubUser;
import com.usernameme.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;

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


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsevret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirecturi);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.gitUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
