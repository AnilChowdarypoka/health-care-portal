import React, { useEffect, useState } from "react";
import "./PharmaDetails.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useHistory } from "react-router-dom";

function AddDoctorDetails({ checkrole }) {
  const [addedBy, setAddedBy] = useState(checkrole.userId);
  const [doctorName, setdoctorName] = useState("");
  const [address, setaddress] = useState("");
  const [mobileNo, setmobileNo] = useState("");

  console.log("checkrole", checkrole);

  const notify = () =>
    toast("Doctors data uploaded Succesfully", {
      position: "top-right",
      autoClose: 600,
    });

  const errornotify = () =>
    toast("Failed to add doctor data", {
      position: "top-right",
      autoClose: 600,
    });

  const history = useHistory();

  const handleSubmit = async (event) => {
    event.preventDefault();

    // setAddedBy(checkrole.userId)

    console.log("addedBy", addedBy);

    const data = {
      addedBy: addedBy,
      doctorName: doctorName,
      address: address,
      mobileNo: mobileNo,
    };
    console.log(data, "TestDats");
    try {
      console.log("apidata", data);

      const response = await fetch(
        "http://localhost:8081/user/add-doctors-details",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },

          body: JSON.stringify(data),
        }
      );
      setAddedBy("");
      setdoctorName("");
      setaddress("");
      setmobileNo("");

      const responseData = await response.json();
      console.log(responseData);

      if (responseData.status == "Success") {
        notify();
      } else errornotify();
      setTimeout(() => {
        history.push("/");
      }, 1200);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit}>
        {/* <label htmlFor="addedBy">User ID:</label>
        <input
          type="text"
          id="addedBy"
          value={addedBy}
          onChange={(event) => setaddedBy(event.target.value)}
          required
        /> */}

        <label htmlFor="doctorName">Doctor Name:</label>
        <input
          type="text"
          id="doctorName"
          value={doctorName}
          onChange={(event) => setdoctorName(event.target.value)}
          required
        />

        <label htmlFor="address">Address:</label>
        <input
          type="text"
          id="address"
          value={address}
          onChange={(event) => setaddress(event.target.value)}
          required
        />

        <label htmlFor="mobileNo">Mobile:</label>
        <input
          type="text"
          id="mobileNo"
          value={mobileNo}
          onChange={(event) => setmobileNo(event.target.value)}
          required
        />

        <button type="submit">Submit</button>
        <ToastContainer position="top-right" autoClose={600} />
      </form>
    </div>
  );
}

export default AddDoctorDetails;
