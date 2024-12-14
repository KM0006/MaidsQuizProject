package bookstore.maidsquizproject.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;


@Configuration
@SuppressWarnings("FieldCanBeLocal")
public class DataSourceConfig
{

	private final Environment environment;

	private final String UrlKey = "spring.datasource.url";

	private final String UserNameKey = "spring.datasource.username";

	private final String PasswordKey = "spring.datasource.password";

	@Autowired
	public DataSourceConfig(Environment environment)
	{
		this.environment = environment;
	}

	public String Url()
	{
		return environment.getProperty(UrlKey);
	}

	public String UserName()
	{
		return environment.getProperty(UserNameKey);
	}

	public String Password()
	{
		return environment.getProperty(PasswordKey);
	}

	@Bean
	public DataSource dataSource()
	{
		HikariConfig hikariConfig = new HikariConfig();

		hikariConfig.setJdbcUrl(Url());
		hikariConfig.setUsername(UserName());
		hikariConfig.setPassword(Password());

		return new HikariDataSource(hikariConfig);
	}

}



