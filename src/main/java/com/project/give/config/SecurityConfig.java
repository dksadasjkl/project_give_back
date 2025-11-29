package com.project.give.config;

import com.project.give.security.exception.AuthEntryPoint;
import com.project.give.security.filter.JwtAuthenticationFilter;
import com.project.give.security.handler.OAuth2SuccessHandler;
import com.project.give.service.OAuth2PrincipalUserService;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Autowired
    private CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors();
        http.csrf().disable();

        http.authorizeRequests()

                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Blue/Green 배포용 HealthCheck 허용
                .antMatchers("/server/**").permitAll()

                // 1) 비회원도 접근 가능한 공개 URL
                .antMatchers(
                        "/auth/**",
                        "/users/**",
                        "/account/**",
                        "/donations/**",
                        "/categories/**",
                        "/donation-project-details/**",
                        "/donation-project-contributions/**",
                        "/donation-project-comments/**",
                        "/fundings/**",
                        "/store/products/**",
                        "/main/**"
                ).permitAll()

                // 2) 회원(로그인 유저)만 접근 가능한 URL
                .antMatchers(
                        "/store/cart/**",
                        "/store/orders/**",
                        "/store/wishlist/**",
                        "/mypage/**",
                        "/donations/like/**",
                        "/donations/apply/**"
                ).authenticated()

                // 3) 관리자 전용 URL
                .antMatchers("/admin/**").hasRole("ADMIN")

                // 나머지는 인증 필요
                .anyRequest().authenticated()

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
