/** @format */

import { useRef } from "react";
import { Confirm } from "../ui/Modals";
import { useContext } from "react";
import TodoContext from "./contexts/TodoContext.jsx";

const TodoHeader = ({ todoCount, onAllDoneChange }) => {
  console.log(TodoHeader);
  const checkboxRef = useRef();
  const confirmRef = useRef();

  const { componentName } = useContext(TodoContext);
  console.log("TodoHeader : " + componentName);

  if (!componentName || componentName !== "TodoList") {
    return <></>;
  }

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
    onAllDoneChange(checkboxRef.current.checked);
  };
  const onConfirmCloseClickHandler = () => {
    checkboxRef.current.checked = !checkboxRef.current.checked;
  };

  return (
    <>
      <li className="tasks-counter">
        <div>전체 : {todoCount.all}</div>
        <div>진행 중 : {todoCount.done}</div>
        <div>완료 : {todoCount.process}</div>
      </li>
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
    </>
  );
};
export default TodoHeader;
