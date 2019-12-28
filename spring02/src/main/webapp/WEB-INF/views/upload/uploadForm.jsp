<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>

<form id="form1" action="${path}/upload/uploadForm" method="post"
	enctype="multipart/form-data" target="iframe1">
	
	<input type="file" name="file"> <!-- 컨트롤러의 변수명이랑 name="file"이 맞아야함 -->
	<input type="submit" value="업로드">
</form>

<iframe name="iframe1"></iframe>

</body>
</html>