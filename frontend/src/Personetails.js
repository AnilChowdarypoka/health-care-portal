import React, { useEffect, useState } from "react";
import "./Personetails.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link, useHistory } from "react-router-dom";

const Personetails = ({checkrole}) => {

  console.log(checkrole)

  const [userId, setUserId] = useState("");
  const [systolicBp, setSystolicBp] = useState("");
  const [diastolicBp, setDiastolicBp] = useState("");
  const [bloodOxygenLevel, setBloodOxygenLevel] = useState("");
  const [bodyTemperature, setBodyTemperature] = useState("");
  const [previousMedicalHistory, setPreviousMedicalHistory] = useState("");
  const [allergies, setAllergies] = useState("");
  const [otherSymptoms, setotherSymptoms] = useState("");

  
  const notify = () =>
    toast("Medical History uploaded Succesfully", {
      position: "top-right",
      autoClose: 600,
    });

  const errornotify = () =>
    toast("Failed to upload Medical History", {
      position: "top-right",
      autoClose: 600,
    });

  const history = useHistory();

  const handleSubmit = async (event) => {
    event.preventDefault();

    const data = {
      
      userId: checkrole.userId,
      systolicBp: systolicBp,
      diastolicBp: diastolicBp,
      bloodOxygenLevel: bloodOxygenLevel,
      bodyTemperature: bodyTemperature,
      previousMedicalHistory: previousMedicalHistory,
      allergies: allergies,
      otherSymptoms: otherSymptoms,
    };
   

    try {


      const response = await fetch(
        "http://localhost:8081/user/add-medical-history",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(data),
        }
      );
      setUserId("");
      setSystolicBp("");
      setDiastolicBp("");
      setBloodOxygenLevel("");
      setBodyTemperature("");
      setPreviousMedicalHistory("");
      setAllergies("");
      setotherSymptoms("");

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
        {}

        <label htmlFor="systolicBp">
          Systolic BP:
        <input
            type="text"
            id="systolicBp"
            value={systolicBp}
            onChange={(event) => setSystolicBp(event.target.value)}
            required
          />
        </label>

        <label htmlFor="diastolicBp">
          Diastolic BP:
          <input
            type="text"
            id="diastolicBp"
            value={diastolicBp}
            onChange={(event) => setDiastolicBp(event.target.value)}
            required
          />
        </label>

        <label htmlFor="bloodOxygenLevel">
          Blood Oxygen Level:
          <input
            type="text"
            id="bloodOxygenLevel"
            value={bloodOxygenLevel}
            onChange={(event) => setBloodOxygenLevel(event.target.value)}
            required
          />
        </label>

        <label htmlFor="bodyTemperature">
          Body Temperature:
          <input
            type="text"
            id="bodyTemperature"
            value={bodyTemperature}
            onChange={(event) => setBodyTemperature(event.target.value)}
            required
          />
        </label>

        <label htmlFor="previousMedicalHistory">
          Previous Medical History:
          <textarea
            id="previousMedicalHistory"
            value={previousMedicalHistory}
            onChange={(event) => setPreviousMedicalHistory(event.target.value)}
            required
          />
        </label>

        <label htmlFor="allergies">
          Allergies:
          <textarea
            id="allergies"
            value={allergies}
            onChange={(event) => setAllergies(event.target.value)}
            required
          />
        </label>

        <label htmlFor="otherSymptoms">
          otherSymptoms:
          <textarea
            type="text"
            id="otherSymptoms"
            value={otherSymptoms}
            onChange={(event) => setotherSymptoms(event.target.value)}
            required
          />
        </label>

        <button type="submit">Submit</button>
        <ToastContainer position="top-right" autoClose={600} />
      </form>
    </div>
  );
}

export default Personetails;
