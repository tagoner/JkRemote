package com.core;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adaptor.inheritance.UserCheckAdaptee;
import com.util.JwtProvider;
import com.var.Role;

@EnableWebSecurity
public class SecurityConfig {
	
    @Resource(name = "userCheckAdaptor")
    private UserCheckAdaptee userCheckAdaptor;
    
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/test", "/favicon.ico");
    }
    
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    //아직 구현못함
	    http.csrf().disable();
	    //인가
        http.authorizeRequests()
            //특정
            .antMatchers("/resources/**").permitAll()
	        .antMatchers("/js/**").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/view/**").permitAll()
            //.antMatchers("/main").hasAnyAuthority(Role.USER.getCode())
            //.antMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.getCode())
            //나머지
            .anyRequest().authenticated();
        /*
        //로그인
        http.formLogin()
            .loginPage("/signIn")
            .loginProcessingUrl("/login")
            .failureUrl("/signOut")
            .usernameParameter("id")
            .passwordParameter("pw")
            .defaultSuccessUrl("/main", true);
        //로그아웃
        http.logout()
            .logoutUrl("/signOut")
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/login");
        */
        //로그인 대체
        http.httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(buildJwtFilter(), UsernamePasswordAuthenticationFilter.class);
        
	    return http.build();
	}
	
	private OncePerRequestFilter buildJwtFilter() {
	    return new SecurityJwtFilter(userCheckAdaptor, new JwtProvider());
	}
}