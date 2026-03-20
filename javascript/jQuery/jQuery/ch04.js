$().ready(function () {
    $(".package-green-button").on("click", function () {
        var newP = $('<p> From $399.99 <p/>')
        $(".package-green-button").after(newP);
        $(this).remove();

    })

    //  <p> From $399.99 <p/>태그를 button 바깥 아래쫏에 배치
    // var newP = $('<p> From $399.99 <p/>')
    // $(".package-green-button").after(newP);

    // remove는 브라우저 메모리에서 해당 DOm을 완전 제거
    // $(".package-green-button").remove();

    // 브라우저 메모리는 삭제하지 않고 화면에서 제거.
    // $(".package-green-button").detach();


    // contact를 클릭하면 이전 형제태그의 자식 중 "package-deal-comment" 태그의 내용을 출력
    // find : .abc .item
    // children : .abc > .item
    $(".contact").on("click", function () {
        // each : 반복 - 글씨를 나눠서 나오도록
        $(this).prev().find(".package-deal-comment").each(function () {
            console.log($(this).text());
        })
        console.log($(this)
            .prev()
            .find(".package-deal-comment")
            .text()
        );
    })

});