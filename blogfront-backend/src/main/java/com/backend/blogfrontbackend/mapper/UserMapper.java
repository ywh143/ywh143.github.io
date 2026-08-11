package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.user.User;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from blog.user where user_account_username = #{text}")
    User findByUsername(String text);

    @Update("""
            UPDATE blog.user
            set name=#{name},gender=#{gender},age=#{age},
            school=#{school},identity=#{identity}
            where user_account_username=#{userAccountUserName}
            """)
    boolean updateUserByUserName(User user);

}
