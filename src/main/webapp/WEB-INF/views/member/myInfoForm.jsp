<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	href="${cp}/resources/css/myInfoForm.css" />
</head>
<body>
	<header>
		<%@ include file="../member/header.jsp"%>
	</header>

	<main>
		<div id="myInfoForm">
			<p id="fontVHard"> MYINFO </p>
			<br>
			<table>
				<tr>
					<td><input type="submit" id="fontHard"  onclick="location.href='${cp}/review/myReviewForm'" value="My ReView List"></td>
				</tr>
				<tr>
					<td><input type="submit" id="fontHard" onclick="location.href='${cp}/member/modifyForm'" value="My Info Modify"></td>
				</tr>
				<tr>
					<td><input type="submit" id="fontHard"  onclick="location.href='${cp}/member/removeForm'" value="Membership Withdrawal"></td>
				</tr>
			</table>
		</div>
	</main>
</body>
</html>