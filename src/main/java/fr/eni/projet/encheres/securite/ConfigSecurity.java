package fr.eni.projet.encheres.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

	@Autowired
	private DataSource dataSource;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE pseudo = ?")
				.authoritiesByUsernameQuery("SELECT ?, 'admin' ");
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
//			auth
			// Permettre aux visiteurs d'acc�der � la liste des ench�res
//					.requestMatchers(HttpMethod.GET, "/encheres").permitAll()
//					// Acc�s � la vue principale et aux actions de base
//					.requestMatchers(HttpMethod.GET, "/details").permitAll()
//					.requestMatchers(HttpMethod.GET, "/recherche").permitAll()
//					.requestMatchers(HttpMethod.GET, "/inscription").permitAll()
//					.requestMatchers(HttpMethod.POST, "/register").permitAll()
//					//si t'es connect�
//					.requestMatchers(HttpMethod.GET, "/creerarticle").authenticated()
//					.requestMatchers(HttpMethod.GET, "/article").permitAll().requestMatchers(HttpMethod.GET, "/error")
//					.permitAll().requestMatchers(HttpMethod.POST, "/creationarticle").permitAll()
//					.requestMatchers(HttpMethod.POST, "/encherir").permitAll().requestMatchers("/").permitAll()

			// Permettre � tous d'afficher correctement ressources et � l'index
			auth.requestMatchers("/*").permitAll().requestMatchers("/css/*").permitAll().requestMatchers("/images/*")
					.permitAll().requestMatchers("/javascript/*").permitAll()
					// Il faut �tre connect� pour toutes autres URLs
					.anyRequest().authenticated();
		});
//		// formulaire de connexion par d�faut
//		http.formLogin(Customizer.withDefaults());

		// Customiser le formulaire
		http.formLogin(form -> {
			form.loginPage("/login").permitAll();
			form.defaultSuccessUrl("/enchere").permitAll();
			form.failureUrl("/error");
		});

		// /logout --> vider la session et le contexte de s�curit�
		http.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/enchere").permitAll());

		return http.build();

	}

}
