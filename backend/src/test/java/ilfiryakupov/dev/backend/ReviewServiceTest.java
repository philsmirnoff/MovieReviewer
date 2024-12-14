package ilfiryakupov.dev.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);

        reviewService = new ReviewService();

        // Inject the mock ReviewRepository using reflection
        Field reviewRepoField = ReviewService.class.getDeclaredField("reviewRepository");
        reviewRepoField.setAccessible(true);
        reviewRepoField.set(reviewService, reviewRepository);

        // Inject the mock MongoTemplate using reflection
        Field mongoTemplateField = ReviewService.class.getDeclaredField("mongoTemplate");
        mongoTemplateField.setAccessible(true);
        mongoTemplateField.set(reviewService, mongoTemplate);
    }

    @Test
    void testCreateReview() {
        // Arrange
        String reviewBody = "This is a great movie!";
        String imdbId = "tt1234567";

        Review mockReview = new Review(reviewBody);

        // Mock behavior for ReviewRepository and MongoTemplate
        when(reviewRepository.insert(any(Review.class))).thenReturn(mockReview);

        // Act
        Review createdReview = reviewService.createReview(reviewBody, imdbId);

        // Assert
        verify(reviewRepository, times(1)).insert(any(Review.class));
        verify(mongoTemplate, times(1)).update(eq(Movie.class));
        verify(mongoTemplate).update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(mockReview))
                .first();

        // Additional Assertions
        assert createdReview.getBody().equals(reviewBody);
    }
}

