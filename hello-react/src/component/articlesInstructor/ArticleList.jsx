/** @format */

import { useSelector } from "react-redux";

const ArticleList = () => {
  const { list: articles } = useSelector((state) => state.article);

  return (
    <tbody>
      {articles.map((article) => (
        <tr key={article.id}>
          <td>{article.id}</td>
          <td>{article.subject}</td>
          <td>
            {article.membersVO.name}({article.membersVO.email})
          </td>
          <td>{article.viewCnt}</td>
          <td>{article.crtDt}</td>
        </tr>
      ))}
    </tbody>
  );
};

export default ArticleList;
