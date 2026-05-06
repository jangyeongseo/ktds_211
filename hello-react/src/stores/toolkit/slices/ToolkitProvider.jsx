// Redux Toolkit에서 store를 쉽게 만들어주는 함수
import { configureStore } from "@reduxjs/toolkit";

// 우리가 만든 todoSlice를 가져온다.
// 이 안에는 reducer와 action이 모두 들어있다.
import { todoSlice } from "./todoSlice";

// React와 Redux를 연결해주는 Provider
import { Provider } from "react-redux";
import { articleSlice } from "./articleSlice";
import { userSlice } from "./userSlice";

/**
 * Redux Toolkit 기반 store 생성
 */
const toolkitStore = configureStore({
  // 여러 개의 slice(reducer)를 하나의 store에 등록하는 곳
  reducer: {
    // "todo"라는 이름으로 상태를 등록
    // 이후 useSelector에서 state.todo 로 접근 가능
    todo: todoSlice.reducer,

    // 다른 slice들도 여기에 추가할 수 있다.
    // 예:
    article: articleSlice.reducer,
    user: userSlice.reducer,
  },
});

/**
 * React에서 사용할 Provider 컴포넌트
 *
 * Provider는 Redux store를 React 전체에 공급해주는 역할을 한다.
 */
export const ToolkitProvider = ({ children }) => {
  // children: 이 Provider 안에 들어가는 모든 React 컴포넌트들
  return (
    // store를 전달하면 하위 컴포넌트 어디서든 Redux 사용 가능
    <Provider store={toolkitStore}>{children}</Provider>
  );
};
