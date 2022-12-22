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
            align-items: center;
            margin: auto;
            background-color: #f2f2f2;
            width: 900px;
            padding:30px;
        }
        
        header .title {
            padding: 10px;
            width: 800px;
            border-bottom: 1px solid gray;
            margin: 0px;
        }

        header .regDate {
            margin: 0px;
        }

        .detail_body .content {
            padding: 10px;
            width: 800px;
            font-size: 18px;
            background-color: white;
        }

        .content::placeholder {
            font-size: 18px;
        }

        form {
            width: 800px;
            display: flex;
            flex-direction: column;
        }

        .btns {
            display: flex;
            justify-content: right;
        }

        .btns input, .btns a {
        	display: flex;
        	padding: 10px;
        	justify-content: center;
            align-self: flex-end;
            width: 80px;
            border: 1px solid gray;
			margin-left: 10px;
			
            text-align: center;
			font-size: 14px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
           	color: white;
			background-color: gray;
        }

        .otherPage table{
            width: 800px;
            padding: 10px 0;
            margin: 30px 0;
			border-collapse: collapse;
        }

        .otherPage table td {
            padding: 10px 0;
        }

        .otherPage table tr {
            background-color: white;
            border: none;
        }
        .otherPage .desc{
       		width: 90px;
       	}
       	
       	.otherPage .writer {
       		width: 100px;
       	}
       	
       	.otherPage .regDate {
       		width: 80px;
       	}
       	
       	.otherPage .hit {
       		width: 100px;
       	}

        .otherPage .desc {
            text-indent: 10px;
        }

    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <section>
        <header>
            <h1 class="title">
                ${post.title}
            </h1>
            <div class="regDate">
                <span>작성일: ${post.regDate} |</span>
                <span>작성자: ${post.userName} |</span>
                <span>조회수: ${post.hit}</span>
            </div>
        </header>
        <div class="detail_body">
            <p class="content">
                ${post.content}
            </p>
            <div class="btns">
                <a href="list?id=${post.id}">목록</a>
                <a href="editor?id=${post.id}">편집</a>
                <a href="delete?id=${post.id}">삭제</a>
            </div>
        </div>
        <div class="otherPage">
			<table>
				<c:if test="${!empty nextPost}">
		            <tr class="nextPage">
		                <td class="desc">▲ 다음글</td>
		                <td class="title"><a href="detail?id=${nextPost.id}">${nextPost.title}</a></td>
		                <td class="writer">✏️${nextPost.userName}</td>
		                <td class="regDate">🗓️${nextPost.regDate}</td>
		                <td class="hit">👀${nextPost.hit}</td>
		            </tr>
				</c:if>
				<c:if test="${!empty prevPost}">
		            <tr class="prevPage">
		                <td class="desc">▼ 이전글</td>
		                <td class="title"><a href="detail?id=${prevPost.id}">${prevPost.title}</a></td>
		                <td class="writer">✏️${prevPost.userName}</td>
		                <td class="regDate">🗓️${prevPost.regDate}</td>
		                <td class="hit">👀${prevPost.hit}</td>
		            </tr>
				</c:if>
			</table> 
        </div>
    </section>
</body>
</html>