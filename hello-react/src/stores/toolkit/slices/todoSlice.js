import { createSlice } from "@reduxjs/toolkit";

export const todoSlice = createSlice({
    name: "todo-slice", // action의 type으로 사용될 이름
    initialState: {
        list: [], // todo의 목록

    }, // todo-slice가 사용할 초기 state 값.
    reducers: {
        refresh(store, action) {
            store.list = action.payload;
            // 가변객체의 메모리는 바꾸면 안된다. 값은 변경해도 괜찮지만
        },
        doneItem(store, action) {
            // action ==> done 처리할 todo 의 ID 가 전달된다
            // store.list에서 id가 action과 같은 todo의 인텍스를 찾아온다.
            // store.list[index].done = true; -> 기번객체의 메모리가 바뀌면 안되기 때문에
            const index = store.list.findIndex((todo) => todo.id === action.payload);
            store.list[index].done = true;

        },
        allDone(store) {
            store.list = store.list.map((todo) => ({ ...todo, done: true }));
        }
    }, // 
});

export const todoAction = todoSlice.actions; // redux를 호출하는 객체
console.log(todoAction, "todoAtion");