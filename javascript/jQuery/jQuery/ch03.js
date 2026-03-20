$().ready(function () {
    // 클릭시 france의 li 태그의 문구가 나오도록
    // var destinations = document.querySelectorAll("#destinations");
    // console.log(destinations);
    // var listItem = destinations.children;
    // $("#destinations").children("li").on("click", function () {
    $("#destinations").on("click", "li", function () {
        console.log("클릭한 태그의 내용", $(this).text());
        // function을 호출한 엘리먼트 : 클릭이벤트가 일어난 엘리면트
        // console.log("클릭한 태그의 내용", event.target.innerText);

        console.log("클릭한 태그의 내용", $(this).prev().text()); // 이전
        console.log("클릭한 태그의 내용", $(this).next().text()); // 다음
        console.log("클릭한 태그의 내용", $(this).parent().text()); // 부몰
    });
});

// window.onload = function () {
//     var destinations = document.querySelectorAll("#destinations > li");
//      -> 위에 문장을 더 빠르게 찾을 수 있도록 밑에 처럼 변경
//     var destinations = document.querySelectorAll("#destinations");
//     console.log(destinations);
//     var listItem = destinations.children;

//     for (var i = 0; i < listItem.length; i++) {
//         listItem[i].addEventListener("click", function (event) {
//             console.log(event.target);
//             console.log("클릭한 태그의 내용", event.target.innerText);
//             console.log("클릭한 태그 이전 태그의 내용", event.target.previousElementSibling.innerText);
//             console.log("클릭한 태그 이후 태그의 내용", event.target.nextElementSibling.innerText);
//             console.log("클릭한 태그 부모 태그의 내용", event.target.parentElement.innerText);
//         });
//     };
// }