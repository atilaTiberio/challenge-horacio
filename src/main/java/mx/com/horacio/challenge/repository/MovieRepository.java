package mx.com.horacio.challenge.repository;

import mx.com.horacio.challenge.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findMoviesByReleaseYear(Integer releaseYear);

    @Update("{'$inc': { 'votes' : ?1}}")
    void findAndIncrementVotesById(String id, int increment);
    List<Movie> findAllByNameContainingOrderByVotesDesc(String name);

}
