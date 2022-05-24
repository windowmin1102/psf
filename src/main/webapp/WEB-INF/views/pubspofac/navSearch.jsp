<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/navSearch.css" />
</head>

<body>
	<table>

		<tr>
			<form:form action="${cp}/pubspofac/PsfDatas" method="post"
				commandName="ps">
				<td>
					<p id="fontVHard">SELECT 1</p>
				</td>
				<td><form:select path="pSelectOne">
						<form:option value="user" label="내위치(가까운체육시설 5개)" />
						<form:option value="서울특별시" label="서울특별시" />
						<form:option value="경기도" label="경기도" />
						<form:option value="세종특별자치시" label="세종특별자치시" />
						<form:option value="인천광역시" label="인천광역시	" />
						<form:option value="광주광역시" label="광주광역시" />
						<form:option value="대구광역시" label="대구광역시" />
						<form:option value="대전광역시" label="대전광역시" />
						<form:option value="부산광역시" label="부산광역시" />
						<form:option value="울산광역시" label="울산광역시" />
						<form:option value="전라남도" label="전라남도" />
						<form:option value="전라북도" label="전라북도" />
						<form:option value="경상남도" label="경상남도" />
						<form:option value="경상북도" label="경상북도" />
						<form:option value="충청남도" label="충청남도" />
						<form:option value="충청북도" label="충청북도" />
						<form:option value="강원도" label="강원도" />
						<form:option value="제주특별자치도" label="제주특별자치도" />
					</form:select></td>
				<td>
					<p id="fontVHard">SELECT 2</p>
				</td>
				<td><form:select path="pSelectTwo">
						<form:option value="ALL" label="ALL" />
						<form:option value="축구장" label="축구장" />
						<form:option value="테니스장" label="테니스장" />
						<form:option value="야구장" label="야구장" />
					</form:select></td>

				<td><input type="submit" id="fontVHard" value="SEARCH"></input>
				</td>
			</form:form>
		</tr>
	</table>
</body>
</html>