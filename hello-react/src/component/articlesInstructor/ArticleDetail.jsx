import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchArticleById } from "../../http/articles/fetchArticles";
import { handleFileDownload } from "../utils/download";

export const ArticleDetail = () => {
  /**
   * useParams()
   * URL에서 파라미터 값을 가져온다.
   * 예: /article/BO-20240601-000001
   * → id = "BO-20240601-000001"
   */
  const { id } = useParams();

  /**
   * 게시글 데이터를 저장할 상태
   * 처음에는 데이터가 없으므로 undefined
   */
  const [article, setArticle] = useState();

  /**
   * useEffect
   * 컴포넌트가 처음 실행되거나,
   * id가 바뀔 때마다 실행된다.
   */
  useEffect(() => {
    /**
     * 비동기 함수 (서버에서 데이터 가져오기)
     */
    const loadArticle = async () => {
      // 서버에 게시글 상세 조회 요청
      const articleResult = await fetchArticleById(id);

      /**
       * 에러 처리
       * 서버에서 error가 오면 alert 출력
       */
      if (articleResult.error) {
        alert(articleResult.error);
      } else {
        /**
         * 정상 데이터일 경우
         * 상태에 저장 → 화면 렌더링됨
         */
        setArticle(articleResult);
      }
    };

    // 함수 실행
    loadArticle();
  }, [id]); // id가 바뀌면 다시 실행됨

  /**
   * 데이터가 아직 없을 때 (로딩 상태)
   */
  if (!article) {
    return <div>불러오는 중...</div>;
  }

  return (
    <div>
      <div>{id} 게시글의 상세 내용입니다.</div>
      <div>{article.id}</div>
      <div>{article.subject}</div>
      <div>{article.content}</div>
      <div>
        {article.membersVO?.name} ({article.membersVO?.email})
      </div>
      <div>{article.viewCnt}</div>
      <div>{article.crtDt}</div>

      <ul>
        {/**
         * files가 있을 때만 반복문 실행
         * (없으면 에러 방지)
         */}
        {article.files?.map((f) => (
          <li key={`${f.fileNum}_${f.fileGroupId}`}>
            {/* 
               방법 1: 기본 다운로드 (a 태그)
              - 클릭하면 서버 URL로 이동
              - 브라우저가 파일 다운로드 또는 열기 처리
            */}
            <a
              href={`http://192.168.0.50:8080/file/${f.fileGroupId}/${f.fileNum}`}
            >
              {f.displayName} ({f.fileLength} bytes)
            </a>

            <br />

            {/*
               방법 2: 직접 다운로드 처리 (JS 함수)
              - handleFileDownload 함수 실행
              - fetch → blob → 다운로드 처리
              - 파일 이름 제어 가능
            */}
            <a
              href="#"
              onClick={(e) => {
                e.preventDefault(); // 페이지 이동 막기
                handleFileDownload(
                  `http://192.168.0.50:8080/file/${f.fileGroupId}/${f.fileNum}`,
                );
              }}
            >
              다운로드: {f.displayName}
            </a>
          </li>
        ))}
      </ul>
    </div>
  );
};
