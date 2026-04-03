/*
    회원페이지와 관련된 스크립트 작성
*/

$().ready(function () {
    // 브라우저에서 입력값을 검증하는 방법 2가지
    // 1. 폼 전송할 때 체크하는 방법
    // 2. 입력폼레 값을 입력을 할 때 체크방법(keyup 이벤트 활용)

    // 회원 가입 폼이 전송이 되기 전에 입력값을 제대로 작성했는지 체크

    // 폼이 전송이 될 때 이벤트 처리
    $("#writeVO").on("submit", function (event) {
        // 이밎 브라우저에 할당된 서브밋 콜백 이벤트를 제거한다.
        event.preventDefault();

        // form 내부에 존재하는 ".signerror" 클래스를 가진 요소를 제거한다.
        $(this).find(".signerror").remove();

        $("#password").trigger("keyup"); // password 입력폼에 keyup 이벤트를 강제로 발생시킨다.

        // 이름, 이메일, 비밀번호를 제대로 입력하지 않았다 => 에러 메세지를 화면에 보여준다. 폼 전송 x
        var email = $("#email").val();
        if (!email || !email.includes("@")) {
            var emaillErrorMessage = $("<div>").addClass("signerror").text("올바른 이메일을 입력하세요");
            $("#email").after(emaillErrorMessage);
        }

        var name = $("#name").val();
        if (!name || name.length < 2) {
            var nameErrorMessage = $("<div>").addClass("signerror").text("이름을 입력하세요");
            $("#name").after(nameErrorMessage);
        }

        var passwordPath = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
        var password = $("#password").val();
        if (!password || !passwordPath.test(password)) {
            var passwordErrorMessage = $("<div>").addClass("signerror")
                .text("비밀번호는 영소문자, 영대문자, 숫자 최소 1개를 포함하여 8글자 이상이어야 합니다.");
            $("#password").after(passwordErrorMessage);
        }

        // 이름, 이메일, 비밀번호를 제대로 입력했다 => 폼 전송
        if ($(".signerror").length === 0) {
            // $(this).submit(); => JQuery Event
            // => 15번 라인(preventDefault)에서 전송 이벤트가 사라진 이유 때문에 동작되지 않는다.
            this.submit(); // => Javascript Event
        }
    });


    $("#confirmPassword, #password").on("keyup", function () {
        console.log("keyup 이벤트 발생");
        var confirmPasswordValue = $("#confirmPassword").val();
        var passwordValue = $("#password").val();

        $("#confirmPassword").closest(".main-text").children(".signerror").remove();

        if (confirmPasswordValue !== passwordValue) {
            var passwordErrorMessage = $("<div>").addClass("signerror").text("비밀번호가 일치하지 않습니다.");

            $("#password").after(passwordErrorMessage);
            $("#confirmPassword").after(passwordErrorMessage);

        }
    });

    $("#show-password").on("change", function () {
        var checked = $(this).prop("checked"); // 체크박스의 체크 여부를 가져온다.

        // checked가 true면 비밀번호를 보여주고, false면 비밀번호를 숨긴다.
        if (this.checked) {
            $("#password").attr("type", "text");
        } else {
            $("#password").attr("type", "password");
        }
    });
});