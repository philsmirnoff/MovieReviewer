package ilfiryakupov.dev.backend;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllMovies() {
        // Arrange
        Movie movie1 = new Movie(
                new ObjectId(),
                "tt1234567",
                "Movie 1",
                "2023-01-01",
                "trailer1",
                "poster1",
                List.of("Genre1"),
                List.of("Backdrop1"),
                null // No reviews for simplicity
        );

        Movie movie2 = new Movie(
                new ObjectId(),
                "tt2345678",
                "Movie 2",
                "2023-02-01",
                "trailer2",
                "poster2",
                List.of("Genre2"),
                List.of("Backdrop2"),
                null
        );

        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        // Act
        List<Movie> movies = movieService.allMovies();

        // Assert
        assertEquals(2, movies.size());
        assertEquals("Movie 1", movies.get(0).getTitle());
        assertEquals("Movie 2", movies.get(1).getTitle());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void testSingleMovie() {
        // Arrange
        String imdbId = "tt1234567";
        Movie movie = new Movie(
                new ObjectId(),
                imdbId,
                "Movie 1",
                "2023-01-01",
                "trailer1",
                "poster1",
                List.of("Genre1"),
                List.of("Backdrop1"),
                null
        );

        when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.of(movie));

        // Act
        Optional<Movie> result = movieService.singleMovie(imdbId);

        // Assert
        assertEquals(true, result.isPresent());
        assertEquals("Movie 1", result.get().getTitle());
        assertEquals(imdbId, result.get().getImdbId());
        verify(movieRepository, times(1)).findMovieByImdbId(imdbId);
    }

    @Test
    void testSingleMovieNotFound() {
        // Arrange
        String imdbId = "tt9999999";
        when(movieRepository.findMovieByImdbId(imdbId)).thenReturn(Optional.empty());

        // Act
        Optional<Movie> result = movieService.singleMovie(imdbId);

        // Assert
        assertEquals(false, result.isPresent());
        verify(movieRepository, times(1)).findMovieByImdbId(imdbId);
    }
}
