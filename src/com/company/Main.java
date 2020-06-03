package com.company;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.IntStream;
import java.util.Formatter;

public class Main {


    public static void main(String[] args) throws IOException {

        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();

        System.out.println(url);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            response.append('\n');
        }

        System.out.print(response.toString());

        JSONObject jsonObject = new JSONObject(response.toString());

        JSONObject bpiObject = jsonObject.getJSONObject("bpi");

        for (int i = 0; i < bpiObject.length(); i++) {
            String key = bpiObject.names().get(i).toString();
            JSONObject currencyObject = bpiObject.getJSONObject(key);

            System.out.printf(
                    ">%-25s|%-5s|%,10f|",
                    currencyObject.getString("description"),
                    currencyObject.getString("code"),
                    currencyObject.getDouble("rate_float")

            );
            System.out.println();
        }
    }
}