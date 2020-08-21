package cn.nextop.durian;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiStartup implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApiStartup.class);
		app.setWebApplicationType(WebApplicationType.NONE); app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("11111111111111");
	}
}
