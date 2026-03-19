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

    function printSumResult(from, to, endFunction) {
        // setTimeout(함수, 지연시간)
        // 비동기 - 콜백
        setTimeout(function () {
            console.log("3초 뒤 실행");
            var sum = 0;
            for (var i = from; i <= to; i++) {
                sum += i;
            }
            console.log(sum);

            endFunction(sum);

        }, 3000)
    }

    printSumResult(1, 1_000_000_000, function (sum) {
        if (alert("결과가 정상적으로 나왔습니다. 결과를 보시겠습니까?")) {
            alert(sum);
        }
        else {
            c
        }
    });

    printSumResult(1, 1_000_000_000, function (sum) {
        alert("결과 : " + sum);
    });



};