/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
    @FXML
    private BorderPane borderPane;

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


    @FXML
    private void BtnCRUD(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/movierecsys/gui/view/CRUDView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
