/**
 * todo 목록 전체 조회
 * 서버에서 모든 할 일 데이터를 가져오는 함수
 */
export const fetchTodoList = async () => {
    try {
        // 서버로 GET 요청을 보낸다 (목록 조회)
        const todoResponse = await fetch("http://localhost:8888/api/v1/task");

        // 응답 데이터를 JSON 형태로 변환
        const todoList = await todoResponse.json();

        // 호출한 곳(TodoMain 등)에서 사용할 수 있도록 반환
        return todoList;
    } catch (e) {
        console.log("서버 중단", e);
        return {
            status: 500,
            statusMessage: "internal Server Error",
            pages: 0,
            next: false,
            errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
            count: 0,
            body: [],
        };
    }
};


/**
 * 특정 todo 완료 처리
 * todoId를 받아서 해당 todo의 상태를 변경 (완료 처리)
 */
export const fetchDoneTodo = async (todoId) => {
    try {
        // PUT 요청: 특정 todo를 수정 (완료 상태 변경)
        const fetchResult = await fetch(
            `http://localhost:8888/api/v1/task/${todoId}`,
            {
                method: "put",
            }
        );

        // 서버 응답을 JSON으로 변환
        const doneResult = await fetchResult.json();

        // 결과 확인용 로그
        console.log(doneResult);

        // 결과 반환
        return doneResult;
    } catch (e) {
        console.log("서버 중단", e);
        return {
            status: 500,
            statusMessage: "internal Server Error",
            pages: 0,
            next: false,
            errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
            count: 0,
            body: "",
        };
    }
};


/**
 * 전체 todo 완료 처리
 * 모든 todo를 한 번에 완료 상태로 변경
 */
export const fetchAllDoneTodo = async () => {
    try {
        // PUT 요청: 전체 todo 상태 변경
        const fetchResult = await fetch("http://localhost:8888/api/v1/task", {
            method: "put",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ isDone: true }),
        });

        /**
         * await를 사용해서 서버 응답이 올 때까지 기다림
         * (비동기 처리를 동기처럼 순서대로 실행하기 위해 중요)
         */
        const addResult = await fetchResult.json();

        // 결과 확인
        console.log(addResult);

        // 결과 반환
        return addResult;
    } catch (e) {
        console.log("서버 중단", e);
        return {
            status: 500,
            statusMessage: "internal Server Error",
            pages: 0,
            next: false,
            errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
            count: 0,
            body: null,
        };
    }
};


/**
 * 새로운 todo 추가
 * 입력받은 값(todo, 날짜, 우선순위)을 서버에 저장
 */
export const fetchAddTodo = async (todo, dueDate, priority) => {
    try {
        // POST 요청: 새로운 데이터 생성
        const fetchResult = await fetch("http://localhost:8888/api/v1/task", {
            method: "post",

            // 서버에 JSON 형태로 보내겠다는 의미
            headers: {
                "Content-Type": "application/json",
            },

            // 서버로 보낼 데이터 (문자열(JSON) 형태로 변환 필요)
            body: JSON.stringify({
                task: todo,
                dueDate,
                priority,
                isDone: false, // 처음 생성 시에는 미완료 상태
            }),
        });

        // 서버 응답을 JSON으로 변환
        const addResult = await fetchResult.json();

        // 결과 확인
        console.log(addResult);

        // 필요하면 return 추가 가능
        // return addResult;
    } catch (e) {
        console.log("서버 중단", e);
        return {
            status: 500,
            statusMessage: "internal Server Error",
            pages: 0,
            next: false,
            errors: "서비스가 잠시 중단되었습니다. 잠시 후 다시 시도해주세요.",
            count: 0,
            body: {},
        };
    }
};