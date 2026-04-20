package com.ktdsuniversity.edu.security.authenticate.web;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.common.utils.ServletUtils;
import com.ktdsuniversity.edu.exceptions.HelloSpringApiException;
import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.providers.JsonWebTokenAuthenticationProvider;
import com.ktdsuniversity.edu.security.user.SecurityUser;

import jakarta.validation.Valid;

@Controller
public class JwtLoginController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MembersDao membersDao;
	// service는 @Transactional을 위한건데 우리는 안할거니까 Dao를 가져온다.
	
	@Autowired
	private JsonWebTokenAuthenticationProvider jsonWebTokenAuthenticationProvider;

	@PostMapping("/api/authorization")
	@ResponseBody
	public Map<String, String> doJwtLogin(@Valid @RequestBody LoginVO loginVO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new HelloSpringApiException("로그인 실해", HttpStatus.BAD_REQUEST.value(), bindingResult.getFieldErrors());
		}

		// 이메일을 통해서 회언의 정보를 조회
		UserDetails userDetails = null;
		try {
			userDetails = this.userDetailsService.loadUserByUsername(loginVO.getEmail());
			
		} catch (UsernameNotFoundException unfe) {
			// 사용자 한테는 Json 에러를 전달해준다.
			// 회원 조회
			throw new HelloSpringApiException("로그인 실패", HttpStatus.BAD_REQUEST.value(), "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// lock 걸린거 확인
		if (!userDetails.isAccountNonLocked()) {
			throw new HelloSpringApiException("로그인 실패", HttpStatus.BAD_REQUEST.value(), "아이디 또는 비밀번호가 일치하지 않습니다.");
		}

		// 비밀번호 일치검사 수행
		String password = loginVO.getPassword();
		SecurityPasswordEncoder securityPasswordEncoder = (SecurityPasswordEncoder) this.passwordEncoder;
		
		SecurityUser securityUser = (SecurityUser) userDetails;
		MembersVO membersVO = securityUser.getMembersVO();
		
		// 사용자가 전달한 값이 틀렸다면
		if(!securityPasswordEncoder.matches(password, membersVO.getSalt(), membersVO.getPassword())) {
			// 이거하고 예외처리를 해줘야 한다.
			this.membersDao.updateIncreaseLoginFailCount(loginVO.getEmail());
			this.membersDao.updateBlock(loginVO.getEmail());
			
			throw new HelloSpringApiException("로그인 실패", HttpStatus.BAD_REQUEST.value(), "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		loginVO.setIp(ServletUtils.getIp());
		this.membersDao.updateSuccessLogin(loginVO);

		// jwt 생성 후 API 결과 반환
		String jwt = this.jsonWebTokenAuthenticationProvider.makeJsonWebToken(loginVO.getEmail(), Duration.ofHours(9)); 
		// 9시간 사용할 수 있는 토큰을 달라. - 업무시간만큼
		
		return Map.of("token",jwt); // 토큰으로 jwt를 주겠다.
	}

}
