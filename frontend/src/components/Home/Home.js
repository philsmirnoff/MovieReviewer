import React from 'react';
import Hero from '../Hero/Hero';

// eslint-disable-next-line react/prop-types
const Home = ({movies}) => {

  return (
    <Hero movies = {movies} />
  )
}

export default Home
