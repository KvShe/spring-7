package ru.kvshe.homework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.kvshe.homework.model.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // модерирование авторизаций на уровне метода
public class SecurityConfig {
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // меняет префикс "ROLE_" на ""
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // защита от csrf-атак
                .authorizeHttpRequests(request -> request
//                                .requestMatchers("/user/**").authenticated()

//                                .requestMatchers("/users/**").hasAuthority(Role.USER.getName())
//                                .requestMatchers("/admins/**").hasAuthority(Role.ADMIN.getName())
//                                .requestMatchers("/other/**").hasAnyAuthority(Role.ADMIN.getName(), Role.USER.getName())
//                                .requestMatchers("/").authenticated()
//                                .anyRequest().authenticated() // разрешает заходить только авторизованным
                                .anyRequest().permitAll() // разрешает заходить всем
                )
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) // доступ для формы авторизации всем желающим
                .formLogin(Customizer.withDefaults())
                .build();
    }

    /**
     * Отвечает за то, чтобы приложение воспринимало пользователей
     *
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    /**
     * Кодирует пароль
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
