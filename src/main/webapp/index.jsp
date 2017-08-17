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

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/weblib/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/weblib/css/landing-page.css"/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/weblib/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
<style>
    body { padding-top: 50px; }
</style>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
    <div class="container topnav">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand topnav" href="<%=basePath%>">在线考试系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="nav_user_area">

        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">用户登录</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="account">帐号</label>
                        <input  class="form-control" id="account" placeholder="帐号">
                    </div>
                    <div class="form-group">
                        <label >密码</label>
                        <input type="password" class="form-control" id="password" placeholder="Password">
                    </div>
                </form>
                    <div style="display: none;" id="alertmsg" class="alert alert-danger" role="alert">
                        <a href="#" class="alert-link">...</a>
                    </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="close_modal" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="login">登录</button>
            </div>
        </div>
    </div>
</div>

<!-- Header -->
<a name="about"></a>
<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h1>开始使用</h1>
                    <h3>C/C++在线考试系统</h3>
                    <hr class="intro-divider">
                    <ul class="list-inline intro-social-buttons">
                        <li>
                            <a id="go_exam" class="btn btn-default btn-lg"><i class="glyphicon glyphicon-pencil"></i> <span class="network-name">在线考试</span></a>

                        </li>
                        <li>
                            <a href="#" class="btn btn-default btn-lg" id="go_rank"><i class="glyphicon glyphicon-th-list"></i> <span class="network-name">考试排名</span></a>
                        </li>
                        <li>
                            <a href="#" class="btn btn-default btn-lg" id="go_exercise"><i class="glyphicon glyphicon-book"></i> <span class="network-name">在线练习</span></a>
                        </li>
                        <li>
                            <a href="#" class="btn btn-default btn-lg" id="go_exercise_rank"><i class="glyphicon glyphicon-th-list"></i> <span class="network-name">练习信息</span></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
    <!-- /.container -->

</div>
<!-- /.intro-header -->


<!-- Footer -->
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
                <p class="copyright text-muted small">Copyright &copy; 高嘉尧 2017. All Rights Reserved</p>
            </div>
        </div>
    </div>
</footer>

<!-- jQuery -->
<script src="<c:url value="/weblib/js/jquery.js"/>"></script>
<script src="<c:url value="/weblib/js/home/header.js"/>"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/weblib/js/bootstrap.min.js"/>"></script>
<script>
    $(function () {
        $("#go_exam").click($.proxy(function () {
            if(getCookie("token") == false){
                alert("请先登录系统");
            }
            $.ajax({
                url: basePath + 'examCtr/get_proceeding_exam?token=' + getCookie("token"),
                dataType: 'json' ,
                success: function (data) {
                    if(data.examinfo == undefined){
                        alert("当前没有可以开始的考试");
                    }else{
                        window.location.href = basePath + 'examCtr/to_exam?token='+ getCookie("token")+'&testid='+ data.examinfo.testid ;
                    }
                }
            });
        },this));

        $("#go_rank").click(function () {
            if(getCookie("token") == false){
                alert("请先登录系统");
            }else {
                window.location.href = basePath + "examCtr/go_rank?token=" + getCookie("token");
            }
        });
        //$("#go_rank").attr("href", basePath + "examCtr/go_rank?token=" + getCookie("token"));

        $("#go_exercise").click(function () {
            if(getCookie("token") == false){
                alert("请先登录系统");
            }else {
                window.location.href = basePath + "examCtr/go_exercise?token=" + getCookie("token");
            }
        });
        //$("#go_exercise").attr("href", basePath + "examCtr/go_exercise?token=" + getCookie("token"));

        $("#go_exercise_rank").click(function () {
            if(getCookie("token") == false){
                alert("请先登录系统");
            }else {
                window.location.href = basePath + "examCtr/go_exercise_rank?token=" + getCookie("token");
            }
        });
    });


</script>
</body>

</html>
