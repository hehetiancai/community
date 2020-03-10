package com.usernameme.community.mapper;

import com.usernameme.community.model.User;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: UserMapper
 * @Author: hhtc
 * @Date: 2020/2/28 0028 下午 5:50
 * @Version: V
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
