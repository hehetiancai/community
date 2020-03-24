package com.usernameme.community.dto;

import lombok.Data;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: GithubUser
 * @Author: hhtc
 * @Date: 2020/2/24 0024 下午 7:36
 * @Version: V
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
