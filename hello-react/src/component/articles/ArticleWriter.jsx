import { useState } from "react";

const ArticleWriter = ({ onSaveSubmitHandler }) => {
  console.log(ArticleWriter);

  const [viewMode, setViewMode] = useState("button");

  // 최소한의 재실행을 막기 위한 작성

  return (
    <>
      {viewMode === "form" && (
        <>
          <h2>게시글 작성 폼 (제목, 이메일, 이름, 내용)</h2>
          <div>
            <form onSubmit={onSaveSubmitHandler}>
              <div>
                <label htmlFor="title">제목:</label>
                <input type="text" id="title" name="title" />
              </div>
              <div>
                <label htmlFor="email">이메일:</label>
                <input type="email" id="email" name="email" />
              </div>
              <div>
                <label htmlFor="name">이름:</label>
                <input type="text" id="name" name="name" />
              </div>
              <div>
                <label htmlFor="content">내용:</label>
                <textarea id="content" name="content"></textarea>
              </div>
              <div className="btnBox">
                <button type="submit">작성</button>
                <button
                  type="button"
                  className="cancel"
                  data-view="button"
                  // onClick={() => setIsWrite(false)}
                  onChange={() => setViewMode("button")}
                >
                  취소
                </button>
              </div>
            </form>
          </div>
        </>
      )}{" "}
      :
      {viewMode ===
        "button"(
          <>
            <button className="button" onClick={() => setViewMode("form")}>
              글쓰기
            </button>
          </>,
        )}
    </>
  );
};

export default ArticleWriter;
