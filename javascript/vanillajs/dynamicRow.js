window.onload = function () {
    var button = document.querySelector(".add-row-button");
    var inputelement = document.querySelector("input[type='text']");
    var list = document.querySelector(".list");
    var listItem = document.createElement("li[class='list-item']");

    button.addEventListener("click", function () {
        var value = inputelement.value;
        listItem.innerText = value;

        // 더블클릭 시 삭제
        listItem.addEventListener("dblclick", function (event) {
            list.removeChild(event.target);
        })

        inputelement.value = "";
        list.appendChild(listItem);
    })

}