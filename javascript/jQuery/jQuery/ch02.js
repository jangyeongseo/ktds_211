$().ready(function () {
    $("li").text("서울");

    // 클래스가 promo 인 것의 텍스으를 부산으로 변경
    $(".promo").text("부산");

    // 아이디가 destimations인 태그의 자식 요소 중 
    // 두번째 li의 텍스트를 경주로 변경 - li:ntn-child(2){}
    $("#destinations > li:eq(1)").text("경주");
    // $("#destinations li:eq(1)").text("경주"); 이것도 가능

});



// window.onload = function () {
//     // 모든 li 태그를 가져와서 내용을 서울로 변경
//     // querySelector : 첫번째 데이터만 가져와라
//     // querySelectorAll : 배열이 다 [li, li, li.promo]
//     var listItem = document.querySelectorAll("li");

//     for (var i = 0; i < listItem.length; i++) {
//         listItem[i].innerText = "서울";
//     }

// }