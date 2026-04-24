const ArticleWriter = ({ onSaveSubmitHandler, onButtonClickHandler }) => {
  return (
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
          <button type="button" onClick={onButtonClickHandler}>
            취소
          </button>
        </div>
      </form>
    </div>
  );
};

export default ArticleWriter;
