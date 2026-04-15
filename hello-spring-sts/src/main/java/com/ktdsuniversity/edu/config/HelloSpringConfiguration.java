package com.ktdsuniversity.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.security.authentication.handlers.LoginFailureHandler;
import com.ktdsuniversity.edu.security.authentication.handlers.LoginSuccessHandler;
import com.ktdsuniversity.edu.security.authentication.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.authentication.service.SecurityUserDetailsService;
import com.ktdsuniversity.edu.security.providers.UsernameAndPasswordAuthenticationProvider;

// application.yml에서 작성할 수 없는 설정들을 적용하기 위한 Annotation
// @Component 의 자식 Annotation
@Configuration
// spring-boot-starter-validation 동작 활성화 시키기
// @EnableWebMvc가 추가되면 application.yml의 mvc 관련 설정들이 모두 무시된다.
//   1. spring.mvc.view.prefix, spring.mvc.view.suffix
//   2. src/main/resources/static 경로 사용 불가능.
@EnableWebMvc
// 생략 가능
// Spring Security 라이브러리를 활성화 시킨다.
// Spring Security 의 필터목록을 확인하기 위해서 작성한다.
@EnableWebSecurity(debug = true)
public class HelloSpringConfiguration implements 
		// WebMvc 설정을 위한 Configuration
		// @EnableWebMvc Annotation 에서 적용하는 기본 설정들을 변경하기 위함.
		WebMvcConfigurer {
	
	// SecurityUserDetailsServicedml의 객체 가져오는 방법 
	@Autowired
	private MembersDao membersDao;
	
	// SecurityPasswordEncoder의 Bean을 생성한다.
	/**
	 * @Bean 
	 *  - 메소드가 실행되어서 반환되는 객체를 Bean Container에 적재한다
	 *  - Bean이라는 메소드는 public을 붙일 수 없다
	 *  - Spring이  실행시킨다 - Bean이 들어가 있는 것들은.
	 * @return
	 */
	@Bean
	PasswordEncoder createPasswordEncoder() {
		return new SecurityPasswordEncoder();
	}
	
	// SecurityUserDetailsService의 Bean을 생성한다.
	/**
	 * SecurityUserDetailsService는 membersDao를 사용하고 있어서
	 * 필요한 멤버변수를 전부다 줘야한다.
	 * 
	 * @Bean으로 생성하는 객체(Bean)들은 필요한 의존 객체를 생성자로 주입래 주어야 한다.
	 * @return
	 */
	@Bean
	UserDetailsService createUserDetailsService() {
		return new SecurityUserDetailsService(this.membersDao);
	}
	
	// UsernameAndPasswordAuthenticationProvider의 Bean을 생성한다.
	// Component를 줘서 가져오는 것도 가능하다 
	// - Bean 으로 해도 괜찮고 Component 을 해도 괜찮다.
	// - 2가지의 방법이 있어 알려주기 위해 Bean을 사용해 본것
	@Bean
	AuthenticationProvider createAuthenticationProvider() {
		UserDetailsService  userDetailsService = this.createUserDetailsService();
		PasswordEncoder passwordEncoder  = this.createPasswordEncoder();
		
		return new UsernameAndPasswordAuthenticationProvider(userDetailsService, passwordEncoder);
	}
	
	@Bean
	AuthenticationSuccessHandler createLoginSuccessHandler() {
		return new LoginSuccessHandler(this.membersDao);
	}
	
	@Bean
	AuthenticationFailureHandler createLoginFailureHandler() {
		return new LoginFailureHandler(this.membersDao);
	}
	
	// TODO Spring Login Fliter(BasicAuthenticationFilter) 등록
	// Spring Security의 기본 로그인 ㅈ널차를 수정하는 작업
	@Bean
	SecurityFilterChain configureFilterChain(HttpSecurity httpSecurity) {
		// UsernamePasswordAuthenticationFilter 수정
		httpSecurity.formLogin(formLogin -> 
				// 로그인 URL 수정
				formLogin.loginPage("/login")
				// Login 인증 처리 URL 지정(UsernameAndPasswordAuthenticationProvider 가 실행될 Endpoint)
				.loginProcessingUrl("/login-provider")
				// 로그인에 필요한 아이디 파라미터 이름을 "username"에서 email로 변경한다.
				.usernameParameter("email")
				// 로그인 성공하면
				// this.membersDao.updateSuccessLogin(loginVO); 실행해야 한다.
				.successHandler(this.createLoginSuccessHandler())
				// 로그인 실패하면
				// this.membersDao.updateIncreaseLoginFailCount(loginVO.getEmail());
				// this.membersDao.updateBlock(loginVO.getEmail());
				.failureHandler(this.createLoginFailureHandler())
				);
		
		return httpSecurity.build();
	}
	
	// configureViewResolvers 설정
	//  spring.mvc.view.prefix, spring.mvc.view.suffix 재설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	// addResourceHandlers
	//  src/main/resources/static 경로의 endpoint 재설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /static/css/ 폴더에 있는 파일들에 대한 Endpoint 설정.
		registry.addResourceHandler("/css/**") // /static/css/ 의 엔드포인트
				.addResourceLocations("classpath:/static/css/"); // /static/css/ 의 물리적인 위치
		
		// /static/image/ 폴더에 있는 파일들에 대한 Endpoint 설정.
		registry.addResourceHandler("/image/**") // /static/image/ 의 엔드포인트
				.addResourceLocations("classpath:/static/image/"); // /static/image/ 의 물리적인 위치
		
		// /static/js/ 폴더에 있는 파일들에 대한 Endpoint 설정.
		registry.addResourceHandler("/js/**") // /static/js/ 의 엔드포인트
				.addResourceLocations("classpath:/static/js/"); // /static/js/ 의 물리적인 위치
	}
}
