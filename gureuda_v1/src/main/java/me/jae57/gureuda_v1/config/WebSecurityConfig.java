package me.jae57.gureuda_v1.config;

import me.jae57.gureuda_v1.service.serviceImpl.AuthServiceImpl;
import me.jae57.gureuda_v1.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private AuthenticationEntryPoint authEntryPoint;
    //private final AuthServiceImpl authService;
    @Autowired
    private UserServiceImpl userService;

//    public WebSecurityConfig(UserServiceImpl userService){
////        this.authEntryPoint = authEntryPoint;
//        //this.authService = authService;
//        this.userService = userService;
//    }

//    public void setUserService(UserServiceImpl userService){
//        this.userService = userService;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/join").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/api/**").hasAuthority("USER").anyRequest()
                .authenticated().and().csrf().disable().logout();
//                .and()
//                .logout();
//        http.csrf().disable().authorizeRequests()
//                .anyRequest().authenticated()
//                .and().httpBasic()
//                .authenticationEntryPoint(authEntryPoint)
//                .and()
//                .logout();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        //auth.inMemoryAuthentication().withUser("user").password("$2a$04$xakPRZc.e6PPReLn.r6x2.OLelzpCG/pAoPY91bstTv3oVNXH..Hy").roles("USER");
//        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
