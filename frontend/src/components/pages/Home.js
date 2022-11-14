import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import { AiFillDelete } from 'react-icons/ai';
import axios from '../../helper/axios';
import { showAlert } from "../../helper/toaster";
import "./index.css"

const Home = (props) => {
  const [studentInfo,setStudentInfo]=useState([]);

  const getData=async ()=>{
    const studentResponse=await axios.get(`/student`);
     if(studentResponse.status==200)
     {
      setStudentInfo(studentResponse.data.students);
     }
  }

  useEffect(()=>{
     getData();
     
  },[])

  return (
    <div className='home'>
      <Table responsive style={{ marginTop: "30px" }}>
        <thead>
          <tr>
            {["StudentId", "email", "phone", "Program", "DueAmount","Status"].map((item, index) => (
              <th key={index}>{item}</th>
            ))}
          </tr>
        </thead>
        {<tbody>
          {studentInfo && studentInfo.length > 0 ? studentInfo.map((item, index) => {
            return (
              <tr>
                <td >{item.id}</td>
                <td >{item.email}</td>
                <td >{item.phone}</td>
                <td>{item.program}</td>
                <td>{item.due_amount}</td>
                <td>{item.status}</td>
              </tr>
            )
          }) : <div>No Data</div>}
        </tbody> }
      </Table>
    </div>
  );
}

export default Home;