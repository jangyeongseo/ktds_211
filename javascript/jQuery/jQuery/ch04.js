$().ready(function () {
    //    contact를 클릭하면 이전 형제태그의 자식 중 "package-deal-comment" 태그의 내용을 출력
    $(".contact").on("click", function () {
        console.log($(this)
            .prev()
            .find(".package-deal-comment")
            .text()
        );
    })
})