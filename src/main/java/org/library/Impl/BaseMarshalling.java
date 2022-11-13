package org.library.Impl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

public class BaseMarshalling<T> {
    private String result="";

    public int getResponseLength(Iterable<T> listOfObjects, String type) {
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(T obj: listOfObjects){
            jsonArray.put(new JSONObject(obj));
        }
        jsonObject.put(type,jsonArray);
        result=jsonObject.toString();
        return result.getBytes().length;
    }

    public int getResponseLength(T object) {
        JSONObject jsonObject=new JSONObject(object);
        result = jsonObject.toString();
        return result.getBytes().length;
    }

    public OutputStream getOutputStream(OutputStream os) throws IOException {
        os.write(result.getBytes());
        return os;
    }
}
