package mta.universitate.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRequest {
    HttpURLConnection con;
    HashMap<String, String> parameters = new HashMap<String, String>();
    StringBuilder body;

    ObjectMapper mapper;

    public ApiRequest(String endpoint){
        try
        {
            URL url = new URL(endpoint);
            this.con = (HttpURLConnection) url.openConnection();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setDoOutput(true);
        con.setDoInput(true);

        body = new StringBuilder();
        mapper = new ObjectMapper();
    }

    public void addParameter(String key, String value){
        this.parameters.put(key, value);
    }

    public HttpCookie getCookie(){
        String cookiesHeader = con.getHeaderField("Set-Cookie");
        List<HttpCookie> cookies = HttpCookie.parse(cookiesHeader);

        for(HttpCookie cookie : cookies)
        {
            if (cookie.getName().contentEquals("uid"))
                return cookie;
        }

        return null;
    }

    public void addCookie(HttpCookie cookie) {
        // Here we add the cookie that we want to send
        con.setRequestProperty("Cookie", cookie.toString());
    }

    private void addBodyPayload(){
        try
        {
            // Put parameters in body
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : parameters.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            int postDataLength = postDataBytes.length;
            con.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
            con.getOutputStream().write(postDataBytes);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

    public HashMap<String, Object> send() {
        try
        {
            con.setRequestMethod("POST");
            addBodyPayload();

            // Send the request
            int status = con.getResponseCode();

            // Save the request response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return mapper.readValue(response.toString(), HashMap.class);

        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        return null;

    }

}
