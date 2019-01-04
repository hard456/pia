package cz.jpalcut.pia.config;

import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        //definování přístupu v aplikaci
        http.authorizeRequests()
                //anonymní uživatel
                .antMatchers("/", "/login", "/logoutSuccessful").anonymous()
                //všichni uživatelé
                .antMatchers("/user", "/user/edit", "/logout", "/request/delete/{id}").authenticated()
                //admin
                .antMatchers("/user/list","/user/new","/user/edit/{id}", "/user/{id}",
                        "/user/new/add", "/request/confirm/{id}").hasAuthority("ADMIN")
                //user
                .antMatchers("/account", "/transaction/**", "/template/**",
                        "/account/changeValueLimitBelow/{id}", "/account/changeInternationalPayment/{id}").hasAuthority("USER");

        //přihlašovací formulář
        http.authorizeRequests().and().formLogin()//
                //přihlášení
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/user")
                .failureUrl("/login?error=true")
                .usernameParameter("login_id")
                .passwordParameter("pin")
                //odhlášení
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");

    }

}
