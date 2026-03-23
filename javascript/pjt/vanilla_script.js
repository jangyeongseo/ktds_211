// window.onload = function () {
//     var button = document.querySelector(".btn-add");
//     var mainList = document.querySelector(".main-list");
//     var buttonDelete = document.querySelector(".btn-del");
//     var count = 0;

//     // 아이템 추가
//     button.addEventListener("click", function () {
//         if (mainList.children.length >= 10) {
//             alert("더 이상 추가할 수 없습니다.");
//             return;
//         }

//         var listItem = document.createElement("li");
//         listItem.innerText = "아이템 목록" + count++;
//         mainList.appendChild(listItem);
//         updateCount();
//     });

//     // 전체 삭제
//     buttonDelete.addEventListener("click", function () {
//         mainList.innerHTML = "";
//         count = 0;
//         updateCount();
//     });

//     function updateCount() {
//         var total = mainList.children.length;
//         document.querySelector(".allList").innerText = "총" + total + "개의 아이템이 등록되었습니다.";
//     }
// }