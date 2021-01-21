<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guest book model 4</title>
</head>
<body>
	<!-- *****입력창***** -->
	<form action="${pageContext.request.contextPath }/guest/add" method="post"> 
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="72" rows="5" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td> 
			</tr>
		</table>
		<br><br>
	</form>
	
	<!-- *****출력 화면***** -->
	<c:forEach items="${gList}" var="gVo">
	<table border="1">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 40%;">
			<col style="width: 40%;">
			<col style="width: 10%;">
		</colgroup>
		<tr>
			<td>${gVo.no}</td>
			<td>${gVo.name}</td>
			<td>${gVo.regDate}</td>
			<td><a href="${pageContext.request.contextPath }/guest/dform?no=${gVo.no}">삭제</a></td> 
		</tr>
		<tr>
			<td colspan="4">${gVo.content}</td>
		</tr>
	</table>
	<br>
	</c:forEach>

</body>
</html>