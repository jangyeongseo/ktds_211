window.onload = function () {

    // 
    function addAll() {
        var sum = 0;

        // arguments 배열 ==> 반복하면서 모든 겂을 더한다.
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
    console.log(result); //  NaN


}