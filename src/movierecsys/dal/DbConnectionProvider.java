/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Caspe
 */
public class DbConnectionProvider
{
    private static final String SetServerName = "10.176.111.31";
    private static final String SetDatabaseName = "MRsys";
    private static final String SetUser = "CS2018A_6";
    private static final String SetPassword = "CS2018_6";
    private final SQLServerDataSource ds;
    
    public DbConnectionProvider()
    {
        ds = new SQLServerDataSource();
        ds.setServerName(this.SetServerName);
        ds.setDatabaseName(this.SetDatabaseName);
        ds.setUser(this.SetUser);
        ds.setPassword(this.SetPassword);
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
}
