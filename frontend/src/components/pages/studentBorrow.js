import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import { AiFillDelete } from 'react-icons/ai';
import axios from '../../helper/axios';
import "./index.css"

const StudentBorrow = (props) => {
  const [stBorrowDetails,setStBorrowDetails]=useState([]);

  const getData=async ()=>{
    const studentResponse=await axios.get(`/student/borrow`);
     if(studentResponse.status==200)
     {
      setStBorrowDetails(studentResponse.data.Records);
     }
  }

  useEffect(()=>{
     getData();
     
  },[])
  return (
    <div>
      <Table responsive style={{ marginTop: "30px" }}>
        <thead>
          <tr>
            {["StudentId", "BookId", "Start Date", "End Date"].map((item, index) => (
              <th key={index}>{item}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {stBorrowDetails && stBorrowDetails.length > 0 ? stBorrowDetails.map((item, index) => {
            return (
              <tr>
                <td >{item.id}</td>
                <td >{item.book_id}</td>
                <td >{item.startDate}</td>
                <td>{item.endDate}</td>
              </tr>
            )
          }) : <div>No Data</div>}
        </tbody>
      </Table>
    </div>
  );
}

export default StudentBorrow;