package com.usernameme.community.dto;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: GithubUser
 * @Author: hhtc
 * @Date: 2020/2/24 0024 下午 7:36
 * @Version: V
 */
public class GithubUser {
    private String name;
    private long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
