import React, { useState } from "react";

import "./SignUp.css";

import { Link, useHistory } from "react-router-dom";

import { ToastContainer, toast } from "react-toastify";

import "react-toastify/dist/ReactToastify.css";

function SignUp() {
  const [formData, setFormData] = useState({
    userName: "",
    fullName: "",
    password: "",
    dob: "",
    emailId : "",
    address: "",
    country: "",
    mobileNo: "",
    role: "",
    primaryContactPersonName: "",
    primaryContactPersonMobile: "",
  });

  const notify = () =>
    toast("Succesfully Registered", { position: "top-right", autoClose: 1200 });
  const errornotify = (errMsg) =>
    toast(errMsg, { position: "top-right", autoClose: 1200 });

  const history = useHistory();

  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormData({ ...formData, [name]: value });
  };

  // const handleSubmit = (event) => {

  //   notify();

  //   event.preventDefault();

  //   console.log(formData);

  //   setTimeout(() => {

  //     history.push("/Login");

  //   }, 1200);

  // };

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log("signUpdata=>", formData);
    try {
      const response = await fetch(
        "http://localhost:8081/user/create-user",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json",
          },

          body: JSON.stringify(formData),
        }
      );
      const responseData = await response.json();

      console.log(responseData);

      if (responseData.status == "Success") {
        notify();
        setTimeout(() => {
          history.push("/");
        }, 1200);
      } else errornotify(responseData.message);

      
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Name:
        <input
          type="text"
          name="userName"
          placeholder="create user name"
          value={formData.userName}
          onChange={handleChange}
        />
      </label>
      <label>
        Full name
        <input
          type="text"
          name="fullName"
          placeholder="Enter Full  name"
          value={formData.fullName}
          onChange={handleChange}
        />
      </label>

      <label>
        Password:
        <input
          type="password"
          name="password"
          placeholder="create password"
          value={formData.password}
          onChange={handleChange}
        />
      </label>

      <label>
      email Id:
        <input
          type="email"
          name="emailId"
          placeholder="Enter Email"
          value={formData.emailId}
          onChange={handleChange}
        />
      </label>

      <label>
        Date of Birth:
        <input
          type="date"
          name="dob"
          value={formData.dob}
          onChange={handleChange}
        />
      </label>
      <label>
        Address:
        <input
          type="text"
          name="address"
          value={formData.address}
          onChange={handleChange}
        />
      </label>
      <label>
      Country:
        <select name="country" value={formData.country} onChange={handleChange}>
          <option value={formData.country}>-- Select a Country--</option>

          <option value="India">India</option>

          <option value="USA">USA</option>

        </select>
      </label>

      <label>
        Phone:
        <input
          type="number"
          name="mobileNo"
          value={formData.mobileNo}
          onChange={handleChange}
        />
      </label>

      <label>
        Role:
        <select name="role" value={formData.role} onChange={handleChange}>
          <option value={formData.role}>-- Select a Role--</option>

          <option value="doctor">Doctor</option>

          <option value="user">User</option>

          <option value="admin">Admin</option>
        </select>
      </label>
      <label>
        Primary person Name
        <input
          type="text"
          name="primaryContactPersonName"
          placeholder="Enter Full  primaryContactPersonName"
          value={formData.primaryContactPersonName}
          onChange={handleChange}
        />
      </label>
      <label>
        Primary person Mobile
        <input
          type="number"
          name="primaryContactPersonMobile"
          value={formData.primaryContactPersonMobile}
          onChange={handleChange}
        />
      </label>
      <button type="submit">Submit</button>

      <div>
        {" "}
        Already have Account? <Link to="/Login">Login here</Link>
      </div>

      <ToastContainer position="top-right" autoClose={600} />
    </form>
  );
}

export default SignUp;