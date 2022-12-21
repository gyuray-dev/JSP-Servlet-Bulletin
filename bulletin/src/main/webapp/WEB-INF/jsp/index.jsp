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

        section {
            display: flex;
            flex-direction: column;
            justify-content: center;
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
            justify-content: space-between;

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
            text-decoration: underline;

        }

        #pager button {
            background-color: aliceblue;
            border: 1px solid gray;
            width: 30px;
            height: 30px;
            cursor: pointer;
        }

    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bulletin</title>
</head>
<body>
    <section>
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
            <a id="write" href="editor">글쓰기</a>
            <div id="pager">
            	<c:set var="firstPage" value="${p - (p - 1) % 5}"/>
                <button onclick="location.href='list?p=${((firstPage - 5) > 1) ? firstPage - 5 : 1}'">⟪</button>
                <ul>
	                <c:forEach var="n" begin="${firstPage}" end="${(firstPage + 4 > lastPage)? lastPage : firstPage + 4}">
	                    <li><a href="list?p=${n}">${n}</a></li>
	                </c:forEach>
                </ul>
                <button onclick="location.href='list?p=${(firstPage + 5) > lastPage ? lastPage : firstPage + 5}'">⟫</button>
            </div>
        </div>
    </section>
</body>
</html>