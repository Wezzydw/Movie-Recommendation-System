/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import movierecsys.be.Movie;
import movierecsys.bll.util.MovieSearcher;

/**
 *
 * @author pgn
 */
public class MovieRecController implements Initializable
{

    MovieSearcher ms;
    MovieRecModel mrm;

    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;
    @FXML
    private TextField txtMovieSearch;

    public MovieRecController() throws IOException
    {
        this.mrm = new MovieRecModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            lstMovies.setItems(mrm.getOBList());
        } catch (IOException ex)
        {
            System.out.println("Shit died here");
        }
    }

    @FXML
    private void handleString(KeyEvent event) throws IOException
    {
        String s = txtMovieSearch.getText();
        mrm.changeView(s);
    }

}
