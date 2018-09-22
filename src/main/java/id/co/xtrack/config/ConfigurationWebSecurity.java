package id.co.xtrack.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import id.co.xtrack.config.security.CustomAuthenticationFailureHandler;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigurationWebSecurity  extends WebSecurityConfigurerAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigurationWebSecurity.class);
	
	
	
	@Bean
	public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
		DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
		defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("PRIV");
		return defaultMethodSecurityExpressionHandler;
	}

	@Bean(name = { "defaultWebSecurityExpressionHandler", "webSecurityExpressionHandler" })
	public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setDefaultRolePrefix("PRIV");
		return defaultWebSecurityExpressionHandler;
	}
	
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher(){
		return new HttpSessionEventPublisher();
	}
	
	
	@Bean
	public CustomAuthenticationFailureHandler customFailAuthentication(){
		return new CustomAuthenticationFailureHandler();
	}
	
	
	
	// Konfigurasi ini akan menyimpan informasi user details ke dalam memori komputer
	// menggunakan teknik populate user. Konfigurasi ini cocok digunakan pada fase development 
	// guna keperluan testing applikasi

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("ConfigurationWebSecurity.configureGlobal: {}", auth);
//		auth.inMemoryAuthentication()
//		.withUser("user").password("password").authorities("ROLE_USER").and()
//		.withUser("admin").password("admin").authorities("ROLE_USER", "ROLE_ADMIN");
	}
		
	
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.antMatchers("/css").access("permitAll")
		.antMatchers("/js/**").access("permitAll")
		.antMatchers("/login*.html").access("permitAll")
		.antMatchers("/logout.html").access("permitAll")
		.antMatchers("/", "/index.html").access("isAuthenticated()")
		.antMatchers("/master/**").access("isAuthenticated()")
		.and().exceptionHandling().accessDeniedPage("/403.html");

		httpSecurity
		.formLogin()
		.loginPage("/login.html")
		.loginProcessingUrl("/login.html")
		.defaultSuccessUrl("/index.html")
		.failureUrl("/login-error.html")
		.failureHandler(customFailAuthentication());
		
		httpSecurity
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout.html", "GET"))
		.logoutSuccessUrl("/login.html")
		.invalidateHttpSession(true)
		.clearAuthentication(true);
		
		httpSecurity
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.maximumSessions(2) //maximum 2 session untuk user yang sama yang dapat mengakses
		.maxSessionsPreventsLogin(true)
		.expiredUrl("/login.html")
		.and()
		.invalidSessionUrl("/login.html")
		.sessionFixation().migrateSession();
		
		
		
	}


	
	 
	
	
	
	
	
	
	
}
