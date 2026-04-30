// inputDate : {todo, dueDate, priority}
// -> onChange 이벤트 핸들러 함수 - newTodo 상태값 변경

import { memo, useRef, useState } from "react";
import { Alert } from "../ui/Modals";
import { fetchAddTodo, fetchTodoList } from "../../http/todo/fetchTodo";
import { useDispatch } from "react-redux";
import { todoAction } from "../../stores/toolkit/slices/todoSlice";

const TodoAppender = memo(() => {
  console.log(TodoAppender);
  const [isFetching, setIsFetching] = useState(false);

  // Component Rendering을 Delay
  // for (let i = 1; i < 10000; i++) {
  //   console.log(i);
  // }
  const reactReduxDispatcher = useDispatch();
  const todoRef = useRef();
  const dueDateRef = useRef();
  const priorityRef = useRef();

  const alertRef = useRef();

  const onInputRefClickHandler = async () => {
    // todoRef랑 이름이 같으면 안됨
    const todo = todoRef.current.value;
    const dueDate = dueDateRef.current.value;
    const priority = priorityRef.current.value;

    if (!todo) {
      alertRef.current.showModal("스케줄을 입력하세요.");
      return;
    }
    if (!dueDate) {
      alertRef.current.showModal("날짜를 등록하세요.");
      return;
    }
    if (!priority) {
      alertRef.current.showModal("우선순위를 등록하세요.");
      return;
    }

    setIsFetching(true);
    console.log(todo, dueDate, priority);

    const addResult = await fetchAddTodo(todo, dueDate, priority);
    if (addResult) {
      alert(addResult.errors);
    }

    setIsFetching(false); // 등록을 하기 위한 페칭이 끝났다.
    const fetchResult = await fetchTodoList();
    reactReduxDispatcher(todoAction.refresh(fetchResult.body));

    // 입력값 초기화
    todoRef.current.value = "";
    dueDateRef.current.value = "";
    priorityRef.current.value = "";
  };

  return (
    <footer>
      <Alert dialogRef={alertRef} />
      <input type="text" name="todo" placeholder="Task" ref={todoRef} />
      <input type="date" name="dueDate" ref={dueDateRef} />
      <select name="priority" ref={priorityRef}>
        {/* 우선순위한테 값이 '' 공백일 때 이걸 우선순위로 넣어라 */}
        <option value="">우선순위</option>
        <option value="1">높음</option>
        <option value="2">보통</option>
        <option value="3">낮음</option>
      </select>
      <button
        type="button"
        disabled={isFetching}
        onClick={onInputRefClickHandler}
      >
        {isFetching ? "저장 중..." : "저장"}
      </button>
    </footer>
  );
});

export default TodoAppender;
