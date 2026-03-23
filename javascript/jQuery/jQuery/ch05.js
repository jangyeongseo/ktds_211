$().ready(function () {
    // event를 할당할 수 없다.
    // 객체화가 끝난 이후에 시작하는데 p는 단 한줄도 없어서 동작할 수 없다.
    // $(".package-button-area").find("p").on("click", function () {
    //     alert($(this).text());
    // })


    // 처음부터 존재했던 .package-button-area dom을 통해서
    // 새롭게 생성된 p.white-color 에게 click 이벤트를 할당한다.
    $(".package-button-area").on("click", "p.white-color", function () {
        alert($(this).text());
    })

    //  $(".package-green-button").on("click", "p", function () {
    // 원래부터 존재하던 DOM에게 접근하여 p태그에 접근하겠다
    $(".package-green-button").on("click", function () {
        var price = $(this).closest(".package").data("price");

        // 이렇게 작성하는 것은 동적으로 추가가된다.
        // 이 방법을 더 많이 씀 : 추적을 할 수 있기 편하기 때문
        // var newP = $("<p>").text("From" + " $" + price).on("click", function () {
        //     alert($(this).text());
        // });
        var newP = $("<p>").text("From" + " $" + price);
        $(this).after(newP);
        $(this).remove();

        // 새롭게 만든 p 태그에게 inline Style을 부여
        // newP.css({ color: "white" });

        // 새롭게 만든 p 태그에게 white-color를 부여
        newP.addClass("white-color");
        newP.removeClass("white-color");
    })
});