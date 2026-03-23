$().ready(function () {
    var count = 0;

    // 아이템 추가
    $(".btn-add").on("click", function () {
        // 언디파인드(undefined) 적용 방식
        if (count >= 10) {
            alert("더 이상 추가할 수 없습니다.");
            return;
        }

        count++;
        $(".main-list").append(`<li>아이템 ${count}</li>`);
        updateCount();
    });

    // 모든 아이템 제거
    $(".btn-del").on("click", function () {
        if (count === 0) {
            alert("이미 삭제된 상태입니다.");
            return;
        }

        $(".main-list").empty();
        count = 0;
        updateCount();
    });

    // 개수 업데이트 함수
    function updateCount() {
        // var total = $(".main-list li").length;
        // $(".allList").text(`총 ${total}개의 아이템이 등록되었습니다.`);
        
        $(".allList").text("총 " + count + "개의 아이템이 등록되었습니다.");
    }

    updateCount();
});