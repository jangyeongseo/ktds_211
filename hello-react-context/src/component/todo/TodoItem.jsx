import { useContext, useRef } from "react";
import { Confirm } from "../ui/Modals";
import { TodoContext } from "./contexts/TodoContext.jsx";

const TodoItem = ({ id, priorities }) => {
  const { getTodo, done } = useContext(TodoContext);
  const { id: todoId, todo: todoTask, dueDate, priority, isDone } = getTodo(id);
  console.log("getTodo 결과:", getTodo(id));

  const todoItemConfirmRef = useRef();
  const checkboxRef = useRef();

  const doneClass = isDone ? "done" : "";

  const onDoneChangeHandler = () => {
    let message = `"${todoTask}"을 "${
      checkboxRef.current.checked ? "완료" : "미완료"
    }" 하시겠습니까?`;

    // confirm 모달 열기
    todoItemConfirmRef.current.showConfirm(message);
  };

  const onConfirmOkClickHandler = () => {
    console.log("현재 체크 상태:", checkboxRef.current.checked);

    // 부모에게 상태 변경 요청
    done(todoId, !checkboxRef.current.checked);
  };

  const onConfirmCloseClickHandler = () => {
    // 아무 작업 안함 (취소니까)
  };

  return (
    <li className="tasks-item">
      <Confirm
        dialogRef={todoItemConfirmRef}
        onOkClick={onConfirmOkClickHandler}
        onCloseClick={onConfirmCloseClickHandler}
      />

      <input
        id={id}
        type="checkbox"
        /**
         * 중요: controlled component
         * todo.isDone이 "진짜 상태"
         * UI는 props가 결정함
         */
        checked={isDone}
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
