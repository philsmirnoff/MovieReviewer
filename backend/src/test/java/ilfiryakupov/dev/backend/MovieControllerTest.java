package ilfiryakupov.dev.backend;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMovies() {
        // Arrange
        Movie movie1 = new Movie(new ObjectId(), "tt1234567", "Movie 1", "2023-01-01", "trailer1", "poster1", List.of("Genre1"), List.of("Backdrop1"), null);
        Movie movie2 = new Movie(new ObjectId(), "tt2345678", "Movie 2", "2023-02-01", "trailer2", "poster2", List.of("Genre2"), List.of("Backdrop2"), null);

        when(movieService.allMovies()).thenReturn(Arrays.asList(movie1, movie2));

        // Act
        ResponseEntity<List<Movie>> response = movieController.getAllMovies();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(movieService, times(1)).allMovies();
    }

    @Test
    void testGetSingleMovie() {
        // Arrange
        String imdbId = "tt1234567";
        Movie movie = new Movie(new ObjectId(), imdbId, "Movie 1", "2023-01-01", "trailer1", "poster1", List.of("Genre1"), List.of("Backdrop1"), null);

        when(movieService.singleMovie(imdbId)).thenReturn(Optional.of(movie));

        // Act
        ResponseEntity<Optional<Movie>> response = movieController.getSingleMovie(imdbId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isPresent());
        assertEquals(imdbId, response.getBody().get().getImdbId());
        verify(movieService, times(1)).singleMovie(imdbId);
    }

    @Test
    void testGetSingleMovieNotFound() {
        // Arrange
        String imdbId = "tt9999999";
        when(movieService.singleMovie(imdbId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Optional<Movie>> response = movieController.getSingleMovie(imdbId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody().isPresent());
        verify(movieService, times(1)).singleMovie(imdbId);
    }
}
