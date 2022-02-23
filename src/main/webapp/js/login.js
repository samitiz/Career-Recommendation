const username = document.getElementById("username");
const password = document.getElementById("password");
let flag = true;
let state = false;

function checkInputs() {
    //checking the username
    checkUser();
    //checking the password
    checkPass();

    if (checkPass() && checkUser()) {
        return flag;
    } else {
        return false;
    }
}
function checkUser() {
    const usernameValue = username.value;

    if (usernameValue === "") {
        setErrorFor(username, "Username is required");
        return false;
    } else {
        setSuccessFor(username);
        return true;
    }
}

function checkPass() {
    const passwordValue = password.value;

    if (passwordValue === "") {
        setErrorFor(password, "Password is required");
        return false;
    } else {
        setSuccessFor(password);
        return true;
    }
}
function toogle() {
    if (state) {
        document.getElementById("password").setAttribute("type", "password");
        document.getElementById("eye").style.color = "#7a797e";
        state = false;
    } else {
        document.getElementById("password").setAttribute("type", "text");
        document.getElementById("eye").style.color = "#5887ef";

        state = true;
    }
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
