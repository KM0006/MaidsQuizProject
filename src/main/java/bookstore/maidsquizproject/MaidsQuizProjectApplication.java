package bookstore.maidsquizproject;

import bookstore.maidsquizproject.Utils.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MaidsQuizProjectApplication
{

	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(MaidsQuizProjectApplication.class, args);
		DataSourceConfig config = context.getBean(DataSourceConfig.class);
	}

}
