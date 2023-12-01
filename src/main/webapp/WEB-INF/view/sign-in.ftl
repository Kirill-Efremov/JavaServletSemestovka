<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <link href="resources/css/sign-in.css" rel="stylesheet">
    <title>Sign In</title>
</head>
<body>
<div class="login">
    <form class="form-center-content" method="post">
        <div class="form-signin-heading">Sign in</div>
        <label>
            <input class="form-control" name="email" type="email" placeholder="Email">
        </label>
        <div class="password">
            <label>
                <input class="form-control" name="password" type="password" placeholder="Password">
            </label>
        </div>
        <#if errorMessage??>
            <div class="error_message">${errorMessage}</div>
        </#if>
        <input class="login-button" type="submit" value="Sign in">
        <a class="registration" href="sign-up">Login in account</a>
    </form>
</div>
</body>
</html>
