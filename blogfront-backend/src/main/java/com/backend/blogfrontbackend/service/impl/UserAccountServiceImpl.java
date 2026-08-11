package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.user.UserAccount;
import com.backend.blogfrontbackend.mapper.UserAccountMapper;

import com.backend.blogfrontbackend.service.UserAccountService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Value("${spring.mail.username}")
    String from;

    @Resource
    UserAccountMapper mapper;

    @Resource
    MailSender mailSender;

    //加密
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String email_code;

//    @Resource
//    StringRedisTemplate redisTemplate;

    //登录
    @Override
    public UserDetails loadUserByUsername(String userAccountName) throws UsernameNotFoundException {
        UserAccount account = mapper.findByUsername(userAccountName);
        if (userAccountName == null) throw new UsernameNotFoundException("用户名不能为空");
            if (account == null) {
                throw new UsernameNotFoundException("用户名或密码错误");
            }else{
                return User
                        .withUsername(account.getUserAccountUserName())
                        .password(account.getPassword())
                        .roles("user")
                        .build();
            }
        }


    //管理员注册
    @Override
    public String registerAccount(UserAccount account, String code,String identity) {
        System.out.println(account + "    " + identity);
        if ((mapper.findByUsername(account.getUserAccountUserName()) != null))
            return "用户名已存在";
        if (code == null)
            return "请先发送获取验证码";
        if (code.equals(email_code)) {
            //密码加密
            String password = encoder.encode(account.getPassword());
            account.setPassword(password);
            com.backend.blogfrontbackend.entity.user.User userInfo = new com.backend.blogfrontbackend.entity.user.User();
            userInfo.setUserAccountUserName(account.getUserAccountUserName());
            userInfo.setIdentity(identity);

            if (mapper.registerAccount(account) > 0) {
                if (mapper.insertInfo(userInfo) > 0){
                    email_code = "";
                    if (identity.equals("教师")){
                        mapper.insertTeacherInfo(userInfo);
                        mapper.insertAdminInfo(account.getUserAccountUserName(),account.getEmail(),account.getPassword());
                    }
                }
                return null;
            } else
                return "内部错误,请联系管理员";
        } else {
            return "验证码错误，请重新输入";
        }
    }


    //重置密码
    @Override
    public boolean resetPassword(String password, String email) {
        //加密
        password = encoder.encode(password);
        return mapper.resetPasswordByEmail(password, email) > 0 ? true : false;
    }

    @Override
    public boolean updateUserAccount(UserAccount userAccount) {
        return mapper.updateUserAccount(userAccount);
    }

    //重置密码 邮件验证码验证
    @Override
    public String validateOnly(String email, String code, String sessionId) {
        if ((mapper.findByUsername(email) == null))
            return "邮箱不存在";
        if (code == null)
            return "请先发送获取验证码";
        if (code.equals(email_code)) {
            email_code = "";
            return null;
        } else
            return "验证码错误，请重新输入";
    }


    /**
     * 1. 生成验证码
     * 2. 将验证码放入redis 时间3分钟 验证码存入低于两分钟可重新发送
     * 3. 发送验证码到指定邮箱
     * 4. 若发送失败，把redis里的验证码删除
     * 5. 若成功，用户注册时从redis取出键值对，然后验证验证码是否一直
     */
    @Override
    public String sendValidateEmail(String email, String sessionId, String hasAccount) {
        UserAccount account = mapper.findByUsername(email);
        System.out.println(account);

        if (account == null && hasAccount.equals("reset")){
            System.out.println("没有此邮件的用户");
            return "没有此邮件的用户";}
        if (account != null && hasAccount.equals("register")){
            return "此邮件的已被注册";}
        Random random = new Random();
        //生成6位数的验证码
        email_code = (random.nextInt(899999) + 100000) + "";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        //发给谁
        message.setTo(email);
        message.setSubject("您的验证邮件");
        message.setText("😎行太不面对 | 验证码😎 ：" + email_code);
        System.out.println(message);
        //发送失败 捕获异常
        try {
            mailSender.send(message);
            return null;
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送失败，请检查邮件地址是否有效";
        }
    }
}

