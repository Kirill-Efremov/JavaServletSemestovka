<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Post</title>
    <link href="resources/css/add-post.css" rel="stylesheet">
</head>
<script>
    function validateForm() {
        var title = document.getElementById("title").value;
        var content = document.getElementById("content").value;
        var price = document.getElementById("price").value;
        if (title.trim() === "" || content.trim() === "" || price.trim() === "") {
            alert("Fields cannot be empty. Please fill in all the required fields.");
            return false;
        }
        return true;
    }
</script>
<body>
<div class="container">
    <#include "menu.ftl">
    <div class="title">Add Post</div>
    <form method="POST" class="form-vertical" enctype="multipart/form-data" onsubmit="return validateForm()">
        <div id="add-post" class="white-container">
            <label for="title" class="form-label">
                <input type="text" class="form-control" id="title" name="title" placeholder="title"></label>
            <label for="content" class="form-label">
                <textarea type="text" class="form-control" id="content" name="content" placeholder="content"></textarea></label>
            <label for="price" class="form-label">
                <input type="number" class="form-control" id="price" name="price" placeholder="price"></label>
            <input type="file" name="file" id="fileInput">
            <div class="error-message">
                <#if errorMessage??>
                    <div class="error_message">${errorMessage}</div>
                </#if>
            </div>
            <button type="submit" class="btn btn-default">Add post</button>
        </div>
    </form>
</div>
</body>
</html>
