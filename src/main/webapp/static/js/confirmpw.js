function checkForm(form) {
    return checkPassword(form);
}

function checkPassword(form) {
    let password = form.password.valueOf();
    let confirmPassword = form.repassword.valueOf();

    if (password != confirmPassword) {
        alert("Invalid e-mail or password");
        form.password.focus();
        return false;
    }
}