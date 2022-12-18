package eu.ifine.fine.common;

import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import com.alibaba.fastjson2.JSONObject;
import org.checkerframework.checker.units.qual.A;

public class MotdHandler {

    public static Boolean IsOnline(String ip, int port) throws IOException {

        String be = MotdPe(ip, port);
        //String je = MotdJe(ip, port);

        JSONObject beJson = JSONObject.parseObject(be);
        //JSONObject jeJson = JSONObject.parseObject(je);

        String out1 = beJson.getString("status");
       // String out2 = jeJson.getString("status");

        return out1.equals("online"); //|| out2.equals("online");
    }

    public static String MotdPe(String ip, int port) {
        //get请求
        String url = "http://127.0.0.1:8080/api/v1/motd/be?ip=" + ip + "&port=" + port;
        String content = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            HttpURLConnection connection = (HttpURLConnection) urlConnection;
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                        (connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder bs = new StringBuilder();
                String l;
                while ((l = bufferedReader.readLine()) != null) {
                    bs.append(l).append("\n");
                }
                content = bs.toString();
            }
        }catch (IOException e) {
            return content;
        }
        return content;
    }

    public static String MotdJe(String ip, int port) {
        //get请求
        String url = "http://127.0.0.1:8080/api/v1/motd/je?ip=" + ip + "&port=" + port;
        String content = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            HttpURLConnection connection = (HttpURLConnection) urlConnection;
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                        (connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder bs = new StringBuilder();
                String l;
                while ((l = bufferedReader.readLine()) != null) {
                    bs.append(l).append("\n");
                }
                content = bs.toString();
            }
        }catch (IOException e) {
            return content;
        }
        return content;
    }
}
