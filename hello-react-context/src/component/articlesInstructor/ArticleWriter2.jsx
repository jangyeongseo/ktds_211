/** @format */

import { useRef, useState, forwardRef } from "react";
import { Alert } from "../ui/Modals";

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

const ArticleWriter2 = ({ onAddArticleClick }) => {
  /**
   *  useRef
   * - 각각 input, textarea DOM을 직접 참조하기 위한 변수
   * - 나중에 .current로 실제 DOM 접근 가능
   */
  const subjectRef = useRef();
  const nameRef = useRef();
  const emailRef = useRef();
  const contentRef = useRef();

  const alertRef = useRef(); // dialogRef (다이얼로그)를 제어할 ref(레프)

  /**
   *  viewMode
   * - button: 글쓰기 버튼만 보임
   * - form: 입력 폼 보임
   */
  const [viewMode, setViewMode] = useState("button");

  /**
   *  저장 버튼 클릭 시 실행
   * - ref를 통해 입력값을 직접 가져옴
   */
  const onSaveButtonClickHandler = () => {
    // .current → 실제 DOM
    const subject = subjectRef.current.value;
    const name = nameRef.current.value;
    const email = emailRef.current.value;
    const content = contentRef.current.value;

    console.log("제목:", subject);
    console.log("이름:", name);
    console.log("이메일:", email);
    console.log("내용:", content);
    console.log("모달(어러트?)", alertRef);

    // dialog가 있어 가능
    if (!subject) {
      alertRef.current?.showModal("제목을 입력해주세요");
      return;
    }

    if (!name) {
      alertRef.current?.showModal("이름을 입력해주세요");
      return;
    }

    if (!email) {
      alertRef.current?.showModal("이메일을 입력해주세요");
      return;
    }

    if (!content) {
      alertRef.current?.showModal("내용을 입력해주세요");
      return;
    }

    // 부모 컴포넌트로 전달 가능
    onAddArticleClick({
      subject,
      name,
      email,
      content,
    });

    // 입력값 초기화
    subjectRef.current.value = "";
    nameRef.current.value = "";
    emailRef.current.value = "";
    contentRef.current.value = "";

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
    <div className="article-writer">
      {/* 글쓰기 버튼 */}
      {viewMode === "button" && (
        <button
          type="button"
          onClick={() => onViewChangeButtonClickHandler("form")}
        >
          글쓰기
        </button>
      )}

      {/* 입력 폼 */}
      {/* 옵셔널 체이닝 */}
      {viewMode === "form" && (
        <>
          <Alert dialogRef={alertRef} />
          {/* ref를 각 input에 연결 */}
          <Input id="subject" title="제목" ref={subjectRef} />
          <Input id="name" title="이름" ref={nameRef} />
          <Input id="email" title="이메일" ref={emailRef} />
          <Textarea id="content" title="내용" ref={contentRef} />

          {/* 저장 버튼 */}
          <button
            type="button"
            className="positive-button"
            onClick={onSaveButtonClickHandler}
          >
            저장
          </button>

          {/* 취소 버튼 */}
          <button
            type="button"
            className="negative-button"
            onClick={() => onViewChangeButtonClickHandler("button")}
          >
            취소
          </button>
        </>
      )}
    </div>
  );
};

export default ArticleWriter2;
