package com.transfermark.clients;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransferMarkClient extends HttpClient{

    private static final String STRING_URL = "https://transfermarkt-api.fly.dev";

    public String request(String path) throws IOException {
        URL url = new URL(STRING_URL + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return readResponse(connection.getInputStream());
    }

}
