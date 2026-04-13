$().ready(function () {
    $(".pagination").find("a").on("click", function(){
        var pageNo = $(this).data("page-no");
        var listSize = $("#list-size").val();
        var searchType = $("#search-type").val();
        var searchKeyWord = $("#search-keyword").val();

        location.href = "/?pageNo=" + pageNo +
            "&listSize=" + listSize +
            "&searchType=" + searchType +
            "&searchKeyWord=" + searchKeyWord;
    })
    
    
    $("#list-size").on("change", function () {
        // location.href = "/?pageNo=0&listSize=" + $(this).val();
        $(".search-button").trigger("click");
    })

    $(".search-button").on("click", function () {
        // /?pageNo=0&listSize=#listSiz값&searchType=#search-type값&searchKeyWord=#search-keyword값
        var pageNo = 0;
        var listSize = $("#list-size").val();
        var searchType = $("#search-type").val();
        var searchKeyWord = $("#search-keyword").val();

        location.href = "/?pageNo=" + pageNo +
            "&listSize=" + listSize +
            "&searchType=" + searchType +
            "&searchKeyWord=" + searchKeyWord;
    })



    // 유효성 검사
    $("#writeVO").on("submit", function (event) {
        event.preventDefault();

        console.log("submit 실행됨");

        $(this).find(".validation-error").remove();

        var subject = $("#subject").val();
        if (!subject || subject.length < 3) {
            $("#subject").after("<div class='validation-error'>제목을 3글자 이상 입력하세요</div>");
        }

        var content = $("#content").val();
        if (!content) {
            $("#content").after("<div class='validation-error'>내용을 입력하세요</div>");
        }

        this.submit();
    });

    // ".add-files" 을 클릭하면
    // 새로운 파일 인푸과 버튼을 
    // ".attach-files"아래에 추가한다.
    // $(".add-files").on("click", function () {
    $(".attach-files").on("click", ".add-files", function () {
        // 새로운 파일이 추가될 때 마다 기존의 "add-file" 버튼을 "del-files" 로 변경하고
        // 텍스트는 "-"로 변경한다.
        $(this).closest(".attach-files").children(".add-files")
            .removeClass("add-files")
            .addClass("del-files")
            .text("-")
            .off("click")
            .on("click", function () {
                // 버튼 왼쪽에 있는 인풋 태그 삭제
                $(this).prev().remove();

                // 버튼도 삭제
                $(this).remove();

            });// 새로운 이벤트를 추가한다.

        var fileInput = $("<input />").attr({
            type: "file",
            name: "attachFiles"
        });
        var addButton = $("<button />").attr("type", "button").text("+").addClass("add-files");

        $(".attach-files").append(fileInput).append(addButton);

    });

});
