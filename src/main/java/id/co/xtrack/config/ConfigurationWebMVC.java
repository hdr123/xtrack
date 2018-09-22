package id.co.xtrack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * The auto-configuration adds the following features on top of Spring’s defaults:
 * 1.Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.
 * 2.Support for serving static resources, including support for WebJars.
 * 3.Automatic registration of Converter, GenericConverter, Formatter beans.
 * 4.Support for HttpMessageConverters.
 * 5.Automatic registration of MessageCodesResolver.
 * 6.Static index.html support.
 * 7.Custom Favicon support.
 * 8.Automatic use of a ConfigurableWebBindingInitializer bean.
 */



@Configuration
@EnableWebMvc
public class ConfigurationWebMVC extends WebMvcConfigurerAdapter{
	
	
	@Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/**").addResourceLocations("/");
	  }
}
