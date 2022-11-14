import React, { useState } from "react"
import axios from "../../helper/axios";
import { showAlert } from "../../helper/toaster";




const DeleteStudentBorrow=(props)=>{
  const [studentId,setStudentId]=useState();
  const [bookId,setBookId]=useState();
  const Delete = async (e) => {
    e.preventDefault();
    await axios.delete(`/student/borrow`, { data: { id:studentId,book_id:bookId } }).then((res)=>{
        if (res.status == 200) {
            showAlert({
                msg_type: "success",
                msg_text: `Student Borrow Deleted successfully`
            });
    }
    }).catch((e)=>{
            showAlert({ msg_type: "error",
            msg_text: "Failed"})        });
}
  return(
    <div>
          <form style={{ padding: "50px", overflow: "auto" }} onSubmit={Delete}>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Student Id</label>
                                <input type="text" class="form-control" id="exampleInput3" placeholder="Student Id" value={studentId} onChange={(e) => setStudentId(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Book Id</label>
                                <input type="text" class="form-control" id="exampleInput3" placeholder="Book Id" value={bookId} onChange={(e) => setBookId(e.target.value)} required />
                            </div>
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
        </div>
  )
}

export default DeleteStudentBorrow;