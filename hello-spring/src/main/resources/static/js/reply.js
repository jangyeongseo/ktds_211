$().ready(function () {

    var refreshReplies = function () {
        var articleId = $(".reply-save").data("article-id");

        fetch("/api/replies/" + articleId)
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                console.log(json);

                var count = json.count;
                $(".replies-count").children(".count").text(count);

                var replies = json.replies;

                for (var i = 0; i < replies.length; i++) {
                    var reply = replies[i];

                    // template 가져오기
                    var replyTemplate = $(".reply-item-template").html();

                    replyTemplate = replyTemplate
                        .replace("#replyId#", reply.id)
                        .replace("#name#", reply.memberVO.name)
                        .replace("#email#", reply.email)
                        .replace("#createDate#", reply.crtDt)
                        .replace("#modifyDate#", reply.mdfyDt)
                        .replace("#content#", reply.reply);
                        
                    //TODO 로그인 한 회원이 작성한 댓글 인 경우
                    //  추천하기 - 노출 x
                    // TODO 로그인 한 회원이 작성하지 않은 댓글 인 경우
                    //  수정과 삭제 - 노출 x
                    // TODO 첨부파일이 있을 경우 첨부파일 목록 보여주기
                    // TODO 수정날짜가 없을 때는 수정날짜 노출 x
                    // TODO 추천 수 노출
                    // TODO 게시글 추천, 수정, 삭제
                    

                    var replyDom = $(replyTemplate);
                    replyDom.css({ "margin-left": (reply.level - 1) * 32 + "px" });
                    replyDom.find(".links-write").on("click", function () {
                        var replyId = $(this).closest(".reply-item").data("reply-id");
                        console.log("click! -" + replyId);

                        $(".reply-form").children(".parent-reply-id").val(replyId);
                        $(".replies-count").focus();
                    });
                    $(".replies").append(replyDom);

                }
            });
    };

    // 최초 로딩 시 댓글 가져오기
    refreshReplies();

    $(".reply-save").on("click", function () {

        var articleId = $(this).data("article-id");
        var parentReplyId = $(".parent-reply-id").val();
        var replyContent = $(".reply-content").val();
        var fileInput = $(".reply-attach-file")[0];
        console.log(fileInput.files[0]); // 파일의 리스트가 나온다.

        var formData = new FormData();
        formData.append("reply", replyContent);
        formData.append("articleId", articleId);
        formData.append("parentReplyId", parentReplyId);


        if (fileInput.files.length > 0) {
            formData.append("attachFile", fileInput.files[0]);
        }

        fetch("/api/replies-with-file", {
            method: "POST",
            body: formData // Content-Type 제거해야 함
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                console.log("등록 완료:", json);

                // 댓글 등록하기 후 처리
                $(".reply-form").children(".parent-reply-id").val("");
                $(".reply-content").val(""); // 입력창 초기화
                $(".replies").html("");

                refreshReplies(); // 다시 조회
            });

        console.log(replyContent, articleId, parentReplyId);
    });

});