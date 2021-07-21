package com.example.student.dao;

import com.example.student.StudentInfoApplication;
import com.example.student.model.Student;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class StudentDao {
	final org.apache.logging.log4j.Logger logger = LogManager.getLogger(StudentDao.class);
static InputStream is = null;
static JSONObject jObj = null;
static String json = "";
static String statuscode=null;
private static List<Student> students= new ArrayList<>();
// constructor

@ExceptionHandler
@Autowired
public List<Student> getJSONFromUrl() throws NullPointerException {
	List<Student> students= new ArrayList<>();
    // Making HTTP request
    try {
        // defaultHttpClient
    	  DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://localhost:8081/student");

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();
		

    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();}
        catch (NullPointerException e) {
           logger.info("internal server error");
    }

    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
            Logger.getLogger(line);
            logger.info(sb);
        }
        is.close();
        json = sb.toString();
       Logger.getLogger(json);
    } catch (Exception e) {
    	logger.info("Buffer Error");
    }
    
    

    students=stringtolist(json);

    // return JSON String
    return students;

}

public List<Student> stringtolist(String sb)
{
	//
	 Gson gson = new Gson();
	    JsonArray jsonArray = new JsonParser().parse(sb).getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
		    JsonElement str = jsonArray.get(i);
		    Student obj = gson.fromJson(str, Student.class);
		    logger.info(obj);
		    students.add(obj);
	}
		return students;
		
		


}

public ResponseEntity<Student> fineOne(String id)
{
		for(Student student : students)
	{
		if (student.getSid().equalsIgnoreCase(id))
		{logger.info(student.toString());
			return  ResponseEntity.of(Optional.of(student));
			}
		if(student.equals(null))
		{
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	 
	return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
}
