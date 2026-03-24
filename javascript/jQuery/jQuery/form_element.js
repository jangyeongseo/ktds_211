// 변수
// var runMode = "vanilla";
var runMode = "jquery";

// JQuery
$().ready(function () {
    if (runMode === "jquery") {
        // event ~~
        // vanilla => input, select의 value를 get 하려면 element.value;
        // vanilla => input, select의 value를 set 하려면 element.value = 값;
        // JQuery => input, select의 value를 get 하려면 element.value();
        // JQuery => input, select의 value를 set 하려면 element.value(값);

        $("#checked-all").on("change", function () {
            $("input[type='checkbox'][name='favorate-genre']")
                .prop("checked", $(this).prop("checked"));
        });

        $("input[type='checkbox'][name='favorate-genre']").on("change", function () {
            // 체크박스의 개수 세기
            var checkbox = $("input[type='checkbox'][name='favorate-genre']");
            var checkboxCount = checkbox.length;

            // 체크한 체크박스의 개수 세기
            // $("input[type='checkbox'][name='favorate-genre']:checked");
            var checkedCount = checkbox.filter(":checked").length;
            $("#checked-all").prop("checked", checkboxCount === checkedCount);

        })

        // ===============================================================================
        // radio
        $("input[type='radio'][name='age']").on("change", function () {
            var value = $(this).val();
            console.log(value);
        })

        // select
        $("#jobs").on("change", function () {
            var value = $(this).val();
            // var select = $(this).selectedIndex().text();
            var select1 = $(this).children("option:selected").text();
            var select2 = $(this).children("option").filter(":selected").text();
            console.log(value, select1, select2);
        });

        // 이메일
        var emailValue = $("#email").val();
        console.log(emailValue);
    }
})


// vanilla
window.onload = function () {
    if (runMode === "vanilla") {
        // event ~~
        //  모든 form tag 고통
        var email = document.querySelector("#email").value;
        console.log(email);
        document.querySelector("#email").value = "text@gmail.com";

        var jobs = document.querySelector("#jobs").value;
        console.log(jobs); // value 가 없으면 텍스트의 값("선택하세요.")이 나온다.

        // select 태그에서 option의 value가 3인것을 선택
        document.querySelector("#jobs").value = "3";

        // select 태그에서 option의 value가 999인것을 선택 => 결과 : 아무것도 안나옴
        // // select 태그에서 option을 변경했을 때 해당값을 출력
        document.querySelector("#jobs").addEventListener("change", function () {
            this.value; // this는 바꾼 값 
            console.log(this.value);
            console.log(this.options[this.selectedIndex].innerText);
            console.log(this.querySelector("option[value = '" + this.value + "']").innerText);
        });

        // radio event (click) -> radio를 클릭 할때
        var radios = document.querySelectorAll("input[type='radio'][name='age']");
        // for (var i = 0; i < radios.length; i++) {
        //     radios[i].addEventListener("click", function () {
        //          클릭한 radio의 선택 상태를 콘솔에 출력
        //         console.log(this.value, this.checked);
        //     })
        // }

        //  radio event (click) -> radio가 선택될 때
        // 불필요한 이벤트를 줄이고자 change를 사용하는 것이 좋다.
        for (var i = 0; i < radios.length; i++) {
            radios[i].addEventListener("change", function () {
                // 선택상태가 변경된 radio의 선택 상태를 콘솔에 출력.
                console.log(this.value, this.checked);
            })
        }

        var checkboxs = document.querySelectorAll("input[type='checkbox'][name='favorate-genre']");
        var checkedAll = document.querySelector("#checked-all");

        // 전체선택을 클릭할 경우 모든 checkbox가 선택되도록
        checkedAll.addEventListener("change", function () {
            for (var i = 0; i < checkboxs.length; i++) {
                checkboxs[i].checked = this.checked;
            }
        })

        for (var j in checkboxs) {
            // !isNaN(j) : j가 숫자라면
            if (!isNaN(j)) {
                // console.log(j, checkboxs[j]);
                checkboxs[j].addEventListener("change", function () {
                    // 체크박스의 선택 상태가 변경될 때 마다 체크된 체크박스의 개수를 조회하여 출력
                    // checkboxs 다시 한 번 반복
                    var checkedCount = 0;
                    for (var index = 0; index < checkboxs.length; index++) {
                        if (checkboxs[index].checked) {
                            checkedCount++;
                        }
                    }
                    console.log(checkedCount, "개의 체크박스가 선택됨.");
                    // 체그 개수가 4개면 전체선택 채크상태로 변경되도록 
                    checkedAll.checked = checkboxs.length === checkedCount;

                    // 체크가 된 것만 출력을 한다. (vcalue 값만)
                    if (this.checked) {
                        console.log(this.value, this.checked);
                    }
                })
            }
        }

        // for (var j = 0; j < checkboxs.length; j++) {
        //     checkboxs[j].addEventListener("change", function () {
        //         var checkedCount = 0;
        //         for (var index = 0; index < checkboxs.length; index++) {
        //             if (checkboxs[index].checked) {
        //                 checkedCount++;
        //             }
        //         }
        //         console.log(checkedCount, "개의 체크박스가 선택됨.");
        //         if (this.checked) {
        //             console.log(this.value, this.checked);
        //         }
        //     });
        // }

    }
}