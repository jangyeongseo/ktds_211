$().ready(function () {
    // 새로운 p 태그를 만든다. 내용은 after라고 한다.
    // 새로운 p 태그는 wrapper 바깥 아래쪽에 위치
    var newP1 = $("<p>");
    newP1.text("after");

    $(".wrapper").after(newP1);

    // 새로운 p 태그를 만든다. 내용은 before라고 한다.
    // 새로운 p 태그는 wrapper 바깥 위쪽에 위치

    var newP2 = $("<p>");
    newP2.text("before");

    $(".wrapper").before(newP2);


    // 새로운 p 태그를 만든다. 내용은 append라고 한다.
    // 새로운 p 태그는 wrapper 안쪽 아래쪽에 위치
    var newP3 = $("<p>");
    newP3.text("append");

    $(".wrapper").append(newP3);

    // 새로운 p 태그를 만든다. 내용은 prepend라고 한다.
    // 새로운 p 태그는 wrapper 안쪽 아래쪽에 위치
    var newP4 = $("<p>");
    newP4.text("prepend");

    $(".wrapper").prepend(newP4);

    // 새로운 div 태그를 만든다. 내용은 "newDiv"
    // 새로운 div 대그는 ".a" 바깥 아래쪽에 위치
    var newDiv = $("<div>").text("newDiv");
    $(".a").after(newDiv);

    // 새로운 div 태그를 만든다. 내용은 "newDiv2"
    // 새로운 div 대그는 ".c" 바깥 위쪽에 위치
    var newDiv2 = $("<div>").text("newDiv2");
    $(".c").before(newDiv2);

    // 새로운 span 태그를 만든다. 내용은 "newSpan"
    // 새로운 span 대그는 ".b" 안쪽 아레쪽에 위치
    var newSpan = $("<span>").text("newSpan");
    $(".b").append("<br/>").append(newSpan);

    // 안쪽 위
    var newSpan2 = $("<span>").text("newSpan2");
    $(".b").prepend("<br/>").prepend(newSpan2);

});