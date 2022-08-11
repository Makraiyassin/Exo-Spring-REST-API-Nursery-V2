package be.bstorm.akimts.rest.bxl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.security.cert.Extension;
import java.util.List;
import java.util.Scanner;

@Configuration
public class BeanConfig {

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        return new InMemoryUserDetailsManager(
                List.of(
                        User.builder()
                                .username("personnel")
                                .password(encoder.encode("1234"))
                                .roles("PERSONNEL")
                                .build(),
                        User.builder()
                                .username("admin")
                                .password(encoder.encode("1234"))
                                .roles("ADMIN")
                                .build()
                )
        );
    }
}
