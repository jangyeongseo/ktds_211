$().ready(function () {
    $(".load-git-users").on("click", function () {
        //"avatar_url" 의 값을 <img src="" /> 에 추가
        fetch("https://api.github.com/users")
            .then(function (jsonRestonse) {
                return jsonRestonse.json();

            }).then(function (users) {
                // URL을 fetch 로 호출해 반환되는 데이터를 console 로 출력하도록 합니다.
                console.log(users);

                for (var i = 0; i < users.length; i++) {
                    var user = users[i];

                    var logIn = user.login;
                    var avatar = user.avatar_url;

                    // "login"의 값을 <div></div>에 추가
                    var div = $("<div>").text(logIn);

                    // "avatar_url" 의 값을 <img src="" /> 에 추가
                    var img = $("<img>").attr("src", avatar).css({
                        width: "50px",
                        height: "50px",
                        borderRadius: "50%"
                    });

                    // "html_url"의 값을 5에서 만든 div를 클릭했을 때 이동
                    div.on("click", function () {
                        window.location.href = user.html_url;
                    })

                    // ".posts"의 li로 추가
                    var li = $("<li>").append(img, div);
                    $(".posts").append(li);
                }

            });
    });
})