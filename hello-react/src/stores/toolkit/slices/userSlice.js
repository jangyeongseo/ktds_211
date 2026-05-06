import { createSlice } from "@reduxjs/toolkit";
import { fetchLogin, fetchMyInfo } from "../../../http/articles/fetchLogin";
import { isString } from "../../../component/utils/type";
import { getValidationResult } from "../../../component/utils/errorHandler";

// ReduxToolkit slice store 생성
export const userSlice = createSlice({
    name: "user",
    initialState: {
        token: null,
        info: null,
        error: null,
    },
    reducers: {
        autoLogin(store) {
            //sesstion Storage에 있는 token을 가져와서 userSlice에 등록한다.
            const token = sessionStorage.getItem("token");
            if (token) {
                store.token = token;
            }
        },
        login(store, action) {
            store.token = action.payload;
            store.error = null; // 로그인 성공시 error 없애기
        },
        logout(store) {
            store.token = null;
            store.info = null;
        },
        loadMyInfo(store, action) {
            // 사용자의 정보를 info한테 넣어라
            store.info = action.payload;
        },
        error(store, action) {
            if (isString(action.password)) {
                store.error = action.password;
            } else {
                store.error = getValidationResult(action.payload);
            }
        },
    }

});
export const userAction = userSlice.actions;

// toolkit slice store에 대한 custom aciton(reducer) => fetch + dispatch 생성
export const userThunk = {
    login(email, password) {
        return async (dispatcher) => {
            //fetch
            const loginResult = await fetchLogin(email, password);
            // dispatch
            if (!loginResult.error()) {
                sessionStorage.setItem("token", loginResult.token)
                dispatcher(userAction.login(loginResult.token));
            } else {
                dispatcher(userAction.error(loginResult.error));
            }
        };
    },
    loadMyInfo() {
        return async (dispatcher) => {
            const myInfo = await fetchMyInfo();
            // 에러가 있으면
            if (myInfo.error()) {
                sessionStorage.removeItem("token");
                dispatcher(userAction.logout(myInfo));
            } else {
                dispatcher(userAction.error(myInfo));
            }
        }
    },
    logout() {
        return async (dispatcher) => {
            sessionStorage.removeItem("token");
            dispatcher(userAction.logout());
        }
    }
};