import React, { useEffect, useState } from "react"
import axios from "../../helper/axios";

import { showAlert } from "../../helper/toaster";

const Student=(props)=>{
    const [studentId, setStudentId] = useState();
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [status, setStatus] = useState("ALLOWED");
    const [program, setProgram] = useState("");

    const submitStudentData = async (e) => {
        const payload = { id:studentId, email, phone, program, status };
        e.preventDefault();
        await axios.post(`/student`, payload).then((res)=>{
            console.log(res.status)
            if (res.status == 200) {
                showAlert({
                    msg_type: "success",
                    msg_text: `Student created successfully`
                });
        }
        }).catch((e)=>{
            showAlert({ msg_type: "error",
            msg_text: "Failed"})        })
    }
    
 
    return (
        <div className='students'>
          <form style={{ padding: "50px", overflow: "auto" }} onSubmit={submitStudentData}>
                            <div class="mb-3">
                                <label for="exampleInput1" class="form-label">Student Id</label>
                                <input class="form-control" id="exampleInput1"  placeholder="Student Id" value={studentId} onChange={(e) => setStudentId(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput2" class="form-label">Status</label>
                                <select class="form-control" onChange={(e) => setStatus(e.target.value)} id="exampleInput6" name="status" value={status}>
                                    {["ALLOWED","BLACKLISTED"].map(s =>
                                        <option value={s}>{s}</option>
                                    )}

                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Email</label>
                                <input type="email" class="form-control" id="exampleInput3" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput4" class="form-label">Program</label>
                                <input name="program" placeholder="program" class="form-control" id="exampleInput4"  value={program} onChange={(e) => setProgram(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput5" class="form-label">Phone</label>
                                <input class="form-control" id="exampleInput5" placeholder="Phone" value={phone} onChange={(e) => setPhone(e.target.value)} required />
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
        </div>
      );

}

export default Student;