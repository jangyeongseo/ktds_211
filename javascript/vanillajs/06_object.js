// String 객체에 contains라는 기능을 추가한다.
String.prototype.contains = function (findText) {
    console.log(findText); // a라는 글자가 출력됨.
    console.log(this); // contains를 호출한 객체 - text

    return this.indexOf(findText) >= 0;
}

// tempObject에 print 라는 기능 추가
Object.prototype.print = function () {
    console.log("객체의 내용", this);
}

var text = "sdhjsk";
text.print();

var tempObject = {};
tempObject.print();
console.dir(tempObject); // 구조 출력

window.onload = function () {
    // 객체 구조 (상속)
    var text = "abcdefg abcdefg";
    // string의 기능이 뭐가 있나? ==> String
    console.dir(String);

    var contain = text.contains("a");
    // "Uncaugh Error : text.contains is not a function at window.onload" 
    console.log(contain);


    // 일반 적으로 사용하는 방법
    var list = document.querySelector(".list");
    var listItem = [
        { tagName: "li", text: "first", class: "list-item" },
        { tagName: "li", text: "second", class: "list-item" },
        { tagName: "li", text: "third", class: "list-item" }
    ];

    for (var i = 0; i < listItem.length; i++) {
        var item = listItem[i];

        var eachItem = document.createElement(item.tagName);
        eachItem.className = item.class;
        eachItem.innerText = item.text;

        list.appendChild(eachItem);
    }

    // 객체를 활용하여 원한느 곳에 값을 집어 넣을 수 있다.
    function calc(param) {
        // 앞에 값이 undefined라면 0으로 처리해라
        return (param.n1 || 0) +
            (param.n2 || 0) +
            (param.n3 || 0);
    }
    var result = calc({ n1: 10, n3: 50 });
    console.log(result);

    function getObject() {
        // 객체를 반환시켜라
        return {
            price: 13484,
            name: "163851",
            model: "21684asf",
            fan: 8,
            chain: ["GS", "CJ", "hanjin"],
            address: {
                city: "seoul",
                state: "guro"
            }
        };
    }
    var a = getObject();
    console.log(a.chain);

    // 객체 생성
    var headphone = {
        serial_number: "이거 가능?",
        "serial-number": "된다",
        modelName: "XM-5",
        manufacture: "Sony",
        type: "over-ear",
        power: false,
        powerOn: function () {
            // 함수 안에서 모델 내용의 변수가 있어야 가능하다 -> this
            console.log(this.modelName, "이 켜집니다.");
            this.power = true;
        },
        powerOff: function () {
            console.log(this.modelName, "이 꺼집니다.");
            this.power = false;
        }
    };
    console.log(headphone, typeof headphone);

    console.log(headphone["serial-number"]);
    // 특수문자를 활용할때 이렇게 작서해야 나온다.

    console.log(headphone.serial_number);
    // 대체로 사용하는 방법

    headphone.powerOn();
    console.log(headphone.power);

    headphone.powerOff();
    console.log(headphone.power);

}