/*
    회원페이지와 관련된 스크립트 작성
*/

$().ready(function () {
    
    // 형재 Location의 pathname을 가지고 온다.
    var pathname = location.pathname;
    // pathname이 "/login"이 아니라면 action을 "/login?go=/write"으로 수정한다.
    if(pathname !== "/login"){
        pathname = "?go=" + pathname;
    }else{
        pathname = "";
    }
    
    $("#loginVO").attr({action:"/login" + pathname})
    
    // 브라우저에서 입력값을 검증하는 방법 2가지
    // 1. 폼 전송할 때 체크하는 방법
    // 2. 입력폼레 값을 입력을 할 때 체크방법(keyup 이벤트 활용)

    // 회원 가입 폼이 전송이 되기 전에 입력값을 제대로 작성했는지 체크

    // 폼이 전송이 될 때 이벤트 처리
    $("#writeVO, #loginVO").on("submit", function (event) {
        // 이밎 브라우저에 할당된 서브밋 콜백 이벤트를 제거한다.
        event.preventDefault();

        // form 내부에 존재하는 ".signerror" 클래스를 가진 요소를 제거한다.
        $(this).find(".signerror").remove();
        
        var formId = $(this).attr("id");
        
        $("#password").trigger("keyup"); // password 입력폼에 keyup 이벤트를 강제로 발생시킨다.

        // 이름, 이메일, 비밀번호를 제대로 입력하지 않았다 => 에러 메세지를 화면에 보여준다. 폼 전송 x
        var email = $("#email").val();
        if (!email || !email.includes("@")) {
            var emaillErrorMessage = $("<div>").addClass("signerror").text("올바른 이메일을 입력하세요");
            $("#email").after(emaillErrorMessage);
        }

        var passwordPath = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;
        var password = $("#password").val();
        if (!password || !passwordPath.test(password)) {
            var passwordErrorMessage = $("<div>").addClass("signerror")
                .text("비밀번호는 영소문자, 영대문자, 숫자 최소 1개를 포함하여 8글자 이상이어야 합니다.");
            $("#password").after(passwordErrorMessage);
        }
        
                if (formId === "writeVO") {
                    var name = $("#name").val();
                    if (!name || name.length < 2) {
                        $("#name").after(
                            $("<div>").addClass("signerror").text("이름을 입력하세요")
                        );
                    }
                }

        // 이름, 이메일, 비밀번호를 제대로 입력했다 => 폼 전송
        if ($(".signerror").length === 0) {
            // $(this).submit(); => JQuery Event
            // => 15번 라인(preventDefault)에서 전송 이벤트가 사라진 이유 때문에 동작되지 않는다.
            this.submit(); // => Javascript Event
        }
    });

    // 이메일 입력폼에 keyup 이벤트가 발생할 때마다 입력값이 이메일 형식에 맞는지 체크
    // 이메일 포커스가 해제되면. 0.15초 이후에 이메일 재검사.
    $("#email").on("blur", function () {
        setTimeout(function () {
            $("#email").trigger("keyup");
        }, 150);
    }); // -> 이거 과제할때 안해도 괜찮다.

    // email 키 입력을 시작한 시간.
    var keyUpStartTime = new Date().getTime();

    $("#email").on("keyup", function () {
        var emailValue = $(this).val();

        // 이메일 키 입력이 발생한 시간
        var nowTime = new Date().getTime();
        // 시간의 차가 0.1초 이내라면 이벤트 반응하지 않음
        if (nowTime - keyUpStartTime < 100) {
            return; // 0.1초가 지나지 않았다면 함수를 종료
        }
        keyUpStartTime = nowTime; // 키 입력이 발생한 시간을 업데이트


        var emailPattern = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

        // $(this).closest(".main-text").children(".signsuccess, .signerror").remove();

        // 이메일을 입룍 했을 때
        if (emailPattern.test(emailValue)) {
            // 비동기로 중복 여부를 검사해 온다.
            // '/'로 시작한다 하면 앞에 도메인 주소가 붙는다. 
            //  => http://localhost:8080/regist/check/duplicate/입력한이메일
            // 앞에 있는 주소를 브라우저가 자동으로 넣어주는 것이다.
            // '/' 로 시작하지 않으면 현재 페이지의 주소 뒤에 붙는다.
            fetch("/regist/check/duplicate/" + emailValue)
                // 비동기 결과를 이용해서 메시지를 노출하거나 숨긴다.
                .then(function (fetchResult) {
                    return fetchResult.json(); // 응답 결과를 JSON으로 파싱한다.
                })
                .then(function (json) {
                    // console.log(json); // {duplicate: true} 또는 {duplicate: false}
                    var duplicateResult = $("#email").closest(".main-text").children(".signerror");

                    if (duplicateResult.length === 0) {
                        duplicateResult = $("#email").closest(".main-text").children(".signsuccess");
                    }

                    if (duplicateResult.length === 0) {
                        var duplicateResult = $("<div>");
                        $("#email").after(duplicateResult);
                    }


                    if (!json.duplicate) {
                        // 사용 가능한 이메일
                        duplicateResult.removeClass("signerror");
                        duplicateResult.addClass("signsuccess").text("사용 가능한 이메일입니다.");

                    } else {
                        // 사용 불가능한 이메일
                        duplicateResult.removeClass("signsuccess");
                        duplicateResult.addClass("signerror").text("이미 사용 중인 이메일입니다.");
                    }

                })
        } else {
            // 이메일 형식이 올바르지 않을 때
            $(this).closest(".main-text").children(".signsuccess, .signerror").remove();
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