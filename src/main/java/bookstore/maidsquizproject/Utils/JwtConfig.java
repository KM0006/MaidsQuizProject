package bookstore.maidsquizproject.Utils;

import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Configuration
@SuppressWarnings("FieldCanBeLocal")
public class JwtConfig
{

	private final Environment environment;

	private final String SecretKeyKey = "jwt.secret";

	private final String ExpirationIntervalKey = "jwt.expiration";

	@Autowired
	public JwtConfig(Environment environment)
	{
		this.environment = environment;
	}

	public String SecretKey()
	{
		return environment.getProperty(SecretKeyKey);
	}

	public Long ExpirationInterval()
	{
		return Long.valueOf(Objects.requireNonNull(environment.getProperty(ExpirationIntervalKey)));
	}

}
