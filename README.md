# AnonymousMovieReviewer


<img width="1440" alt="Screenshot 2024-12-17 at 9 23 29 PM" src="https://github.com/user-attachments/assets/39ee6f75-4683-4c33-989b-d76d78b4fda7" />

## Full-Stack Movie Review Application
### A full-stack application that enables users to browse movies, view trailers, and anonymously submit reviews. Built using Java Spring Boot for the backend and React.js for the frontend.

## Key Features:
1) User can browse the movies through carousel.
2) User can watch the trailers of the movies.
3) User can create review for the movies.
4) User can see the prevously written reviews.

<img width="1440" alt="Screenshot 2024-12-17 at 10 02 22 PM" src="https://github.com/user-attachments/assets/8f1037f6-ac12-4928-bed0-de3b1028f827" />


## Technologies Used:
### Frontend (JavaScript, React.js Bootstrap, Axios):
Designed a responsive user interface with React.js and Bootstrap.
Integrated the frontend with backend APIs using Axios for seamless data exchange.
Implemented React Router for dynamic routing and navigation.

### Backend (Java, Spring Boot, Maven, MongoDB):
Developed RESTful APIs to fetch movie data and handle user reviews.
Implemented robust CRUD operations for movies and reviews using a MongoDB database.
Ensured security with CORS configurations and input validations.
Utilized service and repository design patterns for clean and maintainable code.



### Code snippets:
This code snippet updates the reviewIds array in the Movie collection. Each movie initially contains an empty reviewIds array. When a new review is created, the MongoTemplate performs an update operation, matching the movie in the database by its imdbId. The reviewIds array is updated by pushing the newly created review's ID, ensuring that the review is correctly linked to the corresponding movie.
```
package ilfiryakupov.dev.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


/**
 * Service for managing reviews.
 */
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {

        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}

```

Tools:

IntelliJ IDEA,
Postman,
Git,
GitHub

