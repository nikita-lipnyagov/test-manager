package com.epam.lab.pet_project.controllers.admin_servlets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import static org.junit.Assert.*;

public class BanOrActivateUserServletTest {

    @Test
    public void doPost_Method_GET_Not_Supported() throws Exception {
        //WHEN
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/admin/banOrActivateUser?userName=user1");
        //THEN
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            System.out.println(response.getStatusLine());
        }
    }

}