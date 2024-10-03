package pulse.com.br.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/users/**").permitAll();
            auth.anyRequest().authenticated();
        }); httpSecurity.cors(cors -> {
        });

        httpSecurity.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
