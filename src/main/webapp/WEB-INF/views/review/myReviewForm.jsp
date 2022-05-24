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
	href="${cp}/resources/css/myReviewForm.css" />
</head>
<body>
	<header>
		<%@ include file="../member/header.jsp"%>
	</header>

	<main>
		<div id="myReviewForm">

			<p id="fontVHard">My ReView List</p>
			<br>
			<div id="myReviewList">
				<c:if test="${!empty reviews}">
					<c:forEach var="rv" items="${reviews}">
						<form:form action="${cp}/review/reviewModifyForm" method="post"
							commandName="review">
							<form:hidden path="reviewId" value="${rv.reviewId}" />
							<form:hidden path="reviewContent" value="${rv.reviewContent}" />
							<form:hidden path="psf.psfName" value="${rv.psf.psfName}" />

							<div id="myReview">
								<p id="fontHard">&nbsp;${rv.psf.psfName} |
									${rv.psf.psfAdministrativeDivision} | ${rv.psf.psfFacilityType}</p>
								<p id="fontNormal">
									&nbsp;위도: ${rv.psf.psfLatitude} | 경도: ${rv.psf.psfLongitude} <span
										id="fontEasy">&nbsp;평점:${rv.reviewGrade} |
										${rv.reviewDate}</span>
									<c:if test="${rv.modfiedOrNot eq 'Y'}">
										<span id="fontEasy">(수정됨)</span>
									</c:if>
									<br>
								<hr id="dotted" />
								<br>
								<p id="fontNormal">&nbsp;${rv.reviewContent}</p>
								<br>
								<hr id="dotted" />
								<input type="submit" id="rBtn" value="수정" /> <input
									type="button" id="rBtn"
									onclick="location.href='${cp}/review/reviewRemove?rid=${rv.reviewId}'"
									value="삭제" />
							</div>
						</form:form>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</main>
</body>
</html>