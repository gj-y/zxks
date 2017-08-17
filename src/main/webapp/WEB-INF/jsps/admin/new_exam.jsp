<%--
  Created by IntelliJ IDEA.
  User: 嘉尧
  Date: 2017/1/28
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script>
    var basePath = '<%=basePath%>';
</script>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>在线考试系统</title>

    <!-- Bootstrap Core CSS-->
    <link href="/zxks/weblib/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/weblib/css/landing-page.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/weblib/css/bootstrap-datetimepicker.min.css"/>" />

    <script src="<c:url value="/weblib/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/weblib/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/weblib/js/bootstrap-datetimepicker.min.js"/>"></script>



    <!-- Custom Fonts -->
    <link href="<c:url value="/weblib/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <link href="<c:url value="/weblib/css/style.css"/>" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<c:if test="${result != null}">
    <script>
        var result = ${result.result};
        if(result == -1){
            alert("已经有正在进行的考试了，请考试完成后创建");
        }else {
            alert("创建成功");
        }
    </script>
</c:if>

<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb" style="background: none;font-size: 12px;">
            <li><a href="#">后台管理</a></li>
            <li class="active">创建考试</li>
        </ol>
    </div>
</div>
<div class="container">
    <form id="start_exam_form" class="form-horizontal" method="post">
        <div class="form-group">
            <label for="examname" class="col-sm-2 control-label">考试名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="examname" id="examname" >
            </div>
        </div>
        <div class="form-group">
            <label for="starttime" class="col-sm-2 control-label">考试开始时间</label>
            <div class="col-sm-10">
                <input type="datetime" class="form-control" name="starttime" id="starttime" placeholder="2017-04-13 14:35">
            </div>
        </div>
        <div class="form-group">
            <label for="inputTime" class="col-sm-2 control-label">考试时长（分钟）</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="inputTime" id="inputTime" >
            </div>
        </div>
        <div class="form-group">
            <label for="inputNum" class="col-sm-2 control-label">考试题数</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="inputNum" id="inputNum" >
            </div>
        </div>

        <script>
            $("#starttime").datetimepicker({
                format: "yyyy-mm-dd hh:ii",
            });
        </script>
    </form>
    <button id="start_exam_btn" class="btn btn-default">提交</button>
</div>

</body>
<script src="<c:url value="/weblib/js/admin/admin.js"/>"></script>
<script>
    $(function () {
       $("#start_exam_btn").click(function () {
           $("#start_exam_form").attr("action", basePath + "examCtr/admin/start_new_exam?token=" + getCookie("token"));
           $("#start_exam_form").submit();
       });
    });
</script>
</html>
