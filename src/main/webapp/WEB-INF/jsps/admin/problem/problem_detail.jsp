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
    <style>

    </style>
</head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script>
    var basePath = '<%=basePath%>';
</script>
<body>
    <div class="container">
        <form id="addorupdateform" method="post">
            <input type="hidden" id="is_add" <c:if test="${problem == null}">value="1"</c:if><c:if test="${problem != null}">value="0"</c:if>  >
            <input type="hidden" id="problemId" value="${problem.problemId}"/>
            <div class="form-group">
                <label>题目标题</label>
                <input type="text" class="form-control" name="title" value="${problem.title}"/>
            </div>
            <div class="form-group">
                <label>题目描述</label>
                <textarea type="text" class="form-control" name="description" >${problem.description}</textarea>
            </div>
            <div class="form-group">
                <label>样例输入</label>
                <textarea type="text" class="form-control" name="sampleInput" >${problem.sampleInput}</textarea>
            </div>
            <div class="form-group">
                <label>样例输出</label>
                <textarea type="text" class="form-control" name="sampleOutput" >${problem.sampleOutput}</textarea>
            </div>
            <div class="form-group">
                <label>输入</label>
                <textarea type="text" class="form-control" name="input" >${problem.input}</textarea>
            </div>
            <div class="form-group">
                <label>输出</label>
                <textarea type="text" class="form-control" name="output" >${problem.output}</textarea>
            </div>
            <div class="form-group">
                <label>内存(MB)</label>
                <textarea type="text" class="form-control" name="memoryLimit" >${problem.memoryLimit}</textarea>
            </div>
            <div class="form-group">
                <label>时间(ms)</label>
                <textarea type="text" class="form-control" name="timeLimit" >${problem.timeLimit}</textarea>
            </div>
            <button id="submit_updateoradd" class="btn btn-default">Submit</button>
        </form>

    </div>

<script src="<c:url value="/weblib/js/admin/problem.js"/>"></script>

</body>

</html>
