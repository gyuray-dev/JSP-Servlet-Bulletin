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
       	padding: 10px 0;
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

    .inputDiv, .submitBtn {
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

	.validation {
		text-align: center;
	}


</style>
</head>
<body>
    <section>
        <form class="signUp_Form" action="signup" method="post">
            <div class="inputDiv">
                <label for="userId">아이디</label>
                <input id="userIdInput" type="text" name="userId" />
            </div>
            <div class="validation val_id"></div>
            <div class="inputDiv">
                <label for="userName">닉네임</label>
                <input id="userNameInput" type="text" name="userName" />
            </div>
            <div class="validation val_name">중복된 닉네임입니다.</div>
            <div class="inputDiv">
                <label for="userPassword">비밀번호</label>
                <input id="passwordInput" type="password" name="userPassword"/>
                <input id="encryptedPassword" type="hidden" name="encryptedPassword" />
            </div class="inputDiv">
            <div class="inputDiv">
                <label for="userEmail">이메일</label>
                <input id="userEmailInput" type="email" name="userEmail" />
            </div>
            <div class="validation val_email">중복된 이메일입니다.</div>
            <div class="submitBtn">
                <input id="signup" type="button" value="가입하기"/>
            </div>
        </form>
    </section>
</body>
    <script type="text/javascript" src="./js/jsencrypt.min.js"></script>
    <script type="text/javascript">
   		
    	// 비밀번호 암호화 공개키 등 준비
        let encryptedPassword = document.querySelector("#encryptedPassword");
    	let publicKey = '${publicKey}';
    	let crypt = new JSEncrypt();
    	crypt.setPrivateKey(publicKey);
    	
    	let signUpForm = document.querySelector(".signUp_Form");
   		let signupButton = document.querySelector("#signup");
    	let userIdInput = document.querySelector("#userIdInput");
   		let userPasswordInput = document.querySelector("#passwordInput");
    	let userNameInput = document.querySelector("#userNameInput"); 
    	let userEmailInput = document.querySelector("#userEmailInput"); 
    	
    	signupButton.addEventListener("click", function(e) {
    		// 양식 유효성 검사
    		if(userIdInput.value.length < 4 || userIdInput.value.length > 12) {
    			alert("아이디는 6~14글자 범위로 입력해주세요.");
    		} else if(userNameInput.value.length < 2 || userNameInput.value.length > 10) {
    			alert("닉네임은 2~10글자 범위로 입력해주세요.");
    		} else if(userPasswordInput.value.length < 4 || userPasswordInput.value.length > 14) {
    			alert("비밀번호는 4~14글자 범위로 입력해주세요.");
    		} else if(userEmailInput.value.length < 4 || userEmailInput.value.indexOf('@') == -1 || userEmailInput.value.indexOf('.') == -1) {
    			alert("유효한 이메일을 입력해주세요.");
    		} else {
	    		// 암호화
	    		e.stopPropagation();
	    		let encryptedText = crypt.encrypt(userPasswordInput.value);
	    		userPasswordInput.value = "";
	    		encryptedPassword.value = encryptedText;
	    		signUpForm.submit();
    		}
    	});
    	
    	//중복 검사
    	
    	var valName = document.querySelector(".val_name");    	
    	var valEmail = document.querySelector(".val_email");    	
    	var valId = document.querySelector(".val_id");    	
    	
    	userIdInput.addEventListener("keyup", function(e) {
    		let dupCheck = function (bool) {
        		if(bool) {
        			valId.innerText = "이미 존재하는 아이디입니다.";
        			valId.style.color = "red";
       			} else {
        			valId.innerText = "사용 가능한 아이디입니다.";
        			valId.style.color = "green";
       			}
    		}
    		
    		if(userIdInput.value.length < 6 || userIdInput.value.length > 12) {
    			valId.innerText = "6~12글자 범위로 입력해주세요.";
        			valId.style.color = "red";
   			} else {
	    		isDup("userId", e.target.value, dupCheck);
   			}
    	});
    	
    	function isDup(column, value, dupCheck) {
	   		let request = new XMLHttpRequest();
	   		request.open("GET", "isdup?column=" + encodeURIComponent(column) + "&value=" + encodeURIComponent(value), true);
	   		request.onreadystatechange = function() {
	   			if(request.readyState == 4 && request.status == 200) {
		   			if(request.responseText == 'true') {
		   				dupCheck(true);
		   			} else {
		   				dupCheck(false);
		   			}
		   			
	   			}
	   		}
	   		request.send();
    	}

    </script>
</html>












