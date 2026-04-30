import { isString } from "../utils/type";

const Login = ({
  loginErrors,
  loginform,
  onLoginChangeHandler,
  onTokenButtonClickHandler,
}) => {
  return (
    <div className="write-form">
      <h2>로그인</h2>

      {/* 전체 에러 */}
      {isString(loginErrors) && <div className="error-text">{loginErrors}</div>}

      <div>
        <label htmlFor="email">이메일</label>
        <input
          id="email"
          type="text"
          name="email"
          value={loginform.email}
          placeholder="이메일 입력"
          onChange={onLoginChangeHandler}
        />
        {loginErrors?.email && (
          <div className="error-text">{loginErrors.email}</div>
        )}

        <label htmlFor="password">비밀번호</label>
        <input
          id="password"
          type="password"
          name="password"
          value={loginform.password}
          onChange={onLoginChangeHandler}
          placeholder="비밀번호 입력"
        />
        {loginErrors?.password && (
          <div className="error-text">{loginErrors.password}</div>
        )}
      </div>

      <div>
        <button
          type="button"
          className="button"
          onClick={onTokenButtonClickHandler}
        >
          로그인
        </button>
      </div>
    </div>
  );
};

export default Login;
