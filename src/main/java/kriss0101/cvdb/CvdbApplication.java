package kriss0101.cvdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CvdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvdbApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplateBuilder().build();
	}
}
