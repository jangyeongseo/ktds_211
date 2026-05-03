// Redux Toolkit에서 createSlice를 가져온다.
// createSlice는 reducer + action을 한 번에 만들어주는 도구다.
import { createSlice } from "@reduxjs/toolkit";

// todo와 관련된 상태와 기능들을 하나로 묶어서 slice를 생성
export const todoSlice = createSlice({

    // slice 이름
    // action type을 만들 때 "todo-slice/refresh" 이런 식으로 자동으로 붙는다.
    name: "todo-slice",

    // 이 slice가 사용할 초기 상태 값
    initialState: {
        list: [], // todo 목록을 저장할 배열
    },

    // 상태를 변경하는 함수들 (reducer들)
    reducers: {

        /**
         * todo 목록 전체를 새로 바꿀 때 사용하는 함수
         * 
         * @param {*} store 현재 상태
         * @param {*} action payload에 새로운 todo 목록이 들어온다
         */
        refresh(store, action) {

            // 기존 list를 payload로 통째로 교체
            store.list = action.payload;

            // Redux 기본 규칙:
            // 원래는 상태를 직접 수정하면 안 된다 (불변성 유지)
            // 하지만 Redux Toolkit은 내부적으로 Immer를 사용해서
            // 이렇게 직접 수정해도 안전하게 처리된다.
        },

        /**
         * 특정 todo 하나를 완료 상태로 변경
         * 
         * @param {*} store 현재 상태
         * @param {*} action payload에는 완료 처리할 todo의 id가 들어온다
         */
        doneItem(store, action) {

            // list 배열에서 id가 같은 todo의 인덱스를 찾는다.
            const index = store.list.findIndex(
                (todo) => todo.id === action.payload
            );

            // 해당 인덱스의 todo를 완료 상태(true)로 변경
            store.list[index].done = true;

            // Toolkit에서는 이렇게 직접 값을 바꿔도 괜찮다 (Immer 때문)
        },

        /**
         * 모든 todo를 완료 상태로 변경
         */
        allDone(store) {

            // map을 사용해서 모든 todo의 done을 true로 바꾼 새 배열 생성
            store.list = store.list.map((todo) => ({
                ...todo,
                done: true,
            }));
        }
    },
});

// 자동으로 생성된 action들을 export
// 예: todoAction.refresh(), todoAction.doneItem() 같은 형태로 사용
export const todoAction = todoSlice.actions;