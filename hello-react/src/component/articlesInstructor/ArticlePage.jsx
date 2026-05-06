import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { articleThunk } from "../../stores/toolkit/slices/articleSlice";

const ArticlePage = ({ children }) => {
  // 현재 페이지 번호 상태
  const [viewPageNo, setViewPageNo] = useState(0);

  // 안전하게 분리
  const { pagination = {} } = useSelector((store) => store.article);
  const { pageNo = 0, pageCount = 0 } = pagination;

  const toolkitDispatch = useDispatch();

  // 게시글 목록 조회 함수
  const refreshArticleList = () => {
    toolkitDispatch(articleThunk.reducers(viewPageNo));
  };

  // 컴포넌트 처음 실행 + 페이지 변경 시 목록 조회
  useEffect(() => {
    refreshArticleList();
  }, [viewPageNo]);

  return (
    <>
      {/* 게시글 목록 테이블 */}
      <table>{children}</table>

      <div className="btnBox">
        {pageNo > 0 && (
          <button onClick={() => setViewPageNo(pageNo - 1)}>이전</button>
        )}

        {pageNo < pageCount - 1 && (
          <button onClick={() => setViewPageNo(pageNo + 1)}>다음</button>
        )}
      </div>
    </>
  );
};

export default ArticlePage;
