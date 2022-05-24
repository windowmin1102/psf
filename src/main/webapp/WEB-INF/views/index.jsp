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
</head>

<body>
	<header>
		<%@ include file="./member/header.jsp"%>
	</header>

	<main>
		<aside id="aside">
			<%@ include file="./pubspofac/asidePsfInfo.jsp"%>
		</aside>

		<section>
			<nav>
				<%@ include file="./pubspofac/navSearch.jsp"%>
			</nav>

			<article>
				<%@ include file="./pubspofac/articleMap.jsp"%>
			</article>
		</section>
	</main>
</body>
</html>
