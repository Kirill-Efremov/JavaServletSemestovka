<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="resources/css/sign-in.css">
</head>
<body>
<div class="login">
<form class="form-center-content" method="post">
    <div class="form-signin-heading">Sign Up</div>
    <label>
        <input class="form-control" name="firstName" type="text" placeholder="First name">
    </label>
    <label>
        <input class="form-control" name="lastName" type="text" placeholder="Last name">
    </label>
    <label>
        <input class="form-control" name="email" type="email" placeholder="Email">
    </label>
    <label>
        <input class="form-control" name="password" type="password" placeholder="Password">
    </label>
    <#if errorMessage??>
        <div class="error_message">${errorMessage}</div>
    </#if>
    <input class="login-button" type="submit" value="Sign-up">
    <a class="registration" href="sign-in">Sign in</a>
</form>
</div>
</body>
</html>