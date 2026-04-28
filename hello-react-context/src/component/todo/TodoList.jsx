/** @format */

import { useContext } from "react";
import TodoItem from "./TodoItem";
import { TodoContext } from "./contexts/TodoContext.jsx";

const TodoList = () => {
  const priorities = ["없음", "높음", "보통", "낮음"];
  const { todos } = useContext(TodoContext);

  return (
    <>
      {todos.map(({ id }) => (
        <TodoItem key={id} id={id} priorities={priorities} />
      ))}
    </>
  );
};
export default TodoList;
