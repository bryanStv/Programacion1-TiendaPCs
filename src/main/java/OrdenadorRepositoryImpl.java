import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrdenadorRepositoryImpl implements IRepository<Ordenador>{
    private static Set<Ordenador> cacheOrdenadores = new HashSet<>();
    private java.sql.Connection con;

    public OrdenadorRepositoryImpl() {
        this.con = OrdenadorService.getConnection();
    }
    @Override
    public List<Ordenador> findAll() throws SQLException {
        return null;
    }

    @Override
    public Ordenador findById(int id) throws SQLException {
        return null;
    }

    @Override
    public void save(Ordenador entity) throws SQLException {

    }

    @Override
    public void delete(Ordenador entity) throws SQLException {

    }

    @Override
    public Ordenador bdToEntity(ResultSet rs) throws SQLException {
        return null;
    }
}
