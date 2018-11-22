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
    List<Movie> searchBase;
    List<Movie> empty;
    MovieRecModel mrm;

    /**
     * The TextField containing the query word.
     */
    @FXML
    private ListView<Movie> lstMovies;
    @FXML
    private TextField txtMovieSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        mrm = new MovieRecModel();
        empty = new ArrayList();
        ms = new MovieSearcher();
        searchBase = new ArrayList();
        try
        {
            searchBase = ms.search(empty, "");
        } catch (IOException ex)
        {
            Logger.getLogger(MovieRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
        lstMovies.getItems().addAll(searchBase);
    }

    @FXML
    private void handleString(KeyEvent event) throws IOException
    {
        String s = txtMovieSearch.getText();

            //New one, testing later lstMovies.getItems().setAll(mrm.getOBList(s));
            lstMovies.getItems().setAll(ms.search(empty, s));



    }

}
