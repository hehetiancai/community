package com.usernameme.community.model;

import lombok.Data;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: Question
 * @Author: hhtc
 * @Date: 2020/3/22 0022 下午 1:47
 * @Version: V
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Integer creator;
    private long gmt_Create;
    private long gmt_Modified;
    private String tag;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
}

