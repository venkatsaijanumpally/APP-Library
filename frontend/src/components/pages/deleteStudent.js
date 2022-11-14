import React, { useState } from "react"
import axios from "../../helper/axios";
import { showAlert } from "../../helper/toaster";



const DeleteStudent=(props)=>{
  const [studentId,setStudentId]=useState();
  const Delete = async (e) => {
    e.preventDefault();
    await axios.delete(`/student`, { data: { id:studentId } }).then((res)=>{
        if (res.status == 200) {
            showAlert({
                msg_type: "success",
                msg_text: `Student Deleted successfully`
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
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
        </div>
  )
}

export default DeleteStudent;