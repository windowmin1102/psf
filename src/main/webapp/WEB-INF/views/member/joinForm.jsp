<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>공공체육시설정보조회시스템</title>
<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/joinForm.css" />
</head>
<body>
	<header>
		<%@ include file="../member/header.jsp"%>
	</header>

	<main>
		<form:form action="${cp}/member/join" method="post"
			commandName="member">
			<div id="joinForm">
				<p id="fontVHard"> SING UP </p>
				<br>
				<table>
					<tr>
						<td>
							<p id="fontHard">NAME</p>
						</td>
						<td><form:input id="joinName" path="memName" /></td>
					</tr>
					<tr>
						<td>
							<p id="fontHard">ID</p>
						</td>
						<td><form:input id="joinId" path="memId" /></td>
					</tr>
					<tr>
						<td>
							<p id="fontHard">PW</p>
						</td>
						<td><form:input type="password" id="joinPw" path="memPw" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" id="fontHard"
							value="Join"></td>
					</tr>
				</table>
			</div>
		</form:form>

		<script type="text/javascript">
			<c:if test="${!empty message}">
				alert("${message}");
			</c:if>
		</script>
	</main>
</body>
</html>