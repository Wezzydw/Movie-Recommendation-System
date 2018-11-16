/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class FileReaderTester
{

    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {


<<<<<<< HEAD

=======
        UserDAO userDAO = new UserDAO();
        for (User u : userDAO.getAllUsers())
        {
            System.out.println("User: " + u.getId() + u.getName());
        }
        
        
>>>>>>> 3d3d062ff798ab3c86b8aa8eb17cb37f1bd0d704
    }
}
