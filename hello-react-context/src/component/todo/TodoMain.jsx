/** @format */

import { StateTest } from "./StateTest.jsx";
import TodoAppender from "./TodoAppender.jsx";
import TodoHeader from "./TodoHeader.jsx";
import TodoList from "./TodoList.jsx";
import TodoContextProvider from "./contexts/TodoContext.jsx";

/*
  메인 컴포넌트
  => 전체 Todo UI의 뼈대 역할
*/
const TodoMain = () => {
  return (
    <div className="wrapper">
      <header>React Todo</header>

      {/*
        Context Provider로 감싸기
        => 이 안에 있는 모든 컴포넌트는
           TodoContext 값을 사용할 수 있음
      */}
      <TodoContextProvider>
        <ul className="tasks">
          {/*
            헤더 컴포넌트
            => 전체 선택 / 상태 표시 등
            => Context에서 데이터 가져다 씀
          */}
          <TodoHeader />

          {/*
            실제 리스트 출력
            => todos 배열을 Context에서 가져와서 렌더링
          */}
          <TodoList />
        </ul>

        {/*
          할 일 추가 입력창
          => addTodo 함수(Context)를 사용해서 데이터 추가
        */}
        <TodoAppender />
      </TodoContextProvider>
    </div>
  );
};

export default TodoMain;
