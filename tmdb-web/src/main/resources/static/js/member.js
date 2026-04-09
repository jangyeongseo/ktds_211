$().ready(function () {

    /* 이메일 중복 체크 */
    let keyUpStartTime = new Date().getTime();

    $("#email").on("keyup", function () {

        const email = $(this).val();
        const nowTime = new Date().getTime();

        // 너무 빠른 입력 방지
        if (nowTime - keyUpStartTime < 100) {
            return;
        }
        keyUpStartTime = nowTime;

        const emailPattern = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

        // 기존 메시지
        $("#email").next(".memberError, .membersuccess").remove();

        if (email && emailPattern.test(email)) {

            fetch("/regist/check/duplicate/" + email)
                .then(res => res.json())
                .then(function (data) {

                    let resultDiv = $("<div>");

                    if (!data.duplicate) {
                        resultDiv
                            .addClass("membersuccess")
                            .text("사용 가능한 이메일입니다.");
                    } else {
                        resultDiv
                            .addClass("memberError")
                            .text("이미 사용 중인 이메일입니다.");
                    }

                    $("#email").after(resultDiv);
                });

        }
    });


    /* 비밀번호 실시간 체크 */
    $("#password, #confirmPassword").on("keyup", function () {

        // 기존 에러 제거
        $(".password-error").remove();

        const password = $("#password").val();
        const confirmPassword = $("#confirmPassword").val();

        if (confirmPassword && password !== confirmPassword) {

            const error = $("<div>")
                .addClass("memberError password-error")
                .text("비밀번호가 일치하지 않습니다.");

            // wrapper 밖에 넣기
            $("#confirmPassword").after(error);
        }
    });


    /* 폼 제출 검증 */
    $("#memberWriteVO").on("submit", function (event) {

        event.preventDefault();

        // 기존 에러 전체 제거
        $(this).find(".memberError").remove();

        const nickname = $("#name").val();
        const email = $("#email").val();
        const password = $("#password").val();
        const confirmPassword = $("#confirmPassword").val();

        let isValid = true;

        // 닉네임 검사
        const nicknameRegex = /^[가-힣a-zA-Z]{2,}$/;
        if (!nicknameRegex.test(nickname)) {
            $("#name").after(
                $("<div>").addClass("memberError").text("닉네임은 한글 또는 영문 2글자 이상이어야 합니다.")
            );
            isValid = false;
        }

        // 이메일 검사
        if (!email || !email.includes("@")) {
            $("#email").after(
                $("<div>").addClass("memberError").text("올바른 이메일을 입력하세요.")
            );
            isValid = false;
        }

        // 비밀번호 검사
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
        if (!password || !passwordPattern.test(password)) {
            $(".password-wrapper").after(
                $("<div>").addClass("memberError").text("비밀번호는 영문 대/소문자, 숫자 포함 8자 이상")
            );
            isValid = false;
        }

        // 비밀번호 확인
        if (password !== confirmPassword) {
            $("#confirmPassword").closest(".password-wrapper").after(
                $("<div>").addClass("memberError").text("비밀번호가 일치하지 않습니다.")
            );
            isValid = false;
        }

        // 통과 시 제출
        if (isValid) {
            this.submit();
        }
    });


    /* 비밀번호 보기 토글 */
    $(".toggle-password").on("click", function () {

        const targetId = $(this).data("target");
        const input = $("#" + targetId);

        if (input.attr("type") === "password") {
            input.attr("type", "text");
        } else {
            input.attr("type", "password");
        }
    });

});