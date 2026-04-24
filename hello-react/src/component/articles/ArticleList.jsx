const ArticleList = ({ articlesData }) => {
  return (
    <>
      {articlesData.articles.map((article) => (
        <tr key={article.id}>
          <td>{article.subject}</td>
          <td>{article.membersVO.email}</td>
          <td>{article.membersVO.name}</td>
          <td>{article.content}</td>
        </tr>
      ))}
    </>
  );
};

export default ArticleList;
