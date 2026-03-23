$().ready(function () {
    // $(".mouse-over-event-exam").css({
    //     padding: "10px",
    //     color: "white",
    //     listStyle: "none"
    // });

    // 정말 필요할 때만 사용하기
    // $(".mouse-over-event-exam").on("mouseover", function () {
    //     var listItem = $("<li>").text($(this).children("ul").children("li").length + 1);
    //     $(this).children("ul").append(listItem);
    // })

    // $(".mouse-over-event-exam").on("mouseenter", function () {
    //     var listItem = $("<li>").text($(this).children("ul").children("li").length + 1);
    //     $(this).children("ul").append(listItem);
    // });

    // $(".mouse-over-event-exam").on("mouseout", function () {
    //     $(this).children("ul").children("li").last().remove();
    // });

    // $(".mouse-over-event-exam").on("mouseleave", function () {
    //     $(this).children("ul").children("li").last().remove();
    // });

    // 이런 형식도 가능하다.
    $(".mouse-over-event-exam").css({
        padding: "10px",
        color: "white"

    }).on("mouseenter", function () {
        var listItem = $("<li>").text($(this).children("ul").children("li").length + 1);
        $(this).children("ul").append(listItem);
    }).on("mouseleave", function () {
        $(this).children("ul").children("li").last().remove();
    });

    // 이벤트
    $(".vacation-title").children("img").on("mouseenter", function () {
        $(".ticket").show();
        // show - display: block;
    }).on("mouseleave", function () {
        $(".ticket").hide();
        // hide - display:
    });

});