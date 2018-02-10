package com.epam.lab.pet_project.controllers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginServletTest {
    @Test
    public void doPost_Error() throws Exception {
        //WHEN
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/authorization?userName=null?password=null&language=eng");
        //THEN
        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            System.out.println(response.getStatusLine());
        }
    }
}