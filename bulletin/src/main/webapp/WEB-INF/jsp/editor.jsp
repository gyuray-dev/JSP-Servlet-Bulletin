<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <style>
		/* 공통 */
        * {
            box-sizing: border-box;
			margin: 0;
			padding: 0;
        }
		
		/* 컨테이너 */
		.container {
			display: flex;
			flex-direction: column;
			width: 800px;
			margin: 100px auto;
		}

			/* 제목: 글쓰기 */
			.container h1 {
				display: block;
				border-bottom: 1px solid #ddd;
				padding: 10px 0;
				margin: 20px 0;
			}

		/* 폼 */
		form {
            display: flex;
            flex-direction: column;
            width: 800px;
        }

			/* 파일 첨부 */
			.files {
				margin: 10px 0;
			}

			/* 제목 입력창 */
			.title {
				width: 800px;
				height: 35px;
				padding: 10px;
				border-radius: 10px;
			}
			
			.title:focus {
				outline-color: hotpink;
			}
			
			/* 내용 입력창 */
			.content {
				padding: 10px;
				width: 800px;
				height: 500px;
				font-size: 18px;
				resize: none;
				border-radius: 10px;
        	}
			
			
				.content:focus {
					outline-color: hotpink;
				}

				.content::placeholder {
					font-size: 18px;
				}

			/* 버튼 컨테이너 */
			.btns {
				display: flex;
				justify-content: right;
				margin: 20px 0;
			}

				/* 버튼 */
				.btns input, .btns #cancle {
					display: flex;
					justify-content: center;
					align-self: flex-end;
					border-radius: 5px;
					border: 1px solid gray;
					width: 80px;
					margin-left: 10px;
					padding: 10px;
					background-color: gray;
					
					text-align: center;
					text-decoration: none;
					font-size: 14px;
					font-weight: bold;
					color: white;
					
					cursor: pointer;
				}
    </style>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
	<div class="container">
		<h1>글쓰기</h1>

	    <form action="editor" method="post" enctype="multipart/form-data">
	        <input class="title" type="text" name="title" placeholder ="${(empty post)? '제목을 입력해주세요':''}" value="${(empty post)? '':post.title}" required>
			<input class="files" type="file" name="files">
	        <textarea class="content" name="content" placeholder ="${(empty post)? '내용을 입력해주세요':''}" required>${(empty post)? '':post.content}</textarea>
	        <div class="btns">
	            <input type="submit">
	            <a id="cancle" href="list">취소</a>
	        </div>
	        <input type="hidden" name="userName" value="운영자"/>
			<input type="hidden" name="id" value="${(empty post)? -1:post.id}"/>
    	</form>
	</div>
</body>
</html>