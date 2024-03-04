package mx.com.horacio.challenge;

import mx.com.horacio.challenge.entity.Movie;
import mx.com.horacio.challenge.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MovieServiceTests {

    @Autowired
    private  MovieService movieService;

    public void setupData(){
        Movie lordOfTheRings = Movie.builder()
                .name("Lord of the Rings")
                .votes(10)
                .releaseYear(2000)
                .urlPicture("/some/url")
                .build();

        Movie terminator = Movie.builder()
                .name("Terminator")
                .votes(20)
                .releaseYear(1984)
                .urlPicture("/some/url")
                .build();

        Movie movie = Movie.builder()
                .name("Terminator 2")
                .votes(30)
                .releaseYear(1984)
                .urlPicture("/some/url")
                .build();

        movieService.insertMovie(lordOfTheRings);
        movieService.insertMovie(terminator);
        movieService.insertMovie(movie);
    }

    @Test
    public void testInsertMovie(){
        Movie movie = Movie.builder()
                .name("Harry Potter")
                .votes(0)
                .releaseYear(2000)
                .urlPicture("/some/url")
                .build();
        Movie returnByService = movieService.insertMovie(movie);

        Assert.notNull(returnByService.getId(),"Movie was not saved");
    }

    @Test
    public void testGetMovieByReleaseYear(){
        setupData();
        List<Movie> movies = movieService.getMoviesByReleaseYear(1984);

        Assert.isTrue(movies.size() == 6, "Something went wrong when listing movies");

    }

    @Test
    public void testGetMoviesGroupByReleaseYear(){
        setupData();
        Map<Integer,List<Movie>> movies = movieService.getMoviesGroupByReleaseYear();

        Assert.isTrue(movies.size() == 2, "Movie group is not working as expected");
    }

    @Test
    public void testIncrementMovieVote (){
        Movie movie = Movie.builder()
                .name("Harry Potter")
                .votes(0)
                .releaseYear(2000)
                .urlPicture("/some/url")
                .build();
        Movie returnByService = movieService.insertMovie(movie);

        Movie incrementedMovieVote = movieService.incrementMovieVote(returnByService.getId(), 50);
        Assert.isTrue(incrementedMovieVote.getVotes() == 50, "Something went wrong when updating movie vote");
    }

    @Test
    public void testOrderedMovies(){
        setupData();
        List<Movie> movies = movieService.getMoviesByName("Terminator");

        Assert.isTrue( movies.get(0).getVotes() == 30, "Movies are not ordered");
        Assert.isTrue( movies.size() == 4, "Something went wrong when getting movies");
    }

    @Test
    public void testGetMovieById (){
        Movie movie = Movie.builder()
                .name("Harry Potter")
                .votes(0)
                .releaseYear(2000)
                .urlPicture("/some/url")
                .build();
        Movie returnByService = movieService.insertMovie(movie);

        Movie getMovie = movieService.getMovieById( returnByService.getId());

        Assert.isTrue(movie.getName().equals( getMovie.getName()), "It's not the same movie");
        Assert.isTrue(movie.getReleaseYear().intValue() == getMovie.getReleaseYear().intValue(), "It's not the same movie");
    }
}
