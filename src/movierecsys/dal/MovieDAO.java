/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;

/**
 *
 * @author pgn
 */
public class MovieDAO
{

    private static final String SOURCE = "data/movie_titles.txt";
    /**
     * Gets a list of all movies in the persistence storage.
     *
     * @return List of movies.
     */
    public List<Movie> getAllMovies() throws IOException
    {
        List<Movie> allMovies = new ArrayList();
        File file = new File(SOURCE);
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines)
        {
            String[] column = line.split(",");
            int id = Integer.parseInt(column[0]);
            int year = Integer.parseInt(column[1]);
            String title = column[2];
            Movie movie = new Movie (id, year, title);
            allMovies.add(movie);
        }
        return allMovies;
    }

    /**
     * Creates a movie in the persistence storage.
     *
     * @param releaseYear The release year of the movie
     * @param title The title of the movie
     * @return The object representation of the movie added to the persistence
     * storage.
     */
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        BufferedWriter bw = null;
        FileWriter fw = null;
        List<Movie> movies = getAllMovies();
        int id = movies.size() + 2;
        Movie newMovie = new Movie(id, releaseYear, title);
        movies.add(newMovie); 
        
        fw = new FileWriter(SOURCE, true);
        bw = new BufferedWriter(fw);
        bw.write(toString(newMovie));
        bw.close();
        fw.close();
        return newMovie;
    }

    /**
     * Deletes a movie from the persistence storage.
     *
     * @param movie The movie to delete.
     */
    private void deleteMovie(Movie movie)
    {
        //TODO Delete movie
    }

    /**
     * Updates the movie in the persistence storage to reflect the values in the
     * given Movie object.
     *
     * @param movie The updated movie.
     */
    private void updateMovie(Movie movie)
    {
        //TODO Update movies
    }

    /**
     * Gets a the movie with the given ID.
     *
     * @param id ID of the movie.
     * @return A Movie object.
     */
    private Movie getMovie(int id)
    {
        //TODO Get one Movie
        return null;
    }
    
    public String toString(Movie movie)
    {
        return "" +movie.getId() + "," + movie.getYear() + "," + movie.getTitle();
    }

}
