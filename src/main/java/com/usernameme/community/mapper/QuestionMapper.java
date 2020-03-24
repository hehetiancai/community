package com.usernameme.community.mapper;

import com.usernameme.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能描述:
 *
 * @email:username_me@163.com
 * @ClassName: QuestionMapper
 * @Author: hhtc
 * @Date: 2020/3/22 0022 下午 1:38
 * @Version: V
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (id,title,description,gmt_create,gmt_modified,creator,tag) values (#{id},#{title},#{description},#{gmt_Create},#{gmt_Modified},#{creator},#{tag})")
    void create(Question question);
}
