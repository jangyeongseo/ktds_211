/** @format */
// articles.json 파일 불러오기
import { useCallback, useEffect, useRef, useState } from "react";
import ArticleHeader from "./ArticleHeader.jsx";
import ArticleList from "./ArticleList.jsx";
import ArticleWriter2 from "./ArticleWriter2.jsx";
import {
  fetchAddArticle,
  fetchArticleList,
  fetchJsonWebToken,
} from "../../http/articles/fetchArticles.js";
import "./ArticleMain.css";
import Login from "./Login.jsx";
import { isString } from "../utils/type.js";
import { getValidationResult } from "../utils/errorHandler.js";

const ArticleMain2 = () => {
  // state를 변경했다!
  // 컴포넌트가 재실행된다. (props의 전달 여부 관계 없이.)
  // console.log("ArticleMain");

  const [loginErrors, setLoginErrors] = useState(); // 로그인 에러
  const [token, setToken] = useState(() => {
    return localStorage.getItem("token") || "";
  }); // 토큰

  const writeRef = useRef();
  const [loginform, setLoginform] = useState({
    email: "",
    password: "",
  }); // 로그인

  // 페이지네이션
  const [viewPageNo, setViewPageNo] = useState(0);
  const onPaginationButtonClickHandler = (nextPageNo) => {
    setViewPageNo(nextPageNo);
  };

  // 전체 보여주기
  const [
    {
      count,
      result: articles,
      pagination: { pageNo = 0, pageCount = 0 },
    },
    setArticles,
  ] = useState({
    count: 0,
    result: [],
    pagination: {},
  });
  const refreshArticleList = async () => {
    const articleList = await fetchArticleList(viewPageNo);
    /*  articleList의 구조
    {
      result: { count: 0, result: [] },
      pagination: {},
    }
    */
    const {
      result: { count, result },
      pagination,
    } = articleList;

    setArticles({ count, result, pagination });

    if (articleList.error) {
      alert(articleList.error);
    }
  };

  useEffect(() => {
    refreshArticleList();
  }, [viewPageNo]);

  // 로그인 작성 input
  const onLoginChangeHandler = (event) => {
    const { name, value } = event.target;

    setLoginform((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // 로그인 버튼
  const onTokenButtonClickHandler = async () => {
    const jsonWebToken = await fetchJsonWebToken(
      loginform.email,
      loginform.password,
    );

    if (jsonWebToken?.token) {
      setToken(jsonWebToken.token);
      // “브라우저 저장소”라서 페이지 새로고침해도 유지됨
      localStorage.setItem("token", jsonWebToken.token);
      setLoginErrors(null);
    } else {
      if (jsonWebToken.error) {
        if (isString(jsonWebToken.error)) {
          setLoginErrors(jsonWebToken.error); // 문자열
        } else {
          setLoginErrors(getValidationResult(jsonWebToken.error)); // 객체
        }
      }
    }
  };

  // 글 작성
  const onAddArticleClickHandler = useCallback(
    async (subject, attachFile, content) => {
      const addResult = await fetchAddArticle(subject, attachFile, content);

      if (!addResult.error) {
        refreshArticleList();
      } else {
        writeRef.current.setResponseError(addResult.error);
      }
    },
    [],
  );

  return (
    <div className="wrapper">
      {!token ? (
        <Login
          loginErrors={loginErrors}
          loginform={loginform}
          onLoginChangeHandler={onLoginChangeHandler}
          onTokenButtonClickHandler={onTokenButtonClickHandler}
        />
      ) : (
        <>
          <div>{count}개의 게시글</div>
          <table>
            <ArticleHeader />
            <ArticleList articles={articles} />
          </table>

          <div className="btnBox">
            {pageNo > 0 && (
              <button
                onClick={() => onPaginationButtonClickHandler(pageNo - 1)}
              >
                이전
              </button>
            )}

            {pageNo < pageCount - 1 && (
              <button
                onClick={() => onPaginationButtonClickHandler(pageNo + 1)}
              >
                다음
              </button>
            )}
          </div>

          <ArticleWriter2
            errorHandlerRef={errorHandlerRef}
            onAddArticleClick={onAddArticleClickHandler}
          />
        </>
      )}
    </div>
  );
};
export default ArticleMain2;
