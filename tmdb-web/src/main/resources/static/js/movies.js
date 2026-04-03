/**
 * 영화 유효성 검사
 */
$().ready(function () {
    $("#movieWriteVO").on("submit", function (event) {
        event.preventDefault();

        $(this).find(".movieError").remove();

        var title = $("#title").val();
        if (!title || title.length < 2) {
            var titleErrorMessage = $("<div>").addClass("movieError").text("영화 제목을 입력하세요");
            $("#title").after(titleErrorMessage);
        }

        var movieRating = $("#movieRating").val();
        if (!movieRating) {
            var ratingErrorMessage = $("<div>").addClass("movieError").text("관련 가능 나이를 3글 이하로 입력해주세요.");
            $("#movieRating").after(ratingErrorMessage);
        }

        var synopsis = $("#synopsis").val();
        if (!synopsis) {
            var synopsisErrorMessage = $("<div>").addClass("movieError").text("줄거리는 최소 10자 이상이어야 합니다.");
            $("#synopsis").after(synopsisErrorMessage);
        }

        var state = $("#state").val();
        if (!state) {
            var stateErrorMessage = $("<div>").addClass("movieError").text("상태를 입력하세요.");
            $("#state").after(stateErrorMessage);
        }

        var language = $("#language").val();
        if (!language) {
            var languageErrorMessage = $("<div>").addClass("movieError").text("언어를 입력하세요.");
            $("#language").after(languageErrorMessage);
        }
        
        if($(".movieError").length === 0){            
            this.submit();
        }

    });
});