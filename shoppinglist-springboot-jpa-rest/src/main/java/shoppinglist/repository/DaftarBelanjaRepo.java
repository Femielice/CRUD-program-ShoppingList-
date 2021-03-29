/*
 * DaftarBelanjaRepo.java
 *
 * Created on Mar 22, 2021, 00.19
 */
package shoppinglist.repository;

import com.sun.org.apache.xml.internal.utils.res.XResources_de;
import org.springframework.data.jpa.repository.JpaRepository;
import shoppinglist.entity.DaftarBelanja;

/**
 * @author irfin
 */

public interface DaftarBelanjaRepo extends JpaRepository<DaftarBelanja, Long>
{
    private final String INSERT = "INSERT INTO daftarbelanja (tanggal, judul) " + " VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE mahasiswa SET tanggal=?, judul=? WHERE id=?";
    private final String DELETE = "DELETE FROM mahasiswa WHERE id=?";
    private final String SELECT_ALL = "SELECT tanggal, judul FROM daftarbelanja";

    private resources dataSource;

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public boolean insert(DaftarBelanja daftarBelanja) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1,daftarBelanja.getTanggal());
            preparedStatement.setString(2, daftarBelanja.getJudul());
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            LOGGER.error(null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }

        }
        return false;
    }

}
