/**
 * Created by 嘉尧 on 2017/4/3.
 */

$(function () {
    //页面加载时判断登录状态
    checkLogin();
    checkHasTest();


    $("#login").click(function () {
        var account = $("#account").val();
        var pwd =  $("#password").val();
        $.ajax({
            url: basePath + "userCtr/public/login",
            dataType: 'json',
            data: {"account": account, "pwd": pwd},
            success: function (data) {
                if(data.userinfo == undefined){
                    $("#alertmsg").show();
                    $("#alertmsg").find("a").html(data.msg);
                    setTimeout(function () {
                        $("#alertmsg").hide();
                    },5000);
                }else {
                    $("#close_modal").click();
                    setCookie("token", data.token, 60*60*2);
                    checkLogin();
                }
            }
        });
    });

});

//取得cookie
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';'); //把cookie分割成组
    for(var i=0;i < ca.length;i++) {
        var c = ca[i]; //取得字符串
        while (c.charAt(0)==' ') { //判断一下字符串有没有前导空格
            c = c.substring(1,c.length); //有的话，从第二位开始取
        }
        if (c.indexOf(nameEQ) == 0) { //如果含有我们要的name
            return unescape(c.substring(nameEQ.length,c.length)); //解码并截取我们要值
        }
    }
    return false;
}
//清除cookie
function clearCookie(name) {
    setCookie(name, "", -1);
}
//设置cookie
function setCookie(name, value, seconds) {
    seconds = seconds || 0; //seconds有值就直接赋值，没有为0，这个根php不一样。
    var expires = "";
    if (seconds != 0 ) { //设置cookie生存时间
        var date = new Date();
        date.setTime(date.getTime()+(seconds*1000));
        expires = "; expires="+date.toGMTString();
    }
    document.cookie = name+"="+escape(value)+expires+"; path=/"; //转码并赋值
}

function checkLogin() {
    var token = getCookie("token");
        $.ajax({
            url: basePath + "userCtr/public/check_token",
            dataType: 'json',
            data: {"token": token},
            success: function (data) {
                if (data.userinfo != undefined){
                    var html = '<ul class="nav navbar-nav navbar-right" >';
                    html += '<li><a id="announce_notify"></a></li>';
                    html += '<li><a>'+ data.userinfo.nick +'</a></li><li>';
                    html += '<a style="padding:0px;"><image style="border-radius: 22px;height: 40px;width: 40px;margin: 3px;" src="data:image/jpg;base64,'+ data.userinfo.picture +'"/></a>';
                    if (data.userinfo.rightstr == "administrator"){
                        html += '<li><a href="#" id="go_admin">后台管理</a></li>';
                    }
                    html += '</li><li><a href="#" id="logout">退出</a>';
                    html += '</li></ul>';
                    $("#nav_user_area").html(html);
                    $("#logout").click(function () {
                        if (confirm("您确认要退出系统？")==true){
                            $.ajax({
                                url: basePath + "userCtr/public/logout",
                                dataType: 'json',
                                data: {"token": getCookie("token")},
                                success: function (data) {
                                    clearCookie("token");
                                    checkLogin();
                                    window.location.href = basePath;
                                }
                            });
                        }
                    });
                    $("#go_admin").click(function () {
                        window.location.href = basePath + "examCtr/admin/to_admin_page?token=" + getCookie("token");
                    });
                }else {
                    var html = '<ul class="nav navbar-nav navbar-right" >';
                    //html += '<li><a href="#about">About</a></li>';
                    html += '<li><a href="#services" data-toggle="modal" data-target="#myModal">登陆</a></li>';
                    //html += '<li><a href="#contact">注册</a></li></ul>';
                    $("#nav_user_area").html(html);
                }
            }
        });
}

/**
 * 查询是否有自己的考试
 */
function checkHasTest() {
    setInterval(function () {
        $.ajax({
            url: basePath + "examCtr/get_exam_annouce?token=" + getCookie("token"),
            dataType: 'json',
            success: function (data) {
                var $temp = $("#announce_notify");
                if(data.exams != undefined){
                    var html = '<div class="dropdown">';
                    html += '<button style="border:0px;background: #ffba91;border-radius: 6px" id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">';
                    html += data.exams.length + '条消息';
                    html += '<span class="caret"></span>';
                    html += '</button>';
                    html += '<ul class="dropdown-menu" aria-labelledby="dLabel">';
                    for (var i = 0; i < data.exams.length; i++) {
                        var obj = data.exams[i];
                        html += '<li><a href= "'+ basePath + 'examCtr/to_exam?token='+ getCookie("token")+'&testid='+ obj.testid +'">考试名称：'+ obj.examname +'</a></li>';
                    }

                    html += '</ul></div>';
                    //if($temp.html() == ""){
                        $temp.html(html);
                    //}
                }else{
                    $temp.html("");
                }
            }
        });
    },3000);
}

function checkIsValidAccountAndPassword()
{
    reg=/^\w{6,}$/;
    if(reg.test(document.all.password1.value)==false)
    {
        alert('密码格式不正确');
        document.all.password1.focus();
        return false;
    }
    reg=/^[1-9]\d{4,9}$/;
    if(reg.test(document.all.qq.value)==false)
    {
        alert('帐号格式无效!');
        return false;
    }
    return true;
}
