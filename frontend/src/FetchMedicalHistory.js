import React, { useEffect, useState } from "react";
// import "./FetchMedicalHistory.css";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

const FetchMedicalHistory = () => {
  const [medicalList, setMedicalList] = useState([]);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [fetchIndvItem, setFetchIndvItem] = useState([]);

  const getMedicalHistory = async () => {
    const data = await fetch(
      "http://localhost:8081/user/fetch-medical-history"
    );
    const res = await data.json();
    setMedicalList(res.data);
  };

  

  useEffect(() => {
    if (medicalList.length == 0) getMedicalHistory();
  }, [medicalList]);

  const handleMedical = (id) => {
    console.log("docid", id);
    setFetchIndvItem(id);
    setModalIsOpen(true);
  };

  const tempNames = Object.entries(medicalList);
  
  const indvItem = tempNames.map(([key, value]) => (

    <div className="card" key = { key } >
    <div className="image">
      {' '}
    {}
      <center>
        {' '}
        <br />
        <h1>{key}</h1>
      </center>
    </div>

    <div class="des">
    
      <button onClick={() => handleMedical(value)}>
            Get Medical History
         </button>
      {}
    </div>  
  </div>

 
  ));

  console.log("tempNames", tempNames);
  return (
    <div>
      {indvItem}
      {modalIsOpen && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={() => setModalIsOpen(false)}>
              &times;
            </span>
            <TableContainer component={Paper}>
    <Table sx={{ minWidth: 650 }} aria-label="simple table">
      <TableHead style = {{color:"red"}} >
        <TableRow >
          <TableCell align="right">bodyTemperature</TableCell>
          <TableCell align="right">diastolicBp</TableCell>
          <TableCell align="right">systolicBp&nbsp;</TableCell>
          <TableCell align="right">allergies&nbsp;</TableCell>
          <TableCell align="right">bloodOxygenlevel&nbsp;</TableCell>
          <TableCell align="right">otherSymptoms&nbsp;</TableCell>
          <TableCell align="right">previousMedicalHistory&nbsp;</TableCell>
          <TableCell align="right">recordedAt&nbsp;</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {fetchIndvItem.map((row) => (
          <TableRow
            key={row.id}
            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
          >
            <TableCell component="th" scope="row">
              {row.bodyTemperature}
            </TableCell>
            <TableCell align="right">{row.diastolicBp}</TableCell>
            <TableCell align="right">{row.systolicBp}</TableCell>
            <TableCell align="right">{row.allergies}</TableCell>
            <TableCell align="right">{row.bloodOxygenlevel}</TableCell>
            <TableCell align="right">{row.otherSymptoms}</TableCell>
            <TableCell align="right">{row.previousMedicalHistory}</TableCell>
            <TableCell align="right">{row.recordedAt}</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  </TableContainer>
          </div>
        </div>
      )}
    </div>
  );
};
export default FetchMedicalHistory;





//   <TableContainer component={Paper}>
//     <Table sx={{ minWidth: 650 }} aria-label="simple table">
//       <TableHead>
//         <TableRow>
//           <TableCell>Dessert (100g serving)</TableCell>
//           <TableCell align="right">Calories</TableCell>
//           <TableCell align="right">Fat&nbsp;(g)</TableCell>
//           <TableCell align="right">Carbs&nbsp;(g)</TableCell>
//           <TableCell align="right">Protein&nbsp;(g)</TableCell>
//         </TableRow>
//       </TableHead>
//       <TableBody>
//         {rows.map((row) => (
//           <TableRow
//             key={row.name}
//             sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
//           >
//             <TableCell component="th" scope="row">
//               {row.name}
//             </TableCell>
//             <TableCell align="right">{row.calories}</TableCell>
//             <TableCell align="right">{row.fat}</TableCell>
//             <TableCell align="right">{row.carbs}</TableCell>
//             <TableCell align="right">{row.protein}</TableCell>
//           </TableRow>
//         ))}
//       </TableBody>
//     </Table>
//   </TableContainer>
// );