package mx.com.horacio.challenge.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
@Builder
public class Movie {

    @Id
    private String id;
    private String name;
    private String urlPicture;
    private Integer releaseYear;
    private Integer votes;


}
