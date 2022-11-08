package org.library.Impl;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class ExceptionHandler {

    private String responseJson;
    public int errorCode;
    public int responseLength;

    public ExceptionHandler(Exception exception){
        if(exception instanceof RuntimeException){
            errorCode=400;
        }
        else {
            errorCode=500;
        }
        formResponse(exception);
        exception.printStackTrace();
    }

    private void formResponse(Exception exception) {
        JSONObject object=new JSONObject();
        object.put("Error Code", errorCode);
        object.put("Message", exception.getMessage());
        responseJson=object.toString();
        responseLength=responseJson.length();
    }

    public OutputStream getResponseStream(OutputStream os) throws IOException {
        os.write(responseJson.getBytes());
        return os;
    }

    public int getResponseLength() {
        return responseLength;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
