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
        }

		form {
				width: 800px;
				display: flex;
				flex-direction: column;
		}

		table {
			border-collapse: collapse;
		}

		/* 메인 컨테이너 */
		section {
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			margin: auto;
			background-color: #f2f2f2;
			width: 850px;
			padding-top: 10px;
			border-radius: 10px;
		}
        
		/* 헤더 */
        header .title {
			padding: 10px;
            width: 800px;
            border-bottom: 1px solid gray;
            margin: 0px;
        }
		
			/* 헤더 - 작성 정보 */
			header .regInfo {
				margin: 10px 0px;
			}

			.files a{
				font-size: 20px;
			}

			.files a:link, .files a:visited {
				text-decoration: none;
				color: darkslategray;
			}
			
		/* 본문 */
		.content {
            padding: 10px;
            width: 800px;
            font-size: 18px;
            background-color: white;
        }

			.content::placeholder {
				font-size: 18px;
			}

			/* 버튼 */
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

		/* 이전글, 다음글 */
        .otherPage table {
            width: 800px;
            table-layout: fixed;
            padding: 10px 0;
            margin: 30px 0;
        }
         
        .otherPage table td {
            padding: 10px 0;
        }

        .otherPage table tr {
            background-color: white;
            border: none;
        }
        
        .otherPage .title a {
        	text-decoration: none;
        }
        
        .otherPage .title a:-webkit-any-link {
        	color: darkslategray;
        }
        
        .otherPage .desc {width: 10%;}
       	.otherPage .writer {width: 10%;}
       	.otherPage .regDate {width: 23%;}
		.otherPage .hit {width: 7%;}
		.otherPage .desc {text-indent: 10%;}

        .comment {
            width: 800px;
        }

		/* 댓글 개수 */
		.commentNumber {
            font-size: 18px;
            font-weight: bold;
        }

		/* 댓글 목록 */
        .comment_table {
        	width: 100%;
            background-color: white;
            border-radius: 10px;
            margin: 10px 0;
        }

        .comment_userName {
            padding: 10px 0 10px 10px;
            font-weight: bold;
            font-size: 18px;
        }
        
        .comment_userName a {
            text-decoration: none;
            color: gray;
        }

        .comment_regDate {
            width: 20%;
			white-space: nowrap;
            padding-right: 20px;
        }

        .comment_userName {
            width: 80%;
        }

		.comment_delete_form {
			width: 30px;
		}
		
		.comment_delete_td {
			padding-right: 10px;
		}
        
		.comment_content {
            padding: 10px 10px;
        }

		/* 댓글 추가란 */
        .add_comment {
            margin: 10px 0;
        }

        .add_comment_content {
            border: none;
            height: 100px;
            border-radius: 10px;
            resize: none;
            padding: 10px;
        }
		        
        .comment_delete {
            background-color: gray;
            color: white;
            border: 1px solid gray;
            border-radius: 3px;
            padding: 0 5px;
            width: 30px;
            cursor: pointer;
        }

        .add_comment_submit {
            margin: 10px 0;
            height: 30px;
            background-color: white;
            border: 2px solid hotpink;
            border-radius: 10px;
        }

    </style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <section>

		<!-- 게시글 정보-->
        <header>
            <h1 class="title">
                ${post.title}
            </h1>
            <div class="regInfo">
                <span>작성일: ${post.regDate} |</span>
                <span>작성자: ${post.userName} |</span>
                <span>조회수: ${post.hit}</span>
            </div>
			<div class="files">
				<span>💾 첨부파일: </span>
				<!-- 첨부파일 -->
				<c:forTokens var="fileName" items="${post.files}" delims="," varStatus="st">
					<a href="upload/${fileName}" download><strong>${fileName} </strong></a>
					<c:if test="${!st.last}">
					 || 
					</c:if>
				</c:forTokens>
			</div>
        </header>

		<!-- 본문 -->
        <div class="detail_body">
            <p class="content">
                ${post.content}
            </p>
            <div class="btns">
                <a href="list?id=${post.id}&p=${param.p}">목록</a>
                <a href="editor?id=${post.id}">편집</a>
                <a href="delete?id=${post.id}">삭제</a>
            </div>
        </div>

		<!-- 다른 페이지 -->
        <div class="otherPage">
			<table>
				<c:if test="${!empty nextPost}">
		            <tr class="nextPage">
		                <td class="desc">▲ 다음글</td>
		                <td class="title"><a href="detail?id=${nextPost.id}">${nextPost.title}</a></td>
		                <td class="writer">✏️ ${nextPost.userName}</td>
		                <td class="regDate">🗓️ ${nextPost.regDate}</td>
		                <td class="hit">👀 ${nextPost.hit}</td>
		            </tr>
				</c:if>
				<c:if test="${!empty prevPost}">
		            <tr class="prevPage">
		                <td class="desc">▼ 이전글</td>
		                <td class="title"><a href="detail?id=${prevPost.id}">${prevPost.title}</a></td>
		                <td class="writer">✏️ ${prevPost.userName}</td>
		                <td class="regDate">🗓️ ${prevPost.regDate}</td>
		                <td class="hit">👀 ${prevPost.hit}</td>
		            </tr>
				</c:if>
			</table> 
        </div>
        
        <!-- 댓글 -->
        <div class="comment">
			<c:if test="${empty comments}">
				<tr>
					<td>등록된 댓글이 없습니다.</td>
				</tr>
			</c:if>
			
			<c:if test="${!empty comments}">
				<p class="commentNumber">${post.commentsCount}개의 댓글</p>
				<hr>
				<c:forEach var="comment" items="${comments}">
					<table class="comment_table">
						<tr>
							<td class="comment_userName">
								<a href="#">${comment.userId}</a>
							</td>
							<td class="comment_regDate">${comment.regDate}</td>
							<td class="comment_delete_td">
								<form action="comment" method="POST" class="comment_delete_form">
									<input type="submit" class="comment_delete" value="X️"/>
									<input type="hidden" name="commentId" value="${comment.commentId}"/>
									<input type="hidden" name="_method" value="DELETE"/>
									<input type="hidden" name="postId" value="${post.id}">
								</form>
							</td>
						</tr>
						<tr>
							<td  class="comment_content" colspan="3">
								${comment.content}
							</td>
						</tr>
					</table>
				</c:forEach>
			</c:if>
        </div>
        
        <!-- 댓글 추가 버튼 -->
        <form action="comment" class="add_comment" method="post">
            <textarea name="commentContent" class="add_comment_content" cols="30" rows="10" placeholder="댓글을 입력하세요" required></textarea>
            <input type="submit" class="add_comment_submit" value="댓글 달기">
            <input type="hidden" name="postId" value="${post.id}">
        </form>
        
    </section>
</body>
</html>