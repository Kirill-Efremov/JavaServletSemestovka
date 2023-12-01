<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link href="resources/css/profile.css" rel="stylesheet">
    <link href="resources/css/posts.css" rel="stylesheet">
</head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function () {
        $(".delete-button").click(function () {
            var postId = $(this).attr('data-postid');
            console.log('Data from server:', postId);
            $.ajax({
                type: 'POST',
                url: 'delete-post',
                data: {
                    postId: postId
                },
                success: function (response) {
                    alert('Post deleted successfully');
                },
                error: function (error) {
                    console.error('Error deleting post:', error);
                }
            });
        });
    });
</script>
<body>
<div class="container">
    <#include "menu.ftl">
    <div class="container-favourite">
        <div class="title">Profile</div>
        <div id="profile" class="white-container">
            <div class="user-info-text">
                <div class="avatar-image">
                    <#if user.avatarId != 0>
                        <img class="avatar" alt="IMAGE" src="files/${user.avatarId}"/>
                    <#else>
                        <img class="avatar" alt="IMAGE" src="no-avatar.png"/>
                    </#if>
                </div>
                <div class="user-info"> Fist name: ${user.firstName}</div>
                <div class="user-info">Last name: ${user.lastName}</div>
                <div class="user-info">Email: ${user.email}</div>
            </div>

        </div>
        <div class="container-posts">
            <div class="my-posts-title">
                <h2>My Posts</h2>
            </div>
            <#if posts?size == 0>
                <p>You haven't created any posts yet.</p>
            <#else>
                <div class="index-section">
                    <#list posts as postDto>
                        <form>
                            <div class="post">
                                <div class="post_item">
                                    <div class="post-photo">
                                        <#if postDto.photoId != 0 >
                                            <img class="photo" alt="IMAGE" src="files/${postDto.photoId}"/>
                                        <#else>
                                            <img class="photo" alt="IMAGE" src="no-photo.jpg"/>
                                        </#if>
                                    </div>
                                    <div>
                                        <a class="logo_text_h2">Price: </a>${postDto.price}
                                    </div>
                                    <div>
                                        <a class="logo_text_h2">Title: </a>${postDto.title}
                                    </div>

                                    <div>
                                        <a class="logo_text_h2">Content:</a>${postDto.content}
                                    </div>
                                    <div>
                                        <input class="delete-button" type="button" value="Delete my post"
                                               data-postid="${postDto.id}">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </#list>
                </div>
            </#if>
        </div>
    </div>
</div>
</body>
</html>