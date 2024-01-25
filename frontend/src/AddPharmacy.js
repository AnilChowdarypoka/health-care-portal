





import React, { useEffect, useState } from "react";
import "./PharmaDetails.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useHistory } from "react-router-dom";

function AddPharmacy({checkrole}) {
  const [addedBy, setAddedBy] = useState(checkrole.userId);
  const [pharmacyName, setpharmacyName] = useState("");
  const [address, setaddress] = useState("");
  const [mobileNo, setmobileNo] = useState("");
 
  console.log("checkrole pharmacy" , checkrole)
//   setAddedBy(checkrole.userId)
  



  const notify = () =>
  toast("Pharmacy data uploaded Succesfully", { position: "top-right", autoClose: 600 });

  const errornotify = () =>
  toast("Failed to add doctor data", { position: "top-right", autoClose: 600 });

  const history = useHistory();

  const handleSubmit = async (event) => {
    event.preventDefault();

     
console.log("addedBy pharmacy ", addedBy)


    const data = {
      addedBy: addedBy,
      pharmacyName: pharmacyName,
      address: address,
      mobileNo: mobileNo,

      
    };
    console.log(data, "TestDats");
    try {
      const response = await fetch("http://localhost:8081/user/add-pharmacy-details", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },

        body: JSON.stringify(data),
      });
      setAddedBy("")
      setpharmacyName("")
      setaddress("")
      setmobileNo("")
      

      const responseData = await response.json();
      console.log(responseData);

      if (responseData.status == 'Success')  {      notify()      }
      else errornotify()
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

        <label htmlFor="pharmacyName">Pharmacy Name:</label>
        <input
          type="text"
          id="pharmacyName"
          value={pharmacyName}
          onChange={(event) => setpharmacyName(event.target.value)}
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

export default AddPharmacy;
