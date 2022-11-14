import React, { useState } from "react"
import axios from "../../helper/axios";
import { showAlert } from "../../helper/toaster";


const Pricing=(props)=>{
  const [pricing,setPricing]=useState(1);
  const submitPricing = async (e) => {
    const payload = { strategy:pricing };
    e.preventDefault();
    const res = await axios.post(`/book/pricing`, payload).then((res)=>{
        if (res.status == 200) {
            showAlert({
                msg_type: "success",
                msg_text: `Pricing Updated successfully`
            });
    }
    }).catch((e)=>{
        showAlert({ msg_type: "error",
        msg_text: "Failed"}) 
    })
}
  return(
    <div>
          <form style={{ padding: "50px", overflow: "auto" }} onSubmit={submitPricing}>
          <div class="mb-3">
                                <label for="exampleInput2" class="form-label">pricing</label>
                                <select class="form-control" onChange={(e) => setPricing(e.target.value)} id="exampleInput6" name="pricing" value={pricing}>
                                    {[1,2].map(s =>
                                        <option value={s}>{s}</option>
                                    )}

                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
        </div>
  )
}

export default Pricing;