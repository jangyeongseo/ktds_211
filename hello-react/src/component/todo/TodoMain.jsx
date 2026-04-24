/** @format */

import React, { useState } from "react";
import TodoHeader from "./TodoHeader";
import TodoList from "./TodoList";
import TodoAppender from "./TodoAppender";
import { StateTest } from "./StateTest";

const todoData = [
  {
    id: "todo_1",
    todo: "React Component Master1",
    dueDate: "2026-04-22",
    priority: 1,
    ischeck: false,
  },
  {
    id: "todo_2",
    todo: "React Component Master2",
    dueDate: "2026-04-23",
    priority: 2,
    ischeck: false,
  },
  {
    id: "todo_3",
    todo: "React Component Master3",
    dueDate: "2026-04-24",
    priority: 3,
    ischeck: false,
  },
];

// ema function (fat arrow function)
// const : 상수를 정의하는 키워드
// (parameter) => {function body} : fat arrow function의 기본 형태
// const TodoMain = () => {};

// function과 fat arrow function의 기능적 차이
// function => 함수를 호출한 대상을 this 객체로 알 수 있따.
// fat arrow function => this 키워드 사용 불가.
//                       함수를 호출한 대상을 알 수 없다 event 파라미터로만 알 수 있다.

// export default 이후에 const 키워드가 나타날 수 없음.
// export const TodoMain = () => {};
const TodoMain = () => {
  const [todos, setTodos] = useState(todoData);
  const priority = ["없음", "높음", "보통", "낮음"];
  // const ==> 상수 정의
  // let ==> 변수 정의
  // TODO JSON DATA

  // 특정 todo의 체크박스 상태 변경 이벤트 핸들러 함수 정의
  const onDoneChangeHandler = (todoId) => {
    const updatedTodos = todos.map((todo) =>
      // ...todo => todo 객체의 모든 속성을 펼쳐서 새로운 객체를 만들어줌.
      todo.id === todoId ? { ...todo, ischeck: !todo.ischeck } : todo,
    );

    setTodos(updatedTodos);
  };

  // 이벤트 핸들러 함수 정의
  const onTaskKeyUpHandler = (event) => {
    if (event.key === "Enter") {
      console.log("Enter key is pressed");
    }
  };

  const onPrioritySelectChangeHandler = (event) => {
    console.log(event.target.value);
  };

  const onDateChangeHandler = (event) => {
    console.log(event.target.value);
  };

  const onSaveButtonClickHandler = () => {
    console.log("Save button is clicked");
  };

  // 컴포넌트가 만들어줄 HTML Tag set를 반환
  return (
    <div className="wrapper">
      {/* <StateTest /> */}
      <header>React Todo</header>
      <ul className="tasks">
        <TodoHeader priority={priority} todoData={todoData} />
        <TodoList
          priority={priority}
          todoData={todos}
          onCheckChange={onDoneChangeHandler}
        />
      </ul>
      <TodoAppender
        onTaskKeyUpHandler={onTaskKeyUpHandler}
        onDateChangeHandler={onDateChangeHandler}
        onPrioritySelectChangeHandler={onPrioritySelectChangeHandler}
        onSaveButtonClickHandler={onSaveButtonClickHandler}
      />
    </div>
  );
};

export default TodoMain;
