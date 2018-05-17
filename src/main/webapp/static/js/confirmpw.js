function checkForm(form) {
    return checkPassword(form);
}

function checkPassword(form) {
    let password = form.password.value;
    let confirmPassword = form.repassword.value;

    if (password != confirmPassword) {
        alert("Password did not match");
        form.password.focus();
        return false;
    }
}