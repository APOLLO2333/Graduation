package com.supersong.graduation.configuration;

import com.supersong.graduation.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SelfAuthenticationProvider provider;

    @Autowired
    private MsgAuthenticationSuccessHandler msgAuthenticationSuccessHandler;

    @Autowired
    private MsgAuthenticationFailureHandler msgAuthenticationFailureHandler;

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private MsgAuthenticationEntryPoint msgAuthenticationEntryPoint;

    @Autowired
    private MsgLogoutSuccessHandler msgLogoutSuccessHandler;

    @Autowired
    private SelfLogoutHandler selfLogoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .and()
                .httpBasic().authenticationEntryPoint(msgAuthenticationEntryPoint)

                .and()
                .authorizeRequests()
                .antMatchers("/*.jpg","/*.jpeg","/*.png","/*.gif").permitAll()
                .antMatchers("//systemSetting/get").permitAll()
                .antMatchers("/login","/user/register","/news/getAll","/news/getNewsByQuery","/news/getNewsImportant",
                        "/news/getNewsNotImportant","/news/getNewsById").permitAll()
                .antMatchers("/newsTurn/getAll","/newsType/getAll","/newsDiscuss/getNewsDiscussByNewsId").permitAll()
                .antMatchers("/user/getUserByToken").permitAll()
                .antMatchers().hasRole("ADMIN")
//                .antMatchers("/news*/**").hasRole("NEWSMANAGER")
//                .antMatchers("/news/addNewsVisitor").authenticated()
                .anyRequest()
                .authenticated()// 其他 url 需要身份认证

                .and()
                .formLogin()  //开启登录
                .successHandler(msgAuthenticationSuccessHandler)// 登录成功
                .failureHandler(msgAuthenticationFailureHandler)//登陆失败
                 // 登录失败
                .permitAll()

                .and()
                .logout().addLogoutHandler(selfLogoutHandler) //清除token
                .logoutSuccessHandler(msgLogoutSuccessHandler) //登出成功
                .permitAll();

        http.addFilterBefore(authenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
    }



}
