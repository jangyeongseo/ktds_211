// window.addEventListener("load", function () { })
// 콜백
// 언제 시작할지 언제 종료될지를 알고 싶을 때 콜백을 활용해라

window.onload = function () {
    function addAll() {
        var sum = 0;

        // arguments 배열 ==> 반복하면서 모든 값을 더한다.
        for (var i = 0; i < arguments.length; i++) {
            sum += arguments[i];
        }
        return sum;
    }

    var allResult = addAll(1, 2, 3, 4, 6);
    console.log(allResult);

    function calc(num1, num2) {
        console.log(arguments);
        // 읽기 전용 배열 = arguments

        // NaN 방지 (값 없을 때 0 처리)
        // num1 = num1 || 0;
        // num2 = num2 || 0;

        return num1 + num2;
    }

    var result = calc(10, 30, 100);
    console.log(result); // 40

    result = calc(10, 30, 100);
    console.log(result); // 40

    result = calc(10, 30, 100, 1000);
    console.log(result); // 40

    result = calc(10);
    console.log(result); // NaN

    result = calc();
    console.log(result); // NaN

    // 2026.03.19 0 함수 중첩 함수
    function printCalcResult(num1, num2, operator) {
        var result; // 한 번만 선언

        if (operator === "+") {
            result = getAddResult(num1, num2);
            console.log(result);

        } else if (operator === "-") {
            result = getSubtractResult(num1, num2);
            console.log(result);

        } else if (operator === "/") {
            result = getDivideResult(num1, num2);
            console.log(result);

        } else if (operator === "*") {
            result = getMulticateResult(num1, num2);
            console.log(result);
        }

        function getAddResult(num1, num2) {
            return num1 + num2;
        }

        function getSubtractResult(num1, num2) {
            return num1 - num2;
        }

        function getDivideResult(num1, num2) {
            return num1 / num2;
        }

        function getMulticateResult(num1, num2) {
            return num1 * num2;
        }

        return result;
    }

    printCalcResult(10, 20, "+");
    printCalcResult(10, 20, "-");
    printCalcResult(10, 20, "/");
    printCalcResult(10, 20, "*");

    // 내부 함수는 밖에서 못 씀 → 제거 or 외부로 빼야 함
    // getAddResult(100, 200);
    // getDivideResult(10, 10);

    // callback
    // 주로 비동기 또는 이벤트 핸들링에서 사용하는 함수의 표현식.
    // 비동기의 특징
    // 1. 어떤 함수가 시작하는 시간 또는 지점, 어떤 함수가 종료되는 시간 또는 지점이 명확하지 않은 코드의 형태
    // 2. 정상적인 코드 흐름에서 분리되는 형태(모든 코드는 위에서 아래로 실행. 하나의 명령이 종료되어야 다음 명령이 실행된다.)
    //   -> 하나의 명령이 종료되지 않은 상태에서 다름 코드가 실행된다.

    // 함수를 변수에 할당
    var printMessage = function (message) {
        console.log(message);
    }

    // 변수의 이름 - 함수의 이름
    printMessage("dkdkdkdkk");
    console.log(printMessage, typeof printMessage);
    // => 이런 식을 '함수 표현식'이라고 부른다.
    // 함수 표현식을 이용해서 콜백을 가져옴.
    // var - 베리어블

    // function printSumResult(from, to, endFunction) {
    //     // setTimeout(함수, 지연시간)
    //     // 비동기 - 콜백
    //     setTimeout(function () {
    //         console.log("3초 뒤 실행");
    //         var sum = 0;
    //         for (var i = from; i <= to; i++) {
    //             sum += i;
    //         }
    //         console.log(sum);

    //         endFunction(sum);

    //     }, 3000)
    // }

    // printSumResult(1, 1_000_000_000, function (sum) {
    //     if (alert("결과가 정상적으로 나왔습니다. 결과를 보시겠습니까?")) {
    //         alert(sum);
    //     }
    // });

    // printSumResult(1, 1_000_000_000, function (sum) {
    //     alert("결과 : " + sum);
    // });





    // 즉시 실행함수란? -> 함수를 생성하자마자 스스로를 실행시키는 함수.
    // (function (number) {
    //     console.log("즉시 실행함수 입니다.", number);
    // })(100)

    var list = document.querySelector(".list");
    console.log(list); // 값 출력
    console.dir(list); // 값의 구조 출력

    console.log(list.dataset.count);
    var dataCount = parseInt(list.dataset.count);

    // .list에 dataCount의 수만큼 li 태그를 생성한다.
    for (var i = 0; i < dataCount; i++) {
        // li 태그를 생성하고 내부 텍스트는 i+1한값을 셋팅.
        var eachItem = document.createElement("li");
        eachItem.innerText = i + 1;

        // 무엇을 클릭해도 11이 나오는 원인?
        eachItem.addEventListener("click", function () {
            alert(i + 1);
        });

        // 생성된 li 태그를 .list추가
        list.appendChild(eachItem);
    }

    //  1. event parameter 받아와서 출력하기.
    for (let i = 0; i < dataCount; i++) {
        var eachItem2 = document.createElement("li");
        eachItem2.innerText = i + 1;

        eachItem2.addEventListener("click", function () {
            alert(i + 1);
        });

        list.appendChild(eachItem2);
    }

    // 2. 즉시실행함수를 이용해서 출력하기.
    for (let j = 0; j < dataCount; j++) {

        var eachItem3 = document.createElement("li");
        eachItem3.innerText = j + 1;

        eachItem3.addEventListener("click", function () {
            alert(j + 1);
        });

        list.appendChild(eachItem3);
    }

    var aaa = 1; // 전역 변수 (어디서든 접근 가능)
    function add() {
        // var bbb = 2; // 지역 변수 (add 함수 내부에서만 사용 가능)

        aaa++; // 전역 변수 aaa를 1 증가
        console.log(aaa); // 2 출력
    }
    add(); // 함수 실행 → aaa가 2가 됨
    console.log(aaa); // 2 출력 (전역 변수라 접근 가능)

    // console.log(bbb); // 에러 발생
    // bbb는 함수 내부 변수라서 밖에서 접근 불가 (ReferenceError)

    function print1() {
        var num1 = 10; // 함수 전체에서 사용 가능한 변수

        if (num1 > 0) {
            var num2 = 100; // var는 if 블록이 아니라 함수 전체에서 사용됨
            console.log(num1); // 10
            console.log(num2); // 100
        }

        console.log(num1); // 10
        console.log(num2); // 100 (밖에서도 접근 가능)
    }
    print1();

    function print2() {
        var num1 = -10; // 함수 전체에서 사용 가능

        if (num1 > 0) {
            var num2 = 100; // 선언은 "호이스팅"됨 (위로 끌어올려짐)
            console.log(num1);
            console.log(num2);
        }

        console.log(num1); // -10
        console.log(num2); // undefined (에러 아님!)
    }
    print2();















};