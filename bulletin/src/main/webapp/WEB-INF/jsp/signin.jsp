<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
    section {
        width: 300px;
        height: 150px;
        margin: auto;
        border: 1px solid gray;
    }
    
    .signIn_Form {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .signIn_Form input {
        height: 30px;
    }

    .signIn_Form div {
        display: flex;
        width: 300px;
        padding: 10px;
        justify-content: space-between;
        align-content: center;
    }

    .signIn_Form .submitBtn {
        width: 100px;
    }

    .signIn_Form .submitBtn input {
        background-color: gray;
        color: white;
        font-weight: bold;
    }
    
    .signIn_Form div label {
        display: flex;
        align-items: center;
    }
    
    .signIn_Form input {
        width: 200px;
    }

	.cancleBtn {
		background-color: gray;
		border: 1px solid gray;
		padding: 10px;
		cursor: pointer;
		border-radius: 3px;
		text-decoration: none;
		margin-top: 20px;
	}
	
	.cancleBtn:visited {
		color: white;
	}

</style>
</head>
<body>
    <section>
        <form class="signIn_Form" action="signin" method="post">
            <div class="userIdInput">
                <label for="userId">아이디</label>
                <input type="text" name="userId" pattern=".{6,14}" title="6~14 글자" required/>
            </div>
            <div class="passwordInput">
                <label for="userPassword">비밀번호</label>
                <input type="password" name="userPassword" pattern=".{4,12}" title="4~12 글자" required/>
            </div>
            <div class="submitBtn">
                <input type="submit" value="로그인"/>
            </div>
        </form>
       	<a class="cancleBtn" href="list">취소</a>
    </section>
</body>
</html>