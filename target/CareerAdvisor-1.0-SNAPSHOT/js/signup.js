const username = document.getElementById("username");
const email = document.getElementById("email");
const password = document.getElementById("password");
const cPassword = document.getElementById("confirmPassword");
let flag = true;
let state = false;

function checkInputs() {
    //checking the username
    checkUser();
    //checking the email
    checkEmail();
    //checking the password
    checkPass();
    //checking the password
    checkcPass();

    return flag;
}

function setErrorFor(input, error) {
    const formControl = input.parentElement;
    const small = formControl.querySelector("small");
    small.innerText = error;
    flag = false;
    if (input.id == "password" || input.id == "confirmPassword")
        formControl.className = "form-control error white";
    else formControl.className = "form-control error";
}
function setSuccessFor(input) {
    flag = true;
    const formControl = input.parentElement;
    if (input.id == "password" || input.id == "confirmPassword")
        formControl.className = "form-control success white";
    else formControl.className = "form-control success";
}
function toogle(id, eye_id) {
    if (state) {
        document.getElementById(id).setAttribute("type", "password");
        document.getElementById(eye_id).style.color = "#7a797e";
        state = false;
    } else {
        document.getElementById(id).setAttribute("type", "text");
        document.getElementById(eye_id).style.color = "#5887ef";

        state = true;
    }
}

function checkUser() {
    if (username.value === "") {
        setErrorFor(username, "Username is required");
    } else {
        if (username.value.length < 3 || username.value.length > 16) {
            setErrorFor(username, "Username should be 3 - 16 chracters long");
        } else {
            setSuccessFor(username);
        }
    }
}

function checkEmail() {
    let emailValue = email.value;
    if (emailValue === "") {
        setErrorFor(email, "Email is required");
    } else if (emailValue.indexOf("@") == -1) {
        setErrorFor(email, "You need an '@' symbol in your address");
    } else if (emailValue.indexOf(".") < emailValue.indexOf("@") + 1) {
        dumy = emailValue.split("@");

        if (dumy[1].indexOf(".") === 0 || dumy[1].indexOf(".") === -1) {
            setErrorFor(
                email,
                "You need an '.' symbol after '@' in your address"
            );
        } else {
            setSuccessFor(email);
        }
    } else {
        setSuccessFor(email);
    }
}

function checkPass() {
    let passwordValue = password.value;

    if (passwordValue === "") {
        setErrorFor(password, "Password is required");
    } else if (passwordValue != "") {
        var format = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (passwordValue.match(format)) setSuccessFor(password);
        else
            setErrorFor(
                password,
                "Password should be more than 8 chracters and contain atleast 1 number"
            );
    }
}

function checkcPass() {
    if (cPassword.value == "") {
        setErrorFor(cPassword, "Confirm Password is required");
    }
    if (cPassword.value != "") {
        if (cPassword.value === password.value) setSuccessFor(cPassword);
        else setErrorFor(cPassword, "Passwords does not match");
    }
}
