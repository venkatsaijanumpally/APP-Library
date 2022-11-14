import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import axios from '../../helper/axios';
import "./index.css"

const GetDeny = (props) => {
  const [deny,setDeny]=useState([]);

  const getData=async ()=>{
    const Response=await axios.get(`/student/borrow/deny`);
     if(Response.status==200)
     {
        setDeny(Response.data["Deny Records"]);
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
            {["End date", "Student id", "Book id", "start date"].map((item, index) => (
              <th key={index}>{item}</th>
            ))}
          </tr>
        </thead>
        {<tbody>
          {deny && deny.length > 0 ? deny.map((item, index) => {
            return (
              <tr>
                <td >{item.endDate}</td>
                <td >{item.id}</td>
                <td >{item.book_id}</td>
                <td>{item.startDate}</td>
              </tr>
            )
          }) : <div>No Data</div>}
        </tbody> }
      </Table>
    </div>
  );
}

export default GetDeny;