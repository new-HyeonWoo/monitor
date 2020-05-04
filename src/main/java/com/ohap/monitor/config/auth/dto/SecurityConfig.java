//package com.ohap.monitor.config.auth.dto;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@RequiredArgsConstructor
//// Spring Security 설정들을 활성화 해줌.
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
//                .csrf().disable().headers().frameOptions().disable()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/h2-console/**").permitAll();
//
//    }
//
//
//}
