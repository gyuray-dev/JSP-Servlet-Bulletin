<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<style>
    section {
        width: 300px;
        height: 280px;
        margin: auto;
        border: 1px solid gray;
    }
    
    .signUp_Form {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .signUp_Form input {
        height: 30px;
    }

    .signUp_Form div {
        display: flex;
        width: 270px;
        padding: 10px;
        justify-content: space-between;
        align-content: center;
    }

    .signUp_Form .submitBtn {
        width: 100px;
    }

    .signUp_Form .submitBtn input {
        background-color: gray;
        color: white;
        font-weight: bold;
    }
    
    .signUp_Form div label {
        display: flex;
        align-items: center;
    }
    
    .signUp_Form input {
        width: 200px;
    }



</style>
</head>
<body>
    <section>
        <form class="signUp_Form" action="signup" method="post">
            <div class="userIdInput">
                <label for="userId">아이디</label>
                <input type="text" name="userId" pattern=".{6,14}" title="6~14 글자" required/>
            </div>
            <div class="userNameInput">
                <label for="userName">닉네임</label>
                <input type="text" name="userName" pattern=".{2,10}" title="2~10 글자" required/>
            </div>
            <div class="passwordInput">
                <label for="userPassword">비밀번호</label>
                <input id="password" type="password" name="userPassword" pattern=".{4,12}" title="4~12 글자" required/>
                <input id="encryptedPassword" type="hidden" name="encryptedPassword" />
            </div>
            <div class="emailInput">
                <label for="userEmail">이메일</label>
                <input type="email" name="userEmail" required/>
            </div>
            <div class="submitBtn">
                <input id="signup" type="submit" value="가입하기"/>
            </div>
        </form>
    </section>
</body>
    <script type="text/javascript" src="./js/jsencrypt.min.js"></script>
    <script type="text/javascript">
        let encryptedPassword = document.querySelector("#encryptedPassword");
    	let signUpForm = document.querySelector(".signUp_Form");
   		let passwordInput = document.querySelector("#password");
   		let signupButton = document.querySelector("#signup");
    	let publicKey = '${publicKey}';
    	let crypt = new JSEncrypt();
    	crypt.setPrivateKey(publicKey);
    	
    	signupButton.addEventListener("click", function(e) {
    		e.stopPropagation();
    		let encryptedText = crypt.encrypt(passwordInput.value);
    		passwordInput.value = "";
    		encryptedPassword.value = encryptedText;
    		signUpForm.submit();
    	});
    </script>
</html>