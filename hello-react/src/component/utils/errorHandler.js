// error [{ field defailtMessage }]
import { isArray, isObject } from "./type";

export const getValidationResult = (error) => {
    if (isArray(error)) {
        // error가 배열이냐
        const message = [];

        for (let eachError of error) {
            if (isObject(eachError)) {
                // eachError가 객체이냐
                if (eachError.field && eachError.defailtMessage) {
                    message[eachError.field] = eachError.defailtMessage;
                    // {email : "email을 입력해주세요", password: "비밀번호를 입력해주세요"}} 라고 나오도록 함
                } else {
                    // 객체가 아닐 경우에 return이 필요하다.
                    return undefined;
                }

            } else {
                // 객체가 아닐 경우에 return이 필요하다.
                return undefined;
            }
        }
    }
};