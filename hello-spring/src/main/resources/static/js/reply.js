$().ready(function () {

    var loginEmail = $(".member-info").data("email");

    var refreshReplies = function () {
        var articleId = $(".reply-save").data("article-id"); // 게시글 아이디

        fetch("/api/replies/" + articleId)
            .then(function (response) {
                return response.json();
            })
            .then(function (json) {
                console.log(json);

                var count = json.count;
                $(".replies-count").children(".count").text(count);

                var replies = json.result || [];
                for (var i = 0; i < replies.length; i++) {
                    var reply = replies[i];
                    console.log(replies[i]);

                    // template 가져오기
                    var replyTemplate = $(".reply-item-template").html();
                    replyTemplate = replyTemplate
                        .replace("#replyId#", reply.id)
                        .replace("#name#", reply.memberVO.name)
                        .replace("#email#", reply.email)
                        .replace("#createDate#", reply.crtDt)
                        .replace("#modifyDate#", reply.mdfyDt)
                        .replace("#recommendCount#", reply.recommendCnt)
                        .replace("#content#", reply.reply);

                    var replyDom = $(replyTemplate);

                    //로그인 한 회원이 작성한 댓글 인 경우
                    //  추천하기 - 노출 x
                    if (!loginEmail || loginEmail === reply.email) {
                        replyDom.find(".links-recommend").remove();
                    }

                    // 로그인 한 회원이 작성하지 않은 댓글 인 경우
                    //  수정과 삭제 - 노출 x
                    if (loginEmail !== reply.email) {
                        replyDom.find(".links-update").remove();
                        replyDom.find(".links-delete").remove();
                    }

                    // 첨부파일이 있을 경우 첨부파일 목록 보여주기
                    if (!reply.fileGroupId) {
                        replyDom.find(".reply-attach-files").remove();
                    } else {
                        replyDom
                            .find(".reply-attach-files")
                            .data("files", JSON.stringify(reply.files));
                        // 객체.배열(reply.files)이라 이것을 String으로 변경해서 넣어줘야 하기 때문에 json.stringify로 해서 넣는다.

                        // reply.files를 반복하면서 a 태그를 ".reply-attach-files"추가
                        for (var j = 0; j < reply.files.length; j++) {
                            var file = reply.files[j];
                            //var fileSize = file.fileLength; // bytes
                            //var capaType = "byte";
                            // if (fileSize > 1024) {
                            //     // kb
                            //     capaType = "kb";
                            //     fileSize = Math.ceil(fileSize / 1024);
                            // }
                            // if (fileSize > 1024) {
                            //     // mb
                            //     capaType = "mb";
                            //     fileSize = Math.ceil(fileSize / 1024);
                            // }
                            // var fileAnchor = $("<a>");
                            // fileAnchor.text(
                            //     file.displayName + " (" + fileSize + capaType + ")"
                            // );
                            // fileAnchor.attr({
                            //     href: "/file/" + file.fileGroupId + "/" + file.fileNum,
                            // });

                            var replyFiles = "<a href='/file/"
                                + file.fileGroupId + "/" + file.fileNum + "'>"
                                + file.displayName + "</a><br />";

                            replyDom.find(".reply-attach-files").append(replyFiles);
                        }
                    }

                    // 수정날짜가 없을 때는 수정날짜 노출 x
                    if (!reply.mdfyDt) {
                        replyDom.find(".modify-date").remove();
                    }

                    // 댓글 추천
                    // 추천하기를 클릭하면, 추천 수가 증가한다.
                    replyDom.find(".links-recommend").on("click", function () {
                        var replyId = $(this).closest(".reply-item").data("reply-id");
                        var targetReply = $(this);

                        // API 호출 (Endpoint => /api/replies/recommend/{댓글아이디})
                        // 호출 결과 ==> { replyId: "RP-20260410-000001": recommendCount: 13 }
                        fetch("/api/replies/recommend/" + replyId)
                            .then(function (result) {
                                return result.json();
                            })
                            .then(function (json) {
                                // ".recommend-count" 텍스트를 추천 결과 값으로 변경.
                                targetReply
                                    .closest(".reply-item")
                                    .find(".recommend-count")
                                    .text(json.recommendCount);
                            });
                    });

                    // 댓글 수정
                    // 수정을 클릭하면, 댓글을 수정할 수 있는 폼이 완성된다.
                    replyDom.find(".links-update").on("click", function () {
                        $(".update-form").remove();
                        $(this).children(".links").remove();

                        var replyAttachFiles = $(this)
                            .closest(".reply-item")
                            .find(".reply-attach-files")
                            .data("files");

                        // json이 객체로 바껴서 넣어진다. 파일들이
                        if (replyAttachFiles) {
                            replyAttachFiles = JSON.parse(replyAttachFiles);
                        }

                        // this : .links-update
                        // content라는 이름으로 댓글의 내용을 가져와라.
                        var content = $(this)
                            .closest(".reply-item")
                            .find(".content")
                            .text();

                        var updateTemplate = $(".reply-item-update-template").html();
                        var updateFormDom = $(updateTemplate);
                        updateFormDom.find("textarea").val(content); // 댓글내용이 들어간다.

                        // 취소 버튼
                        updateFormDom.find(".update-cancel").on("click", function () {
                            $(".update-form").remove();
                        });

                        // 수정 버튼
                        updateFormDom.find(".update-save").on("click", function () {
                            console.log("저장버튼 클릭");
                            // 수정을 위해 필요한 데이터
                            // 1. 수정하려는 댓글의 아이디
                            var replyId = $(this).closest(".reply-item").data("reply-id");

                            // 2. 수정하려는 댓글의 내용
                            var updateContent = $(this).closest(".update-form").find("template").val();

                            // 3. 삭제하려는 파일의 fileNum(여러 개)
                            var deleteFilesNum = $(this).closest(".update-form").find(".update-file-list").find("input[type='checkbox']:checked")

                            // 4. 추가하려는 파일(여러 개)
                            var newAttachFiles = $(this).closest(".update-form").find(".reply-update-attach-file")[0].files;

                            console.log(replyId, updateContent, deleteFilesNum, newAttachFiles);

                            var formData = new FormData();
                            formData.append("content", updateContent);
                            // 삭제할 파일이 있으면 formData 추가한다.
                            deleteFilesNum.each(function () {
                                formData.append("delfileNum", $(this).val());

                            }); // json은 반복문을 넣을 수 있다.

                            // 추가하려는 파일이 있으면 formData에 추가한다.
                            for (var j = 0; j < newAttachFiles.length; j++) {
                                formData.append("newAttachFile", newAttachFiles[j]);
                            }

                            fetch("/api/replies/" + repliId, {
                                method: "post",
                                body: formData
                            })
                                .then(function (response) {
                                    return response.json();
                                })
                                .then(function (json) {
                                    $(".replies").html();
                                    refreshReplies();
                                })
                        });

                        if (replyAttachFiles) {
                            var replyItemsTemplate = $(".reply-item-update-files").html();
                            for (var j = 0; j < replyAttachFiles.length; j++) {
                                var replyItemFile = replyAttachFiles[j];

                                var fileTemplate = replyItemsTemplate
                                    .replaceAll("#fileGroupId#", replyItemFile.fileGroupId)
                                    .replaceAll("#fileNum#", replyItemFile.fileNum)
                                    .replaceAll("#fileDisplayName#", replyItemFile.displayName);

                                updateFormDom.find(".update-file-list").append($(fileTemplate)); // fileTemplate을 객체화 시켜서 넣어라
                            }
                        }

                        $(this)
                            .closest(".reply-item")
                            .find(".content")
                            .after(updateFormDom);
                    });

                    // 댓글 삭제
                    // 삭제를 클릭하묜, "삭제하시겠습니까?"를 물어보고 "확인"을 클릭했을 때 삭제되고 
                    // 이후 댓글 목록을 새로 고친다.
                    replyDom.find(".links-delete").on("click", function () {
                        // "삭제 하시겠습니까?" 를 사용자에게 물어본다
                        if (confirm("삭제하시겠습니까?")) {
                            // "확인"을 클릭하면 삭제 API를 호출
                            // 호출 결과  => {replyID:"RP=20260410-000001"}
                            var replyId = $(this).closest(".reply-item").data("reply-id");
                            var targetReply = $(this);
                            // 결과의 replyId 댓글을 목록에서 제거한다(댓글 다시 불러오기 x)
                            fetch("/api/replies/delete/" + replyId)
                                .then(function (result) {
                                    return result.json();
                                })
                                .then(function (json) {
                                    console.log(json);
                                    targetReply.closest(".reply-item").remove();
                                });
                        }
                        // 취소를 클릭하면 아무 일도 일어나지 않는다.
                    });

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
            for (var i = 0; i < fileInput.files.length; i++) {
                formData.append("attachFiles", fileInput.files[i]);
            };
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