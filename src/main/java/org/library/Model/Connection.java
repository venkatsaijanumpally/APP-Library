package org.library.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.library.Impl.DatabaseClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.library.Model.BookBorrow.getBookBorrowRecords;

public class Connection {
    /*
     * This is a Testing Class
     */
    public static void main(String[] args) throws ParseException {
        /*String connectionString = "mongodb+srv://Kub-deployment-user:Password@cluster0.nnf4l.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
        }*/
        final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        System.out.println(new Date(System.currentTimeMillis() + 5*MILLIS_IN_A_DAY));
        System.out.println(new Date(System.currentTimeMillis()));
        //Date.parse("nma");
        SimpleDateFormat s=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String date5=s.format(new Date(System.currentTimeMillis()+ 5 * MILLIS_IN_A_DAY));
        System.out.println(date5);
        //System.out.println(s.format(new Date()));
        Date date5parse=s.parse(date5);
        Date current=new Date(System.currentTimeMillis());
        Date currentplus=new Date(System.currentTimeMillis()+2000);
        System.out.println(s.parse(date5));
        System.out.println(currentplus.after(currentplus));
        System.out.println(currentplus.after(current));
        System.out.println(currentplus+"  "+current);


        ConnectionString connectionString = new ConnectionString("mongodb+srv://Kub-deployment-user:Password@cluster0.nnf4l.mongodb.net/?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();



        MongoClient mongoClient = MongoClients.create(clientSettings);
        MongoDatabase LibraryDB = mongoClient.getDatabase("library");
        MongoCollection<Object> TestObjectCollection= LibraryDB.getCollection("TestObj", Object.class);
        Object obj=new Object("abc");
        TestObjectCollection.insertOne(obj);
        System.out.println(TestObjectCollection.find(eq("name","abc")).first().getName());


        Iterable<BookBorrow> borrowRecords= getBookBorrowRecords();
        //Iterable<BookBorrow> list=new ArrayList<>();
        for (BookBorrow b:borrowRecords){
            if(b.book_id==6623)
                borrowRecords.iterator().remove();
        }
    }



    /*public int getResponseLength(Iterable<T> listOfObjects) throws JsonProcessingException {
        ArrayList<JSONObject> jsonArray=new ArrayList<>();
        for(T obj: listOfObjects){
            jsonArray.add(new JSONObject(obj));
        }
        result=jsonArray.toString();
        System.out.println(result);
        return result.getBytes().length;



        StringBuilder resultString=new StringBuilder();
        //resultString.append("{\n");
        //esultString.append(objectWriter.writeValueAsString("{"));
        resultString.append("{\n");
        for(T obj: listOfObjects){
            resultString.append(objectWriter.writeValueAsString(obj));
            resultString.append("\n");
            //resultString.append(objectWriter.writeValueAsString("\n"));
        }
        //resultString.append(objectWriter.writeValueAsString("}"));
        resultString.append("}");
        result= resultString.toString();
        ObjectMapper mapper = new ObjectMapper();
        //result=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        System.out.println(new JSONObject(listOfObjects.iterator().next()).toString());
        JSONObject[] js=new JSONObject[2];
        js[0]=new JSONObject(listOfObjects.iterator().next());
        js[1]=new JSONObject(listOfObjects.iterator().next());
        System.out.println(Arrays.toString(js));
        result=Arrays.toString(js);
        JSONArray arr=new JSONArray();
        arr.put(new JSONObject(listOfObjects.iterator().next()));
        arr.put(new JSONObject(listOfObjects.iterator().next()));
        System.out.println(arr.toString());
        *//*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp=new JsonParser();
        JsonElement je=jp.parse(result);
        result=gson.toJson(je);
        System.out.println(result);*//*
        //result= objectWriter.withDefaultPrettyPrinter().writeValueAsString(resultString.toString());
        //result=new JSONObject(result).toString();
        return result.getBytes().length;
    }*/
}





//System.out.println(IOUtils.toString(exchange.getRequestBody()));

        /*JSONObject obj= new JSONObject(IOUtils.toString(exchange.getRequestBody()));
        System.out.println(obj.getJSONObject("name").toString());
        System.out.println(obj.getJSONObject("name"));
        System.out.println(obj.getString("name"));*/
//new Student(Integer.parseInt(params.get("id")), Status.valueOf(params.get("status")), params.get("email"),params.get("program"),params.get("phone"));
        //Map<String,String> params=queryToMap(exchange.getRequestURI().getQuery());



/*Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 5);
        dt = c.getTime();
        loanEntry.append("id",id)
                .append("Book",BookId)
                //.append("Date", new Timestamp(System.currentTimeMillis()))
                .append("Date",new Timestamp(System.currentTimeMillis()))
                .append("Deadline",dt.toString());*/