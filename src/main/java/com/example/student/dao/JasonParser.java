package com.example.student.dao;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.student.model.Student;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



public class JasonParser {

static InputStream is = null;
static JSONObject jObj = null;
static String json = "";
private static List<Student> students= new ArrayList<>();
// constructor

@ExceptionHandler
public String getJSONFromUrl(String url) {

    // Making HTTP request
    try {
        // defaultHttpClient
    	  DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        is = httpEntity.getContent();

    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "iso-8859-1"), 8);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
            System.out.println(sb);
        }
        is.close();
        json = sb.toString();

    } catch (Exception e) {
    	System.out.println("Buffer Error");
    }

   

    // return JSON String
    return json;

}

public List<Student> stringtolist(String sb)
{
	//List<Student> students= new ArrayList<>();
	 Gson gson = new Gson();
	    JsonArray jsonArray = new JsonParser().parse(sb).getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
		    JsonElement str = jsonArray.get(i);
		    Student obj = gson.fromJson(str, Student.class);
		    students.add(obj);
	}
		return students;
		
		


}

public Student fineOne(String id)
{
	for(Student student : students)
	{
		if (student.getSid().equalsIgnoreCase(id))
			return student;
	}
	
	return null;
}
}