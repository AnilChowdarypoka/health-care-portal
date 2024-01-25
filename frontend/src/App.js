import React, { useState } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Home from "./Home";
import SignUp from "./SignUp";
import NavBar from "./NavBar";
import Doctors from "./Doctors";
import PharmaDetails from "./PharmaDetails";
import FetchMedicalHistory from "./FetchMedicalHistory";
import Login from "./Login";
import "./App.css";
import Personetails from "./Personetails";
import AddDoctorDetails from "./AddDoctorDetails";
import AddPharmacy from "./AddPharmacy";

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [checkrole, setCheckrole] = useState("");

  console.log(checkrole, "testing anil");

  return (
    <Router>
      <div>
        <NavBar
          isLoggedIn={isLoggedIn}
          setIsLoggedIn={setIsLoggedIn}
          checkrole={checkrole}
        />

        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route path="/doctorsList">
            <Doctors checkrole={checkrole} />
          </Route>
          <Route path="/pharmaciesdetails">
            <PharmaDetails checkrole={checkrole} />
          </Route>
          <Route path="/SignUp">
            <SignUp />
          </Route>
          <Route path="/persondetails">
            <Personetails checkrole={checkrole} />
          </Route>
          <Route path="/fetchmedicalhistory">
            <FetchMedicalHistory />
          </Route>

          <Route path="/adddoctordetails">
            <AddDoctorDetails checkrole={checkrole}  />
          </Route>

          <Route path="/addpharmacy">
            <AddPharmacy checkrole={checkrole}  />
          </Route>
          <Route path="/Login">
            <Login
              isLoggedIn={isLoggedIn}
              setIsLoggedIn={setIsLoggedIn}
              checkrole={checkrole}
              setCheckrole={setCheckrole}
            />
          </Route>
        </Switch>
      </div>
    </Router>
  );
};

export default App;
