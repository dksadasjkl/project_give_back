package com.project.give.config;

import com.project.give.security.exception.AuthEntryPoint;
import com.project.give.security.filter.JwtAuthenticationFilter;
import com.project.give.security.handler.OAuth2SuccessHandler;
import com.project.give.service.OAuth2PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthEntryPoint authEntryPoint;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    // 비밀번호 암호화에 사용할 BCryptPasswordEncoder 빈 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private OAuth2PrincipalUserService oAuth2PrincipalUserService;
    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable(); //  // CSRF 보호 기능 비활성화
        http.authorizeRequests()
                .antMatchers("/auth/**", "/users/**", "/account/**", "/donations/**", "/categories/**",
                                "/donation-project-details/**", "/donation-project-contributions/**", "/donation-project-comments/**", "/fundings/**", "/store/products/**"
                ) // 비회원, 회원, 관리자 방식으로 수정 예정
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(oAuth2PrincipalUserService);
    }
}
