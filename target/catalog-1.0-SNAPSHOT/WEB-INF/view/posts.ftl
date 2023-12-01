<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="resources/css/posts.css" rel="stylesheet">
    <title>Posts</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function () {
        $('.add-button').on('click', function (e) {
            e.preventDefault();
            var postId = $(this).attr('data-postid');
            $.ajax({
                type: 'POST',
                url: 'check-favourite',
                data: {
                    postId: postId
                },

                success: function (data) {
                    if (data === 'not_added') {
                        $.ajax({
                            type: 'POST',
                            url: 'add-to-favourite',
                            data: {
                                postId: postId
                            },
                            success: function (response) {
                                alert('Post added to favorites successfully');
                            },
                            error: function (error) {
                                alert('Error adding post to favorites');
                            }
                        });
                    } else {
                        alert('Post is already in favorites');
                    }
                },
                error: function (error) {
                    console.error('Error checking favorite status', error);
                }
            });
        });
    });
</script>
<body>
<div class="container">
    <#include "menu.ftl">
    <div class="container-posts">
        <#if posts?size == 0>
            <p>There are currently no posts created</p>
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
                                <div><input class="add-button" type="submit" value="add" data-postid="${postDto.id}">
                                </div>
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
