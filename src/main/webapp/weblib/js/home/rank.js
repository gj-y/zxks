/**
 * Created by 嘉尧 on 2017/4/3.
 */
$(function () {
    init();

    setInterval(init, 3000);

    //路径导航
    $linkNav = $(".breadcrumb");
    $linkNav.eq(0).find("a").eq(1).attr('href', basePath + "examCtr/go_rank?token=" + getCookie("token"));
    
    $("#export_excel").click(function () {
        $.ajax({
            url: basePath + "examCtr/download_exam_rank?token=" + getCookie("token"),
            dataType: 'json',
            data: {"examid": examid},
            success: function () {

            }
        });
    });
});
function init() {
    //加载排名信息
    $.ajax({
        url: basePath + "examCtr/get_exam_rank?token=" + getCookie("token"),
        dataType: 'json',
        data: {"examid": examid},
        success: function (data) {
            showList(data);
        }
    });
}
function showList(data) {
    console.log(data);
    var $table_body = $("#rank_list");
    $table_body.html("");
    for (var i = 0; i < data.testPaper.length; i++){
        var test = data.testPaper[i];
        var html = "";
        html += '<tr>';
        html += '<td value="'+i+'">'+(i + 1)+'</td>';
        html += '<td>'+test.stuinfo.nick+'</td>';
        html += '<td>'+test.score+'</td>';
        html += '<td><a class="testdetail" data-toggle="modal" data-target=".bs-example-modal-lg">详情</a></td>';
        html += '</tr>';
        $table_body.append(html);
    }
    $(".testdetail").click(function () {
        var testid = $(this).parent().siblings().eq(0).attr("value");
        var list = data.testPaper[testid].solutions;
        $("#test_detail").html("");
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            var html = "";
            html += '<tr>';
            html += '<td>'+ obj.problemId +'</td>';
            html += '<td>'+ translateResult(obj.result) +'</td>';
            html += '<td>'+ (obj.result == 4 ? obj.goal : 0) +'</td>';
            html += '<td>'+ translateLanuge(obj.language) +'</td>';
            html += '<td>'+ obj.memory +'K</td>';
            html += '<td>'+ obj.time +'ms</td>';

            html += '</tr>';
            $("#test_detail").append(html);
        }
    });
}

function translateResult(result) {
    switch (result) {
        case 0: return "编译错误";
        case 1: return "WT1";
        case 2: return "编译中";
        case 3: return "运行并评判 ";
        case 4: return "程序通过!";
        case 5: return "答案格式错误";
        case 6: return "答案不对";
        case 7: return "运行超出时间限制";
        case 8: return "超出内存限制";
        case 9: return "输出超过限制";
        case 10: return "运行时错误";
        case 11: return "编译错误";
        case 12: return "编译成功 ";
    }
}
function translateLanuge(lanuage) {
    switch (lanuage) {
        case 0: return "C";
        case 1: return "C++";
        case 2: return "Pascal";
        case 3: return "Java";
        case 4: return "Ruby";
        case 5: return "Bash ";
        case 6: return "Python";
        case 7: return "php";
        case 8: return "perl";
        case 9: return "mono c#";
        case 10: return "objective-c";
        case 11: return "free basic";
        case 12: return "scheme guile";
    }
}