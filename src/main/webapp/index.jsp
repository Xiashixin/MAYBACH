<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 兼容移动设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap css -->
    <link href="<%=request.getContextPath()%>/plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <!-- 巨幕样式 -->
    <link href="<%=request.getContextPath()%>/plugin/bootstrap/css/jumbotron.css" rel="stylesheet" media="screen"/>
    <!-- 登陆样式 -->
    <link href="<%=request.getContextPath()%>/plugin/bootstrap/css/signin.css" rel="stylesheet" media="screen"/>
    <!-- 按钮样式 -->
    <link href="<%=request.getContextPath()%>/plugin/ui/button/buttons.css" rel="stylesheet" media="screen"/>
    <!-- 平台自定义 -->
    <link href="<%=request.getContextPath()%>/plugin/maybach/maybach.login.css" rel="stylesheet" media="screen"/>
    
    <!-- IE9以下版本的支持 -->
    <!-- Respond.js在静态模式下不能访问，也就是说必须先启动服务器 -->
    <!--[if lt IE 9]>
      <script src="http://labfile.oss.aliyuncs.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://labfile.oss.aliyuncs.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
    <title>即使是一个简单的页面，也可以用心做的很风骚......</title>
  </head>
  <body>
    <!-- 导航栏 start -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
	        <div class="navbar-header">
	            <a class="navbar-brand" href="#">MAYBACH</a>
	        </div>
	        <div id="navbar" class="collapse navbar-collapse">
	            <ul class="nav navbar-nav maybach-nav-top">
		            <li><a href="#maybach-introduce" class="navbar-nav-a">关于MAYBACH</a></li>
	            </ul>
	        </div>
        </div>
    </nav>
    <!-- 导航栏 end -->
    
    <!-- 登陆区域 start -->
    <div class="jumbotron maybach-bg">
        <div class="container">
	        <form id="login-form" class="form-signin">
	            <h2 class="form-signin-heading">欢迎使用MAYBACH</h2>
	            <label for="inputName" class="sr-only">Username</label>
	            <input type="text" id="inputName" class="form-control" placeholder="Username" required>
		        <label for="inputPassword" class="sr-only">Password</label>
		        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
		        <div class="checkbox">
		            <label>
		                <input type="checkbox" value="remember-me"> Remember me
		            </label>
		        </div>
		        <button id="login" class="btn button button-glow button-rounded button-raised button-primary btn-block">Sign in</button>
	        </form>
	    </div>
    </div>
    <!-- 登陆区域 start -->
    
    <!-- MAYBACH介绍 start -->
    <div id="maybach-introduce" class="jumbotron maybach-introduce">
        <div class="container">
            <h2 class="maybach-h2">技术推动生活</h2>
            <h3 class="maybach-h3">MAYBACH的目标是帮助技术人员更容易的去构建高可用的系统</h3>
            <h3 class="maybach-h3">黑夜给了我黑色的眼睛，我要用它来寻找光明</h3>
        </div>
    </div>
    <!-- MAYBACH介绍 end -->
    
    <!-- 联系方式 start -->
    <nav class="navbar navbar-default navbar-fixed-bottom">
	    <div class="container">
	       <div class="navbar-header">
                <a class="navbar-brand" href="#">Contact Me：</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav maybach-nav-bottom">
                    <li><a href="#" class="navbar-nav-a">QQ：370367231</a></li>
                    <li><a href="#" class="navbar-nav-a">Email：xiasxjy@163.com</a></li>
                    <li><a href="#" class="navbar-nav-a">Weixin：大新新</a></li>
                </ul>
            </div>
	    </div>
	</nav>
    <!-- 联系方式 end -->
    
    <!-- 脚本引入 -->
    <script src="<%=request.getContextPath()%>/plugin/jquery/jquery-1.10.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/plugin/layer/layer.js"></script>
    <script type="text/javascript">
        //初始化
        $(function(){
        	//按钮提交的事件
        	$('#login').bind('click', function(){
        		var username = $('#inputName').val();
        		var password = $('#inputPassword').val();
        		if(username && password){
        			$.ajax({
                        url: '<%=request.getContextPath()%>/framework/login/ajaxLogin.do',
                        data: 'name='+ username +'&pwd='+ password,
                        type: 'POST',
                        dataType:'text',
                        success: function(result){
                            if(result=='T'){
                            	alert(result);
                                //如果登陆成功返回T，则跳转到主页面
                                window.location.href = '<%=request.getContextPath()%>/framework/login/main.do';
                            } else{
                                //返回错误提示信息
                                alert('用户名或密码错误，请重新输入！');
                            }
                        },
                        error: function(result) {
                            alert('网络连接错误！');
                        }
                    });
        		}
        	});
        });
    </script>
  </body>
</html>