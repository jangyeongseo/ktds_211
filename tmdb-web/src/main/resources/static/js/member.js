$().ready(function () {
    // 이메일 중복 체크
    var keyUpStartTime = new Date().getTime();

    $("#email").on("keyup", function () {

        var email = $(this).val();

        // 이메일 키 입력이 발생한 시간
        var nowTime = new Date().getTime();

        if (nowTime - keyUpStartTime < 100) {
            return;
        }

        keyUpStartTime = nowTime;

        var emailPattern = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

        if (email && emailPattern.test(email)) {
            fetch("/regist/check/duplicate/" + email)
                .then(response => response.json())
                .then(function (data) {
                    var duplicateResult = $("#email").closest(".login-container").children(".memberError");

                    if (duplicateResult.length === 0) {
                        duplicateResult = $("#email").closest(".login-container").children(".membersuccess");
                    }

                    if (duplicateResult.length === 0) {
                        var duplicateResult = $("<div>");
                        $("#email").after(duplicateResult);
                    }

                    if (!data.duplicate) {
                        // 사용 가능한 이메일
                        duplicateResult.removeClass("memberError");
                        duplicateResult.addClass("membersuccess").text("사용 가능한 이메일입니다.");

                    } else {
                        // 사용 불가능한 이메일
                        duplicateResult.removeClass("membersuccess");
                        duplicateResult.addClass("memberError").text("이미 사용 중인 이메일입니다.");
                    }

                })
        } else {
            // 이메일 형식이 올바르지 않을 때
            $(this).closest(".login-container").children(".membersuccess, .memberError").remove();
        }

    });


    // 비밀번호 실시간 체크
    $("#password, #confirmPassword").on("keyup", function () {
        var confirmPasswordValue = $("#confirmPassword").val();
        var passwordValue = $("#password").val();

        $("#confirmPassword").closest(".login-container").children(".memberError").remove();

        if (confirmPasswordValue !== passwordValue) {
            var passwordErrorMessage = $("<div>").addClass("memberError").text("비밀번호가 일치하지 않습니다.");

            $("#password").after(passwordErrorMessage);
            $("#confirmPassword").after(passwordErrorMessage);

        }
    });
    
    $("#memberWriteVO").on("submit", function (event) {
        event.preventDefault();

        $(this).find(".memberError").remove();

        var email = $("#email").val();
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();

        // 이메일 검사
        if (!email || !email.includes("@")) {
            $("#email").after(
                $("<div>").addClass("memberError").text("올바른 이메일을 입력하세요")
            );
        }

        // 비밀번호 검사
        var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

        if (!password || !passwordPattern.test(password)) {
            $("#password").after(
                $("<div>").addClass("memberError").text("비밀번호는 영문 대/소문자, 숫자 포함 8자 이상")
            );
        }

        // 비밀번호 확인
        if (password !== confirmPassword) {
            $("#confirmPassword").after(
                $("<div>").addClass("memberError").text("비밀번호가 일치하지 않습니다")
            );
        }

        // 에러 없으면 전송
        if ($(".memberError").length === 0) {
            this.submit();
        }
    });

});