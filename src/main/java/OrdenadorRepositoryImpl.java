import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrdenadorRepositoryImpl implements IRepository<Ordenador>{
    private java.sql.Connection con;
    public OrdenadorRepositoryImpl(){
        this.con = OrdenadorService.getConnection();
    }
    @Override
    public Ordenador bdToEntity(ResultSet rs) throws SQLException {
        return new Ordenador(rs.getInt("id"), rs.getString("modelo"),rs.getInt("precio"),rs.getInt("portatil"));
    }
    @Override
    public List<Ordenador> findAll() throws SQLException {
        List<Ordenador> ordenadores = new ArrayList<>();

        Statement st = this.con.createStatement();
        //Ejecutar la consulta, guardando los datos devueltos en un Resulset
        ResultSet rs = st.executeQuery("SELECT * FROM ordenadores");

        while(rs.next()){
            //Mapeamos el registro de la BD en un post
            Ordenador ordenador = bdToEntity(rs);
            //Añadir el Post al conjunto de posts
            ordenadores.add(ordenador);

        }
        return ordenadores;
    }

    @Override
    public Ordenador findById(int id) throws SQLException {
        Ordenador ordenador = null;
        PreparedStatement st = con.prepareStatement("SELECT * FROM ordenadores WHERE id = ? ");
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        //Si la consulta devuelve algún resultado ...
        if (rs.next()){
            // ... lo mapeamos a un objeto Post
            ordenador = bdToEntity(rs);
        }
        //Devolvemos el Post ya mapeado
        return ordenador;
    }

    public List<Ordenador> findPortatil() throws SQLException {
        List<Ordenador> ordenadores = new ArrayList<>();

        Statement st = this.con.createStatement();
        //Ejecutar la consulta, guardando los datos devueltos en un Resulset
        ResultSet rs = st.executeQuery("SELECT * FROM ordenadores WHERE portatil = 1");

        while(rs.next()){
            //Mapeamos el registro de la BD en un post
            Ordenador ordenador = bdToEntity(rs);

            //Añadir el Post al conjunto de posts
            ordenadores.add(ordenador);
        }
        return ordenadores;
    }

    public List<Ordenador> findNotPortatil() throws SQLException {
        List<Ordenador> ordenadores = new ArrayList<>();

        Statement st = this.con.createStatement();
        //Ejecutar la consulta, guardando los datos devueltos en un Resulset
        ResultSet rs = st.executeQuery("SELECT * FROM ordenadores WHERE portatil = 0");

        while(rs.next()){
            //Mapeamos el registro de la BD en un post
            Ordenador ordenador = bdToEntity(rs);
            //Añadir el Post al conjunto de posts
            ordenadores.add(ordenador);

        }
        return ordenadores;
    }

    @Override
    public void save(Ordenador entity) throws SQLException {
        /*ResultSet rs;
        PreparedStatement st = null;
        if (entity.getId() == -1){
            String query = "INSERT INTO ordenadores (id, modelo, precio, portatil) VALUES (?, ?, ?, ?)";
            //Fijáos en Statement.RETURN_GENERATED_KEYS. Permite recuperar el campo ID autogenerado por MySql
            st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, entity.getId());
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
        }*/
    }

    @Override
    public void delete(Ordenador entity) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM ordenadores WHERE id = ?");
        st.setInt(1, entity.getId());
        st.executeUpdate();
        st.close();
    }
}
