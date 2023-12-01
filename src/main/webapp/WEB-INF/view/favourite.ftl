<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="resources/css/posts.css" rel="stylesheet">
    <title>Favourite</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function () {
        $(".delete-button").on("click", function (e) {
            e.preventDefault();
            var postId = $(this).attr('data-postid');
            $.ajax({
                type: "POST",
                url: "delete-favourite-post",
                data: {postId: postId},
                success: function () {
                    alert("Post deleted successfully!");
                },
                error: function () {
                    alert("Failed to delete post. Please try again.");
                }
            });
        });
    });
</script>

<body>
<div class="container">
    <#include "menu.ftl">
    <div class="container-favourite">
        <#if posts?size == 0>
            <p>You haven't added any posts to your favorites yet</p>
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
                                    <a class="logo_text_h2">Product Info:</a>${postDto.content}
                                </div>
                            </div>
                            <div><input class="delete-button" type="submit" value="delete" data-postid="${postDto.id}">
                            </div>
                        </div>
                    </form>
                </#list>
            </div>
        </#if>
    </div>
</div>
</body>
</html>