package com.example.application.views.Utils;

import java.net.CookieManager;
import java.net.HttpCookie;

public class OwnCookieManager {

    private static OwnCookieManager instance= null;

    private CookieManager cookieManager;

    private OwnCookieManager(){
        cookieManager = new CookieManager();
    };

    public static OwnCookieManager getInstance()
    {
        if(instance == null)
        {
            instance = new OwnCookieManager();
        }

        return instance;
    }

    public void addCookie(HttpCookie C)
    {
        cookieManager.getCookieStore().add(null,C);
    }

    public HttpCookie getCookie()
    {
        return cookieManager.getCookieStore().getCookies().get(0);
    }
}
