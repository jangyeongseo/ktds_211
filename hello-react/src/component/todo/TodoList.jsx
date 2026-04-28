/** @format */

import { useContext } from "react";
import TodoContext from "./contexts/TodoContext.jsx";

const TodoList = ({ children }) => {
  const { componentName } = useContext(TodoContext);
  if (!componentName || componentName !== "TodoGrid") {
    return <></>;
  }

  const providerProps = { componentName: "TodoList" };

  // context를 활용한 컴퍼넌트 공유
  return (
    <TodoContext.Provider value={providerProps}>
      {children}
    </TodoContext.Provider>
  );
};
export default TodoList;
