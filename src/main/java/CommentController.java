import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class CommentController {
    private static final CommentRepositoryImpl commentRepository = new CommentRepositoryImpl();;

    /**
     * Imprime por pantalla todos los commentarios de un post
     * @throws SQLException
     */
    public static void printCommentsByPost(Post post) throws SQLException {
        List<Comment> comments = commentRepository.findAllByPost(post);
        System.out.print(AnsiColor.BLUE.getCode());
        for (Comment comment : comments){
            System.out.println("\t" + comment);
        }
        System.out.print(AnsiColor.RESET.getCode());
    }
    public static void addComment() throws SQLException{
        PostController.printOthersPosts();
        PostRepositoryImpl postRepository = new PostRepositoryImpl();
        Scanner sc = new Scanner(System.in);
        System.out.print(AnsiColor.BLUE.getCode());
        System.out.print("Post ID: ");
        int postId = Integer.parseInt(sc.nextLine());
        System.out.print("Text: ");
        String text = sc.nextLine();
        System.out.print(AnsiColor.RESET.getCode());

        Post post = postRepository.findById(postId);

        Comment comment = new Comment(text,  new Date(new java.util.Date().getTime()), SocialNetworkService.user, post);
        commentRepository.save(comment);
    }

}
