package org.library;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.library.Model.*;

import java.io.IOException;

public class ApiTest {

    CloseableHttpClient httpClient;

    @Test
    public void testStudentCreateApi() throws IOException {

        JSONObject json=getStudentJson();
        HttpResponse response = performJsonRequest("student", json);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);

        if(Student.studentExist(8822))
            Student.deleteStudent(8822);
        httpClient.close();
    }

    @Test
    public void getStudentsTest() throws IOException {

        JSONObject json=getStudentJson();
        HttpResponse response = performJsonRequest("student", json);
        httpClient.close();

        httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8000/student");
        HttpResponse response2=httpClient.execute(request);
        Assert.assertEquals(response2.getStatusLine().getStatusCode(),200);

        JSONObject obj=new JSONObject(response2.getEntity());
        Utils.retrieveResourceFromResponse(response2, Student.class);

        if(Student.studentExist(8822))
            Student.deleteStudent(8822);
        httpClient.close();
    }

    @Test
    public void testStudentDeleteApi() throws IOException {

        JSONObject json=getStudentJson();
        HttpResponse response = performJsonRequest("student", json);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);

        JSONObject deleteJson=getStudentDeleteJson();
        HttpResponse responseDelete=performJsonDeleteRequest("student",deleteJson);
        Assert.assertEquals(responseDelete.getStatusLine().getStatusCode(),200);

        HttpResponse responseDelete2=performJsonDeleteRequest("student",deleteJson);
        Assert.assertEquals(responseDelete2.getStatusLine().getStatusCode(),400);

        if(Student.studentExist(8822))
            Student.deleteStudent(8822);
        httpClient.close();
    }

    @Test
    public void testBookApi() throws IOException {

        JSONObject json=getBookJson();
        HttpResponse response = performJsonRequest("book", json);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);

        Database.deleteBook("8888");
        httpClient.close();
    }

    @Test
    public void testBookBorrowApi() throws IOException {
        JSONObject jsonStudent=getStudentJson();
        JSONObject jsonBook=getBookJson();
        HttpResponse responseStudent = performJsonRequest("student", jsonStudent);
        Assert.assertEquals(responseStudent.getStatusLine().getStatusCode(),200);
        httpClient.close();

        HttpResponse responseBook = performJsonRequest("book", jsonBook);
        Assert.assertEquals(responseBook.getStatusLine().getStatusCode(),200);

        JSONObject jsonBookBorrow=getBookBorrowJson();
        HttpResponse responseBookBorrow = performJsonRequest("student/borrow", jsonBookBorrow);
        Assert.assertEquals(responseBookBorrow.getStatusLine().getStatusCode(),200);

        BookBorrow.deleteRecord(8822,8888);
        Database.deleteBook("8888");
        httpClient.close();
        if(Student.studentExist(8822))
            Student.deleteStudent(8822);
    }

    @Test
    public void testBookBorrowDeleteApi() throws IOException {
        JSONObject jsonStudent=getStudentJson();
        JSONObject jsonBook=getBookJson();
        HttpResponse responseStudent = performJsonRequest("student", jsonStudent);
        Assert.assertEquals(responseStudent.getStatusLine().getStatusCode(),200);
        httpClient.close();

        HttpResponse responseBook = performJsonRequest("book", jsonBook);
        Assert.assertEquals(responseBook.getStatusLine().getStatusCode(),200);
        httpClient.close();

        JSONObject jsonBookBorrow=getBookBorrowJson();
        HttpResponse responseBookBorrow = performJsonRequest("student/borrow", jsonBookBorrow);
        Assert.assertEquals(responseBookBorrow.getStatusLine().getStatusCode(),200);
        httpClient.close();

        JSONObject jsonBookBorrowDelete=getBookBorrowDeleteJson();
        HttpResponse responseBookBorrowDelete = performJsonDeleteRequest("student/borrow", jsonBookBorrowDelete);
        Assert.assertEquals(responseBookBorrowDelete.getStatusLine().getStatusCode(),200);
        httpClient.close();

        HttpResponse responseBookBorrowDelete2 = performJsonDeleteRequest("student/borrow", jsonBookBorrowDelete);
        Assert.assertEquals(responseBookBorrowDelete2.getStatusLine().getStatusCode(),400);

        Database.deleteBook("8888");
        httpClient.close();
        if(Student.studentExist(8822))
            Student.deleteStudent(8822);
    }

    @Test
    public void testPricingStrategyApi() throws IOException {
        JSONObject json=getPricingJson();
        HttpResponse response = performJsonRequest("book/pricing", json);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        httpClient.close();
    }

    @Test
    public void testPricingUpdateJSON() throws IOException {
        HttpUriRequest request = new HttpGet( "http://localhost:8000/book/pricing");
        Catalog.updatePricingStrategy(1);
        HttpResponse response1 = HttpClientBuilder.create().build().execute( request );
        Assert.assertEquals(Utils.retrieveResourceFromResponse(response1, SinglePricingStrategy.class).getPerDayCost(),1);
        Catalog.updatePricingStrategy(2);
        HttpResponse response2 = HttpClientBuilder.create().build().execute( request );
        Assert.assertEquals(Utils.retrieveResourceFromResponse(response2, DoublePricingStrategy.class).getPerDayCost(),2);
    }

    public HttpResponse performJsonRequest(String context, JSONObject json) throws IOException {
        httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8000/" + context);
        StringEntity params = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        return httpClient.execute(request);
    }

    public HttpResponse performJsonDeleteRequest(String context, JSONObject json) throws IOException {
        httpClient = HttpClientBuilder.create().build();
        HttpDeleteWithEntity request = new HttpDeleteWithEntity("http://localhost:8000/" + context);
        StringEntity params = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        return httpClient.execute(request);
    }

    private JSONObject getStudentDeleteJson() {
        JSONObject json = new JSONObject();
        json.put("id", "8822");
        return json;
    }

    private JSONObject getBookBorrowDeleteJson() {
        JSONObject json = new JSONObject();
        json.put("id", "8822");
        json.put("book_id", "8888");
        return json;
    }

    private JSONObject getBookBorrowJson() {
        JSONObject json = new JSONObject();
        json.put("id", "8822");
        json.put("book_id", "8888");
        return json;
    }

    private JSONObject getPricingJson() {
        JSONObject json = new JSONObject();
        json.put("strategy", "1");
        return json;
    }

    private JSONObject getBookJson() {
        JSONObject json = new JSONObject();
        json.put("book_title", "PHYSICS");
        json.put("author", "I A MARON");
        json.put("copies", "10");
        json.put("book_id", "8888");
        return json;
    }

    private JSONObject getStudentJson() {
        JSONObject json = new JSONObject();
        json.put("id", "8822");
        json.put("name", "TEST STUDENT");
        json.put("email", "TEST@gmail.com");
        json.put("phone", "8888822222");
        json.put("program", "CSE");
        json.put("status", "ALLOWED");
        return json;
    }
}
