package com.JMR.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.JMR.domain.PerfilTipo;
import com.JMR.service.UsuarioService;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
	private static final String AFILIADO = PerfilTipo.AFILIADO.getDesc();

    // private static final String START = PerfilTipo.START.getDesc();

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> authorize

                        // acessos p�blicos liberados para todas as pessoas verem
                        // __________________________________________________________________
                        .requestMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
                        .requestMatchers("/vendas/**").permitAll()
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/fragments/links-header/visitante.html").permitAll()

                        .requestMatchers("/imagem/{idprod}", "/imagemCurso/{idprod}", "/imagemLivro/{idprod}",
                                "/imagemFisico/{idprod}", "/imagemIndicado/{idprod}", "/imagemParceiro/{idprod}")
                        .permitAll()

                        .requestMatchers("/ShowScrollTopUser/ajax", "/CursoScrollTopUser/ajax", "/LivroScrollTopUser/ajax",
                                "/FisicoScrollTopUser/ajax", "/IndicadoScrollTopUser/ajax", "/ParceiroScrollTopUser/ajax")
                        .permitAll()

                        .requestMatchers("/Produtos", "/Cursos", "/Livros", "/Fisicos", "/Indicados", "/Parceiros").permitAll()

                        .requestMatchers("/pesquisarProdutos", "/pesquisarCursos", "/pesquisarLivros", "/pesquisarFisicos",
                                "/pesquisarIndicados", "/pesquisarParceiros")
                        .permitAll()

                        .requestMatchers("/buscarProdutos", "/buscarCursos", "/buscarFisicos", "/buscarIndicados",
                                "buscarLivros", "buscarParceiros")
                        .permitAll()

                        // acessos privados admin
                        .requestMatchers("/u/editar/senha", "/u/confirmar/senha").hasAnyAuthority(AFILIADO)
                        .requestMatchers("/u/**").hasAuthority(ADMIN)

                        // acessos privados afiliados
                        // __________________________________________________________________
                        .requestMatchers("/afiliados/dados", "/afiliados/salvar", "/afiliados/editar", "/salvarCategoria")
                        .hasAnyAuthority(AFILIADO, ADMIN)

                        .requestMatchers("/afiliados/**").hasAuthority(AFILIADO)

                        // acessos privados especialidades
                        // __________________________________________________________________
                        .requestMatchers("/especialidades/datatables/server/afiliado/*").hasAnyAuthority(AFILIADO, ADMIN)
                        .requestMatchers("/especialidades/titulo").hasAnyAuthority(AFILIADO, ADMIN)
                        .requestMatchers("/especialidades/**").hasAuthority(ADMIN)

                        // acessos premitido
                        // __________________________________________________________________
                        .anyRequest().authenticated()

        ).formLogin().loginPage("/login").defaultSuccessUrl("/", true).failureUrl("/login-error").permitAll().and()
                .logout().logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/acesso-negado");

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
                                                          UsuarioService userDetailsService) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder).and().build();
    }

}
