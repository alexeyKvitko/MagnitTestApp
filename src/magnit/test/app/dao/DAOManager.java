package magnit.test.app.dao;

import magnit.test.app.AppConstants;
import magnit.test.app.exception.DAOException;
import magnit.test.app.pojo.Entry;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by a.kvitko on 13.01.2016.
 */
public class DAOManager {

    public static final String INSERT_STATEMENT = "INSERT INTO test ( field ) VALUES ( ? )";
    public static final String DELETE_STATEMENT = "DELETE FROM test";
    public static final String SELECT_STATEMENT = "SELECT field FROM test ORDER BY field";

    /**
     *  Return connection to database
     *  @return Connection
     *  @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        return connection;
    }

    /**
     *  Delete all data from database
     *  @throws DAOException
     */
    public void deleteAllFieldsFromDatabase() throws DAOException {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(DELETE_STATEMENT);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     *  Select all data from database
     *  @return ArrayList<Entry>
     *  @throws DAOException
     */
    public ArrayList<Entry> selectFieldsFromDataBase() throws DAOException {
        ArrayList<Entry> entries = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(SELECT_STATEMENT);
            while (resultSet.next()) {
                Entry entry = new Entry();
                entry.setField(resultSet.getInt(AppConstants.COLUMN_FIELD));
                entries.add(entry);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return entries;
    }

    /**
     *  Insert batch of data in database
     *  @throws DAOException
     */
    public void insertFieldsToDatabase(Integer from, Integer to) throws DAOException {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement prepStmt = conn.prepareStatement(INSERT_STATEMENT)) {
                for (int i = from; i < to; i++) {
                    prepStmt.setInt(1, i);
                    prepStmt.addBatch();
                }
                prepStmt.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

}
