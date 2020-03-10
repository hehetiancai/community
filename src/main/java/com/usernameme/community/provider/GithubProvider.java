package com.usernameme.community.provider;

import com.alibaba.fastjson.JSON;
import com.usernameme.community.dto.AccessTokenDTO;
import com.usernameme.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import java.io.IOException;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: GithubProvider
 * @Author: hhtc
 * @Date: 2020/2/21 0021 上午 10:35
 * @Version: V
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubuser = JSON.parseObject(string, GithubUser.class);
            System.out.println("正确");
            return githubuser;
        } catch (IOException e) {
            System.out.println("异常");
        }
        return null;
    }
}
