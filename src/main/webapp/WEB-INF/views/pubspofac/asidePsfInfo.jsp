<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/asidePsfInfo.css" />
</head>
<body>
	<div id="img" class="psfImg">
		<img class="img" />
	</div>

	<div id="infor" class="psfInfor">
		<p id="fontHard" class="psfName"></p>
		<p id="fontEasy" class="psfFacilityType"></p>
		<br>
		<hr>
		<p id="fontHard" class="address"></p>
		<p id="fontNormal" class="psfAddress"></p>
		<p id="fontHard" class="mangement"></p>
		<p id="fontNormal" class="psfMangementGroup">
		<p id="fontEasy" class="psfManagementDepartment" />
		<p id="fontHard" class="phoneNum"></p>
		<p id="fontNormal" class="psfPhoneNumber"></p>
	</div>

	<div id="reViewW" class="psfReViewW">
		<p id="fontVHard" class="reView"></p>
		<br>
		<p id="fontHard" class="grade"></p>
		<p id="fontNormal" class="gradeN"></p>
		<br>
		<hr>
		<br> <input type="submit" id="fontHard" class="reviewBtn"
			onclick="openPopUp()" value="Write a review" />
		<p></p>
		<br>
	</div>
	
	<div class="reviews"></div>

	<script type="text/javascript">
	var popupX = (document.body.offsetWidth / 2) - (500 / 2);
	var popupY= (window.screen.height / 2) - (500 / 2);

	function openPopUp() {
		<c:if test="${!empty sessionScope.member}">
		 	window.open("${cp}/review/reviewForm?id="+markerPsfData.psfId+ "&name="+markerPsfData.psfName,'reviewWrite','width=500, height=500, left='+ popupX + ', top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY + ', scrollbars=no, resizable=no, toolbars=no, menubar=no');
		 </c:if>
		 <c:if test="${empty sessionScope.member}">
		 	alert("로그인 후 이용 가능합니다.");
		 </c:if>
	}
	</script>
</body>
</html>