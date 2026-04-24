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
  const [todos, setTodos] = useState(todoData); // todoData는 초기값, todos는 상태값, setTodos는 상태값을 변경하는 함수
  const [newTodo, setNewTodo] = useState({
    todo: "",
    dueDate: "",
    priority: 0,
  }); // 값 넣기

  const priority = ["없음", "높음", "보통", "낮음"];
  // const ==> 상수 정의
  // let ==> 변수 정의
  // TODO JSON DATA

  // 전체 선택용
  const onAllCheckChangeHandler = (event) => {
    const ischeck = event.target.checked;
    const updatedTodos = todos.map((todo) => ({ ...todo, ischeck }));

    setTodos(updatedTodos);
  };

  // 특정 todo의 체크박스 상태 변경 이벤트 핸들러 함수 정의
  const onDoneChangeHandler = (todoId) => {
    const updatedTodos = todos.map((todo) =>
      // ...todo => todo 객체의 모든 속성을 펼쳐서 새로운 객체를 만들어줌.
      todo.id === todoId ? { ...todo, ischeck: !todo.ischeck } : todo,
    );

    setTodos(updatedTodos);
  };

  // 이벤트 핸들러 함수 정의
  const onNewTodoChangeHandler = (event) => {
    const { name, value } = event.target;

    setNewTodo((prev) => ({
      ...prev,
      [name]: name === "priority" ? Number(value) : value,
    }));
  };

  const onSaveButtonClickHandler = () => {
    const newItem = {
      id: `todo_${todos.length + 1}`,
      ...newTodo,
      ischeck: false,
    };
    setTodos((prev) => [...prev, newItem]);

    // 입력 초기화
    setNewTodo({
      todo: "",
      dueDate: "",
      priority: 0,
    });
  };

  // 컴포넌트가 만들어줄 HTML Tag set를 반환
  return (
    <div className="wrapper">
      {/* <StateTest /> */}
      <header>React Todo</header>
      <ul className="tasks">
        <TodoHeader onAllCheckChangeHandler={onAllCheckChangeHandler} />
        <TodoList
          priority={priority}
          todoData={todos}
          onCheckChange={onDoneChangeHandler}
        />
      </ul>
      <TodoAppender
        newTodo={newTodo}
        onNewTodoChangeHandler={onNewTodoChangeHandler}
        onSaveButtonClickHandler={onSaveButtonClickHandler}
      />
    </div>
  );
};

export default TodoMain;
