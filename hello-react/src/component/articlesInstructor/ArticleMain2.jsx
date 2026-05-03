import { useCallback, useEffect, useState } from "react";

import ArticleHeader from "./ArticleHeader.jsx";
import ArticleList from "./ArticleList.jsx";
import ArticleWriter2 from "./ArticleWriter2.jsx";

import {
  fetchArticleList,
  fetchJsonWebToken,
} from "../../http/articles/fetchArticles.js";

import "./ArticleMain.css";
import Login from "./Login.jsx";

import { isString } from "../utils/type.js";
import { getValidationResult } from "../utils/errorHandler.js";
import { useDispatch, useSelector } from "react-redux";
import { articleAction } from "../../stores/toolkit/slices/articleSlice.js";

// 게시글 메인 컴포넌트
const ArticleMain2 = () => {
  // 로그인 에러 메시지 상태
  const [loginErrors, setLoginErrors] = useState();

  // 토큰 상태 (초기값은 localStorage에서 가져옴)
  const [token, setToken] = useState(() => {
    return localStorage.getItem("token") || "";
  });

  // 로그인 입력값 상태
  const [loginform, setLoginform] = useState({
    email: "",
    password: "",
  });

  // 현재 페이지 번호 상태
  const [viewPageNo, setViewPageNo] = useState(0);

  // Redux store에서 게시글 데이터 가져오기
  const {
    list: articles,
    count,
    pagination,
  } = useSelector((state) => state.article);

  // pagination 값 구조 분해
  const { pageNo = 0, pageCount = 0 } = pagination;

  // Redux dispatch 함수
  const dispatch = useDispatch();

  // 게시글 목록 조회 함수
  const refreshArticleList = useCallback(async () => {
    const articleList = await fetchArticleList(viewPageNo);

    const {
      result: { count, result },
      pagination,
    } = articleList;

    // Redux store에 데이터 저장
    dispatch(
      articleAction.refresh({
        list: result,
        count,
        pagination,
      }),
    );

    // 에러 발생 시 알림
    if (articleList.error) {
      alert(articleList.error);
    }
  }, [viewPageNo, dispatch]);

  // 컴포넌트 처음 실행 + 페이지 변경 시 목록 조회
  useEffect(() => {
    refreshArticleList();
  }, [refreshArticleList]);

  // 로그인 입력값 변경 처리
  const onLoginChangeHandler = (event) => {
    const { name, value } = event.target;

    setLoginform((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // 로그인 버튼 클릭 시 토큰 요청
  const onTokenButtonClickHandler = async () => {
    const jsonWebToken = await fetchJsonWebToken(
      loginform.email,
      loginform.password,
    );

    // 로그인 성공 시
    if (jsonWebToken?.token) {
      setToken(jsonWebToken.token);
      localStorage.setItem("token", jsonWebToken.token);
      setLoginErrors(null);
    }
    // 로그인 실패 시
    else {
      if (jsonWebToken.error) {
        if (isString(jsonWebToken.error)) {
          setLoginErrors(jsonWebToken.error);
        } else {
          setLoginErrors(getValidationResult(jsonWebToken.error));
        }
      }
    }
  };

  return (
    <div className="wrapper">
      {/* 로그인 안된 상태 */}
      {!token ? (
        <Login
          loginErrors={loginErrors}
          loginform={loginform}
          onLoginChangeHandler={onLoginChangeHandler}
          onTokenButtonClickHandler={onTokenButtonClickHandler}
        />
      ) : (
        <>
          {/* 게시글 개수 */}
          <div>{count}개의 게시글</div>

          {/* 게시글 목록 테이블 */}
          <table>
            <ArticleHeader />
            <ArticleList articles={articles} />
          </table>

          {/* 페이지 이동 버튼 */}
          <div className="btnBox">
            {pageNo > 0 && (
              <button onClick={() => setViewPageNo(pageNo - 1)}>이전</button>
            )}

            {pageNo < pageCount - 1 && (
              <button onClick={() => setViewPageNo(pageNo + 1)}>다음</button>
            )}
          </div>

          {/* 게시글 작성 컴포넌트 */}
          <ArticleWriter2 token={token} pageNo={pageNo} />
        </>
      )}
    </div>
  );
};

export default ArticleMain2;
