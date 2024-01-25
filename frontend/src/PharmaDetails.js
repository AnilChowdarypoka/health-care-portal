import React, { useEffect, useState } from "react";
import "./PharmaDetails.css";
import { ToastContainer, toast } from "react-toastify";
import { Link, useHistory } from "react-router-dom";


// import { Modal, ModalHeader, ModalBody } from "reactstrap";
const PharmaDetails = ({checkrole}) => {
  const [arrayList, setArrayList] = useState([]);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [userDate, setUseDate] = useState("");
  const [doctorId,setDoctorId] = useState("");

  console.log("checkrole" , checkrole)
 
  const history = useHistory();

  const getPharmaDetails = async () => {
    const data = await fetch('http://localhost:8081/user/fetch-pharmacy-details');
    const res = await data.json();

    setArrayList(res.data);
    console.log("testdata", res);
  };
  useEffect(() => {
    if (arrayList.length === 0) {
      getPharmaDetails();
    }
  }, [arrayList]);

  const notify = () =>
  toast("Medical slot Booked Successfully", { position: "top-right", autoClose: 1200 });
const errornotify = (errMsg) =>
  toast(errMsg, { position: "top-right", autoClose: 1200 });

  const handleSelectedDate=(e)=>{
    setUseDate(e.target.value)
  }

  const handbookAppointment = (id) => {
    console.log("pharma id ",id)
    setModalIsOpen(true);
    setDoctorId(id.pharmacyId);
  }



  const handleSelect = async () => {
    console.log("here",checkrole.password)
    console.log({"userId" : checkrole.userId , "pharmacyId" : doctorId , "EstimatedDeliveryDate" : userDate})
    console.log(userDate);
    try {
      const response = await fetch(
        "http://localhost:8081/user/medical-appointment",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({"userId" : checkrole.userId, "pharmacyId" : doctorId , "estimatedDeliveryDate" : userDate}),
        }
      );
      const responseData = await response.json();

      console.log(responseData);

      if (responseData.status == "Success") {
        console.log("in if")
        notify();
        setTimeout(() => {
          history.push("/pharmaciesdetails");
        }, 1200);
      } else errornotify(responseData.message);

      
    } catch (error) {
      console.error(error);
    }
    setUseDate("");
  };


  const deletePharmacy =  async (id) => {
    try {
      console.log("delete pharma" , id)
      const response = await fetch(
        "http://localhost:8081/user/delete-pharmacy-details",
        {
          method: "DELETE",

          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(id),
        }
      );
      const responseData = await response.json();
      getPharmaDetails();
      console.log(responseData);

      if (responseData.status == "Success") {
        console.log("in if")
        notify();
        setTimeout(() => {
        }, 1200);
      } else errornotify(responseData.message);

      
    } catch (error) {
      console.error(error);
    }
    setUseDate("");
   
  }
  return (
    <div>
      {arrayList.map((item) => {
        return (
          <div className="card" key = { item.doctorId } >
            <div className="image">
              {" "}
              {/* { checkrole.role=='admin' ? <button onClick={ ()=> deleteDoctor(item )} >Delete</button> : null} */}
              <center>
                {" "}
                <h1>{item.pharmacyName}</h1>
              </center>
            </div>
            <br />
            <div class="title"  key = { item.doctorId } >
              <p> Phone :{item.mobileNo}</p>
            </div>
            <div class="des">
              <p>Address:{item.address}</p>
              <button onClick={() => handbookAppointment(item) } style = {{margin:"10px"}}>Buy Now</button>
              { checkrole.role=='admin' ? <button onClick={ ()=> deletePharmacy(item )} >Delete</button> : null}
            </div>
          </div>
        );
      })}

      {modalIsOpen && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={() => setModalIsOpen(false)}>
              &times;
            </span>
            <p>Select your date here</p>
            <label>
              Date of Birth:
              <input
                type="date"
                name="dob"
                value={userDate}
                onChange={handleSelectedDate}
              />
            </label>
            <button onClick={handleSelect}>Select</button>
            <ToastContainer position="top-right" autoClose={600} />
          </div>
        </div>
      )}
    </div>
  );
};
export default PharmaDetails;