import ArticleHeader from "./ArticleHeader.jsx";
import ArticleList from "./ArticleList.jsx";
import ArticleWriter2 from "./ArticleWriter2.jsx";
import "./ArticleMain.css";
import ArticlePage from "./ArticlePage.jsx";
import { useSelector } from "react-redux";

// 게시글 메인 컴포넌트
const ArticleMain2 = () => {
  const { count } = useSelector((state) => state.article);

  return (
    <div className="wrapper">
      {/* 게시글 개수 */}
      <div>{count}개의 게시글</div>
      <ArticlePage>
        <ArticleHeader />
        <ArticleList />
      </ArticlePage>

      {/* 게시글 작성 컴포넌트 */}
      <ArticleWriter2 />
    </div>
  );
};

export default ArticleMain2;
