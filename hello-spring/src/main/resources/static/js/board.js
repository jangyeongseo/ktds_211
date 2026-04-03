$().ready(function () {
    // ".add-files" 을 클릭하면
    // 새로운 파일 인푸과 버튼을 
    // ".attach-files"아래에 추가한다.
    // $(".add-files").on("click", function () {
    $(".attach-files").on("click", ".add-files", function () {
        // 새로운 파일이 추가될 때 마다 기존의 "add-file" 버튼을 "del-files" 로 변경하고
        // 텍스트는 "-"로 변경한다.
        $(this).closest(".attach-files").children(".add-files")
            .removeClass("add-files")
            .addClass("del-files")
            .text("-")
            .off("click")
            .on("click", function () {
                // 버튼 왼쪽에 있는 인풋 태그 삭제
                $(this).prev().remove();

                // 버튼도 삭제
                $(this).remove();

            });// 새로운 이벤트를 추가한다.

        var fileInput = $("<input />").attr({
            type: "file",
            name: "attachFiles"
        });
        var addButton = $("<button />").attr("type", "button").text("+").addClass("add-files");

        $(".attach-files").append(fileInput).append(addButton);

    });

    // 유효성 검사
    $("#writeVO").on("submit", function (event) {
        event.preventDefault();
        $(this).find(".validation-error").remove();

        var subject = $("#subject").val();
        if (!subject || subject.length < 3) {
            var subjectErrorMessage = $("<div>").addClass("validation-error").text("제목을 3글자 이상 입력하세요");
            $("#subject").after(subjectErrorMessage);
            return false;

        }

        var email = $("#email").val();
        if (!email || !email.includes("@")) {
            var emailErrorMessage = $("<div>").addClass("validation-error").text("올바른 이메일을 입력하세요");
            $("#email").after(emailErrorMessage);
            return false;
        }

        var content = $("#content").val();
        if (!content) {
            var contentErrorMessage = $("<div>").addClass("validation-error").text("내용을 입력하세요");
            $("#content").after(contentErrorMessage);
            return false;
        }

        if ($(".validation-error").length === 0) {
            this.submit();
        }
    });

});
