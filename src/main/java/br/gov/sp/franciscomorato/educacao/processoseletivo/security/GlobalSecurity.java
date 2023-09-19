package br.gov.sp.franciscomorato.educacao.processoseletivo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class GlobalSecurity
{

    /**
     * configura rotas de acesso e permissões de rotas
     * @param http
     * @return http.build()
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests((requests) ->
            requests
                .requestMatchers("/").permitAll()
                .requestMatchers("/assets/**").permitAll()
                    .anyRequest().authenticated()
        );

        return http.build();
    }

}
