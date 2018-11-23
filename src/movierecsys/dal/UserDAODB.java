/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.User;

/**
 *
 * @author Wezzy
 */
public class UserDAODB implements IUserRepository
{
    UandP up = new UandP();
    
    
    @Override
    public List<User> getAllUsers() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        List<User> users = new ArrayList();
        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM User;";
            statement.execute(sql);
            ResultSet r = statement.getResultSet();
            while (r.next())
            {
                User u = new User(r.getInt("id"), r.getString("name"));
                users.add(u);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUser(int id) throws IOException
    {
        User u = null;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
           
            String sql = "SELECT FROM User WHERE id = " + id + ";";
            statement.execute(sql);
            ResultSet r = statement.getResultSet();
            u = new User(r.getInt("id"), r.getString("name"));

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return u;
    }

    @Override
    public void updateUser(User user) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
           
            String sql = "UPDATE User SET name = "
                    + user.getName()+ "WHERE id = " + user.getId() + ";";
                    
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    
    
}
