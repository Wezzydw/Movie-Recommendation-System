/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class UserDAO
{

    private static final String USERS_SOURCE = "data/users.txt";

    /**
     * Gets a list of all known users.
     *
     * @return List of users.
     */
    public List<User> getAllUsers() throws IOException
    {
        File file = new File(USERS_SOURCE);
        List<User> allUsers = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    String[] s = br.readLine().split(",");
                    int id = Integer.parseInt(s[0]);
                    String name = s[1];
                    User user = new User(id, name);
                    allUsers.add(user);
                }
            }
        }
        return allUsers;
    }

    /**
     * Gets a single User by its ID.
     *
     * @param id The ID of the user.
     * @return The User with the ID.
     */
    public User getUser(int id) throws IOException
    {
        List<User> users = getAllUsers();
        User u = users.get(id);
        if (id < users.size())
        {
            if (u.getId() == id)
            {
                return users.get(id);
            }

            if (u.getId() < id)
            {
                for (int i = id; i < users.size(); i++)
                {
                    if (users.get(i).getId() == id)
                    {
                        return users.get(i);
                    }
                }
            }

            if (u.getId() > id)
            {
                for (int i = id; i >= 0; i--)
                {
                    if (users.get(i).getId() == id)
                    {
                        return users.get(i);
                    }
                }
            }
        } else
        {
            for (int i = users.size(); i >= 0; i--)
            {
                if (users.get(i).getId() == id)
                {
                    return users.get(i);
                }
            }
        }

        return null;
    }

    /**
     * Updates a user so the persistence storage reflects the given User object.
     *
     * @param user The updated user.
     */
    public void updateUser(User user) throws IOException
    {
        File tmpfile = new File("data/temp_users.txt");
        List<User> getAllUsers = getAllUsers();
        int id = user.getId();
        String name = user.getName();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfile)))
        {
            for (User m : getAllUsers)
            {
                if (m.getId() == id)
                {
                    bw.write(m.getId() + "," + name);
                    bw.newLine();
                } else
                {
                    bw.write(m.getId() + "," + m.getName());
                    bw.newLine();
                }
            }
        }
        Files.copy(tmpfile.toPath(), new File(USERS_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmpfile.toPath());
    }

}
