package com.transfermark.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class HttpClient {

    abstract String request(String path) throws IOException;

    String readResponse(InputStream inputStreamResponse) throws IOException {
        return readBufferReader(new BufferedReader(new InputStreamReader(inputStreamResponse)));
    }

    private String readBufferReader(BufferedReader bufferedReaderResponse) throws IOException {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = bufferedReaderResponse.readLine()) != null)
            response.append(line);
        bufferedReaderResponse.close();
        return response.toString();
    }
}
