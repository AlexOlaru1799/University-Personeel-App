package mta.universitate.Utils;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;

public class ApiRequestExample {
    public static void main(String[] args){

        // Here we will store the cookie
        CookieManager cookieManager = new CookieManager();

        // Create request and set the endpoint
        ApiRequest req = new ApiRequest("http://localhost:8080/login");

        // Add parameters to the request
        req.addParameter("username", "cazamir.teodor@mta.ro");
        req.addParameter("password", "123456789");

        // Send the request and get the response
        HashMap<String, Object> response = req.send();

        // Get the cookie and store it in the CookieManager
        cookieManager.getCookieStore().add(null, req.getCookie());


        req = new ApiRequest("http://localhost:8080/logout");
        // Add cookie to the request
        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        for(HttpCookie cookie : cookies)
            if (cookie.getName().contentEquals("uid"))
            {
                req.addCookie(cookie);
                break;
            }
        response = req.send();

        System.out.println(response.get("status"));



    }
}
