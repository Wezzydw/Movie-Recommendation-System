/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wezzy
 */
public class CRUDViewController implements Initializable
{
    MovieRecModel mrm;
    
    @FXML
    private TextField txtFieldId;
    @FXML
    private TextField txtFieldYear;
    @FXML
    private TextField txtFieldTitle;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            this.mrm = new MovieRecModel();
        } catch (IOException ex)
        {
            Logger.getLogger(CRUDViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnC(ActionEvent event)
    {
        String s = txtFieldId.getText();
        s += "," + txtFieldYear.getText();
        s += "," +txtFieldTitle.getText();
        System.out.println(s);
        mrm.createMovie(s);
    }

    @FXML
    private void btnU(ActionEvent event)
    {
        String s = txtFieldId.getText();
        s += "," + txtFieldYear.getText();
        s += "," +txtFieldTitle.getText();
        mrm.updateMovie(s);
    }

    @FXML
    private void btnD(ActionEvent event)
    {
        String s = txtFieldId.getText();
        s += "," + txtFieldYear.getText();
        s += "," +txtFieldTitle.getText();
        mrm.deleteMovie(s);
    }

    @FXML
    private void btnBack(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/movierecsys/gui/view/MovieRecView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
}
