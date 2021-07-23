<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폰북4</title>
</head>
<body>

	<h1>폰북4 전화번호 리스트 ${ pageContext.request.contextPath }</h1>
	<p>입력한 정보 내역입니다.</p>

	<c:forEach items="${ requestScope.pList }" var="vo" varStatus="status">
	<table border="1">
		<tr>
			<td>이름</td>
			<td>${ vo.name }</td>
		</tr>
		<tr>
			<td>핸드폰</td>
			<td>${ vo.hp }</td>
		</tr>
		<tr>
			<td>회사</td>
			<td>${ vo.company }</td>
		</tr>
		<tr>
			<td><a href="${ pageContext.request.contextPath }/modifyForm?id=${ vo.personID }">[수정폼]</a></td>
			<td><a href="${ pageContext.request.contextPath }/delete?id=${ vo.personID }">[삭제]</a></td>
		</tr>
	</table>

	<br>
	</c:forEach>
	
	<a href="${ pageContext.request.contextPath }/writeForm">[추가번호 등록]</a>

</body>
</html>