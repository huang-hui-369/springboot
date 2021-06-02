package water.huang.jdbctemplate.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcTemplateDemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateDemoApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("hello");
    }

}
