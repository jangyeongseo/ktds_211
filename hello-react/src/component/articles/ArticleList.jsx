import { Link } from "react-router-dom";
import { useSelector } from "react-redux";

const ArticleList = () => {
  const { list: articles } = useSelector((state) => state.article);

  if (!articles) return null;

  return (
    <>
      {articles.map((article) => (
        <tr key={article.id}>
          <td>{article.id}</td>
          <td>
            <Link to={`/article/${article.id}`}>{article.subject}</Link>
          </td>
          <td>{article.content}</td>
          <td>
            {article.membersVO?.name} ({article.membersVO?.email})
          </td>
          <td>{article.viewCnt}</td>
          <td>{article.crtDt}</td>
        </tr>
      ))}
    </>
  );
};

export default ArticleList;
