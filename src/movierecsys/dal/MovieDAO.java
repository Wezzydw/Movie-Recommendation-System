/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;

/**
 *
 * @author pgn
 */
public class MovieDAO implements IMovieRepository

{

    private static final String MOVIE_SOURCE = "data/movie_titles.txt";

    /**
     * Gets a list of all movies in the persistence storage.
     *
     * @return List of movies.
     * @throws java.io.IOException
     */
    @Override
    public List<Movie> getAllMovies() throws IOException
    {
        List<Movie> allMovies = new ArrayList();
        File file = new File(MOVIE_SOURCE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) //Using a try with resources!
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    try
                    {
                        Movie mov = stringArrayToMovie(line);
                        allMovies.add(mov);
                    } catch (Exception ex)
                    {
                        //Do nothing. Optimally we would log the error.
                    }
                }
            }
        }
        return allMovies;
    }

    /**
     * Reads a movie from the comma separated line.
     *
     * @param line the comma separated line.
     * @return The representing Movie object.
     * @throws NumberFormatException
     */
    private Movie stringArrayToMovie(String line)
    {
        String[] arrMovie = line.split(",");
        int id = Integer.parseInt(arrMovie[0]);
        int year = Integer.parseInt(arrMovie[1]);
        String title = arrMovie[2];

        if (arrMovie.length > 3)
        {
            for (int i = 3; i < arrMovie.length; i++)
            {
                title += "," + arrMovie[i];
            }
        }

        Movie mov = new Movie(id, year, title);
        return mov;
    }

    /**
     * Creates a movie in the persistence storage.
     *
     * @param releaseYear The release year of the movie
     * @param title The title of the movie
     * @return The object representation of the movie added to the persistence
     * storage.
     */
    @Override
    public Movie createMovie(int releaseYear, String title) throws IOException
    {
        Path path = new File(MOVIE_SOURCE).toPath();
        int id = -1;
        List<Movie> movies = getAllMovies();
        for (Movie m : movies)
        {
            if (m.getTitle().equals(title) && m.getYear() == releaseYear)
            {
                return null;
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.SYNC, StandardOpenOption.APPEND, StandardOpenOption.WRITE))
        {
            id = getNextAvailableMovieID();
            bw.newLine();
            bw.write(id + "," + releaseYear + "," + title);
        }
        return new Movie(id, releaseYear, title);
    }

    /**
     * Examines all stored movies and returns the next available highest ID.
     *
     * @return
     * @throws IOException
     */
    private int getNextAvailableMovieID() throws IOException
    {
        List<Movie> allMovies = getAllMovies();
        int highId = allMovies.get(allMovies.size() - 1).getId();
        return highId + 1;
    }

    /**
     * Deletes a movie from the persistence storage.
     *
     * @param movie The movie to delete.
     */
    @Override
    public void deleteMovie(Movie movie) throws IOException
    {
        File tmpfile = new File("data/tmp_movie_titles.txt");
        List<Movie> movies = getAllMovies();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfile)))
        {
            for (Movie m : movies)
            {
                if (m.getId() != movie.getId())
                {
                    bw.write(m.getId() + "," + m.getYear() + "," + m.getTitle());
                    bw.newLine();
                }
            }
        }

        Files.copy(tmpfile.toPath(), new File(MOVIE_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmpfile.toPath());
    }

    /**
     * Updates the movie in the persistence storage to reflect the values in the
     * given Movie object.
     *
     * @param movie The updated movie.
     */
    @Override
    public void updateMovie(Movie movie) throws IOException
    {
        File tmpfile = new File("data/tmp_movie_title.txt");
        List<Movie> getAllMovie = getAllMovies();
        int id = movie.getId();
        int year = movie.getYear();
        String title = movie.getTitle();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmpfile)))
        {
            for (Movie m : getAllMovie)
            {
                if (m.getId() == id)
                {
                    bw.write(m.getId() + "," + year + "," + title);
                    bw.newLine();
                } else
                {
                    bw.write(m.getId() + "," + m.getYear() + "," + m.getTitle());
                    bw.newLine();
                }
            }
        }
        Files.copy(tmpfile.toPath(), new File(MOVIE_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(tmpfile.toPath());
    }

    /**
     * Gets a the movie with the given ID.
     *
     * @param id ID of the movie.
     * @return A Movie object.
     */
    @Override
    public Movie getMovie(int id) throws IOException
    {
        //Old works in everycase system, but supposedly slower
        //Keep it for good measure in case of errors
//        for (Movie m : getAllMovies())
//        {
//            if (m.getId() == id)
//            {
//                return m;
//            }
//        }

        List<Movie> movies = getAllMovies();
        Movie m = movies.get(id);
        if (id < movies.size())
        {
            if (m.getId() == id)
            {
                return movies.get(id);
            }

            if (m.getId() < id)
            {
                for (int i = id; i < movies.size(); i++)
                {
                    if (movies.get(i).getId() == id)
                    {
                        return movies.get(i);
                    }
                }
            }

            if (m.getId() > id)
            {
                for (int i = id; i >= 0; i--)
                {
                    if (movies.get(i).getId() == id)
                    {
                        return movies.get(i);
                    }
                }
            }
        } else
        {
            for (int i = movies.size(); i >= 0; i--)
            {
                if (movies.get(i).getId() == id)
                {
                    return movies.get(i);
                }
            }
        }

        return null;
    }


}
