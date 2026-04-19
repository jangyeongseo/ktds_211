package com.ktdsuniversity.edu.security.providers;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.user.SecurityUser;

public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider {

    // DB에서 사용자의 정보 조회를 담당하는 서비스
    private UserDetailsService userDetailsService;
    
    /**
     * 사용자가 로그인할 때 입력한 비밀번호와
     * DB에 저장된 비밀번호를 비교하는 객체
     */
    private PasswordEncoder passwordEncoder;
    
    // 생성자 주입
    public UsernameAndPasswordAuthenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 로그인 요청이 들어올 때마다 실행되는 핵심 메서드
     * 
     * 동작 흐름
     * 1. 사용자 입력값(아이디, 비밀번호) 가져오기
     * 2. DB에서 사용자 조회
     * 3. 계정 상태 확인 (잠금 여부 등)
     * 4. 비밀번호 검증
     * 5. 성공 시 인증 토큰 발급
     */
    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        // 1 사용자가 입력한 이메일
        String email = authentication.getName();
        
        // 2️ DB에서 사용자 조회
        // UserDetails 구현체 -> SecurityUser
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

        // 3️ 계정 잠금 여부 확인
        // isAccountNonLocked() -> false -> 계정 잠김 상태
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        
        // 4️ 사용자가 입력한 평문 비밀번호
        String rawPassword = authentication.getCredentials().toString();
        
        // UserDetails -> SecurityUser로 캐스팅
        // 실제 DB 데이터를 MemberVO에서 꺼냄
        MemberVO memberVO = ((SecurityUser) userDetails).getMemberVO();

        // 5️ PasswordEncoder를 구현체로 캐스팅 -> salt 기반 비교를 위해
        SecurityPasswordEncoder passwordComparator = (SecurityPasswordEncoder) this.passwordEncoder;

        // 6️ 비밀번호 비교
        // rawPassword + salt -> 암호화 -> DB 비밀번호와 비교
        boolean isMatch = passwordComparator.matches(
                rawPassword, 
                memberVO.getSalt(),      // DB에 저장된 salt
                userDetails.getPassword() // DB에 저장된 암호화 비밀번호
        );

        // 비밀번호 불일치 -> 로그인 실패
        if (!isMatch) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        
        // 7️ 인증 성공 -> 인증 토큰 생성
        // principal: 로그인 사용자 정보 -> memberVO
        // credentials: 비밀번호
        // authorities: 권한 목록
        return new UsernamePasswordAuthenticationToken(
                memberVO, 
                userDetails.getPassword(), 
                userDetails.getAuthorities()
        );
    }
    
    /**
     * 이 Provider 가 어떤 인증 토큰을 처리할지 결정
     * UsernamePasswordAuthenticationToken만 처리하겠다
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
