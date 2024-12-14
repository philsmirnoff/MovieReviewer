package ilfiryakupov.dev.backend;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MovieTest {

    @Test
    void testDefaultConstructor() {
        // Arrange
        Movie movie = new Movie();

        // Assert
        assertNull(movie.getId());
        assertNull(movie.getImdbId());
        assertNull(movie.getTitle());
        assertNull(movie.getReleaseDate());
        assertNull(movie.getTrailerLink());
        assertNull(movie.getPoster());
        assertNull(movie.getGenres());
        assertNull(movie.getBackdrops());
        assertNull(movie.getReviewIds());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        ObjectId id = new ObjectId();
        String imdbId = "tt1234567";
        String title = "Test Movie";
        String releaseDate = "2023-01-01";
        String trailerLink = "http://trailer.com";
        String poster = "http://poster.com";
        List<String> genres = List.of("Action", "Adventure");
        List<String> backdrops = List.of("Backdrop1", "Backdrop2");
        List<Review> reviewIds = List.of(new Review("Great movie!"), new Review("Must watch!"));

        // Act
        Movie movie = new Movie(id, imdbId, title, releaseDate, trailerLink, poster, genres, backdrops, reviewIds);

        // Assert
        assertEquals(id, movie.getId());
        assertEquals(imdbId, movie.getImdbId());
        assertEquals(title, movie.getTitle());
        assertEquals(releaseDate, movie.getReleaseDate());
        assertEquals(trailerLink, movie.getTrailerLink());
        assertEquals(poster, movie.getPoster());
        assertEquals(genres, movie.getGenres());
        assertEquals(backdrops, movie.getBackdrops());
        assertEquals(reviewIds, movie.getReviewIds());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Movie movie = new Movie();

        ObjectId id = new ObjectId();
        String imdbId = "tt1234567";
        String title = "Test Movie";
        String releaseDate = "2023-01-01";
        String trailerLink = "http://trailer.com";
        String poster = "http://poster.com";
        List<String> genres = List.of("Action", "Adventure");
        List<String> backdrops = List.of("Backdrop1", "Backdrop2");
        List<Review> reviewIds = List.of(new Review("Great movie!"), new Review("Must watch!"));

        // Act
        movie.setId(id);
        movie.setImdbId(imdbId);
        movie.setTitle(title);
        movie.setReleaseDate(releaseDate);
        movie.setTrailerLink(trailerLink);
        movie.setPoster(poster);
        movie.setGenres(genres);
        movie.setBackdrops(backdrops);
        movie.setReviewIds(reviewIds);

        // Assert
        assertEquals(id, movie.getId());
        assertEquals(imdbId, movie.getImdbId());
        assertEquals(title, movie.getTitle());
        assertEquals(releaseDate, movie.getReleaseDate());
        assertEquals(trailerLink, movie.getTrailerLink());
        assertEquals(poster, movie.getPoster());
        assertEquals(genres, movie.getGenres());
        assertEquals(backdrops, movie.getBackdrops());
        assertEquals(reviewIds, movie.getReviewIds());
    }
}

