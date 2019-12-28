<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>

<script type="text/javascript">
	
	$(function() {
		
		$("#btnAdd").click(function() {
			location.href="${path}/shop/product/write.do";
		});
	});
</script>

</head>
<body>
<%@ include file="../include/menu.jsp" %>
	
	<h2>상품목록</h2>
	<button type="button" id="btnAdd">상품등록</button>
	
	<table border="1" width="500px">
		<tr>
			<th>상품ID</th>
			<th>&nbsp;</th>
			<th>상품명</th>
			<th>각격</th>
		</tr>
		
		<c:forEach var="row" items="${list}">
		ㅇㅇㅇ=>${row.picture_url}
			<tr align="center">
				
				<td>${row.product_id}</td>
				
				<td>
					<img src="${path}/images/${row.picture_url}" width="100" height="100">	
				</td>
				
				<td>
					<!-- 
						http://localhost:8181/spring02/shop/product/detail/6 
						이렇게하면 상품 개당 고유의 url를 갖는다 
						Controller에서 받을 때는 @PathVariable로 받아야 함
						옛날 방식 -> http://localhost:8181/spring02/shop/product/detail.do?product_id=6
					-->
					<a href="${path}/shop/product/detail/${row.product_id}"> 
						${row.product_name}
					</a>
					
					<!-- 관리자만 편집 할수 있게 조건문 걸어줌 -->
					<c:if test="${sessionScope.admin_userid != null }">
						<br>							
													 <!-- 선택 되면 상품 번호가 넘어가게 되어 있음 -->
						<a href="${path}/shop/product/edit/${row.product_id}">[편집]</a>	
					</c:if>
					
				</td>
				<td>
					<fmt:formatNumber value="${row.price}" pattern="#, ###"/> <!-- #, ### => 3자리 마다 ,를 찍으라는 명령 -->
				</td>
			
			</tr>
		</c:forEach>
	</table>

</body>
</html>