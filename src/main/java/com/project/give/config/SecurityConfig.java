package com.project.give.config;

import com.project.give.security.exception.AuthEntryPoint;
import com.project.give.security.filter.JwtAuthenticationFilter;
import com.project.give.security.handler.OAuth2SuccessHandler;
import com.project.give.service.OAuth2PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

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

        http.csrf().disable();
        http.cors().disable();  // ★ 기본 CORS 로직 제거 → CorsFilter만 적용

        // ★ CORS 필터 적용
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()

                // ★ Preflight OPTIONS 반드시 허용
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .antMatchers("/server/**").permitAll()

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

                .antMatchers(
                        "/store/cart/**",
                        "/store/orders/**",
                        "/store/wishlist/**",
                        "/mypage/**",
                        "/donations/like/**",
                        "/donations/apply/**"
                ).authenticated()

                .antMatchers("/admin/**").hasRole("ADMIN")

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
