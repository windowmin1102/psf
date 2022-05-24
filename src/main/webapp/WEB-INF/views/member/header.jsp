<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/header.css" />
</head>
<body>
	<table>
		<tr>
			<td id="title"><a href="${cp}/">&nbsp;PSF Information
					Inquiry System</a></td>

			<c:if test="${empty sessionScope.member}">
				<td id="welcome"></td>
				<td id="myInfo"><a href="${cp}/member/joinForm"> SING UP </a></td>
				<td id="logInOut"><a href="${cp}/member/loginForm"> LOGIN </a></td>
			</c:if>

			<c:if test="${!empty sessionScope.member}">
				<td id="welcome">
					<p id="fontHard">Welcome To "${sessionScope.member.memName}"</p>
				</td>
				<td id="myInfo"><a href="${cp}/member/myInfoForm"> MYINFO </a></td>

				<td id="logInOut"><a href="${cp}/member/logout"> LOGOUT </a></td>
			</c:if>
		</tr>
	</table>

</body>
</html>