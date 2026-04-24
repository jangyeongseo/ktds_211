/** @format */
// articles.json 파일 불러오기
import React from "react";
import articlesData from "./articles.json";
import ArticleHeader from "./ArticleHeader";
import ArticleList from "./ArticleList";
import ArticleWriter from "./ArticleWriter";
import "./ArticleMain.css";

const ArticleMain = () => {
  console.log(articlesData);

  const onSaveSubmitHandler = (event) => {
    event.preventDefault(); // 새로고침 막기
    console.log("폼 제출됨");
  };

  const onTitleChangeHandler = (event) => {
    console.log(event.target.value);
  };
  const onEmailChangeHandler = (event) => {
    console.log(event.target.value);
  };
  const onNameChangeHandler = (event) => {
    console.log(event.target.value);
  };
  const onContentChangeHandler = (event) => {
    console.log(event.target.value);
  };

  return (
    <div className="container">
      <ArticleHeader />
      <table>
        <thead>
          <tr>
            <th>제목</th>
            <th>이메일</th>
            <th>이름</th>
            <th>내용</th>
          </tr>
        </thead>
        <tbody>
          <ArticleList articlesData={articlesData} />
        </tbody>
      </table>
      <div className="write-form">
        <h2>게시글 작성 폼 (제목, 이메일, 이름, 내용)</h2>
        <ArticleWriter
          onSaveSubmitHandler={onSaveSubmitHandler}
          onTitleChangeHandler={onTitleChangeHandler}
          onEmailChangeHandler={onEmailChangeHandler}
          onNameChangeHandler={onNameChangeHandler}
          onContentChangeHandler={onContentChangeHandler}
        />
      </div>
    </div>
  );
};
export default ArticleMain;
