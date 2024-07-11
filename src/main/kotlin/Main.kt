package ru.netology


data class Likes(var count: Int = 0)

data class Comments(
    var count: Int = 0,
    var canPost: Boolean = true, // может ли комментировать
    var canClose: Boolean = false // может ли закрыть коментарии к посту
)

data class Repost(
    var count: Int,
    var canRepost: Boolean // можно ли делать репост записи
)

data class Post(
    val id: Int, // id поста
    val fromId: Int, // id автора записи
    val text: String,
    val like: Likes,
    val comments: Comments,
    val repost: Repost,
    val canEdit: Boolean,
    val canDelete: Boolean
)

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0
    private var comments = emptyArray<Comment>()

    fun clear() {
        posts = emptyArray()
        lastId = 0
        comments = emptyArray()
    }

    fun add(post: Post): Post {
        posts += post.copy(
            id = ++lastId,
            like = post.like.copy(),
            comments = post.comments.copy(),
            repost = post.repost.copy()
        )
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (post.id == newPost.id) {
                posts[index] = newPost.copy(
                    like = post.like.copy(),
                    comments = post.comments.copy(),
                    repost = post.repost.copy()
                )
                return true
            }

        }
        return false
    }

    fun lookPost() {
        for (post in posts) {
            println()
            println(post)
        }

    }

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                println("Добавлен коментарий к посту с id ${post.id}")
                return comment
            }
        }
        throw PostNotFoundException("Нет поста с id $postId")
    }
}


fun main() {
    WallService.add(
        Post(
            1,
            2222,
            "Текст поста",
            like = Likes(),
            comments = Comments(),
            repost = Repost(1, true),
            canEdit = true,
            canDelete = false
        )
    )

    WallService.add(
        Post(
            2,
            2222,
            "Новый текст поста",
            like = Likes(count = +1),
            comments = Comments(),
            repost = Repost(count = +1, true),
            canEdit = true,
            true
        )
    )

    WallService.update(
        Post(
            1,
            2222,
            "Обновленный текст поста",
            like = Likes(),
            comments = Comments(),
            repost = Repost(1, true),
            canEdit = true,
            true
        )
    )

    try {
        WallService.createComment(
            1, Comment(
                1,
                2222,
                22112023,
                "Коментарий к посту"
            )
        )
    } catch (e: PostNotFoundException) {
        println(e.message)
    }
    WallService.lookPost()

}