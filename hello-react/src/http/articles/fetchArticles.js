/** @format */

export const fetchArticleList = async (pageNo = 0, listSize = 10) => {
    try {
        const fetchResult = await fetch(`http://192.168.0.50:8080/api/articles?pageNo=${pageNo}&listSize=${listSize}`);
        const listResult = await fetchResult.json();
        return listResult;
    } catch (e) {
        console.log("서버 중단", e)
        return {
            result: { count: 0, result: [] },
            pagination: {},
            error: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
        };
    }
};

// 토큰을 가져왔니?
export const fetchJsonWebToken = async (email, password) => {
    try {
        const jsonWebToken = await fetch(
            "http://192.168.0.50:8080/api/authorization",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            }
        );

        if (!jsonWebToken.ok) {
            throw new Error("Login failed: " + jsonWebToken.status);
        }

        const token = await jsonWebToken.json();
        return token;

    } catch (e) {
        console.log("서버 중단", e);
        return {
            result: { count: 0, result: [] },
            pagination: {},
            error: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
        };
    }
};

// 인증 정보 필요.
export const fetchAddArticle = async (jwt, subject, attachFile, content) => {
    try {
        const formDate = new FormData();
        formDate.append("subject", subject);

        // attachFile ==> FileList배열.
        //FileList내에 존재하는 파일 객체들을 attachFile로 하나씩 할당
        for (const file of attachFile) {
            formDate.append("attachFile", file);
        }
        formDate.append("content", content);

        const fetchResult = await fetch(`http://192.168.0.50:8080/api/articles`, {
            method: "post",
            headers: {
                Authorization: `Bearer${jwt}`,
            },
            body: formDate
        });
        const addResult = await fetchResult.json();
        return addResult;
    } catch (e) {
        console.log("서버 중단", e)
        return {
            result: false,
            error: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
        };
    }
};