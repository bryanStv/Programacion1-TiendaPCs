import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class CommentRepositoryImpl implements IRepository<Comment>{
    private static Set<Comment> commentsCached = new HashSet<>();
    private java.sql.Connection con;
    public CommentRepositoryImpl(){
        this.con = SocialNetworkService.getConnection();
    }
    /**
     * Se encarga de mapear um registro de la base de datos para convertirlo en un objeto Comment
     * @param rs
     * @return Un objeto Post
     * @throws SQLException
     */
    public Comment bdToEntity(ResultSet rs) throws SQLException {
        Comment c = getCached(rs.getInt("id"));
        if (c == null) {
            User user = new UserRepositoryImpl().findById(rs.getInt("userId"));
            Post post = new PostRepositoryImpl().findById(rs.getInt("postId"));
            c = new Comment(rs.getInt("id"), rs.getString("text"), rs.getDate("date"), user, post);
            commentsCached.add(c);
        }
        return c;
    }
    /**
     * Consulta todos los registros de la tabla posts
     * @return Una lista de objetos Post
     * @throws SQLException
     */
    public List<Comment> findAll() throws SQLException {

        List<Comment> comments = new ArrayList<>();

        Statement st = this.con.createStatement();
        //Ejecutar la consulta, guardando los datos devueltos en un Resulset
        ResultSet rs = st.executeQuery("SELECT * FROM comments ORDER BY date DESC");

        while(rs.next()){
            //Mapeamos el registro de la BD en un post
            Comment comment = bdToEntity(rs);
            //Añadir el Post al conjunto de posts
            comments.add(comment);

        }
        return comments;
    }

    /**
     * Consulta todos los registros de la tabla commens del usuario concreto
     * @return Una lista de objetos Comment
     * @throws SQLException
     */
    public List<Comment> findAllByPost(Post post) throws SQLException {

        List<Comment> comments = new ArrayList<>();
        PreparedStatement st = con.prepareStatement("SELECT * FROM comments WHERE postId = ? ORDER BY date DESC");
        st.setInt(1, post.getId());
        //Ejecutar la consulta, guardando los datos devueltos en un Resulset
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            //Mapeamos el registro de la BD en un post
            Comment comment = bdToEntity(rs);
            //Añadir el Post al conjunto de posts
            comments.add(comment);
        }
        return comments;
    }
    /**
     * Busca un comentario por id en la tabla comments
     * @param id
     * @return El objeto Comment o null si no existe
     * @throws SQLException
     */
    public Comment findById(int id) throws SQLException {
        Comment comment = getCached(id);
        if (comment != null) {
            return comment;
        }
        PreparedStatement st = con.prepareStatement("SELECT * FROM comments WHERE id = ? ");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        //Si la consulta devuelve algún resultado ...
        if (rs.next()){
            // ... lo mapeamos a un objeto Post
            comment = bdToEntity(rs);
        }
        //Devolvemos el Post ya mapeado
        return comment;
    }
    private Comment getCached(int id){
        for (Comment comment : commentsCached){
            if (comment.getId() == id) return comment;
        }
        return null;
    }
    public void save(Comment comment) throws SQLException{
        ResultSet rs;
        PreparedStatement st = null;
        if (comment.getId() == -1){
            String query = "INSERT INTO comments (text, date, userId, postId) VALUES (?, ?, ?, ?)";
            //Fijáos en Statement.RETURN_GENERATED_KEYS. Permite recuperar el campo ID autogenerado por MySql
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, comment.getText());
            st.setDate(2, comment.getDate());
            st.setInt(3, comment.getUser().getId());
            st.setInt(4, comment.getPost().getId());

            st.executeUpdate();

            //Recuperar el id autogenerado
            rs = st.getGeneratedKeys();
            //Este ResultSet solo puede contener un registro: el ID autogenerado

            if (rs.next()){
                //Ahora ya sabemos cuál es el nuevo id del Usuario
                comment.setId(rs.getInt(1));
            }
        }
    }
    /**
     * Elimina de la base de datos el Comment comment
     * @param comment
     * @throws SQLException
     */
    public void delete(Comment comment) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM comments WHERE id = ?");
        st.setInt(1, comment.getId());
        st.executeUpdate();
        st.close();
    }
}
