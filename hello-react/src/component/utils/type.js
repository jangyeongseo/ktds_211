/** @format */

/**
 * 값이 문자열인지 확인하는 함수
 * typeof는 데이터 타입을 문자열로 반환함 ("string", "number", ...)
 */
export const isString = (value) => {
    // typeof value === "string"이면 true, 아니면 false
    return typeof value === "string";
};

/**
 * 값이 숫자인지 확인하는 함수
 */
export const isNumber = (value) => {
    return typeof value === "number";
};

/**
 * 값이 객체인지 확인하는 함수
 * 단, 배열은 제외 (배열도 typeof는 object이기 때문)
 */
export const isObject = (value) => {
    return (
        typeof value === "object" && // object 타입인지 확인
        value !== null &&            // null도 object라서 제외
        !Array.isArray(value)        // 배열은 제외
    );
};

/**
 * 값이 배열인지 확인하는 함수
 */
export const isArray = (value) => {
    return Array.isArray(value);
};

/**
 * 값이 함수인지 확인하는 함수
 */
export const isFunction = (value) => {
    return typeof value === "function";
};