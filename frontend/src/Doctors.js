import React, { useEffect, useState } from 'react';
//  import CloseButton from 'react-bootstrap/CloseButton';
import { ToastContainer, toast } from "react-toastify";
import { Link, useHistory } from "react-router-dom";


const Doctors = ({checkrole}) => {
  const[arrayList,setArrayList]=useState([])
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [userDate, setUseDate] = useState("");
  const [doctorId,setDoctorId] = useState("");


  const getDoctorDetails =async()=>{
    const data = await fetch('http://localhost:8081/user/fetch-doctors-details');
    const res = await data.json();

    setArrayList(res.data)
    // console.log('testdata',res)

  }

  const history = useHistory();

  useEffect(()=>{
    if ( arrayList.length == 0 )
    getDoctorDetails()
  },[arrayList])

  const notify = () =>
  toast("Appointment Booked Successfully", { position: "top-right", autoClose: 1200 });
const errornotify = (errMsg) =>
  toast(errMsg, { position: "top-right", autoClose: 1200 });

  const handleSelectedDate=(e)=>{
    setUseDate(e.target.value)

  }
  const handleSelect = async () => {
    console.log({"userId" : checkrole.userId, "doctorId" : doctorId , "appointmentDate" : userDate})
    console.log(userDate);
    try {
      const response = await fetch(
        "http://localhost:8081/user/book-appointment",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({"userId" : checkrole.userId, "doctorId" : doctorId , "appointmentDate" : userDate}),
        }
      );
      const responseData = await response.json();

      console.log(responseData);
      getDoctorDetails()
      if (responseData.status == "Success") {
        console.log("in if")
        notify();
        setTimeout(() => {
          history.push("/doctorsList");
        }, 1200);
      } else errornotify(responseData.message);
      setTimeout(() => {
        history.push("/doctorsList");
      }, 1200);
      
    } catch (error) {
      console.error(error);
    }
    setUseDate("");
  };

  const handbookAppointment = (id) => {
    console.log("docid",id)
    setModalIsOpen(true);
    setDoctorId(id.doctorId);
  }

  const deleteDoctor =  async (id) => {
    console.log("delete ", id)
    try {
      const response = await fetch(
        "http://localhost:8081/user/delete-doctors-details",
        {
          method: "DELETE",

          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(id),
        }
      );
      const responseData = await response.json();
      console.log(responseData);
      getDoctorDetails()


      if (responseData.status == "Success") {
        console.log("in delete if")
        notify();
        setTimeout(() => {
        }, 1200);
      } else errornotify(responseData.message);

      setTimeout(() => {
        history.push("/doctorsList");
      }, 1200);
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
              {' '}
            {/* { checkrole.role=='admin' ? <button onClick={ ()=> deleteDoctor(item )} >Delete</button> : null} */}
              <center>
                {' '}
                <br />
                <h1>{item.doctorName}</h1>
              </center>
            </div>
            <br />
            <div class="title" key = { item.doctorId } >
              <p> Phone :{item.mobileNo}</p>
            </div>
            <div class="des">
              <p>Address:{item.address}</p>
              <button onClick={ ()=>handbookAppointment(item )} style = {{margin:"10px"}}>Book Appointment</button>
              { checkrole.role=='admin' ? <button onClick={ ()=> deleteDoctor(item )} >Delete</button> : null}
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
            <p>Select your Appointment date here</p>
            <label>
              Appointment date:
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
export default Doctors;
