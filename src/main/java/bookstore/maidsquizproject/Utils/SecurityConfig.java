package bookstore.maidsquizproject.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import bookstore.maidsquizproject.Services.LibrarianServices;
import org.springframework.beans.factory.annotation.Autowired;
import bookstore.maidsquizproject.Utils.AuthenticationFilters.JwtFilter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;



@Configuration
@EnableWebSecurity
public class SecurityConfig
{

	private final LibrarianServices LibrarianServices;

	private final JwtFilter JwtFilter;

	@Autowired
	public SecurityConfig(LibrarianServices librarianServices, JwtFilter jwtFilter)
	{
		LibrarianServices = librarianServices;
		JwtFilter = jwtFilter;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests
		(
			(auth) ->
				auth
				.requestMatchers("/login", "/register").permitAll()
				.anyRequest().authenticated()
		)
		.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(LibrarianServices);
		return provider;
	}

}
