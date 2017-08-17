/**
 * Created by 嘉尧 on 2017/4/13.
 */
var problems;
$(function () {
    init();

});

function init(){


    $.ajax({
        type:"POST", //请求方式
        url: basePath + "examCtr/get_exercise_problems?pageNo=1&token=" + getCookie("token"), //请求路径
        // data:"name="+name,  //传参
        dataType: 'json',   //返回值类型
        success:function(data){
            problems =  data.pageContent;
            ProblemList();
        }
    });


}
function ProblemList() {
    var $problems = $("#problem_list");
    var html = "";
    for (var i = 0; i < problems.length; i++) {
        var obj = problems[i];
        html += "<tr>";
        html += "<td>" + obj.problemId + "</td>";
        html += "<td colspan='5'><a href='" + basePath + "examCtr/go_exercise_problems?problemid="+obj.problemId+"&token="+getCookie("token")+"'>"+ obj.title +"</a></td>";
        html += "<td>"+obj.accepted+"/"+ obj.submit +"</td>";
        html += "</tr>";
    }
    $problems.empty();
    $problems.append(html);


}