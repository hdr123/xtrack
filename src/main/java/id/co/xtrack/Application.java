package id.co.xtrack;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


/* 
 * @Configuration
 * @ComponentScan(basePackages = { "com.hdr" })
 * @EnableAutoConfiguration
 * The @SpringBootApplication annotation is equivalent to using @Configuration, 
 * @EnableAutoConfiguration and @ComponentScan with their default attributes:
*/

@SpringBootApplication
@PropertySource("file:${xtrack_dir_config}/application.properties")
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
