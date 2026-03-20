// window.onloaddp eodmdehlsms zhem
// window.jQuery로 시작하는 것이다.
// $(document).ready(function () {
//     alert("렌더링 준비끝!")
// });

$().ready(function () {
    $("h1").text("어디로 가고 싶으세요?");

    // p태그의 내용을 가져와서 alert으로 출력
    alert($("p").text());

    // p태그의 내용을 다음 여행을 계획해보세요. 라고 변경
    $("p").text("다음 여행을 계획해보세요.");
});

// window.onload = function () {
//     var h1 = document.querySelector("h1");
//     console.log(h1.innerText);

//     h1.innerText = "어디로 가고 싶으세요?";
// }