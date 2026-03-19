window.onload = function () {
    var randomnumber = parseInt(Math.random() * 10);
    // parseInt : int 값을 정수로 바꿔서 나온다.
    console.log(randomnumber);

    // 난수가 0이라면 연산할 수 없는 숫자 입니다. 라고 보여줘라
    // 난수가 0보다 크다면 0보다 큰 값입니다.
    var number = parseInt(Math.random() * 10);

    if (number) {
        console.log("0보다 큰 값입니다.");
    } else {
        console.log("연산할 수 없는 숫자입니다.");
    }
    // 값이 있는지 없는지로 확인함
    // ture, false로 판단함.

    var name = " ";
    if (name) {
        console.log("name의 값이 있습니다.");
    } else {
        console.log("name의 값이 없습니다.");
    }
    // 뛰어쓰기가 있으면 값이 있는것

    var age;
    if (age) {
        console.log("age의 값이 있습니다.")
    } else {
        console.log("age의 값이 없습니다.")
    }

    var address = null;
    if (address) {
        console.log("address의 값이 있습니다.");
    } else {
        console.log("address의 값이 없습니다.");
    }

    var arr = [];
    if (arr) {
        console.log(arr, "arr의 값이 있습니다");
    } else {
        console.log("arr의 값이 없습니다.");
    }


    // 같다 비교
    // javaScript의 값 동등비교 ==. ===
    console.log(1 == 1); // true
    console.log(1 == 1.0); // true
    console.log("1" == 1.0); // true
    console.log("a" == "a"); // false

    console.log("===============");

    console.log(1 === 1); // true
    console.log(1 === 1.0); // true
    console.log("1" === 1.0); // false
    console.log("a" === "a"); // true

    console.log("1" != 1); //false
    console.log("1" !== 1); //true
    console.log(1 != 1); // false


}