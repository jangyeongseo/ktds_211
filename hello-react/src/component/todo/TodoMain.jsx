/** @format */

import { useEffect } from "react";
import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";
import TodoItem from "./TodoItem.jsx";
import TodoGrid from "./TodoGrid.jsx";
import { fetchTodoList } from "../../http/todo/fetchTodo.js";
import { useDispatch, useSelector } from "react-redux";
import { todoAction } from "../../stores/toolkit/slices/todoSlice.js";

const TodoMain = () => {
  console.log("TodoMain 렌더링");

  // const [cachedData, setCachedData] = useState([]);
  // ReactRedux Store에서 rodo state를 가져온다.
  const { list: todoList } = useSelector((store) => store.todo); // store의 state를 가져옴
  console.log("TodoList state", todoList);
  const storeDispatch = useDispatch(); // todo만을 위한 dispatch가 아니다. / store의 state를 사용?

  const refreshTodoList = async () => {
    const fetchResult = await fetchTodoList();
    // setCachedData(todoList.body);
    storeDispatch(todoAction.refresh(fetchResult.body));
    // redux가 실행된다.
    // 객체를 만들어 줄거임. type으로 payload 뭐할거냐

    // 모든 fetch마다 필요하다.
    if (fetchResult.errors) {
      alert(fetchResult.errors);
    }
  };

  useEffect(() => {
    refreshTodoList();
  }, []);

  return (
    <div className="wrapper">
      <header>React Todo</header>

      <TodoGrid>
        <TodoList>
          <TodoHeader />
          {todoList.map((todo) => (
            <TodoItem key={todo.id} todo={todo} />
          ))}
        </TodoList>
      </TodoGrid>
      <TodoAppender />
    </div>
  );
};

export default TodoMain;
