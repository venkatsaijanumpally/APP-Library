package org.library.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.library.Model.Student;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
