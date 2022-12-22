<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <style>
        * {
            box-sizing: border-box;
        }
        
        a:-webkit-any-link {
        	color: black;
        }

        section {
            display: flex;
            flex-direction: column;
            justify-content: center;
            width: 900px;
            margin: auto;
        }
        
        .listAmount {
        	align-self: flex-end;
        	width: 100px;
        	margin: 5px;
        	text-align: center;
        }
        
        .table_header > td {
            background-color: #DDD;
            padding: 10px 10px;
            text-align: center;
            font-weight: bold;
            font-size: 20px;
            color: darkslategray;
        }

        .table_content > td {
            background-color: #F1F1F1;
            padding: 5px 10px;
            font-size: 16px;
            text-align: center;
            color: darkslategray;
        }

        .table_content > .title {
        	text-align: left;
            padding-left: 20px;
            width: 300px;
        }
        
        .title a {
       		display: inline-block;
        }
        
        .title a:-webkit-any-link {
        	color: darkslategray;
        }
        
       	.id {
       		width: 100px;
       	}
       	
       	.writer {
       		width: 100px;
       	}
       	
       	.regDate {
       		width: 50px;
       	}
       	
       	.hit {
       		width: 100px;
       	}

        a {
            text-decoration: none;
        }

        .toolbar {
            display: flex;
            flex-direction: column;
            padding: 10px 0px;

        }
        
        #write {
        	display: flex;
        	justify-content: center;
        	padding: 10px;
            align-self: flex-end;
            width: 80px;
            border: 1px solid gray;
            
            font-size: 14px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
           	color: white;
			background-color: gray;
        }

        
        #pager {
            align-self: center;
            display: flex;
            align-items: center;
            justify-content: center;

        }
        
        #pager ul{
            display: flex;
            padding: 5px;
            margin: 0px;
            justify-content: space-between;
            list-style-type: none;
        }

        #pager ul li {
            margin: 4px;

            font-size: 20px;
        }

        #pager button {
            background-color: aliceblue;
            border: 1px solid gray;
            width: 30px;
            height: 30px;
            cursor: pointer;
        }

        .searchbar form{
            display: flex;
            justify-content: center;
            align-items: center;
            align-self: center;
        }

        .searchbar form * {
            height: 30px;
            margin: 2px;
        }
        
        .pageBtn {
        	text-decoration: none;
        }
        
        .selPage {
        	font-weight: bold;
	        text-decoration: underline;
        }
        
        .selPage:-webkit-any-link {
        	color: hotpink;
        }

    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bulletin</title>
</head>
<body>
    <section>
    	<select class="listAmount" name="listAmount" onchange="location.href='listAmount?listAmount=' + this.value">
    		<option value="10" ${ (listAmount == 10)? "selected" : ""}>10개씩 보기</option>
    		<option value="30" ${ (listAmount == 30)? "selected" : ""}>30개씩 보기</option>
    		<option value="50" ${ (listAmount == 50)? "selected" : ""}>50개씩 보기</option>
    		<option value="100" ${ (listAmount == 100)? "selected" : ""}>100개씩 보기</option>
    	</select>
        <table class="bulletin">
            <tr class="table_header">
                <td class="id">번호</td>
                <td class="title">제목</td>
                <td class="writer">작성자</td>
                <td class="regDate">작성일</td>
                <td class="hit">조회수</td>
            </tr>
            <c:forEach var="post" items="${posts}" varStatus="st">
	            <tr class="table_content">
	                <td class="id">${post.id}</td>
	                <td class="title"><a href="detail?id=${post.id}">${post.title}</a></td>
	                <td class="writer">${post.userName}</td>
	                <td class="regDate">${post.regDate}</td>
	                <td class="hit">${post.hit}</td>
	            </tr>
            </c:forEach>
        </table>
        <div class="toolbar">
            <c:set var="firstPage" value="${p - (p - 1) % 5}"/>
            <a id="write" href="editor">글쓰기</a>
           <!-- 페이저 -->
            <div id="pager">
                <button onclick="location.href='list?p=${((firstPage - 5) > 1) ? firstPage - 5 : 1}&searchType=${param.searchType}&searchContent=${param.searchContent}'">⟪</button>
                <ul>
	                <c:forEach var="n" begin="${firstPage}" end="${(firstPage + 4 > lastPage)? lastPage : firstPage + 4}">
	                    <li><a class="pageBtn ${(p == n) ? 'selPage' : ''}" href="list?p=${n}&searchType=${param.searchType}&searchContent=${param.searchContent}">${n}</a></li>
	                </c:forEach>
                </ul>
                <button onclick="location.href='list?p=${(firstPage + 5) > lastPage ? lastPage : firstPage + 5}&searchType=${param.searchType}&searchContent=${param.searchContent}'">⟫</button>
            </div>
	        </div>
	        <!-- 검색창 -->
            <div class="searchbar">
	            <form action="list" method="GET">
                    <select name="searchType">
                       <option value="title" ${(param.searchType == "title") ? "selected" : "" }>제목</option>
                       <option value="userName" ${(param.searchType == "userName") ? "selected" : "" }>작성자</option>
                       <option value="content" ${(param.searchType == "content") ? "selected" : "" }>내용</option>
                    </select>
	                <input type="text" name="searchContent" value="${param.searchContent }"/>
	                <input type="submit" value="검색"/>
	            </form>
	        </div>
    </section>
</body>
</html>