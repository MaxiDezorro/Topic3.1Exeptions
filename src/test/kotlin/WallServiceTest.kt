import org.junit.Before
import ru.netology.*
import kotlin.test.*

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateTrue() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(
            Post(
                1,
                2222,
                "text post",
                comments = Comments(0, true, false),
                like = Likes(0),
                repost = Repost(1, true),
                canEdit = true,
                canDelete = true
            )
        )
        service.add(
            Post(
                2,
                2222,
                "text post new",
                comments = Comments(1, true, false),
                like = Likes(1),
                repost = Repost(0, false),
                canEdit = true,
                canDelete = true
            )
        )
        service.add(
            Post(
                3,
                2222,
                "text post №3",
                comments = Comments(3, true, false),
                like = Likes(10),
                repost = Repost(11, true),
                canEdit = true,
                canDelete = true
            )
        )
        // создаём информацию об обновлении
        val update = Post(
            1,
            2222,
            "update text post",
            comments = Comments(0, true, false),
            like = Likes(0),
            repost = Repost(1, true),
            canEdit = true,
            canDelete = true
        )

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(result)
    }

    @Test
    fun updateFalse() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(
            Post(
                1,
                2222,
                "text post",
                comments = Comments(0, true, false),
                like = Likes(0),
                repost = Repost(1, true),
                canEdit = true,
                canDelete = true
            )
        )
        service.add(
            Post(
                2,
                2222,
                "text post new",
                comments = Comments(1, true, false),
                like = Likes(1),
                repost = Repost(0, false),
                canEdit = true,
                canDelete = true
            )
        )
        service.add(
            Post(
                3,
                2222,
                "text post №3",
                comments = Comments(3, true, false),
                like = Likes(10),
                repost = Repost(11, true),
                canEdit = true,
                canDelete = true
            )
        )
        // создаём информацию об обновлении
        val update = Post(
            11,
            2222,
            "update text post",
            comments = Comments(0, true, false),
            like = Likes(0),
            repost = Repost(1, true),
            canEdit = true,
            canDelete = true
        )

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertFalse(result)
    }

    @Test
    fun addPost() {
        val service = WallService

        val result = service.add(
            Post(
                1,
                2222,
                "post add",
                comments = Comments(1, true, false),
                like = Likes(1),
                repost = Repost(0, false),
                canEdit = true,
                canDelete = true
            )
        )
        assertNotEquals(0, result.id)
    }

    @Test
    fun createCommentMade() {
        val service = WallService
        val post = service.add(
            Post(
                1,
                2222,
                "post",
                Likes(1),
                Comments(0, true, true),
                Repost(1, false),
                true,
                true
            )
        )
       val post2 = service.add(Post(2,
            2222,
            "post",
            Likes(1),
            Comments(0, true, true),
            Repost(1, false),
            true,
            true))

        val createComment = service.createComment(
            1, Comment(
                1,
                2222,
                212212,
                "text comment"
            )
        )
        assertNotNull(createComment)
    }


    @Test(expected = PostNotFoundException::class)
    fun shouldThrowCreateComment() {
        val comment = Comment(1, 1, 22122021, "text comment")
        WallService.createComment(3, comment)
    }

}
