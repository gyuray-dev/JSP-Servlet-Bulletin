<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
    section {
    	display: flex;
    	flex-direction: column;
    	width: 350px;
        margin: auto;
        border: 1px solid gray;
        padding: 20px 0;
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
		padding: 3px 10px;
		cursor: pointer;
		border-radius: 3px;
		text-decoration: none;
		margin-top: 5px;
		align-self: center;
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
                <input id="password" type="password" name="userPassword" pattern=".{4,12}" title="4~12 글자" required />
                <input id="encryptedPassword" type="hidden" name="encryptedPassword" />
            </div>
            <div class="submitBtn">
                <input id="signin" type="button" value="로그인"/>
            </div>
        </form>
       	<a class="cancleBtn" href="list">취소</a>
    </section>
    <script type="text/javascript" src="./js/jsencrypt.min.js"></script>
    <script type="text/javascript">
        let encryptedPassword = document.querySelector("#encryptedPassword");
    	let signinForm = document.querySelector(".signIn_Form");
   		let passwordInput = document.querySelector("#password");
   		let signinButton = document.querySelector("#signin");
    	let publicKey = '${publicKey}';
    	let crypt = new JSEncrypt();
    	crypt.setPrivateKey(publicKey);
    	
    	signinButton.addEventListener("click", function(e) {
    		e.stopPropagation();
    		let encryptedText = crypt.encrypt(passwordInput.value);
    		passwordInput.value = "";
    		encryptedPassword.value = encryptedText;
    		signinForm.submit();
    	});
    </script>
</body>
</html>