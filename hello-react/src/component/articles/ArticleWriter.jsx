const ArticleWriter = ({
  onSaveSubmitHandler,
  onTitleChangeHandler,
  onEmailChangeHandler,
  onNameChangeHandler,
  onContentChangeHandler,
}) => {
  return (
    <div>
      <form onSubmit={onSaveSubmitHandler}>
        <div>
          <label htmlFor="title">제목:</label>
          <input
            type="text"
            id="title"
            name="title"
            onChange={onTitleChangeHandler}
          />
        </div>
        <div>
          <label htmlFor="email">이메일:</label>
          <input
            type="email"
            id="email"
            name="email"
            onChange={onEmailChangeHandler}
          />
        </div>
        <div>
          <label htmlFor="name">이름:</label>
          <input
            type="text"
            id="name"
            name="name"
            onChange={onNameChangeHandler}
          />
        </div>
        <div>
          <label htmlFor="content">내용:</label>
          <textarea
            id="content"
            name="content"
            onChange={onContentChangeHandler}
          ></textarea>
        </div>
        <button type="submit">작성</button>
      </form>
    </div>
  );
};

export default ArticleWriter;
