<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>공공체육시설정보조회시스템</title>
<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/modifyForm.css" />
</head>
<body>
	<header>
		<%@ include file="../member/header.jsp"%>
	</header>

	<main>
		<form:form action="${cp}/member/modify" method="post"
			commandName="member">
			<form:hidden path="memId" value="${sessionScope.member.memId}" />
			<div id="modifyForm">
				<p id="fontVHard"> My Info Modify </p>
				<br>
				<table>
					<tr>
						<td><p id="fontHard">ID</p></td>
						<td><p Id="fontHard">${sessionScope.member.memId}<p></td>
					</tr>
					<tr>
						<td><p id="fontHard">PW
							<p></td>
						<td><form:password path="memPw" /></td>
					</tr>
					<tr>
						<td><p id="fontHard">NAME</p></td>
						<td><form:input path="memName" value="${sessionScope.member.memName}" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="fontHard"
							value="Modify"></td>
					</tr>
				</table>
			</div>
		</form:form>
	</main>
		<script type="text/javascript">
			<c:if test="${!empty message}">
				alert("${message}");
			</c:if>
		</script>

</body>
</html>