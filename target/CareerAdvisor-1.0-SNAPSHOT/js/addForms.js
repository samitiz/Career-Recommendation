const form = document.getElementById("form");
const username = document.getElementById("username");
const password = document.getElementById("password");



function checkPassword() {
    const password = document.getElementById("password");
    const passwordValue = password.value;
    //checking the password
    if (passwordValue === "") {
        setErrorFor(password, "Password cannot be blank");
    } else {
        setSuccessFor(password);
    }
}

function checkUsername() {
    const username = document.getElementById("username");
    const usernameValue = username.value;
    //checking the username
    if (usernameValue === "") {
        setErrorFor(username, "username cannot be blank");
    } else {
        setSuccessFor(username);
    }
}

function setErrorFor(input, error) {
    const formControl = input.parentElement;
    const small = formControl.querySelector("small");
    small.innerText = error;

    formControl.className = "form-control error";
}
function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = "form-control success";
}
