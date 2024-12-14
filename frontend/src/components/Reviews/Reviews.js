/* eslint-disable react/prop-types */
import React, { useEffect, useRef } from "react";
import api from "../../api/axiosConfig";
import { useParams } from "react-router-dom";
import { Container, Row, Col } from "react-bootstrap";
import ReviewForm from "../ReviewForm/ReviewForm";

const Reviews = ({ getMovieData, movie, reviews, setReviews }) => {
  const revText = useRef();
  let params = useParams();
  const movieId = params.movieId;

  useEffect(() => {
    getMovieData(movieId);
  }, []);

  const addReview = async (e) => {
    e.preventDefault();

    const rev = revText.current;

    try {
      // eslint-disable-next-line no-unused-vars
      const response = await api.post("/api/v1/reviews", {
        reviewBody: rev.value,
        imdbId: movieId,
      });

      const updatedReviews = [{ body: rev.value }];

      rev.value = "";

      setReviews(updatedReviews);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Container>
      <Row>
        <Col>
          <h3>Reviews</h3>
        </Col>
      </Row>
      <Row className="mt-2 flex-column flex-md-row">
        <Col xs={12} md={4} className="mb-3 mb-md-0">
          <img
            src={movie?.poster}
            alt=""
            style={{ maxWidth: "100%", height: "auto", display: "flex" }}
          />
        </Col>
        <Col xs={12} md={8}>
          {
            <>
              <Row>
                <Col>
                  <ReviewForm
                    handleSubmit={addReview}
                    revText={revText}
                    labelText="Write a Review!"
                  />
                </Col>
              </Row>
              <Row>
                <Col>
                  <hr />
                </Col>
              </Row>
            </>
          }
          {movie?.reviewIds.map((sub, index) => {
            return (
              <React.Fragment key={`movie-review-${index}`}>
                <Row>
                  <Col>{sub.body}</Col>
                </Row>
                <Row>
                  <Col>
                    <hr />
                  </Col>
                </Row>
              </React.Fragment>
            );
          })}

          {reviews?.map((r, index) => {
            return (
              <React.Fragment key={`user-review-${index}`}>
                <Row>
                  <Col>{r.body}</Col>
                </Row>
                <Row>
                  <Col>
                    <hr />
                  </Col>
                </Row>
              </React.Fragment>
            );
          })}
        </Col>
      </Row>
      <Row>
        <Col>
          <hr />
        </Col>
      </Row>
    </Container>
  );
};

export default Reviews;
