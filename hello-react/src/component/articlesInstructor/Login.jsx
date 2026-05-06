import { useEffect, useRef } from "react";
import { isString } from "../utils/type";
import { useDispatch, useSelector } from "react-redux";
import { userAction, userThunk } from "../../stores/toolkit/slices/userSlice";

const Login = () => {
  const emailRef = useRef();
  const passwordRef = useRef();

  const {
    token,
    info,
    error: loginErrors,
  } = useSelector((store) => store.user);
  const toolkitProvider = useDispatch();

  // 앱 시작 시 자동 로그인
  // 자동 로그인 (한 번만)
  useEffect(() => {
    toolkitProvider(userAction.autoLogin());
  }, []);

  // token 생겼을 때만 내 정보 조회
  useEffect(() => {
    if (!token) {
      return;
    }

    toolkitProvider(userThunk.loadMyInfo());
  }, [token]);

  // Thunk 활용하여 작성
  const onLoginButtonClickHandler = () => {
    toolkitProvider(
      userThunk.login(emailRef.current.value, passwordRef.current.value),
    );
  };

  // 로구아웃
  const onLogoutHandler = () => {
    toolkitProvider(userThunk.logout());
  };

  return (
    <div className="write-form">
      {!token ? (
        <>
          <h2>로그인</h2>

          {isString(loginErrors) && (
            <div className="error-text">{loginErrors}</div>
          )}

          <div>
            <label htmlFor="email">이메일</label>
            <input id="email" type="text" ref={emailRef} />

            {loginErrors?.email && (
              <div className="error-text">{loginErrors.email}</div>
            )}

            <label htmlFor="password">비밀번호</label>
            <input id="password" type="password" ref={passwordRef} />

            {loginErrors?.password && (
              <div className="error-text">{loginErrors.password}</div>
            )}
          </div>

          <button onClick={onLoginButtonClickHandler}>로그인</button>
        </>
      ) : (
        <div>
          {info ? (
            <>
              {info.name} ({info.email})
              <button onClick={onLogoutHandler}>로그아웃</button>
            </>
          ) : (
            <div>로딩 중...</div>
          )}
        </div>
      )}
    </div>
  );
};

export default Login;
