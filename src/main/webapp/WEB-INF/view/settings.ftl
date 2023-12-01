<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
    <link href="resources/css/settings.css" rel="stylesheet">
</head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script>
    function updateName() {
        var firstName = $("#updateNameForm [name='firstName']").val();
        var lastName = $("#updateNameForm [name='lastName']").val();
        if (firstName.trim() === "" || lastName.trim() === "") {
            alert("Error: First name and last name cannot be empty.");
            return;
        }
        $.ajax({
            url: 'settings',
            type: 'POST',
            data: {
                firstName: firstName,
                lastName: lastName
            },
            success: function (response) {
                alert("User settings updated successfully!");
            },
            error: function (error) {
                console.error("Error updating user settings:", error);
            }
        });
    }

    function uploadFile() {
        var fileInput = document.getElementById("fileInput");
        var file = fileInput.files[0];

        if (file) {
            var formData = new FormData();
            formData.append("file", file);

                      $.ajax({
                url: 'file-upload-settings',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {

                    alert("File uploaded successfully!");
                },
                error: function (error) {

                    console.error("Error uploading file:", error);
                }
            });
        } else {
            alert("Please select a file.");
        }
    }
</script>

<body>
<div class="container">
    <#include "menu.ftl">
    <div class="title">Settings</div>
    <div class="container-settings">
        <form method="post" id="updateNameForm">
            <div id="settings" class="white-container">
                <label>
                    <input class="form-control" name="firstName" type="text" value="${user.firstName}"
                           placeholder="First name">
                </label>
                <label>
                    <input class="form-control" name="lastName" type="text" value="${user.lastName}"
                           placeholder="Last name">
                </label>
                <#if errorMessage??>
                    <div class="error_message">${errorMessage}</div>
                </#if>
                <input class="login-button" type="button" value="Change" onclick="updateName()">
            </div>
        </form>
        <form class="file-upload" id="uploadFileForm" enctype="multipart/form-data">
            <input type="file" name="file" id="fileInput">
            <input type="button" value="File Upload" onclick="uploadFile()">
        </form>
    </div>
</div>
</body>
</html>