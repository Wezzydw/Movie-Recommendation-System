/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.bll.util.MovieSearcher;
import sun.nio.cs.ext.MS874;

/**
 *
 * @author Wezzy
 */
public class MovieRecModel
{
    
    MovieSearcher ms;
    ObservableList<Movie> mv;
    List<Movie> test;
    
    public void run(String q) throws IOException
    {
        ms = new MovieSearcher();
        mv = FXCollections.observableArrayList(ms.search(test, q));
        //mv.addAll(ms.search(test, ""));
       // mv.addListener(listener);
    }
    
    public ObservableList<Movie> getOBList(String q) throws IOException
    {
        run(q);
        return mv;
    }
    
}
