/** @format */

import { useCallback, useEffect, useMemo, useState } from "react";
import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";
import TodoItem from "./TodoItem.jsx";
import TodoGrid from "./TodoGrid.jsx";
import {
  fetchAddTodo,
  fetchAllDoenTodo,
  fetchDoneTodo,
  fetchTodoList,
} from "../../http/todo/fetchTodo.js";

const TodoMain = () => {
  console.log("TodoMain 렌더링");

  const [cachedData, setCachedData] = useState([]);

  const refreshTodoList = async () => {
    const todoList = await fetchTodoList();
    setCachedData(todoList.body);

    // 모든 fetch마다 필요하다.
    if (todoList.errors) {
      alert(todoList.errors);
    }
  };

  useEffect(() => {
    refreshTodoList();
  }, []);

  const onAllDoneChangeHandler = useCallback(async () => {
    const allDoneResult = await fetchAllDoenTodo();

    if (!allDoneResult.errors) {
      refreshTodoList();
    } else {
      alert(allDoneResult.errors);
    }
  }, []);

  const onDoneChangeHandler = async (todoId) => {
    const doneResult = await fetchDoneTodo(todoId);

    // 모든 fetch마다 필요하다.
    if (doneResult.errors) {
      refreshTodoList();
    } else {
      alert(doneResult.errors);
    }
  };

  const onSaveButtonClickHandler = useCallback(
    async (todo, dueDate, priority) => {
      const addResult = await fetchAddTodo(todo, dueDate, priority);

      // 모든 fetch마다 필요하다.
      if (addResult.errors) {
        refreshTodoList();
      } else {
        alert(addResult.errors);
      }
    },
    [],
  );

  /**
   * todo 개수 계산
   * cachedData가 변경될 때만 다시 계산됨 (성능 최적화)
   */
  const todoCount = useMemo(() => {
    return {
      // 전체 개수
      all: cachedData.length,

      // 완료된 개수
      done: cachedData.filter((todo) => todo.done).length,

      // 진행 중 개수
      process: cachedData.filter((todo) => !todo.done).length,
    };
  }, [cachedData]);

  return (
    <div className="wrapper">
      <header>React Todo</header>

      <TodoGrid>
        <TodoList>
          <TodoHeader
            todoCount={todoCount}
            onAllDoneChange={onAllDoneChangeHandler}
          />
          {cachedData.map((todo) => (
            <TodoItem
              key={todo.id}
              todo={todo}
              onDoneChange={onDoneChangeHandler}
            />
          ))}
        </TodoList>
      </TodoGrid>
      <TodoAppender onSaveButtonClickHandler={onSaveButtonClickHandler} />
    </div>
  );
};

export default TodoMain;
