<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>FaceStory</title>
		<meta name="description" content="Grid Loading and Hover Effect: Recreating Samsung's grid loading effect" />
		<meta name="keywords" content="grid loading, swipe, effect, slide, masonry, web design, tutorial" />
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		<link rel="stylesheet" href="http://dreamsky.github.io/main/blog/common/init.css">
		<style>
		body {
			background: #444;
		}
		.codrops-demos a.current-demo {
			color: #999;
		}
		.codrops-header h1 {
			margin-top: 50px;
			font-family: 'Microsoft Yahei';
			color: #fff;
		}
		</style>
		<script src="js/modernizr.custom.js"></script>
	</head>
	<body>
		<div class="container">
			<header class="codrops-header">
				<h1>Face diary</h1>
			</header>
			<section class="grid-wrap">
					<a href="FileTools">
						<img src="img/blue.png"/>
					</a>
				<ul class="grid swipe-right" id="grid">
				<%String root="D:\\MyEclipse workspace\\faceee\\data";
				File photosPath=new File(root);
				
				//out.println(photosPath.getAbsoluteFile());
				
					File[] users=photosPath.listFiles();
					//out.println(users.length);
					if(users!=null){
						int i=1;
						for(File user:users){
							File[] datas=user.listFiles();
							String currentPath=root+"\\user"+(i-1);
							if(datas!=null){
								for(File data:datas){
									if(data.getName().endsWith(".png")){
										
										out.print("<li style=\"background:#fff;\"><a href=\"#\" style=\"height:320px;\"><img class=\"photo\" src=\"img/"+data.getName()+"\" alt=\"img"+(i-1)+"\">");
									}
									else if(data.getName().endsWith(".txt")){
										InputStreamReader read = new InputStreamReader(new FileInputStream(data));//考虑到编码格式
							            BufferedReader bufferedReader = new BufferedReader(read);
							            String lineTxt = null;
							            while((lineTxt = bufferedReader.readLine()) != null){
							               out.print("<h3>"+lineTxt+"<br>");
										}
									}
										
								}
								out.println("</h3></a></li>");
							}
						}
						i++;
					}
				%>
					
				</ul>
			</section>
		</div><!-- /container -->
		<div class="footer-banner" style="width:728px; margin:160px auto 100px"></div>
		<script src="js/masonry.pkgd.min.js"></script>
		<script src="js/imagesloaded.pkgd.min.js"></script>
		<script src="js/classie.js"></script>
		<script src="js/colorfinder-1.1.js"></script>
		<script src="js/gridScrollFx.js"></script>
		<script>
			new GridScrollFx( document.getElementById( 'grid' ), {
				viewportFactor : 0.4
			} );
		</script>
		<script src="http://dreamsky.github.io/main/blog/common/jquery.min.js"></script>
		<script src="http://dreamsky.github.io/main/blog/common/init.js"></script>
	</body>
</html>