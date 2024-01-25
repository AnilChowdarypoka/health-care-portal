import React from 'react';
import Doctors from './Doctors';

import './Home.css';
import PharmaDetails from './PharmaDetails';
import SignUp from './SignUp';
const Home = () => {
  return (
    <>
      <div className="background-image">
        <div className="background-content">
          <h1>
            Connect With
            <br />
            My Health Care
          </h1>
          <p>
            People who practise medicine are doctors. Doctors are educated to
            promote
            <br />
            wellness and treat illness. Then they choose a course of action. The
            work
            <br />
            of a doctor involves numerous aspects. Doctors must first determine
            <br />
            what is causing a patient's illness.
          </p>
          <div className="icon">
            <a href="https://www.vedantu.com/evs/information-about-doctor">
              Read more
            </a>
          </div>
        </div>
      </div>

      <footer>
        <h2>Copyright &copy; My Health Care.com.</h2>{' '}
      </footer>
    </>
  );
};
export default Home;
