<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>아동 계정 관리</title>
</head>
<body>
<%@ include file = "sidebar.jsp" %>
<div class="w3-row">
	<div class="w3-col w3-hide-small w3-hide-middle l1">&nbsp;</div>
	<div class="w3-col s12 m12 l10" style="font-weight:bold;font-size:1.2em;padding-left:1em;"><img src="./image/manage.png" style="width:20px;">     아동 계정 관리</div>
	<div class="w3-col w3-hide-small w3-hide-middle l1">&nbsp;</div>
</div>
<div class="w3-row">
	<div class="w3-col w3-hide-small w3-hide-middle l1">&nbsp;</div>
	<div class="w3-col s12 m12 l10">
		<div style="width:100%;margin-bottom:20px;">
			<div class="search w3-right" style="width: 300px;">
				<input class="w3-border" type="text" placeholder="아동 이름 검색" style="width: 85%;border: 1px solid #bbb;border-radius: 8px;padding: 10px 12px;font-size:0.8em;">
				<button style="height:42px;"><img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" style="width:17px;"></button>
			</div>
			<div class="buttons" style="width:100%;transform:translateY(0.3em);">
				<button class="w3-button w3-right" style="background-color:#51459E; color:white; font-size:0.8em;margin-right:0.2em;" onclick="deleteChild()">선택 계정 삭제</button>
				<button class="w3-button w3-right" style="background-color:#51459E; color:white; font-size:0.8em;margin-right:0.2em;" onclick="updateChild()">선택 계정 수정</button>
				<button class="w3-button w3-right" style="background-color:#51459E; color:white; font-size:0.8em;margin-right:0.2em;" onclick="location.href='register.jsp?role=child';">아동 계정 생성</button>
			</div>
		</div>

		<%
			ArrayList<User> currUserList = (ArrayList<User>)request.getAttribute("currUserList");
			int currPageNum = (int)request.getAttribute("currPageNum");
			int blockRange = UserPaging.getBlockRange();

		%>
		<form method="post" id="manageFrm">
			<input type="hidden" id="latestChildId" name="latestChildId" value="<%=currUserList.get(0).getUserId()%>"/>
			<div style="font-size:0.5em;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 정렬 기준 : 등록일 순</div>
			<div class="w3-container">
				<table class="w3-table-all w3-hoverable" style="font-size:0.8em;">
					<thead>
					<tr class="w3-light-grey">
						<th><input type="checkbox" name="childId" id="checkChildIdAll" value="0" onclick="selectChildAll(this)"/></th>
						<th>NO.</th>
						<th>이름</th>
						<th>아이디</th>
						<th>생년월일</th>
						<th>성별</th>
						<th>등록일</th>
						<th>이메일</th>
					</tr>
					</thead>
					<%for (int i =0;i<currUserList.size();i++){
					%>
					<tr>
						<td><input type="checkbox" name="childId" value="<%=currUserList.get(i).getUserId()%>" id="check" onclick="setLatestChildId(<%=currUserList.get(i).getUserId()%>)"/></td>
						<td><%=(currPageNum-1)*UserPaging.getListRange()+i+1%></td>
						<td><%=currUserList.get(i).getUserName() %></td>
						<td><%=currUserList.get(i).getUserLoginId() %></td>
						<td><%=currUserList.get(i).getUserBirth() %></td>
						<td><%=currUserList.get(i).getUserGenderKr() %></td>
						<td><%=currUserList.get(i).getRegistrationDate() %></td>
						<td><%=currUserList.get(i).getUserEmail() %></td>
					</tr>
					<% }%>
				</table>
			</div>
			<div class="w3-center">
				<div class="w3-bar">
					<c:set var="uPaging" scope="page" value="${requestScope.userPaging}" />
					<c:set var="curPageNum" scope="page" value="${requestScope.currPageNum}" />
					<c:set var="blockRange" scope="page" value="<%=blockRange%>" />
					<c:if test="${curPageNum > blockRange}">
						<a href="GetManageChild?curPage=${uPaging.blockStartNum - 1}" class="w3-button">&laquo;</a>
					</c:if>
					<c:forEach var="i" begin="${uPaging.blockStartNum}" end="${uPaging.blockEndNum}">
						<c:choose>
							<c:when test="${i>uPaging.lastPageNum}"></c:when>
							<c:when test="${i==curPageNum}">
								<a href="#" class="w3-button w3-gray">${i}</a>
							</c:when>
							<c:otherwise>
								<a href="GetManageChild?curPage=${i}" class="w3-button">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${uPaging.lastPageNum > uPaging.blockEndNum}">
						<a href="GetManageChild?curPage=${uPaging.blockEndNum + 1}" class="w3-button">&raquo;</a>
					</c:if>
					<c:remove var="uPaging" scope="page"/>
					<c:remove var="curPageNum" scope="page"/>
				</div>
			</div>
		</form>
	</div>
	<div class="w3-col w3-hide-small w3-hide-middle l1">&nbsp;</div>
</div>
<script>
	function deleteChild(){
		const childCnt = document.querySelectorAll('input[name="childId"]:checked').length;
		if(childCnt==0){
			alert("아동을 선택해주세요.");
		}else{
			if(confirm("선택한 모든 아동을 삭제합니다.")){
				const deleteFrm = document.getElementById('manageFrm');
				deleteFrm.setAttribute("action", "DeleteUser")
				deleteFrm.submit();
			}
		}
	}
	function updateChild(){
		const childCnt = document.querySelectorAll('input[name="childId"]:checked').length;
		if(childCnt==0){
			alert("아동을 선택해주세요.");
		}else if(childCnt==1){
			const updateFrm = document.getElementById('manageFrm');
			updateFrm.setAttribute("action", "GetUpdateUser")
			updateFrm.submit();
		}else{
			if(confirm("마지막으로 선택한 아동의 계정을 수정합니다.")){
				const updateFrm = document.getElementById('manageFrm');
				updateFrm.setAttribute("action", "GetUpdateUser")
				updateFrm.submit();
			}
		}
	}
	function setLatestChildId(childId){
		const latestChildIdInput = document.getElementById("latestChildId");
		latestChildIdInput.value = childId;
	}
	function selectChildAll(selectChildAll){
		const checkboxes = document.getElementsByName('childId');
		checkboxes.forEach((checkbox) => {
			checkbox.checked = selectChildAll.checked;
		})
	}
</script>
</body>
</html>