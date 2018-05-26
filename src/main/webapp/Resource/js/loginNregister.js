$(function () {
    var tab = 'account_number';
    // 选项卡切换
    $(".account_number").click(function () {
        tab = $(this).attr('class').split(' ')[0];
        checkBtn();
        $(this).addClass("on");
        $(".signup").removeClass("on");
        $(".form2").addClass("hide");
        $(".form1").removeClass("hide");
    });
    // 选项卡切换
    $(".signup").click(function () {
        tab = $(this).attr('class').split(' ')[0];
        checkBtn();
        $(this).addClass("on");
        $(".account_number").removeClass("on");
        $(".form2").removeClass("hide");
        $(".form1").addClass("hide");
    });

    $('#pass').keyup(function () {
        checkBtn();
    });
    $('#passport2').keyup(function (event) {
        if (event.keyCode !== 13) {
            checkBtn();
            document.getElementById('info1').innerText = "";
        }
    });
    $('#passport').keyup(function () {
        if (event.keyCode !== 13) {
            checkBtn();
            document.getElementById('info1').innerText = "";
        }
    });
    $('#account').keyup(function () {
        if (event.keyCode !== 13) {
            checkBtn();
            document.getElementById('info1').innerText = "";
        }
    });
    $('#num').keyup(function () {
        checkBtn();
    });

    // 按钮是否可点击
    function checkBtn() {
        $(".log-btn").off('click');
        if (tab === 'account_number') {
            var inp = $.trim($('#num').val());
            var pass = $.trim($('#pass').val());
            if (inp !== '' && pass !== '') {
                $(".log-btn").removeClass("off");
                sendBtn();
            } else {
                $(".log-btn").addClass("off");
            }
        }
        $(".reg-btn").off('click');
        if (tab === 'signup') {
            var account = $.trim($('#account').val());
            var pwd = $.trim($('#passport').val());
            var pwd2 = $.trim($('#passport2').val());
            if (account !== '' && pwd !== '' && pwd2 !== '') {
                $(".reg-btn").removeClass("off");
                sendBtn();
            } else {
                $(".reg-btn").addClass("off");
            }
        }
    }

    // 登录点击事件
    function sendBtn() {
        if (tab === 'signup') {
            $(".reg-btn").click(function () {
                var RegName = /^[a-zA-Z][a-zA-Z0-9]{5,15}$/;
                var _accountValue = $.trim($("#account").val());
                var _passValue = $.trim($("#passport").val());
                var _passValue2 = $.trim($("#passport2").val());
                var info1 = document.getElementById("info1");
                // 逻辑判断
                if (!RegName.test(_accountValue)) {
                    info1.innerHTML = "账号格式不正确(字母开头，数字和字母结合，6到16位)";
                    return;
                }
                if (_passValue.length < 6 || _passValue.length > 16) {
                    info1.innerHTML = "输入的密码只能在6到16位之间";
                    return;
                }
                if (_passValue !== _passValue2) {
                    info1.innerHTML = "两次输入密码不一致";
                    return;
                }
                var pwd_md5 = $.md5(_accountValue + _passValue);
                // 向服务器发送数据，注册用户
                $.ajax(
                    {
                        url: "register",
                        dataType: "json",
                        type: "post",
                        contentType: "application/json",
                        async: false,
                        data: JSON.stringify({
                            account: _accountValue,
                            password: pwd_md5
                        }),
                        success: function () {
                            alert("注册成功");
                            tab = $(".account_number").attr('class').split(' ')[0];
                            checkBtn();
                            checkBtn();
                            $(".account_number").addClass("on");
                            $(".signup").removeClass("on");
                            $(".form2").addClass("hide");
                            $(".form1").removeClass("hide");
                            $("#num").val(_accountValue);
                            $("#pass").val("");
                            $("#passport").val("");
                            $("#passport2").val("");
                            $("#account").val("");
                            document.getElementById('pass').focus();
                            tab = 'account_number';
                        },
                        error: function () {
                            info1.innerHTML = "账号已存在";
                            $("#account").val("");
                            document.getElementById('account').focus();
                        }
                    });
            });
        }
        if (tab === 'account_number') {
            $(".log-btn").click(function () {
                var acc = $.trim($('#num').val());
                var pwd = $.trim($('#pass').val());
                var pass = $.md5(acc + pwd);
                $.ajax({
                    url: '/login',
                    type: 'post',
                    dataType: 'json',
                    contentType: "application/json",
                    async: false,
                    data: JSON.stringify({
                        account: acc,
                        password: pass
                    }),
                    success: function () {
                        window.location.replace("home.jsp");
                    },
                    error: function () {
                        document.getElementById('info2').innerText = "账号或密码输入错误";
                        $("#pass").val("");
                    }
                });
            });
        }
    }

    // 登录的回车事件
    $(window).keydown(function (event) {
        if (tab === 'account_number') {
            if (event.keyCode === 13) {
                $('.log-btn').trigger('click');
            }
        }
        if (tab === 'signup') {
            if (event.keyCode === 13) {
                $('.reg-btn').trigger('click');
            }
        }
    });
});