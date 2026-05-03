import { createSlice } from "@reduxjs/toolkit";

export const articleSlice = createSlice({
    name: "article",
    initialState: {
        list: [],
        count: 0,
        pagination: {}
    },
    reducers: {
        // 서버에서 가져온 전체 게시글 교체
        refresh(store, action) {
            // payload = articles 배열
            store.list = action.payload;
            store.count = action.payload.count;
            store.pagination = action.payload.pagination;
        },

        // 게시글 추가
        addArticle(store, action) {
            // payload = 새로운 게시글 객체
            store.list.push(action.payload);
        },

        // 조회수 증가
        increaseView(store, action) {
            const article = store.list.find(
                (a) => a.id === action.payload
            );

            if (article) {
                article.viewCnt++;
            }
        },
    },
});

export const articleAction = articleSlice.actions;