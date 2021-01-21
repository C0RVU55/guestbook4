<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guest book model 4</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/guest/delete" method="post">
	
		비밀번호 <input type="text" name="password"> 
		<button type="submit">확인</button>
		<!--코드 no--> <input type="hidden" name="no" value="${param.no}">
	</form>
	
	<c:if test="${param.result == 0}">
		비밀번호가 바르지 않습니다.
	</c:if>
	<br>
	<a href="${pageContext.request.contextPath }/guest/list">메인으로 돌아가기</a>
	
</body>
</html>