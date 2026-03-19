window.onload = function () {
    var array = [];

    var push = document.querySelector(".push");
    var pop = document.querySelector(".pop");
    var unshift = document.querySelector(".unshift");
    var shift = document.querySelector(".shift");

    push.addEventListener("click", () => {
        array.push(array.length + 1);
        console.log(array);
    });

    pop.addEventListener("click", () => {
        var value = array.pop();
        console.log(value, array);
    });

    unshift.addEventListener("click", () => {
        array.unshift(array.length + 1);
        console.log(array);
    });

    shift.addEventListener("click", () => {
        var value = array.shift();
        console.log(value, array);
    });


    function getCalcNumbers(numberOne, numberTwo) {
        var result = numberOne + numberTwo;
        return result;
    }

    var result = getCalcNumbers(10);
    console.log(result);
    // undefined가 나오면 NaN 오류 메시지를 줌

}