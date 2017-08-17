/**
 * Created by 嘉尧 on 2017/3/19.
 */

var problems;
$(function () {
    init();

    //=======problem_detail.jsp=============
    $("#submit_updateoradd").click(function () {
        var isAdd;
        if( $("#is_add").val() == "1" ){
            isAdd = true;
        }else {
            isAdd = false;
        }
        $("#addorupdateform").attr("action", basePath + "examCtr/admin/add_or_update_problem?isAdd=" + isAdd + "&token="+ getCookie("token"));
        $("#addorupdateform").submit();
    });

    $("#up_load_problem").click(function () {
        $("#file_up_form").attr("action", basePath + "examCtr/admin/file_update_problems?token="+ getCookie("token"));
        $("#file_up_form").submit();
    });
});

function init(){


    $.ajax({
        type:"POST", //请求方式
        url:basePath + "examCtr/admin/get_problems?token=" + getCookie("token"), //请求路径
        // data:"name="+name,  //传参
        dataType: 'json',   //返回值类型
        success:function(data){
            problems =  data.pageContent;
            ProblemList();
        }
    });
    $("#add_problem").click(function (){
        window.location.href = basePath + "examCtr/admin/to_add_problem?token=" + getCookie("token");
    });

}

function deleteProblem(evt) {
    $.ajax({
        type:"POST", //请求方式
        url:"http://localhost:8080/zxks/examCtr/admin/delete_problem?token=" + getCookie("token"), //请求路径
        // data:"name="+name,  //传参
        dataType: 'json',   //返回值类型
        data: {"id": $(evt.currentTarget).parent().siblings().eq(0).html()},
        success:$.proxy(function(data){
            init();
        },this)
    });
}

function updateProblem(evt) {
    window.location.href = basePath + "examCtr/admin/to_update_problem?problemid="+$(evt.currentTarget).parent().siblings().eq(0).html()+"&token=" + getCookie("token"); //请求路径
}

function ProblemList() {
    var $problems = $("#problem_list");
    var html = "";
    for (var i = 0; i < problems.length; i++) {
        var obj = problems[i];
        html += "<tr>";
        html += "<td>" + obj.problemId + "</td>";
        html += "<td>"+ obj.title +"</td>";
        html += "<td><a type='button' class='update_problem'>编辑</a>   <a type='button' class='delete_problem'>删除</a></td>";
        html += "</tr>";
    }
    $problems.empty();
    $problems.append(html);
    $(".update_problem").click(updateProblem);
    $(".delete_problem").click(deleteProblem);

}

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