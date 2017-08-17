/**
 * Created by 嘉尧 on 2017/4/14.
 */
    $(function () {
        init();

        setInterval(init, 3000);
    });
function init() {
    $.ajax({
        url: basePath + "examCtr/get_exercise_rank?token=" + getCookie("token"),
        dataType: 'json',
        success: function (data) {
            var $tbody = $("#rank_list");
            $tbody.html("");
            for(var i = 0; i < data.length; i++){
                var obj = data[i];
                var $html = $("<tr></tr>");
                $html.append("<td>"+obj.solutionId+"</td>");
                $html.append("<td>"+obj.problemId+"</td>");
                $html.append("<td>"+obj.userId+"</td>");
                $html.append("<td>"+translateResult(obj.result)+"</td>");
                $html.append("<td>"+obj.time+"ms</td>");
                $html.append("<td>"+obj.memory+"MB</td>");
                $html.append("<td>"+obj.language+"</td>");
                var newDate = new Date();
                newDate.setTime(obj.judgetime);
                $html.append("<td>"+newDate.toLocaleString()+"</td>");
                $tbody.append($html);
            }
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