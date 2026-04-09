$().ready(function(){

    /* 로그인 후 이동 처리 */
    const params = new URLSearchParams(location.search);
    let go = params.get("go");

    // go 값이 없으면 현재 페이지 저장
    if (!go) {
        const pathname = location.pathname;

        if (!pathname.startsWith("/login")) {
            go = pathname;
        }
    }

    if (go) {
        $("#go").val(go);
    }

    /* 로그인 검증 */
    $("#loginVO").on("submit", function (event) {

        // 기존 에러 제거
        $(".memberError").remove();

        const email = $("#email").val();
        const password = $("#password").val();

        let isValid = true;

        // 이메일 검사
        if (!email || !email.includes("@")) {
            $("#email").after(
                $("<div>").addClass("memberError").text("올바른 이메일을 입력하세요.")
            );
            isValid = false;
        }

        // 비밀번호 검사
        if (!password) {
            $("#password").after(
                $("<div>").addClass("memberError").text("비밀번호를 입력하세요.")
            );
            isValid = false;
        }

        // 실패 시 제출 막기
        if (!isValid) {
            event.preventDefault();
        }
    });

});