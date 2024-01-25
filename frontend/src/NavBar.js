import React from "react";
import { NavLink } from "react-router-dom";
import "./Navbar.css";
import { useHistory } from "react-router-dom";
const NavBar= ({ isLoggedIn,setIsLoggedIn , checkrole }) => {
  const history = useHistory()
  const handleLogout=()=>{
    setIsLoggedIn(false)
    history.push('/Login')
  }
  return (
    <>
      <header className="header">
        <h2>
          {" "}
          <NavLink to="/">My Health care</NavLink>
        </h2>
        <nav className="navbar">
          {isLoggedIn ? (
            <>
              <NavLink to="/doctorsList">Doctors</NavLink>
              <NavLink to="/pharmaciesdetails">Pharmacies</NavLink>
              <NavLink to="/persondetails">Medical History</NavLink>  
              {checkrole.role === 'admin' ?  <NavLink to="/addpharmacy">Add Pharmacy</NavLink>   : null }
              {checkrole.role === 'admin' ?  <NavLink to="/adddoctordetails">Add Doctors</NavLink>   : null }
              {checkrole.role === 'doctor' ?  <NavLink to="/fetchmedicalhistory">Get Medical History</NavLink>   : null }
              <button onClick={handleLogout} style = {{margin:"30px" }}> Log out</button>
            </>
          ) : (
            <>
              {" "}
              <NavLink to="/SignUp">Sign Up</NavLink>
              <NavLink to="/Login">Log In</NavLink>
            </>
          )}
        </nav>
      </header>
    </>
  );
};
export default NavBar;
