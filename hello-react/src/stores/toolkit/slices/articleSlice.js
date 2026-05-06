import { createSlice } from "@reduxjs/toolkit";
import { fetchAddArticle, fetchArticleList } from "../../../http/articles/fetchArticles";
import { isString } from "../../../component/utils/type";
import { getValidationResult } from "../../../component/utils/errorHandler";

export const articleSlice = createSlice({
    name: "article",
    initialState: {
        list: [],
        count: 0,
        pagination: { pageNo: 0, pageCount: 0 },
        error: {
            list: null,
            write: null,
        },
    },
    reducers: {
        // 서버에서 가져온 전체 게시글 교체
        refresh(store, action) {
            // payload = articles 배열
            store.list = action.payload.list;
            store.count = action.payload.count;
            store.pagination = action.payload.pagination;
            store.error.list = null;
        },
        listError(store, action) {
            store.error.list = action.payload;
        },
        writeError(store, action) {
            if (isString(action.payload)) {
                store.error.write = action.payload;
            } else {
                store.error.write = getValidationResult(action.payload);
            }
        },
        clearWriteError(store) {
            store.error.write = null;
        },
    },
});

export const articleAction = articleSlice.actions;

export const articleThunk = {
    reducers(pageNo) {
        return async (dispacher) => {
            const fetchResult = await fetchArticleList(pageNo);
            if (fetchResult.error) {
                dispacher(articleAction.listError(fetchResult.error));
                return;
            }

            const {
                result: { count, result },
                pagination,
            } = fetchResult;

            dispacher(
                articleAction.refresh({
                    list: result,
                    count,
                    pagination,
                })
            );
        }
    },
    write(subject, attachFiles, content) {
        return async (dispacher, getState) => {
            const token = getState().user.token;
            const addResult = await fetchAddArticle(
                token,
                subject,
                attachFiles,
                content,
            );

            if (addResult.error) {
                dispacher(articleAction.writeError(addResult.error));
            } else {
                dispacher(articleAction.clearWriteError());
                dispacher(articleThunk.reducers(0));  // 다시 목록 조회
            }
        }
    }
};