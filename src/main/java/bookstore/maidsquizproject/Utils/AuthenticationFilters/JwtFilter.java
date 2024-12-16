package bookstore.maidsquizproject.Utils.AuthenticationFilters;

import bookstore.maidsquizproject.Services.JwtServices;
import bookstore.maidsquizproject.Services.LibrarianServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter
{

	@SuppressWarnings("FieldCanBeLocal")
	private final JwtServices jwtServices;

	private final ApplicationContext context;

	@Autowired
	public JwtFilter(JwtServices jwtServices, ApplicationContext context)
	{
		this.jwtServices = jwtServices;
		this.context = context;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		String authHeader = request.getHeader("Authorization");
		if ((authHeader == null) || !authHeader.startsWith("Bearer "))
		{
			filterChain.doFilter(request, response);
			return;
		}

		String Token = authHeader.substring(7);
		String UserName;
		try
		{
			UserName = jwtServices.ExtractUsername(Token);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return;
		}

		if (UserName != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = context.getBean(LibrarianServices.class).loadUserByUsername(UserName);
			if (jwtServices.ValidateToken(Token))
			{
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);

	}
}
