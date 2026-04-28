import { useRef } from "react";
import { Confirm } from "../ui/Modals";
import { useContext } from "react";
import TodoContext from "./contexts/TodoContext";

const TodoItem = ({ todo, onDoneChange }) => {
  const priorities = ["없음", "높음", "보통", "낮음"];
  /**
   * confirm 모달 제어용 ref
   * 부모처럼 dialog를 직접 열기 위해 사용
   */
  const todoItemConfirmRef = useRef();

  /**
   * checkbox DOM을 직접 참조하는 ref
   * 현재 체크 상태를 직접 읽기 위해 사용
   */
  const checkboxRef = useRef();

  const { componentName } = useContext(TodoContext);
  console.log("TodoItem : " + componentName);

  // if문 작성시 return이 시작되기 전에useRef가 먼저 샐행되어야해서 위에 있어야한다.
  if (!componentName || componentName !== "TodoList") {
    return <></>; // 아무것도 보이게 하지 말아라
  }

  /**
   * todo 구조 분해
   * props로 받은 todo 객체에서 값 꺼내기
   */
  const { id, todo: todoTask, dueDate, priority } = todo;

  /**
   * 완료 상태에 따라 CSS class 변경
   * todo.isDone === true → done 클래스 추가
   */
  const doneClass = todo.isDone ? "done" : "";

  /**
   * 체크박스 클릭했을 때 실행
   *
   * => 중요한 포인트:
   * 아직 "진짜 상태 변경"이 아니라
   * "확인창 먼저 띄우는 단계"
   */
  const onDoneChangeHandler = () => {
    let message = `"${todoTask}"을 "${
      checkboxRef.current.checked ? "완료" : "미완료"
    }" 하시겠습니까?`;

    // confirm 모달 열기
    todoItemConfirmRef.current.showConfirm(message);
  };

  /**
   * OK 버튼 눌렀을 때
   * => 실제 상태 변경 실행
   */
  const onConfirmOkClickHandler = () => {
    console.log("현재 체크 상태:", checkboxRef.current.checked);

    // 부모에게 상태 변경 요청
    onDoneChange(todo.id, !checkboxRef.current.checked);
  };

  /**
   * Cancel 눌렀을 때
   * => 아무 것도 안 바꾸고 그냥 종료
   */
  const onConfirmCloseClickHandler = () => {
    // 아무 작업 안함 (취소니까)
  };

  return (
    <li className="tasks-item">
      {/* confirm 모달 */}
      <Confirm
        dialogRef={todoItemConfirmRef}
        onOkClick={onConfirmOkClickHandler}
        onCloseClick={onConfirmCloseClickHandler}
      />

      {/* 체크박스 */}
      <input
        id={id}
        type="checkbox"
        /**
         * 중요: controlled component
         * todo.isDone이 "진짜 상태"
         * UI는 props가 결정함
         */
        checked={todo.isDone}
        ref={checkboxRef}
        onChange={onDoneChangeHandler}
      />

      {/* 할 일 텍스트 */}
      <label className={doneClass} htmlFor={id}>
        {todoTask}
      </label>

      {/* 날짜 */}
      <span className={`due-date ${doneClass}`}>{dueDate}</span>

      {/* 우선순위 */}
      <span className={`priority ${doneClass}`}>{priorities[priority]}</span>
    </li>
  );
};

export default TodoItem;
