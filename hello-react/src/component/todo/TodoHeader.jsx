/** @format */

import { useRef } from "react";
import { Confirm } from "../ui/Modals";
import { useContext } from "react";
import TodoContext from "./contexts/TodoContext.jsx";
import { useDispatch, useSelector } from "react-redux";
import { fetchAllDoneTodo, fetchTodoList } from "../../http/todo/fetchTodo.js";
import { todoAction } from "../../stores/toolkit/slices/todoSlice.js";

const TodoHeader = () => {
  console.log(TodoHeader);
  const checkboxRef = useRef();
  const confirmRef = useRef();

  // react-redux store -> todo 가져오기
  const { list: todoList } = useSelector((store) => store.todo);
  const reactReduxDispatch = useDispatch();

  const todoCount = {
    // 전체 개수
    all: todoList.length,

    // 완료된 개수
    done: todoList.filter((todo) => todo.done).length,

    // 진행 중 개수
    process: todoList.filter((todo) => !todo.done).length,
  };

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

  const onConfirmOkClickHander = async () => {
    // all done에 대한 낙관적 업데이트 진행.
    // 사용자가 all done을 요청했을 때, 요청결과와 상관없이 우선 all done이 된것 처럼 보여준다.
    // fetch 이후에 실패했을 경우. 원래 상태로 돌려준다.
    //              성공했을 경우, 변경된 상태로 유치
    //              all done을 수행하는 중에 다른 사용자로 인해 데이터가 추가됐다면 불러올 필요.
    reactReduxDispatch({ type: "todo-refresh-done" });

    const allDoneResult = await fetchAllDoneTodo();

    if (!allDoneResult.errors) {
      const fetchResult = await fetchTodoList();
      reactReduxDispatch(todoAction.refresh(fetchResult.body));
    } else {
      alert(allDoneResult.errors);
    }
  };
  const onConfirmCloseClickHandler = () => {
    checkboxRef.current.checked = !checkboxRef.current.checked;
  };

  return (
    <>
      <li className="tasks-counter">
        <div>전체 : {todoCount.all}</div>
        <div>진행 중 : {todoCount.process}</div>
        <div>완료 : {todoCount.done}</div>
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
