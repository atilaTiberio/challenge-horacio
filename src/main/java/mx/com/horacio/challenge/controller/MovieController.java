package mx.com.horacio.challenge.controller;

import mx.com.horacio.challenge.entity.Movie;
import mx.com.horacio.challenge.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("movie")
public class MovieController {

   @Autowired
    private MovieService movieService;
    @PostMapping
    public Movie insertMovie(@RequestBody Movie movie){
        return movieService.insertMovie(movie);
    }

    @GetMapping("{releaseYear}")
    public List<Movie> getMoviesByReleaseYear(@PathVariable Integer releaseYear){
        return movieService.getMoviesByReleaseYear(releaseYear);
    }
    @GetMapping
    public Map<Integer,List<Movie>> getMoviesGroupByReleaseYear(){
        return movieService.getMoviesGroupByReleaseYear();
    }

    @PutMapping("/vote/{id}/{vote}")
    public Movie movieVote(@PathVariable String id, @PathVariable Integer vote){
        return movieService.incrementMovieVote(id,vote);

    }

    @GetMapping("/filter/{name}")
    public List<Movie> getMoviesByName(@PathVariable String name){
        return movieService.getMoviesByName(name);
    }

    @GetMapping("/get/{id}")
    public Movie getMovieById(@PathVariable String id ){
        return movieService.getMovieById(id);
    }

}
