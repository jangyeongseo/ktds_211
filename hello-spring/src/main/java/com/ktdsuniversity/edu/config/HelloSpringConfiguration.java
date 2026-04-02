package com.ktdsuniversity.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		registry.addResourceHandler("/imgs/**")
		.addResourceLocations("classpath:/static/imgs/");

		
		// /static/js/ 폴더에 있는 파일들에 대한 Endpoin 설정
		// js
		registry.addResourceHandler("/js/**")
		.addResourceLocations("classpath:/static/js/");

	}
}
