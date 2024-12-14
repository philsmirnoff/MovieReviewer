package ilfiryakupov.dev.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

class ReviewControllerTest {

    private MockMvc mockMvc;

    private ReviewService reviewService;

    @BeforeEach
    void setup() throws Exception {
        // Mock the ReviewService
        reviewService = mock(ReviewService.class);

        // Create an instance of ReviewController
        ReviewController reviewController = new ReviewController();

        // Use reflection to set the private field 'reviewService'
        Field reviewServiceField = ReviewController.class.getDeclaredField("reviewService");
        reviewServiceField.setAccessible(true);
        reviewServiceField.set(reviewController, reviewService);

        // Set up MockMvc with the controller
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    void testCreateReview() throws Exception {
        // Arrange
        String reviewBody = "This is a great movie!";
        String imdbId = "tt1234567";

        Review mockReview = new Review(reviewBody);

        // Mock behavior for reviewService.createReview
        when(reviewService.createReview(reviewBody, imdbId)).thenReturn(mockReview);

        // Act & Assert
        mockMvc.perform(post("/api/v1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reviewBody\": \"This is a great movie!\", \"imdbId\": \"tt1234567\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.body", is(reviewBody)));
    }
}


