<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/reviewForm.css" />
</head>
<body>
	<div id="wrap">
		<form:form action="${cp}/review/reviewWrite" method="post"
			commandName="review">
			<form:hidden path="psf.psfId" value="${reviewId}" />
			<form:hidden path="member.memId" value="${sessionScope.member.memId}" />

			<table>
				<tr>
					<td id="title">
						<p>${reviewName}</p>
					</td>
				</tr>
				<tr>
					<td id="user">
						<p>${sessionScope.member.memName}(${sessionScope.member.memId})</p>
					</td>
				</tr>
				<tr>
					<td id="grade">

						<div class="starpoint_wrap">
							<br>
							<div class="starpoint_box">
								<label for="starpoint_1" class="label_star" title="0.5"><span
									class="blind">0.5점</span></label> <label for="starpoint_2"
									class="label_star" title="1"><span class="blind">1점</span></label>
								<label for="starpoint_3" class="label_star" title="1.5"><span
									class="blind">1.5점</span></label> <label for="starpoint_4"
									class="label_star" title="2"><span class="blind">2점</span></label>
								<label for="starpoint_5" class="label_star" title="2.5"><span
									class="blind">2.5점</span></label> <label for="starpoint_6"
									class="label_star" title="3"><span class="blind">3점</span></label>
								<label for="starpoint_7" class="label_star" title="3.5"><span
									class="blind">3.5점</span></label> <label for="starpoint_8"
									class="label_star" title="4"><span class="blind">4점</span></label>
								<label for="starpoint_9" class="label_star" title="4.5"><span
									class="blind">4.5점</span> </label> <label for="starpoint_10"
									class="label_star" title="5"><span class="blind">5점</span></label>
								<form:radiobutton path="reviewGrade" value="0.5" 
									name="starpoint" id="starpoint_1" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="1" 
									name="starpoint" id="starpoint_2" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="1.5" 
									name="starpoint" id="starpoint_3" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="2" 
									name="starpoint" id="starpoint_4" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="2.5" 
									name="starpoint" id="starpoint_5" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="3" 
									name="starpoint" id="starpoint_6" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="3.5" 
									name="starpoint" id="starpoint_7" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="4" 
									name="starpoint" id="starpoint_8" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="4.5" 
									name="starpoint" id="starpoint_9" class="star_radio" />
								<form:radiobutton path="reviewGrade" value="5" 
									name="starpoint" id="starpoint_10" class="star_radio" />
								<span class="starpoint_bg"></span>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td id="write"><form:textarea path="reviewContent" 
							class="form-control" rows="10" id="content" name="content"
							placeholder="내용 작성" /></td>
				</tr>
				<tr>
					<td id="btn"><input type="submit" id="btnS" value="게시" /> <input
						type="button" id="btnS" onClick="closeTabClick()" value="취소" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	
	<script type="text/javascript">
		<c:if test="${!empty message}">
				if("${message}" == "success") {
					alert("등록되었습니다.");
					window.close(); 
				} else {
					alert("${message}");
				}
		</c:if>
	
		function closeTabClick() { 
			window.close(); 
		}
		

	</script>
</body>
</html>