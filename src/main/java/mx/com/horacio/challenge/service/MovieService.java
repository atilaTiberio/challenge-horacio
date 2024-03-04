package mx.com.horacio.challenge.service;

import mx.com.horacio.challenge.entity.Movie;
import mx.com.horacio.challenge.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class MovieService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MovieRepository movieRepository;

    public Movie insertMovie( Movie movie){
        return movieRepository.insert(movie);
    }

    public List<Movie> getMoviesByReleaseYear(Integer releaseYear){
        return movieRepository.findMoviesByReleaseYear(releaseYear);
    }

    public Map<Integer,List<Movie>> getMoviesGroupByReleaseYear(){
      /*  TypedAggregation<Movie> movieTypedAggregation =
                Aggregation.newAggregation(Movie.class,
                        Aggregation.group("releaseYear").push(
                                        new BasicDBObject
                                                ("_id","$_id").append
                                                ("name","$name")
                                )
                                .as("movies"));
        AggregationResults<Movie> results = mongoTemplate.aggregate(movieTypedAggregation,Movie.class);

        return results.getMappedResults();
        */
        return movieRepository.findAll().stream().collect(groupingBy(Movie::getReleaseYear));
    }

    public Movie incrementMovieVote(String id, int increment){
        movieRepository.findAndIncrementVotesById(id,increment);
        return movieRepository.findById(id).get();

    }

    public List<Movie> getMoviesByName (String name){
        return movieRepository.findAllByNameContainingOrderByVotesDesc(name);
    }

    public Movie getMovieById (String id){
        return movieRepository.findById(id).get();
    }

}
