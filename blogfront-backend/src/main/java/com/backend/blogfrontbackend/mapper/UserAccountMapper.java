package com.backend.blogfrontbackend.mapper;

import com.backend.blogfrontbackend.entity.user.User;
import com.backend.blogfrontbackend.entity.user.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserAccountMapper {

    @Select("select * from blog.user_account where user_account_username = #{text} or email = #{text}")
    UserAccount findByUsername(String text);

    @Insert("""
    insert into blog.user_account(user_account_username, password,email) values(#{userAccountUserName},#{password},#{email})
    """)
    int registerAccount(UserAccount userAccount);

    @Insert("""
    insert into blog.user(user_account_username, identity) values(#{userAccountUserName},#{identity})
    """)
    int insertInfo(User user);

    @Insert("""
    insert into blog.teacher(user_account_username) values(#{userAccountUserName})
    """)
    int insertTeacherInfo(User user);

    @Insert("""
    insert into blog.admin_account(admin_username,email,password) values(#{adminUserName},#{email},#{password})
    """)
    int insertAdminInfo(String adminUserName,String email,String password);


    @Update("update blog.user_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);

    @Update("update blog.user_account set password = #{password},email =#{email} where user_account_username = #{userAccountUserName}")
    boolean updateUserAccount(UserAccount userAccount);

}
