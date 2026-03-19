window.onload = function () {
    var number = 10;
    console.log("변수의 값은", number, "변수의 타입은", typeof number);

    number = "안녕";
    console.log("변수의 값은", number, "변수의 타입은", typeof number);

    var x;
    console.log("변수의 값은", x, "변수의 타입은", typeof x);
    // undefined : 변수는 있지만 값이 안들어가 있는 상태

    var x2 = null;
    console.log("변수의 값은", x2, "변수의 타입은", typeof x2);
    // undefined와 null은 다르다.

    x3 = 213.156131315315;
    // window.x3 변수를 만든것이다.
    console.log("변수의 값은", x3, "변수의 타입은", typeof x3);

    console.log(window);

}