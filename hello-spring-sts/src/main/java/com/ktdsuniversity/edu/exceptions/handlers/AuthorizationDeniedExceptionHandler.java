package com.ktdsuniversity.edu.exceptions.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.ktdsuniversity.edu.common.utils.AuthUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 이 클래스의 역할
 * 
 * Spring Security에서 @PreAuthorize() 같은 권한 체크에서
 * "권한 없음(AccessDenied)" 예외가 발생했을 때
 * 어떻게 응답할지를 정의하는 클래스
 * 
 * 쉽게 말하면
 * "권한 없는 사람이 접근했을 때 어떻게 처리할지" 담당
 */
public class AuthorizationDeniedExceptionHandler implements AccessDeniedHandler {

    /**
     * AccessDeniedException이 발생하면 이 메서드가 실행됨
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        /**
         * 1. API 요청인지 확인
         * 
         * /api/로 시작하면 -> REST API 요청
         * 아니면 -> 일반 웹 페이지 요청
         */
        boolean isApiRequest = request.getServletPath().startsWith("/api/");

        // 2. API 요청일 경우
        if (isApiRequest) {

            // JSON 형태로 응답해야 함 (프론트에서 fetch/axios 로 받기 때문)
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");

            // 응답 작성
            PrintWriter writer = response.getWriter();
            writer.append("{\"error\" : \"인증이 필요하거나 잘못된 권한입니다.\"}");
            writer.flush();

            return; // 여기서 끝
        }

        // 3. 일반 웹 요청일 경우
        else {

            /**
             * 로그인 상태인지 확인
             */
            if (AuthUtils.isAuthenticated()) {

                /**
                 * 로그인은 되어 있지만 권한이 없는 경우
                 * -> 403 페이지로 이동
                 */
                String viewPath = "/WEB-INF/views/errors/403.jsp";

                // 에러 메시지 전달
                request.setAttribute("errorMessage", "잘못된 접근입니다. 권한이 충분하지 않습니다.");

                // JSP로 forward (URL은 유지됨)
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
                requestDispatcher.forward(request, response);

                return;
            }

            /**
             * 로그인도 안 된 상태
             * -> 로그인 페이지로 이동
             */
            String viewPath = "/WEB-INF/views/members/login.jsp";

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
            requestDispatcher.forward(request, response);
        }
    }
}