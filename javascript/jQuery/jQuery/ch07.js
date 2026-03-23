$().ready(function () {
    $("#package-ticket-count").on("keydown", function (event) {
        console.log("keydown", event.key, typeof event.key);
        console.log("keydown", event.key, parseInt(event.key));
    })

    // 입력한 값을 받을 경우: keyup
    $("#package-ticket-count").on("keyup", function () {
        var price = $(this).closest(".package").data("price");
        // val : input의 값을 가져오는 것
        var count = $(this).val();
        var amount = price * count;
        $("#amount").text(amount);
        console.log(amount);

    }).on("change", function () {
        // 체크 상태 바뀌는 순간 바로 발생
        console.log("change");
        $(this).trigger("keyup");
        // 클릭한 것처럼 동작을 해라

        // keyup → 실시간 입력 대응
        // change → 포커스 빠질 때 대응
    })
});