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

        .page_control{
            position: absolute;
            right: 90px;
            bottom: 0px;

        }
    </style>
</head>

<body>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<script>
    var basePath = '<%=basePath%>';
</script>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb" style="background: none;font-size: 12px;">
            <li><a href="#">后台管理</a></li>
            <li class="active">题库管理</li>
        </ol>
    </div>
</div>
    <div class="container">
        <div class="btn-group" role="group" aria-label="...">
            <div>
                <button type="button" class="btn btn-default"id="add_problem">添加</button>
            </div>
            <form id="file_up_form" enctype="multipart/form-data" method="post">
                <button type="button" class="btn btn-default"id="up_load_problem">批量上传</button>
                <input type="file" name="file" />
            </form>
        </div>

        <table class="table" >
            <tbody>
            <th>id</th>
            <th>题目</th>
            <th>详情</th>
            </tbody>

            <tbody id="problem_list">
            </tbody>
        </table>
        <div class="page_control">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>

        </div>


</div>

<script>

</script>

<!-- Footer -->
<!--
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="list-inline">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="">About</a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="">登陆</a>
                    </li>
                    <li class="footer-menu-divider">&sdot;</li>
                    <li>
                        <a href="">注册</a>
                    </li>
                </ul>
                <p class="copyright text-muted small">Copyright &copy; Your Company 2014. All Rights Reserved</p>
            </div>
        </div>
    </div>
</footer>
-->



<script src="<c:url value="/weblib/js/admin/problem.js"/>"></script>

</body>

</html>
