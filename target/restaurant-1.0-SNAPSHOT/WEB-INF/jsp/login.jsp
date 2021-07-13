<%--
  Created by IntelliJ IDEA.
  User: wxl
  Date: 2021-07-13
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Registration Form Template</title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

</head>

<body>

<!-- Top menu -->
<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">饭店管理系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="top-navbar-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
							<span class="li-text">
								点个start再走吧....
							</span>
<%--                    <a href="#"><strong>links</strong></a>--%>
                    <span class="li-text">
								, here:
							</span>
                    <span class="li-social">
<%--								<a href="#"><i class="fa fa-facebook"></i></a> --%>
<%--								<a href="#"><i class="fa fa-twitter"></i></a> --%>
<%--								<a href="#"><i class="fa fa-envelope"></i></a> --%>
								<a href="#"><i class="fa fa-github"></i></a>
							</span>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-7 text">
                    <h1><strong>饭店管理系统</strong> </h1>
                    <div class="description">
                        <p>
                            这是第八小组的饭店管理系统，成员有樊云慧、张瑜、吴小龙、李高晗、齐少华
                        </p>
                    </div>
                    <div class="top-big-link">
                        <a class="btn btn-link-1" href="/toRegist.do">注册</a>
<%--                        <a class="btn btn-link-2" href="#">Button 2</a>--%>
                    </div>
                </div>
                <div class="col-sm-5 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Login</h3>
                            <p>输入你的用户名以及密码进行登陆:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-pencil"></i>
                        </div>
                    </div>
                    <div class="form-bottom">
                        <form role="form" action="/login.do" method="post" class="registration-form">
                            <div class="form-group">
                                <label class="sr-only" for="form-first-name">Username</label>
                                <input type="text" name="username" placeholder="First name..." class="form-first-name form-control" id="form-first-name">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-first-name">Password</label>
                                <input type="password" name="pwd" placeholder="Password..." class="form-last-name form-control" id="form-password">
                            </div>
<%--                            <div class="form-group">--%>
<%--                                <label class="sr-only" for="form-email">Email</label>--%>
<%--                                <input type="text" name="form-email" placeholder="Email..." class="form-email form-control" id="form-email">--%>
<%--                            </div>--%>
<%--                            <div class="form-group">--%>
<%--                                <label class="sr-only" for="form-about-yourself">About yourself</label>--%>
<%--                                <textarea name="form-about-yourself" placeholder="About yourself..."--%>
<%--                                          class="form-about-yourself form-control" id="form-about-yourself"></textarea>--%>
<%--                            </div>--%>
                            <button type="submit" class="btn">Login!</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>
</div>


<!-- Javascript -->
<script src="assets/js/jquery-1.11.1.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.backstretch.min.js"></script>
<script src="assets/js/retina-1.1.0.min.js"></script>
<script src="assets/js/scripts.js"></script>

<!--[if lt IE 10]>
<script src="assets/js/placeholder.js"></script>
<![endif]-->

</body>

</html>