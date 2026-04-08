package com.ktdsuniversity.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ktdsuniversity.edu.config.interceptor.IllegalAccessInterceptor;
import com.ktdsuniversity.edu.config.interceptor.SessionInterceptor;

// applocation.yml에서 작성할 수 없는 설정들을 적용하기 위한 Annotation
// @Component의 자식 Annotation
@Configuration

// spring-boot-starter-validation 동작 활성화 시키기
// @EnablewebMvc가 추가되면 application.yml의 mvc 관련 설정들이 모두 무시된다.
//  1. spring.mvc.view.prefix, spring.mvc.view.suffix
// 	2. src/main/resources/static 경로 사용 불가
@EnableWebMvc
public class HelloSpringConfiguration implements WebMvcConfigurer {
	// WebMvcConfigurer
	// WebMvx 설정을 위한 Configuration
	// @EnableWebMvc Annotation 에서 적용하는 기본 설정들을 변경하기 위함.

	// interceptor 등록 및 대상 URL 지정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 내가 만든 객체를 Spring Bean 한테 넣어서 동시에 interceptor가 작동하도록 만든
		SessionInterceptor sessionInterceptor = new SessionInterceptor();
		
		// 순서대로 실행이 된다.
		registry.addInterceptor(sessionInterceptor) // 이렇게 작성한 순간 Bean 으로 등록이된다.
				.addPathPatterns("/**") // 모든 URL을 대상으로 sessionInterceptor를 수행하라!
				.excludePathPatterns(
						"/regist/check/duplicate/**",// 회원가입 이메일 중복 검사 
						"/regist", // 회원가입 페이지 & 처리
						"/login", // 로그인 페이지 & 처리
						"/js/**", "/css/**", "/imgs/**", "/file/**", // static resources
						"/", "/view/**", "/error" // 게시글 목록 & 게시글 내용
						) // sessionInterceptor 가 적용되지 않을 URL 명시.
				;
		
		IllegalAccessInterceptor illegalAccessInterceptor = new IllegalAccessInterceptor();
		registry.addInterceptor(illegalAccessInterceptor)
				.addPathPatterns("/regist", "/login", "/regist/check/duplicate/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	// configureViewResolvers 설정
	// spring.mvc.view.prefix, spring.mvc.view.suffix 재 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// addResourceHanflers
	// src/main/resources/static 결로의 endpoint 재설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /static/css/ 폴더에 있는 파일들에 대한 Endpoint 설정
		// CSS
		registry.addResourceHandler("/css/**") // /static/css/ 의 엔드포인트 - /** 그 안에 있는 모든것들
				.addResourceLocations("classpath:/static/css/"); // static/css의 물리적인 위치

		// /static/image/ 폴더에 있는 파일들에 대한 Endpoint 설정
		// imgs
		registry.addResourceHandler("/imgs/**").addResourceLocations("classpath:/static/imgs/");

		// /static/js/ 폴더에 있는 파일들에 대한 Endpoin 설정
		// js
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");

	}
}
