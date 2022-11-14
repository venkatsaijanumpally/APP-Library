import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import axios from '../../helper/axios';
import "./index.css"

const BookDetails = (props) => {
  const[bookDetails,setBookDetails]=useState([]);
  const getData=async ()=>{
    const Response=await axios.get(`/book`);
     if(Response.status==200)
     {
      setBookDetails(Response.data.Books);
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
            {["BookId", "Copies", "Author","Book Title"].map((item, index) => (
              <th key={index}>{item}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {bookDetails && bookDetails.length > 0 ? bookDetails.map((item, index) => {
            return (
              <tr>
               
                <td >{item.bookId}</td>
                <td >{item.copies}</td>
                <td >{item.author}</td>
                <td>{item.bookTitle}</td>
              </tr>
            )
          }) : <div>No Data</div>}
        </tbody>
      </Table>
    </div>
  );
}

export default BookDetails;