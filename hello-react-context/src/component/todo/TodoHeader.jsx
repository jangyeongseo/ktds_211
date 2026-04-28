/** @format */

import { useContext, useRef } from "react";
import { Confirm } from "../ui/Modals";
import { TodoContext } from "./contexts/TodoContext.jsx";

const TodoHeader = () => {
  const { allDone } = useContext(TodoContext); // 전체를 가져오는 것보다 나한테 필요한것만 가져온다.

  const checkboxRef = useRef();
  const confirmRef = useRef();

  const onAllDoneChangeHandler = () => {
    const checked = checkboxRef.current.checked;
    let message = "";
    if (checked) {
      message = "모든 Item들을 '완료'하시겠습니까?";
    } else {
      message = "모든 Item들을 '미완료'하시겠습니까?";
    }

    confirmRef.current.showConfirm(message);
  };

  const onConfirmOkClickHander = () => {
    allDone(checkboxRef.current.checked);
  };
  const onConfirmCloseClickHandler = () => {
    checkboxRef.current.checked = !checkboxRef.current.checked;
  };

  return (
    <li className="tasks-header">
      <Confirm
        dialogRef={confirmRef}
        onOkClick={onConfirmOkClickHander}
        onCloseClick={onConfirmCloseClickHandler}
      />
      <input
        id="checkall"
        type="checkbox"
        ref={checkboxRef}
        onChange={onAllDoneChangeHandler}
      />
      <label>Task</label>
      <span className="due-date">Due Date</span>
      <span className="priority">Priority</span>
    </li>
  );
};
export default TodoHeader;
