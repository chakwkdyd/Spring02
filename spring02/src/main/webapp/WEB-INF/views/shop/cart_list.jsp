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
		
	$("#btnList").click(function() {
		location.href="${path}/shop/product/list.do";
	});
	
	$("#btnDelete").click(function() {
		if(confirm("장바구니를 비우시겠습니까?"));
			location.href
					= "${path}/shop/cart/deleteAll.do";
	});
	
	
});
</script>

</head>
<body>
<%@ include file="../include/menu.jsp" %>

	${map.list }

	<h2>장바구니</h2>
	<c:choose>
	
		<c:when test="${map.count == 0}">
			장바구니가 비었습니다.
		</c:when>
		
		<c:otherwise>
			<form id="form1" name="form1" method="post" action="${path}/shop/cart/update.do">
				<table border="1" width="400px;">
					<tr>
						<th>상품명</th>
						<th>단가</th>
						<th>수량</th>
						<th>금액</th>
						<th>&nbsp;</th>
					</tr>
					



					<!-- var="개별변수" items="집합변수" -->
					<c:forEach var="row1" items="${map.list}">
						<tr align="center">
						
							<td>${row1.product_name}</td>
							
							<td>
								<fmt:formatNumber value="${row1.price}" pattern="#,###,###"/>
							</td>
							
							<!-- 수량 -->
							<td>
								<input type="number" name="amount" style="width:30px;" 
									value="<fmt:formatNumber value="${row1.amount}" pattern="#,###,###"/>">

								<input type="hidden" name="cart_id" value="${row1.cart_id}">
							</td>
							
							
							<td>
								<fmt:formatNumber value="${row1.money}" pattern="#,###,###"/>
							</td>
							
							<td>
								<a href="${path}/shop/cart/delete.do?cart_id=${row1.cart_id}">[삭제]</a>
							</td>
						</tr>
					</c:forEach>
					
						<tr>
							<td colspan="5" align="right">
								장바구니 금액 합계 :
								<fmt:formatNumber value="${map.sumMoney}" pattern="#,###,###"/><br>
								배송료 : ${map.fee}<br>
								총합계 : <fmt:formatNumber value="${map.sum}" pattern="#,###,###"/>
							</td>
						</tr>
				</table>
				<button id="btnUpdate">수정</button>
				<button type="button" id="btnDelete">장바구니 비우기</button>
			</form>
		</c:otherwise>
		
	</c:choose>
	<button type="button" id="btnList">상품목록</button>

</body>
</html>