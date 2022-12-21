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

        textarea {
            resize: none;
        }

        .title {
            padding: 10px;
            width: 800px;
            height: 30px;
        }

        .content {
            padding: 10px;
            width: 800px;
            height: 500px;
            font-size: 18px;
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

        .btns input, .btns #cancle {
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
			
			cursor: pointer;
        }
    </style>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
	    <form action="editor" method="post">
	        <input class="title" type="text" name="title" placeholder ="${(empty post)? '제목을 입력해주세요':''}" value="${(empty post)? '':post.title}" required>
	        <textarea class="content" name="content" placeholder ="${(empty post)? '내용을 입력해주세요':''}" required>${(empty post)? '':post.content}</textarea>
	        <div class="btns">
	            <input type="submit">
	            <a id="cancle" href="list">취소</a>
	        </div>
	        <input type="hidden" name="userName" value="운영자"/>
			<input type="hidden" name="id" value="${(empty post)? -1:post.id}"/>
    	</form>
    	
</body>
</html>