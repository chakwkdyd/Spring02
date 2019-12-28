<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
<%@ include file="include/header.jsp" %>	
</head>
<body>
<%@ include file="include/menu.jsp" %>

이름 11 => ${sessionScope.name}<br>
이름 22 => ${name}<br>

아이디11 => ${sessionScope.userid}<br>
아이디22 => ${userid}<br>
${sessionScope.name}

<c:if test="${sessionScope.userid != null }">
	<h2>
		${sessionScope.name} (${sessionScope.userid})님의 방문을 환영합니다.
	</h2>
</c:if>


	<%-- <c:if test="${sessionScope.userid != null}">
		<h2>${sessionScop.name} (${sessionScope.userid})
			님의 방문을 환영합니다.
		</h2>	
	</c:if> --%>

<h1>Hello world! </h1>

<P>  The time on the server is ${serverTime}. </P>
<%= application.getRealPath("/") %> <!-- 배포 디렉토리  개발디렉토리랑 별게임(주소가 다르게 표현됨)-->

</body>
</html>
