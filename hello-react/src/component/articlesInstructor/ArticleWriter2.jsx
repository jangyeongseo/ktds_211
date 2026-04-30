/** @format */

import { useRef, useState, forwardRef, useImperativeHandle } from "react";
import { Alert } from "../ui/Modals";
import { isString } from "../utils/type";
import { getValidationResult } from "../utils/errorHandler";

/**
 *  Input 컴포넌트
 * - label + input 묶은 재사용 컴포넌트
 * - forwardRef를 사용해야 부모에서 ref 연결 가능
 */
const Input = forwardRef(({ id, title, type = "text", ...props }, ref) => {
  console.log("Input");

  return (
    <div className="input-field">
      {/* label은 input과 연결됨 */}
      <label htmlFor={id}>{title}</label>

      {/* ref를 input에 연결 */}
      <input type={type} id={id} ref={ref} {...props} />
    </div>
  );
});

/**
 *  Textarea 컴포넌트
 * - textarea도 동일하게 ref 연결 가능하도록 forwardRef 사용
 */
const Textarea = forwardRef(({ id, title, ...props }, ref) => {
  return (
    <div className="input-field">
      <label htmlFor={id}>{title}</label>

      {/* textarea에도 ref 연결 */}
      <textarea id={id} ref={ref} {...props}></textarea>
    </div>
  );
});

const ArticleWriter2 = ({ errorHandlerRef, onAddArticleClick }) => {
  /**
   *  useRef
   * - 각각 input, textarea DOM을 직접 참조하기 위한 변수
   * - 나중에 .current로 실제 DOM 접근 가능
   */
  const subjectRef = useRef();
  const contentRef = useRef();
  const attachFileRef = useRef();

  const alertRef = useRef(); // dialogRef (다이얼로그)를 제어할 ref(레프)

  /**
   *  viewMode
   * - button: 글쓰기 버튼만 보임
   * - form: 입력 폼 보임
   */
  const [viewMode, setViewMode] = useState("button");

  const [addError, setAddError] = useState();
  useImperativeHandle(errorHandlerRef, () => {
    return {
      setResponseError(fetchError) {
        if (isString(fetchError)) {
          setAddError(fetchError);
        } else {
          setAddError(getValidationResult(fetchError));
        }
      },
    };
  });

  /**
   *  저장 버튼 클릭 시 실행
   * - ref를 통해 입력값을 직접 가져옴
   */
  const onSaveButtonClickHandler = () => {
    // .current → 실제 DOM
    const subject = subjectRef.current.value;
    const attachFile = attachFileRef.current.files; // 파일이라 file라고 작성
    const content = contentRef.current.value;

    console.log("제목:", subject);
    console.log("파일:", attachFile);
    console.log("내용:", content);
    console.log("모달(어러트?)", alertRef);

    // dialog가 있어 가능
    if (!subject) {
      alertRef.current?.showModal("제목을 입력해주세요");
      return;
    }

    if (!attachFile) {
      alertRef.current?.showModal("파일을 등록해주세요");
      return;
    }

    if (!content) {
      alertRef.current?.showModal("내용을 입력해주세요");
      return;
    }

    // 부모 컴포넌트로 전달 가능
    onAddArticleClick({
      subject,
      attachFile,
      content,
    });

    // 입력값 초기화
    subjectRef.current.value = "";
    contentRef.current.value = "";
    attachFileRef.current.value = "";

    // 다시 버튼 화면으로 전환
    setViewMode("button");
  };

  /**
   *  화면 전환 함수
   */
  const onViewChangeButtonClickHandler = (viewName) => {
    setViewMode(viewName);
  };

  return (
    <div className="write-form">
      {viewMode === "button" && (
        <button
          type="button"
          className="button"
          onClick={() => onViewChangeButtonClickHandler("form")}
        >
          글쓰기
        </button>
      )}

      {viewMode === "form" && (
        <>
          <Alert dialogRef={alertRef} />
          {isString(addError) && <div>{addError}</div>}
          {/* ref를 각 input에 연결 */}
          <Input id="subject" title="제목" ref={subjectRef} />
          <input
            type="file"
            id="attachFile"
            title="파일"
            ref={attachFileRef}
            multiple
          />
          <Textarea id="content" title="내용" ref={contentRef} />
          <div className="btnBox">
            <button
              type="button"
              className="positive-button"
              onClick={onSaveButtonClickHandler}
            >
              저장
            </button>
            <button
              type="button"
              className="negative-button"
              onClick={() => onViewChangeButtonClickHandler("button")}
            >
              취소
            </button>
          </div>
        </>
      )}
    </div>
  );
};

export default ArticleWriter2;
