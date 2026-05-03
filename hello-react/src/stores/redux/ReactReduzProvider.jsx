// react-redux에서 Provider를 가져온다.
// Provider는 Redux의 store를 React 전체 컴포넌트에서 사용할 수 있게 해주는 역할을 한다.
import { Provider } from "react-redux";

// redux에서 createStore를 가져온다.
// createStore는 state를 저장하는 store를 만들어주는 함수다.
import { createStore } from "redux";

/**
 * reducer 함수
 *
 * reducer는 "현재 상태(store)"와 "액션(action)"을 받아서
 * "새로운 상태"를 만들어서 반환하는 함수다.
 *
 * @param {*} store 현재 상태 (state)
 * @param {*} action 상태를 어떻게 바꿀지에 대한 정보 (type, payload)
 */
const reactReduxReducer = (
  // store의 초기값 설정
  // todo: 할 일 목록
  // action: 사용하지 않지만 예시로 넣어둔 상태
  // token: 로그인 토큰 등 저장용
  store = { todo: [], action: [], token: null },
  action,
) => {
  // 어떤 action이 들어오는지 콘솔로 확인
  console.log(action);

  // action에서 type과 payload를 꺼낸다.
  const { type, payload } = action;

  // type이 "todo-refresh"인 경우
  // payload로 받은 todo 목록으로 전체 교체
  if (type === "todo-refresh") {
    return { ...store, todo: payload };

    // 모든 todo를 완료 상태로 변경
  } else if (type === "todo-refresh-done") {
    return {
      ...store,
      // 기존 todo 배열을 순회하면서 done을 true로 변경
      todo: store.todo.map((eachTodo) => ({ ...eachTodo, done: true })),
    };

    // 특정 todo 하나만 완료 처리
  } else if (type === "todo-done-item") {
    return {
      ...store,
      todo: store.todo.map((eachTodo) => {
        // id가 payload와 같은 todo를 찾는다.
        if (eachTodo.id === payload) {
          // 주의: 아래 코드는 실제로 값을 바꾸지 않는다.
          // 비교(===)만 하고 있기 때문에 아무 일도 안 일어남
          // 올바른 코드는 eachTodo.done = true; 또는 새 객체 반환이어야 한다.
          eachTodo.done === true;
        }

        // 기존 todo 그대로 반환
        return eachTodo;
      }),
    };
  }

  // 어떤 조건에도 해당하지 않으면 기존 상태 그대로 반환
  return store;
};

// Redux store를 생성하는 함수
const createReduxStore = () => {
  // reducer를 기반으로 store 생성
  return createStore(reactReduxReducer);
};

// React에서 사용할 Provider 컴포넌트
export const ReactReduxProvider = ({ children }) => {
  // store 생성
  const store = createReduxStore();

  // Provider로 감싸면 하위 컴포넌트 어디서든 store 사용 가능
  return <Provider store={store}>{children}</Provider>;
};
