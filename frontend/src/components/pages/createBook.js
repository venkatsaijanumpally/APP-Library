import React, { useState } from "react"
import axios from "../../helper/axios";
import { showAlert } from "../../helper/toaster";


const CreateBook=(props)=>{
  const [bookId,setBookId]=useState();
  const [copies,setCopies]=useState();
  const [author,setAuthor]=useState();
  const [title,setTitle]=useState();
  const submitCreateBookData = async (e) => {
    const payload = { book_id:bookId, book_title:title,author,copies };
    e.preventDefault();
    const res = await axios.post(`/book`, payload).then((res)=>{
        if (res.status == 200) {
            showAlert({
                msg_type: "success",
                msg_text: `Book Created successfully`
            });
    }
    }).catch((e)=>{
        showAlert({ msg_type: "error",
        msg_text: "Failed"})        })
   
}
  return(
    <div>
          <form style={{ padding: "50px", overflow: "auto" }} onSubmit={submitCreateBookData}>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Book Id</label>
                                <input type="text" class="form-control" id="exampleInput3" placeholder="Book Id" value={bookId} onChange={(e) => setBookId(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Copies</label>
                                <input type="text" class="form-control" id="exampleInput3" placeholder="Copies" value={copies} onChange={(e) => setCopies(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Author</label>
                                <input type="text" class="form-control" id="exampleInput3" placeholder="Author" value={author} onChange={(e) => setAuthor(e.target.value)} required />
                            </div>
                            <div class="mb-3">
                                <label for="exampleInput3" class="form-label">Title</label>
                                <input type="text" class="form-control" id="exampleInput3" placeholder="title" value={title} onChange={(e) => setTitle(e.target.value)} required />
                            </div>
                            
                    
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
        </div>
  )
}

export default CreateBook;