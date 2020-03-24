package com.usernameme.community.dto;

import lombok.Data;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: AccesstokenDTO
 * @Author: hhtc
 * @Date: 2020/2/21 0021 上午 10:37
 * @Version: 封装AccesstokenDTO对象，便于调用
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
