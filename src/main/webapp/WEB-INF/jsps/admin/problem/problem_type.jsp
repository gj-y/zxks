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
    <link rel="stylesheet" href="<c:url value="/weblib/css/bootstrap-treeview.min.css"/>" />

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



<body>
<div class="container-fluid">
    <div class="row">
        <ol class="breadcrumb" style="background: none;font-size: 12px;">
            <li><a href="#">后台管理</a></li>
            <li class="active">题目类型管理</li>
        </ol>
    </div>
</div>
<div class="container">
    <div id="type_tree"></div>
</div>

<script>
    $(function () {
        $('#type_tree').treeview({data: getTree()});

        $('#type_tree').on('nodeSelected', function(event, data) {
            addNode($('#type_tree').treeview('getSelected')[0]);
        });

    });

    function addNode(parentNode) {
        var singleNode = {
            text: "111",
            id: 222,
            code: 333
        };
        $('#tree').treeview('addNodeAfter', [[singleNode], parentNode, { silent: true } ]);
    }
    function getTree() {
        var tree = [
            {
                text: "算法",
                nodes: [
                    {
                        text: "排序",
                        nodes: [
                            {
                                text: "冒泡"
                            },
                            {
                                text: "归并"
                            },
                            {
                                text: "快排"
                            }
                        ]
                    },{
                        text: "正则",
                        nodes: [
                            {
                                text: "字符串匹配"
                            }
                        ]
                    }
                ]
            }
        ];
        return tree;
    }
</script>

</body>
<script src="<c:url value="/weblib/js/admin/admin.js"/>"></script>
<script src="<c:url value="/weblib/js/bootstrap-treeview.js"/>"></script>
</html>
