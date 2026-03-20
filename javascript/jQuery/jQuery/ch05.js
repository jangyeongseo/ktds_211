$().ready(function () {
    $(".package-green-button").on("click", function () {
        var price = $(this).closest(".package").data("price");
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