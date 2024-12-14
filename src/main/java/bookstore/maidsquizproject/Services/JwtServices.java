package bookstore.maidsquizproject.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import bookstore.maidsquizproject.Utils.JwtConfig;

import java.util.Date;
import java.util.function.Function;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import bookstore.maidsquizproject.Models.Librarian;

@Service
@SuppressWarnings("FieldCanBeLocal")
public class JwtServices
{

	private final JwtConfig jwtConfig;


	@Autowired
	public JwtServices(JwtConfig jwtConfig)
	{
		this.jwtConfig = jwtConfig;
	}

	public String GenerateToken(Librarian Librarian)
	{
		return
			Jwts.builder()
			.setSubject(Librarian.UserName)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + (jwtConfig.ExpirationInterval() * 60 * 60 * 1000)))
			.signWith(getSignInKey())
			.compact();
	}

	public String ExtractUsername(String token)
	{
		return ExtractClaim(token, Claims::getSubject);
	}

	public boolean ValidateToken(String token)
	{
		try
		{
			Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}

	private <T> T ExtractClaim(String token, Function<Claims, T> claimsResolver)
	{
		return
			claimsResolver
			.apply
			(
				Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
			);

	}

	private Boolean isTokenExpired(String token)
	{
		return ExtractClaim(token, Claims::getExpiration).before(new Date());
	}

	private SecretKey getSignInKey()
	{
		byte[] bytes = jwtConfig.SecretKey().getBytes(StandardCharsets.UTF_8);
		return new SecretKeySpec(bytes, "HmacSHA256");
	}

}
