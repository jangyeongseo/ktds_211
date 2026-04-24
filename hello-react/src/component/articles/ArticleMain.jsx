/** @format */
// articles.json 파일 불러오기
import React, { useState } from "react";
import articlesData from "./articles.json";
import ArticleHeader from "./ArticleHeader";
import ArticleList from "./ArticleList";
import ArticleWriter from "./ArticleWriter";
import "./ArticleMain.css";

const ArticleMain = () => {
  console.log(articlesData);
  const [articles, setArticles] = useState(articlesData.articles); // articles.json에서 게시글 데이터 초기화
  const [isWrite, setIsWrite] = useState(false); // 화면 변경시 작동

  // 아이디 생성 위한 함수
  const getTodayString = () => {
    const today = new Date(); // 현재 날짜

    const yyyy = today.getFullYear(); // 년도
    const mm = String(today.getMonth() + 1).padStart(2, "0"); // 월 (0부터 시작하므로 +1)
    const dd = String(today.getDate()).padStart(2, "0"); // 일

    return `${yyyy}${mm}${dd}`; // 년월일로 고유한 아이디 생성
  };

  // 아이디 생성 함수
  const generateId = () => {
    const today = getTodayString();

    // 오늘 날짜로 시작하는 게시글들만 필터링
    const todayArticles = articles.filter((article) =>
      article.id?.startsWith(`BO-${today}`),
    );

    const nextIdNumber = todayArticles.length + 1; // 오늘 날짜로 시작하는 게시글 수 + 1
    const padded = String(nextIdNumber).padStart(6, "0"); // 숫자를 6자리로 패딩

    return `BO-${today}-${padded}`; // 최종 아이디 형식: BO-YYYYMMDD-000001
  };

  // 날짜
  const formatDate = () => {
    const d = new Date();
    const yyyy = d.getFullYear();
    const mm = String(d.getMonth() + 1).padStart(2, "0");
    const dd = String(d.getDate()).padStart(2, "0");

    return `${yyyy}-${mm}-${dd}`;
  };

  // form 제출 이벤트 핸들러 함수
  const onSaveSubmitHandler = (event) => {
    event.preventDefault(); // 새로고침 막기
    console.log("폼 제출됨");
    const formData = new FormData(event.target);

    const newArticle = {
      id: generateId(),
      subject: formData.get("title"),
      content: formData.get("content"),
      membersVO: {
        email: formData.get("email"),
        name: formData.get("name"),
      },
      viewCnt: Math.floor(Math.random() * 10000),
      crtDt: formatDate(),
    };
    console.log("새 게시글 데이터:", newArticle);
    setArticles((prevState) => [...prevState, newArticle]);

    // 폼 초기화
    event.target.reset();
  };

  // 취소 버튼 클릭 이벤트 핸들러 함수
  const onButtonClickHandler = (event) => {
    console.log("취소 버튼 클릭됨");

    // 폼 초기화
    //e.target.closest("form").reset();
    event.preventDefault();
    event.currentTarget.form.reset();
  };

  return (
    <div className="boardContainer">
      <ArticleHeader />
      <table>
        <thead>
          <tr>
            <th>아이디</th>
            <th>제목</th>
            <th>내용</th>
            <th>이름</th>
            <th>조회수</th>
            <th>쓴 날짜</th>
          </tr>
        </thead>
        <tbody>
          <ArticleList articles={articles} />
        </tbody>
      </table>
      <div className="write-form">
        <div>
          {isWrite ? (
            <ArticleWriter
              className="writer"
              setIsWrite={setIsWrite}
              onSaveSubmitHandler={onSaveSubmitHandler}
              onButtonClickHandler={onButtonClickHandler}
            />
          ) : (
            <button className="button" onClick={() => setIsWrite(true)}>
              글쓰기
            </button>
          )}
        </div>
      </div>
    </div>
  );
};
export default ArticleMain;
