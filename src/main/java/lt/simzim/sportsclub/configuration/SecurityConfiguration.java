package lt.simzim.sportsclub.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt.simzim.sportsclub.services.ClientService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	ClientService clientService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();	
		
		auth
			.userDetailsService(this.clientService)
			.passwordEncoder(bc);
		
//		auth
//			.inMemoryAuthentication()
//				.withUser("admin").password(bc.encode("123")).roles("admin", "user")
//				.and()
//				.withUser("user").password(bc.encode("12345")).roles("user");		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/client/").permitAll()
				.antMatchers("/workout/").permitAll()
				.antMatchers("/registration/").permitAll()
				
				
				.antMatchers("/login*").permitAll()
				.antMatchers("/register*").permitAll()
				.anyRequest().authenticated()
		
		.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login-error")
	.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	

}
