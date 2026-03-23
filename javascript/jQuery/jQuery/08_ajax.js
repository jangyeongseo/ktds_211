// https://jsonplaceholder.typicode.com/comments
$().ready(function () {
    $(".load-comments").on("click", function () {
        $(".spinner").show();
        // 비동기로 https://jsonplaceholder.typicode.com/comments
        // window.fetch(url); => Promise를 반환 (서버에서 아직 데이터가 전달되지 않은 상태)
        var fetchProm = fetch("https://jsonplaceholder.typicode.com/comments");
        // Pending -> 서버에게 HTTP 요청을 진행중인 상태.
        // fullfilled -> 서버가 fetch에게 HTTP 요청에 대한 값을 반환 완료한 상태.
        // -> 서버로부터 정상적으로 반환되었다. ==> fetchProm.then(); 처리
        // -> 서버로부터 에러가 반환되었다. ==> fetchProm.catch(); 처리

        // 서버로 보낸 HTTP 요청이 서버로부터 정상적으로 처리되어 값을 반환시킨 경우.
        // 서버가 반환시킨값을 console로 출력해본다.
        // jsonRestonse : 서버가 fetch에게 반환시킨 값.
        fetchProm.then(function (jsonRestonse) {
            // jsonRestonse.json() => 비동기 처리(Promise가 반환되었으므로.)
            // console.log(jsonRestonse.json().then);
            return jsonRestonse.json();
        })
            //  return jsonRestonse.json(); 이것에 대한 처리를 해준다.
            // body : jsonRestonse.json();이 반환시킨 값
            .then(function (body) {
                // body -> [{post, id, name, email.body}{}{}]
                console.log(body);
                for (var i = 0; i < body.length; i++) {
                    var comment = body[1];
                    var bodyComment = comment.body;

                    var li = $("<li>").text(bodyComment);
                    $(".comments").append(li);
                }
                $(".spinner").hide();
            });

        // 일정 주기로 함수를 실행하는 함수.
        // setInterval(function(){}, 1000); -> 1초 주기로 함수를 실행.
        // setInterval(function () {
        //     console.log(fetchProm);
        // }, 100);
    })
})


// window.onload = function () {

//     var button = this.document.querySelector(".call-promise");
//     button.addEventListener("click", function () {
//         var delay = parseInt(Math.random() * 100);
//         // 비동기
//         // setTimeout(function () {
//         //     // 예외처리
//         //     try {

//         //         if (delay % 2 === 0) {
//         //             throw new Error("에러가 발생했습니다");
//         //         }
//         //         alert(delay);
//         //     } catch (e) {
//         //         console.log(e.message, delay);
//         //     }
//         // }, delay);

//         //  new Promise(function(성공했을 때, 실패 했을 때)
//         new Promise(function (resolve, reject) {
//             // 비동기 코드 실행
//             setTimeout(function () {
//                 if (delay % 2 === 0) {
//                     reject(delay);
//                 } else {
//                     resolve(delay);
//                 }
//             }, delay);
//         }).then(
//             function (delay) {
//                 // resolve
//                 alert(delay);
//                 setTimeout(function () {
//                     return new Promise(fn1, fn2);
//                 }, 100);

//             }).catch(
//                 function (delay) {
//                     //reject
//                     console.log("에러 발생", delay);
//                 });
//     });
// }