<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
<script src='${cp}/resources/libs/v6.6.1-dist/ol.js'></script>

<!-- The line below is only needed for old environments like Internet Explorer and Android 4.x -->
<script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<link rel="stylesheet" type="text/css"
	href="${cp}/resources/css/articleMap.css" />
<link rel="stylesheet" href="${cp}/resources/libs/v6.6.1-dist/ol.css" />
</head>
<body>
	<script>
		var psfs = [];
		
		//객체 psf 객체 생성자 
		var Psf = function(psfId, psfAdministrativeDivision, psfName,
				psfLatitude, psfLongitude, psfAddress, psfFacilityType,
				psfManagementDepartment, psfMangementGroup, psfPhoneNumber) {
			this.psfId = psfId;
			this.psfAdministrativeDivision = psfAdministrativeDivision;
			this.psfName = psfName;
			this.psfLatitude = psfLatitude;
			this.psfLongitude = psfLongitude;
			this.psfAddress = psfAddress;
			this.psfFacilityType = psfFacilityType;
			this.psfManagementDepartment = psfManagementDepartment;
			this.psfMangementGroup = psfMangementGroup;
			this.psfPhoneNumber = psfPhoneNumber;

			return this;
		}
		
		// psfs 초기화
		<c:if test="${not empty psfs}">
			<c:forEach var="pubSpoFac" items="${psfs}">
				psfs.push(new Psf("${pubSpoFac.psfId}", "${pubSpoFac.psfAdministrativeDivision}", 
						"${pubSpoFac.psfName}", "${pubSpoFac.psfLatitude}", 
						"${pubSpoFac.psfLongitude}", "${pubSpoFac.psfAddress}", 
						"${pubSpoFac.psfFacilityType}", "${pubSpoFac.psfManagementDepartment}", 
						"${pubSpoFac.psfMangementGroup}", "${pubSpoFac.psfPhoneNumber}"));
			</c:forEach>	
		</c:if>
	</script>

	<div id="map"></div>

	<script type="text/javascript">
		//맵 생성
    	var map = new ol.Map({
    	    view: new ol.View({
    	        zoom: 7,
				minZoom: 7,
				maxZoom: 18,
			
    		    center: new ol.geom.Point([ 127, 37 ]) //처음 중앙에 보여질 경도, 위도 
    		    .transform('EPSG:4326', 'EPSG:3857') //GPS 좌표계 -> 구글 좌표계
    		    .getCoordinates(), //포인트의 좌표를 리턴함
    		}),
    	
    		target: 'map',
			controls: ol.control.defaults({
				attribution: false, //저작권 표시? 안보이게 하기
				zoom: false,	// +- 안보이게 하기		
			}), 
    	    
    		layers: [
    			new ol.layer.Tile({
    	 		   	source : new ol.source.XYZ({ 
    	   	   		url: 'https://api.vworld.kr/req/wmts/1.0.0/1AD86B4F-B802-38E8-919C-75ACF03E2A97/Base/{z}/{y}/{x}.png',
    	        	//tileGrid: tileGrid
					})
				})
    		]
		});


		//검색된 select 마커 찍기
		if(psfs[0] != null) {
			var isPsfDT = isPsfDataType(psfs);
			console.log(isPsfDT);
			map.getView().setZoom(10);
			if(isPsfDT == "userALL" || isPsfDT == "user") {
				navigator.geolocation.getCurrentPosition(function(pos) {
			    	var userLatitude = pos.coords.latitude;
			    	var userLongitude = pos.coords.longitude;
			    	
		   	    	map.getView().setCenter(new ol.geom.Point([userLongitude, userLatitude])
		   		    .transform('EPSG:4326', 'EPSG:3857') 
		   		    .getCoordinates());
			    
					if(isPsfDT == "userALL") {				//select1: 내위치 가까운 체육시설 5곳, select2: All
						getUserMarker(userLongitude, userLatitude);
						var psfsfootballPsfs = [];
						var psfsbaseballPsfs = [];
						var psfstennisPsfs = [];
						
						for(var i=0; i< psfs.length; i++) {
							if(psfs[i].psfFacilityType == "테니스장") {
								psfstennisPsfs.push(psfs[i]);
							}else if(psfs[i].psfFacilityType == "축구장") {
								psfsfootballPsfs.push(psfs[i]);
							}else if(psfs[i].psfFacilityType == "야구장") {
								psfsbaseballPsfs.push(psfs[i]);
							}
						}
									
						//내 위치로 부터 가까운 야구장 5곳
						var userPsfDistance = getUserPsfDistance(psfsbaseballPsfs, userLatitude, userLongitude);	
						var psfsbaseballTopFive = getPsfsTopFive(userPsfDistance, psfsbaseballPsfs);	
						
						//내 위치로 부터 가까운 축구장 5곳
						userPsfDistance = getUserPsfDistance(psfsfootballPsfs, userLatitude, userLongitude);	
						var psfsfootballTopFive = getPsfsTopFive(userPsfDistance, psfsfootballPsfs);	
						addMarkers(psfsfootballTopFive, isPsfDT);
						
						//내 위치로 부터 가까운 테니스장 5곳
						userPsfDistance = getUserPsfDistance(psfstennisPsfs, userLatitude, userLongitude);	
						var psfstennisTopFive = getPsfsTopFive(userPsfDistance, psfstennisPsfs);
			
						addMarkers(psfsbaseballTopFive, isPsfDT);
						addMarkers(psfsfootballTopFive, isPsfDT);
						addMarkers(psfstennisTopFive, isPsfDT);
						
					} else if(isPsfDT == "user") { //select1: 내위치 가까운 체육시설 5곳, select2: (야구장, 축구장, 테니스장) 1택
						getUserMarker(userLongitude, userLatitude);	//유저 마커를 찍는다.
						var userPsfDistance = getUserPsfDistance(psfs, userLatitude, userLongitude);	//거리를 구한다
						var psfsTopFive = getPsfsTopFive(userPsfDistance, psfs);	//가까운 거리 5개의 선정한다.

						addMarkers(psfsTopFive, isPsfDT);						
					}
				});

			} else if(isPsfDT == "etc") {	//나머지
				mapZoomAndCenter(psfs[0].psfAdministrativeDivision);
	   		 	addMarkers(psfs, isPsfDT); //select1,2 에서 넘어오는 조건들로 마커들을 생성한다. 
			}
		}
		markerPsfData = null;
		//마커 클릭 시
		map.on( "singleclick", function(e) {
			map.forEachFeatureAtPixel(e.pixel, function (feature, layer) {
				markerPsfData = feature.getProperties();
				if(markerPsfData.psfFacilityType != "user"){
					
					//마커 클릭한 좌표 중심으로
	   	    		map.getView().setCenter(new ol.geom.Point([markerPsfData.psfLongitude, markerPsfData.psfLatitude])
	   		    	.transform('EPSG:4326', 'EPSG:3857') 
	   		    	.getCoordinates());
				
					getPsfMarkerInFor(markerPsfData);
				
					var reviews = document.getElementsByClassName("reviews")[0];
				
					//불어온 마커에 대한 리뷰 목록을 지운다.
					while (reviews.firstChild) {
						reviews.removeChild(reviews.firstChild);
					}
				
				
				$.ajax({ url: '${cp}/review/AttachPsfList', 
					data: JSON.stringify(markerPsfData.psfId), 
					type: 'POST', 
					dataType: 'json', 
					contentType: "application/json", 
					success : function(rvs) {
						//리뷰ㅜ 
				    	document.getElementsByClassName("gradeN")[0].textContent = parseFloat(Average(rvs)).toFixed(2)+"/5";
						
						for(var i=0; i < rvs.length; i++) {
							var review = document.createElement('div');
							review.id = 'reViewList';
							if(rvs[i].modfiedOrNot == 'Y') {
								review.innerHTML =
									"<span id='fontHard'>&nbsp;"+rvs[i].member.memName+"</span> <span id='fontNormal'>("+rvs[i].member.memId+")&nbsp;&nbsp;&nbsp;</span>" +
									"<span id='fontEasy'>"+rvs[i].reviewDate+"</span>" +
									"<span id='fontEasy'>(수정됨)</span><br />" +
									"<span id='fontHard'>&nbsp;평점:</span> <span id='fontNormal'>"+rvs[i].reviewGrade+"</span> <br />" +
									"<hr id='dotted'>" +
									"<br />" +
									"<p id='fontNormal'>&nbsp;"+rvs[i].reviewContent+"</p>" +
									"<br />";
							} else if(rvs[i].modfiedOrNot == 'N'){
								review.innerHTML =
									"<span id='fontHard'>&nbsp;"+rvs[i].member.memName+"</span> <span id='fontNormal'>("+rvs[i].member.memId+")&nbsp;&nbsp;&nbsp;</span>" +
									"<span id='fontEasy'>"+rvs[i].reviewDate+"</span><br />" +
									"<span id='fontHard'>&nbsp;평점:</span> <span id='fontNormal'>"+rvs[i].reviewGrade+"</span> <br />" +
									"<hr id='dotted'>" +
									"<br />" +
									"<p id='fontNormal'>&nbsp;"+rvs[i].reviewContent+"</p>" +
									"<br />";
							}	
							
							reviews.appendChild(review); //부모에 붙이기
						}

					}
				});
				

				}	
				
			});
		});
		  
		function addMarkers(psfs, isPsfDT) {
			var psfFT = null;
			
			for (let i = 0; i < psfs.length; i++) {
				// 야구장, 축구장, 테니스장 등 중복마커레이어 놉
				if(psfFT != psfs[i].psfFacilityType || isPsfDT != "etc") {
					
					psfFT = psfs[i].psfFacilityType;
					
					if(isPsfDT == "etc") {
						var vectorLayer = getVectorLayer(psfs[i].psfFacilityType, 0.06, "");
					} else {
						var vectorLayer = getVectorLayer(psfs[i].psfFacilityType, 0.03	, i+1);
					}
					map.addLayer(vectorLayer);	//백터레이어를 맵에 달아준다.
			
				}
				//addFeature를 이용해서, 여러개의 point를 source에 담는다.
				vectorLayer.getSource().addFeature(createMarker(psfs[i].psfLongitude, psfs[i].psfLatitude, psfs[i])); 
			}
		}
		
		function getVectorLayer(psfFType, size, mNum) {
			// 마커 레이어 생성
			var vectorLayer = new ol.layer.Vector({
		    	source: new ol.source.Vector(),
		    	style: new ol.style.Style({
					image: new ol.style.Icon({ //마커 이미지
						opacity: 1, //투명도 1=100% 
						scale: size, //크기 1=100%
		            	//anchor: [0.05, 1],
		            	//anchorXUnits: "fraction",
		       			//anchorYUnits: "fraction",
		      	   		src: markerType(psfFType, mNum) //marker 이미지, 해당 point를 marker로 변경한다.
		     		})
		  	  	})
			});
			return vectorLayer;
		}
		
		
		//마커 그림 그리기
		function createMarker(lng, lat, psf) {
		    return new ol.Feature({
		    	//Point 좌표 등록
		    	geometry: new ol.geom.Point(ol.proj.fromLonLat([parseFloat(lng), parseFloat(lat)])),
				psfId: psf.psfId,
				psfAdministrativeDivision: psf.psfAdministrativeDivision,
				psfName: psf.psfName,
				psfAddress: psf.psfAddress,
				psfFacilityType: psf.psfFacilityType,
				psfManagementDepartment: psf.psfManagementDepartment,
				psfMangementGroup: psf.psfMangementGroup,
				psfPhoneNumber: psf.psfPhoneNumber,
				psfLongitude: psf.psfLongitude,
				psfLatitude: psf.psfLatitude
		    });
		}
		
		//마커타입이 뭔지
		function markerType(psfFacilityType, mNum) {

			if(psfFacilityType == '야구장') {
				return '../resources/img/marker/baseballMarker'+String(mNum)+'.png';
			}else if(psfFacilityType == '축구장') {
				return '../resources/img/marker/footballMarker'+String(mNum)+'.png';
			}else if(psfFacilityType == '테니스장') {
				return '../resources/img/marker/tennisMarker'+String(mNum)+'.png';
			}else if(psfFacilityType == 'user') {
				return '../resources/img/marker/userMarker.png';
			}
			return null;
		}
		
		//select1 값이 누군지? ex) 내위치, 경상북도 .... 등등
		function isPsfDataType(psfs) {
			var psfADCount= 0;
			var psfFTCount= 0;
			var psfAD= null;
			var psfFT= null;
			
			for (let i = 0; i < psfs.length; i++) {
					if(psfAD != psfs[i].psfAdministrativeDivision) {
						psfADCount = psfADCount + 1;
						psfAD = psfs[i].psfAdministrativeDivision;

					}
					if(psfFT != psfs[i].psfFacilityType) {
						psfFTCount = psfFTCount + 1;
						psfFT = psfs[i].psfFacilityType;
					}
			}

			if(psfADCount >= 15 && psfFTCount >= 3) {;
				return "userALL";

			} else if(psfADCount >= 15) {
				return "user";
				
			} else {	
				return "etc";
			} 
			
			return null;
		}
		//사용자
		function getUserMarker(lon,lat) {
			var vectorLayer = getVectorLayer("user", 0.08,"");
			map.addLayer(vectorLayer);
			vectorLayer.getSource().addFeature(createMarker(lon, lat,new Psf(null,null,null,lat,lon,null,"user",null,null,null))); 
		}
		
		//두 좌표 거리 구하기
		function getDistanceFromLatLonInKm(lat1,lng1,lat2,lng2) { 
			function deg2rad(deg) { 
				return deg * (Math.PI/180) 
			} 
			var R = 6371; // Radius of the earth in km
			var dLat = deg2rad(lat2-lat1); // deg2rad below
			var dLon = deg2rad(lng2-lng1);
			var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
			var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
			var d = R * c; // Distance in km 
			
			return d; 
		}
		
		//유저와 경기장 거리 구하기
		function getUserPsfDistance(psfs, userLat, userLon) {
			var userPsfDistance = [];
			
			for(var i=0; i < psfs.length; i++) {
				userPsfDistance.push(getDistanceFromLatLonInKm(userLat, userLon, psfs[i].psfLatitude, psfs[i].psfLongitude));
			}
	
			return userPsfDistance;
		}
		
		//거리가 가까운 5개 리스트를 뽑는다.
		function getPsfsTopFive (UserPsfD, psfs) {
			
			var temp1 = 0;
			var temp2 = null;
			var psfsTopFive = [];

			for(var i=0; i < 5; i++) {
			    for(var j = i + 1; j < UserPsfD.length; j++) {
			        if(UserPsfD[i] > UserPsfD[j]) {
			            temp1 = UserPsfD[i]; 
			            UserPsfD[i] = UserPsfD[j]; 
			            UserPsfD[j] = temp1; 
						
						temp2 = psfs[i];
						psfs[i] = psfs[j];
						psfs[j] = temp2;
			        }
			    }
			    psfsTopFive.push(psfs[i]);
			    //console.log(psfsTopFive[i].psfLongitude, psfsTopFive[i].psfLatitude);
			}
			return psfsTopFive;
		}
		
		//마커클릭시 마커에 대한 정보를 side에 출력
		function getPsfMarkerInFor(markerPsfData) {
		    if(markerPsfData.psfFacilityType != "user") {	    	
		   		document.getElementsByClassName("psfImg")[0].style.display = "block";
		    	if(markerPsfData.psfFacilityType == "야구장") {
		    		document.getElementsByClassName("img")[0].src = "../resources/img/infor/baseball.jpg";
		    	} else if(markerPsfData.psfFacilityType == "축구장") {
		    		document.getElementsByClassName("img")[0].src = "../resources/img/infor/football.jpg";
		    	} else if(markerPsfData.psfFacilityType == "테니스장") {
		    		document.getElementsByClassName("img")[0].src = "../resources/img/infor/tennis.jpg";
		    	}

		    	document.getElementsByClassName("psfInfor")[0].style.display = "block";
		    	document.getElementsByClassName("psfInfor")[0].style.background = "#FFFFFF";
		    	document.getElementsByClassName("psfName")[0].textContent = markerPsfData.psfName;
		    	document.getElementsByClassName("psfFacilityType")[0].textContent = markerPsfData.psfFacilityType;
		    	document.getElementsByClassName("address")[0].textContent = "도로명주소";
		    	document.getElementsByClassName("psfAddress")[0].textContent = markerPsfData.psfAddress;
		    	document.getElementsByClassName("mangement")[0].textContent = "관리자";
		    	document.getElementsByClassName("psfManagementDepartment")[0].textContent = "("+markerPsfData.psfManagementDepartment+")";
		    	document.getElementsByClassName("psfMangementGroup")[0].textContent = markerPsfData.psfMangementGroup;
		    	document.getElementsByClassName("phoneNum")[0].textContent = "전화번호";
		    	document.getElementsByClassName("psfPhoneNumber")[0].textContent = markerPsfData.psfPhoneNumber;
		    	
				//평점
		    	document.getElementsByClassName("psfReViewW")[0].style.display = "block";
		    	document.getElementsByClassName("psfReViewW")[0].style.background = "#FFFFFF";
		    	document.getElementsByClassName("reView")[0].textContent = "Reviews";
		    	document.getElementsByClassName("grade")[0].textContent = "평점";
		    	document.getElementsByClassName("gradeN")[0].textContent = "작성된 리뷰가 없습니다.";
		    	document.getElementsByClassName("reviewBtn")[0].style.visibility = "visible";
		    	
		    	}
			}

		// select1 지역 선택 시, 그 지역 중앙 좌표 세팅.....
		function mapZoomAndCenter(psfAdministrativeDivision) {
			console.log(psfAdministrativeDivision);
			if(psfAdministrativeDivision == "서울특별시") {
	   	    	map.getView().setCenter(new ol.geom.Point([126.97, 37.56])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "경기도") {
	   	    	map.getView().setCenter(new ol.geom.Point([127.190292, 37.567167])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "세종특별자치시") {
	   	    	map.getView().setCenter(new ol.geom.Point([127.2494855, 36.5040736])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "인천광역시") {
	   	    	map.getView().setCenter(new ol.geom.Point([126.7, 37.45])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "광주광역시") {
	   	    	map.getView().setCenter(new ol.geom.Point([126.85, 35.16])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "대구광역시") {
	   	    	map.getView().setCenter(new ol.geom.Point([128.6, 35.87])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());
	   	    	
			}else if(psfAdministrativeDivision == "대전광역시") {
	   	    	map.getView().setCenter(new ol.geom.Point([127.38, 36.35])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());
	   	    	
			}else if(psfAdministrativeDivision == "부산광역시") {
	   	    	map.getView().setCenter(new ol.geom.Point([129.07, 35.18])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());
	   	    	
			}else if(psfAdministrativeDivision == "울산광역시") {
	   	    	map.getView().setCenter(new ol.geom.Point([129.31, 35.53])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "전라남도") {
	   	    	map.getView().setCenter(new ol.geom.Point([126.893113, 34.819400])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "전라북도") {
	   	    	map.getView().setCenter(new ol.geom.Point([127.144185, 35.716705])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "경상남도") {
	   	    	map.getView().setCenter(new ol.geom.Point([128.664734, 35.259787])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "경상북도") {
	   	    	map.getView().setCenter(new ol.geom.Point([128.664734, 36.248647])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "충청남도") {
	   	    	map.getView().setCenter(new ol.geom.Point([126.779757, 36.557229])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "충청북도") {
	   	    	map.getView().setCenter(new ol.geom.Point([127.929344, 36.628503])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "강원도") {
	   	    	map.getView().setCenter(new ol.geom.Point([128.209315, 37.555837])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());

			}else if(psfAdministrativeDivision == "제주특별자치도") {
	   	    	map.getView().setCenter(new ol.geom.Point([126.542671, 33.364805])
	   		    .transform('EPSG:4326', 'EPSG:3857') 
	   		    .getCoordinates());
			}
		}
		
		//평점 평균 계산
		function Average(rvs){
			var gAverage = null;
			var gAverageCount =0;
			
			for(var i=0; i< rvs.length; i++) {
				gAverageCount = gAverageCount + rvs[i].reviewGrade;
			}
			
			gAverage = gAverageCount/rvs.length;
			
			return gAverage;
		}
	</script>
</body>
</html>