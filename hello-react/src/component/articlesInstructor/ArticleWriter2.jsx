import { useRef, useState, forwardRef } from "react";
import { Alert } from "../ui/Modals";
import { useDispatch, useSelector } from "react-redux";
import { articleThunk } from "../../stores/toolkit/slices/articleSlice";
import { isString } from "../utils/type";

//forwardRef: 부모에서 ref로 input 값 접근 가능하게 함
const Input = forwardRef(({ id, title, type = "text", ...props }, ref) => {
  return (
    <div className="input-field">
      <label htmlFor={id}>{title}</label>
      <input type={type} id={id} ref={ref} {...props} />
    </div>
  );
});

const Textarea = forwardRef(({ id, title, ...props }, ref) => {
  return (
    <div className="input-field">
      <label htmlFor={id}>{title}</label>
      <textarea id={id} ref={ref} {...props}></textarea>
    </div>
  );
});

const ArticleWriter = () => {
  // Redux dispatch는 이 컴포넌트에서 사용하는 구조
  const {
    error: { write: addError },
  } = useSelector((store) => store.article);
  const dispatch = useDispatch();
  const [viewMode, setViewMode] = useState("button");

  const subjectRef = useRef();
  const contentRef = useRef();
  const attachFileRef = useRef();
  const alertRef = useRef();

  const onSaveButtonClickHandler = async () => {
    // 제목 검증
    if (!subjectRef.current.value) {
      alertRef.current.showModal("제목을 입력해주세요.");
      return;
    }

    // 내용 검증
    if (!contentRef.current.value) {
      alertRef.current.showModal("내용을 입력해주세요.");
      return;
    }

    // 파일 검증
    if (!attachFileRef.current.files.length) {
      alertRef.current.showModal("파일을 선택해주세요.");
      return;
    }

    // 서버 등록
    dispatch(
      articleThunk.write(
        subjectRef.current.value,
        attachFileRef.current.value,
        contentRef.current.value,
      ),
    );

    // 입력값 초기화
    subjectRef.current.value = "";
    attachFileRef.current.value = "";
    contentRef.current.value = "";

    setViewMode("button");
  };

  return (
    <div className="article-writer">
      {viewMode === "button" && (
        <button onClick={() => setViewMode("form")}>글쓰기</button>
      )}

      {viewMode === "form" && (
        <>
          <Alert dialogRef={alertRef} />

          {/* 에러 출력 */}
          {isString(addError) && <div>{addError}</div>}

          <Input id="subject" title="제목" ref={subjectRef} />
          <Textarea id="content" title="내용" ref={contentRef} />
          <Input type="file" id="file" title="첨부파일" ref={attachFileRef} />

          <button onClick={onSaveButtonClickHandler}>저장</button>
          <button onClick={() => setViewMode("button")}>취소</button>
        </>
      )}
    </div>
  );
};

export default ArticleWriter;
