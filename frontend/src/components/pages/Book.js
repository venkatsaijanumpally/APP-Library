import React, { useState } from "react"
import axios from "../../helper/axios";
import { showAlert } from "../../helper/toaster";


const Book=(props)=>{
  const [studentId,setStudentId]=useState();
  const [bookId,setBookId]=useState();
  const [days,setDays]=useState(1);

  const submitBookData = async (e) => {
    const payload = { id:studentId, book_id:bookId,days };
    e.preventDefault();
     await axios.post(`/student/borrow`, payload).then((res)=>{
        if (res.status == 200) {
            showAlert({
                msg_type: "success",
                msg_text: `Borrow recored successfully`
            });
    }
    }).catch((e)=>{
        showAlert({ msg_type: "error",
        msg_text: "Failed"})        })
}
  return(
    <div>
          <form style={{ padding: "50px", overflow: "auto" }} onSubmit={submitBookData}>
                            <div class="mb-3">
                                <label for="exampleInput1" class="form-label">Student Id</label>
                                <input class="form-control" id="exampleInput1"  placeholder="Student Id" value={studentId} onChange={(e) => setStudentId(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Book Id</label>
                                <input type="text" class="form-control" id="exampleInput3" placeholder="Book Id" value={bookId} onChange={(e) => setBookId(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput2" class="form-label">Days</label>
                                <select class="form-control" onChange={(e) => setDays(e.target.value)} id="exampleInput6" name="Days" value={days}>
                                    {[1,2,3,4,5].map(s =>
                                        <option value={s}>{s}</option>
                                    )}

                                </select>
                            </div>
                    
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
        </div>
  )
}

export default Book;