// CommonJS
// const { createContext } = require("react");
import { createContext, useState } from "react";

export const TodoContext = createContext({
  todos: [],
  done(todoId, doneStatus) {},
  allDone(doneStatus) {}, // 모두 done 처리, doneStatus - 체크를 했니 안했니
  addTodo(taskName, dueDate, priority) {}, // 추가해라
  getTodo(todoId) {},
});

// 공유를 받고 싶은 것을 children한테 준다.
const TodoContextProvider = ({ children }) => {
  // todoContext를 제공하는 컴포넌트
  // const ==> 상수 정의
  // let ==> 변수 정의
  // TODO JSON DATA
  const todoDatas = [
    {
      id: "todo_1",
      todo: "React Component Master",
      dueDate: "2026-04-22",
      priority: 1,
      isDone: true,
    },
    {
      id: "todo_2",
      todo: "React Component Master 2",
      dueDate: "2026-04-23",
      priority: 2,
      isDone: false,
    },
    {
      id: "todo_3",
      todo: "React Component Master 3",
      dueDate: "2026-04-24",
      priority: 3,
      isDone: false,
    },
  ];

  const [cachedData, setCachedData] = useState(todoDatas);

  const todoContextProvider = {
    todos: cachedData,
    done(todoId, doneStatus) {
      setCachedData((prevData) => {
        // const newStateMemory = [...prevData];
        // for (const todo of newStateMemory) {
        //   if (todo.id === todoId) {
        //     todo.isDone = doneStatus;
        //     break;
        //   }
        // }
        const newStateMemory = prevData.map((todo) => {
          if (todo.id === todoId) {
            todo.isDone = doneStatus;
          }
          return todo;
        });

        return newStateMemory;
      });
    },
    allDone(doneStatus) {
      setCachedData((prevData) => {
        const newData = prevData.map((todo) => ({
          ...todo,
          isDone: doneStatus,
        }));
        return newData;
      });
    }, // 모두 done 처리
    addTodo(taskName, dueDate, priority) {
      setCachedData((prevData) => [
        ...prevData,
        {
          id: "todo_" + (prevData.length + 1),
          todo: taskName,
          dueDate,
          priority,
          isDone: false,
        },
      ]);
    }, // 추가해라
    getTodo(todoId) {
      const todoArray = cachedData.find((eachTodo) => {
        return eachTodo.id === todoId;
      });
      // filter, map, find는 배열한테 사용
      // filter는 array가 나오지 않는다.

      return todoArray; // 없으면 nudefind 나옴
    },
  };

  // Context의 provider 값을 공유 받을 수 있는 컴포넌트는
  // Context.Provider의 자식 컴포넌트만 대상.
  return (
    <TodoContext.Provider value={todoContextProvider}>
      {children}
    </TodoContext.Provider>
  );
};

export default TodoContextProvider;
