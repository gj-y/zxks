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
    <link href="<c:url value="/weblib/js/codemirror/lib/codemirror.css"/>" rel="stylesheet">

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
<style>
    body { padding-top: 50px; }
</style>
<body>

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

<!-- Page Content -->


<div class="container-fluid">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <ol class="breadcrumb" style="background: none;">
                <li><a href="<%=basePath%>">Home</a></li>
                <li><a href="#">课后练习</a></li>
                <li class="active">${problem.title}</li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-md-1">
        </div>

        <div class="col-md-10">

                <div class="col-md-11">
                </div>
                <div class="col-md-1">
                    <div class="dropdown">
                        <button value="" class="btn btn-default dropdown-toggle" type="button" id="lanMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            C
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a class="lanuage_item" value="0">C</a></li>
                            <li><a class="lanuage_item" value="1">C++</a></li>
                            <li><a class="lanuage_item" value="3">Java</a></li>
                        </ul>
                    </div>
                </div>
                <div class="tab-content">
                    <form id="put_form" method="post">
                        <div class="col-md-12">
                            <div>
                                <input type="hidden" name="problemid" value="${problem.problemId}"/>
                                <p class="text-center" id="problem_title'+i+'">${problem.title} </p>
                            </div>
                            <div>
                                <p id="problem_description'+i+'">题目描述：${problem.description}</p>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <textarea class="form-control" rows="12" style="resize: none;" name="code" ></textarea>
                        </div>
                        <div class="col-md-6">
                            <span>样例输入</span>
                            <textarea id="problem_samplein" class="form-control" rows="4" style="resize: none;" disabled>${problem.sampleInput}</textarea>
                        </div>
                        <div class="col-md-6">
                            <span>样例输出</span>
                            <textarea id="problem_sampleout" class="form-control" rows="4" style="resize: none;" disabled>${problem.sampleOutput}</textarea>
                        </div>
                    </form>
            </div>

            <div class="col-md-12">
                <!--<button class="btn btn-primary btn-lg" id="put_test">提交，下一题</button>-->
                <button class="btn btn-primary btn-lg" id="put_exercise">提交</button>
            </div>
        </div>
        <div class="col-md-1">
        </div>
    </div>

</div>
<script>

</script>


<script src="<c:url value="/weblib/js/jquery.min.js"/>"></script>
<script src="<c:url value="/weblib/js/codemirror/lib/codemirror.js"/>"></script>
<script src="<c:url value="/weblib/js/home/header.js"/>"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/weblib/js/bootstrap.min.js"/>"></script>

<script>
    $(function () {
        //路径导航
        $linkNav = $(".breadcrumb");
        $linkNav.eq(0).find("a").eq(1).attr('href', basePath + "examCtr/go_exercise?token=" + getCookie("token"));

        $("#put_exercise").click(function () {
            var lanuage = $("#lanMenu").val();
            $("#put_form").attr("action", basePath + "examCtr/put_exercise_answer?token=" + getCookie("token")+"&lanuage="+lanuage);
            $("#put_form").submit();
        });

        var $language_item = $(".lanuage_item");
        $("#lanMenu").val($language_item.eq(0).attr("value"));
        $language_item.click(function () {
            var $lanMenu = $("#lanMenu");
            $lanMenu.html($(this).eq(0).html()+'<span class="caret"></span>');
            $lanMenu.val($(this).attr("value"));
        });
    });
</script>


</body>

</html>
