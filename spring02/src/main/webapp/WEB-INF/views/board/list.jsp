<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<%@ include file="../include/header.jsp" %>

<script type="text/javascript">
	$(function(){
		
		$("#btnWriter").click(function(){
			location.href="${path}/board/write.do";
		});
		
	});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>

	<h2>게시판</h2>
	<button type="button" id="btnWriter">글 쓰기</button>
	<table border="1" width="600px"> 
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
						
	<c:forEach var="row" items="${map.list}">
	<tr>
	<td>${row.bno}</td>
	   <td>${row.title}</td>
	   <td>${row.writer}</td>
	   <td>
	   	<fmt:formatDate value="${row.regdate}"
	   		pattern="yyyy-MM-dd HH:mm:ss"/> 
	   </td>
	   <td>${row.viewcnt}</td>
	   </tr>
	  </c:forEach>
	</table>

</body>
</html>