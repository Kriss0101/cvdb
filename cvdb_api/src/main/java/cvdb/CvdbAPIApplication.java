package cvdb;

import cvdb.api.mappers.ResumeMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CvdbAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvdbAPIApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplateBuilder().build();
	}

	@Bean
	public ResumeMapper getResumeMapper() {
		return Mappers.getMapper(ResumeMapper.class);
	}
	
	


}
