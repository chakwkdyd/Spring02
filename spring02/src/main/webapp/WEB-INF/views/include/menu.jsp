 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="${path}">Home</a> |
<a href="${path}/memo/list.do">한줄 메모장</a> |
<a href="${path}/board/list.do">게시판</a> |
<a href="${path}/upload/uploadForm">업로드 테스트</a> |
<a href="${path}/shop/product/list.do">상품목록</a> |

${sessionScope.userid}
<div style="text-align: right;">
	<!-- 일반 회원 로그인/로그아웃 -->
	<c:choose>
		<c:when test="${empty sessionScope.userid }">
			<a href="${path}/member/login.do">로그인</a>	
			<a href="${path}/admin/login.do">관리자 로그인</a>
		</c:when>
		<c:otherwise>
			${sessionScope.name}님이 로그인 중입니다.
			<a href="${path}/member/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>