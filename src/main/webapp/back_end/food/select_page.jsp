<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>






<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>餐點管理</title>


<style type="text/css">
	body {
	margin: 20px auto;
	padding: 20px;
	width: 80%;
	border-radius: 25px;
	justify-content: center;
	padding: 100px;
	background-color: rgb(0 0 0/ 60%);
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	left: 150px;
	border: 2px solid white;
	margin-left: 80px;
	color:white;
}
	form {
	margin: 20px auto;
	padding: 20px;
	width: 110%;
	border-radius: 25px;
	justify-content: center;
	padding: 100px;
	background-color: rgb(0 0 0/ 60%);
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	left: 150px;
	border: 2px solid white;
	margin-left: 80px;
	font-size: 25px;
	color:white;
	
}

input[type="text"], input[type="date"], select {
	width: calc(100% - 15px);
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	 color: white;
}



input[type="submit"] {
  background-color: grey;
  border-radius: 15px;
  background-color: rgba(162, 161, 167, 0.5);
  font-size: 30px;
  width: 150px;
  
  cursor:pointer;
  transition-duration: 0.5s;
  color: white;
}

input[type="submit"]:hover {
	background-color: rgba(87, 87, 89, 0.8);
}

ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

li {
	color: red;
	margin-bottom: 5px;
}

h4{
  font-size: 30px;
}

h4{
  font-size: 30px;
}

#a{
  font-size: 30px;
  color: white; /* 设置背景颜色为蓝色 */
}

select {
  appearance: none;
  -webkit-appearance: none;
 width:calc(100% - 15px);
  padding: 5px;
  font-size: 20px;
  background-color: rgba(162, 161, 167, 0.5);
  border: 1px solid #caced1;
  border-radius: 5px;
  color: #ffff;
  cursor: pointer;
  text-align: center;
  height:42px;
}

input {
  width: 150px;
  font-size: 15px;
  border-radius: 5px;
  background-color: rgba(162, 161, 167, 0.5);
  color: #ffff;
}  





button {
  background-color: grey;
  border-radius: 25px;
  background-color: rgba(162, 161, 167, 0.5);
  font-size: 20px;
  width: 90px;

  transition-duration: 0.5s;
  color: white;
}

button:hover {
  background-color: rgba(87, 87, 89, 0.8);
}
</style>
</head>
<body>

<%@ include file="/management.jsp" %>
<nav class="navbar navbar-expand-md navbar-dark bg-success fixed-top justify-content-center">
		 <div align="center"> <h2>餐點資料管理</h2>
</nav>

	<h4><span>資料查詢:</span></h4>

	<div></div><br>
	<div class="a">
	  <ul>
		<li><h5><a id="a" href='<%=request.getContextPath()%>/back-end/food/listAllFood.jsp'>所有餐點列表</a><br><br></h5></li>

		<li>
			<form method="post" action="<%=request.getContextPath()%>/food/food.do">
				輸入食物編號:
				<input type="text" name="foodId"><font color=red>${errorMsgs.foodId}</font>
                <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出"> (輸入如101)
			</form><br>

        <jsp:useBean id="foodSvc" scope="page" class="com.service.FoodService" />

		<li><form method="post" action="<%=request.getContextPath()%>/food/food.do">
				選擇食物編號:
				<select size="1" name="foodId">
                  <c:forEach var="Food" items="${foodSvc.all}" > 
                    <option value="${Food.foodId}">${Food.foodId}
                  </c:forEach>   
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form><br>
		</li>

		<li><form method="post" action="<%=request.getContextPath()%>/food/food.do">
				選擇食物名稱:
				<select size="1" name="foodId">
                  <c:forEach var="Food" items="${foodSvc.all}" > 
                    <option value="${Food.foodId}">${Food.foodName}
                  </c:forEach>   
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form><br>
		</li>
	  </ul>

      <h4><span>餐點管理</span></h4>
	  <ul>
		  <li><h5><a id="a" href='http://localhost:8081/HelloWorldCinema/back_end/food/select_page.jsp'>新增餐點</a></h5></li>
	  </ul>
	</div>

    <c:if test="${getOne_For_Display}"><!-- 旗標getOne_For_Display見FoodServlet.java的第74行 -->
         <div class="b">
	      <%@ include file="listOneFood-div-fragment.file" %> <%-- 或(也可) <jsp:include page="listOneFood.jsp" /> --%>
	     </div>  
    </c:if>
</body>

</html>