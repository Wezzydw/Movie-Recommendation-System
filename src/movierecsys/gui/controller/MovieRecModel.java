/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import movierecsys.be.Movie;
import movierecsys.bll.util.MovieSearcher;

/**
 *
 * @author Wezzy
 */
public class MovieRecModel
{
    
    MovieSearcher ms;
    ObservableList<Movie> mvAll;
    ObservableList<Movie> mv;
    ObservableList<Movie> all;
    List<Movie> test;
    
    public MovieRecModel() throws IOException
    {
        ms = new MovieSearcher();
        mv = FXCollections.observableArrayList(ms.search(test, ""));
        all = FXCollections.observableArrayList(ms.search(test, ""));
    }
    
    public ObservableList<Movie> getOBList() throws IOException
    {
        return mv;
    }
    
    public void changeView(String q) throws IOException
    {
        mvAll = FXCollections.observableArrayList(ms.search(all, q));
        mv.clear();
        for (Movie l : mvAll)
        {
            mv.add(l);
        }
    }
    
}
