import React, { useState } from "react";
import "./SignUp.css";
import { useHistory } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

function SignUp({setIsLoggedIn,setCheckrole}) {
  const [formData, setFormData] = useState({
    userName: "",
    password: "",
  });
const history = useHistory()
  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  
  const notify = () =>
    toast("User logged in  Successfully", { position: "top-right", autoClose: 1200 });
  const errornotify = (errMsg) =>
    toast(errMsg, { position: "top-right", autoClose: 1200 });


  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log("signUpdata=>", formData);
    try {
      const response = await fetch(
        "http://localhost:8081/user/login",
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
        setIsLoggedIn(true);
        setCheckrole(responseData.data)
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
        User Name:
        <input
          type="text"
          name="userName"
          placeholder="enter user name "
          value={formData.userName}
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
      

      <button type="submit">Login</button>
      <ToastContainer position="top-right" autoClose={600} />

    </form>
  );
}

export default SignUp;
