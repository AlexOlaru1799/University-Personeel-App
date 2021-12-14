package mta.universitate.Utils;
import javax.servlet.http.Cookie;
import mta.universitate.Model.User;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CookieManager {
    private static CookieManager manager;
    HashMap<Integer, Cookie> cookies = new HashMap<Integer, Cookie>();  // Username + Cookie

    private CookieManager(){}

    public static CookieManager getInstance() {
        if (manager == null)
            manager = new CookieManager();

        return manager;
    }

    public Cookie generateCookie(User U){

        Cookie existing_cookie = cookies.get(U.getId());
        if (existing_cookie != null)
            return existing_cookie;

        Random random = ThreadLocalRandom.current();
        byte[] r = new byte[256];
        random.nextBytes(r);
        String uid_cookie = new String();

        boolean unique = false;
        while (!unique)
        {
            unique = true;
            uid_cookie = Base64.encodeBase64String(r);
            for (Cookie cookie : cookies.values())
            {
                if (cookie.getValue().contentEquals(uid_cookie)) {
                    unique = false;
                    break;
                }
            }
        }
        Cookie generated_cookie = new Cookie("uid", uid_cookie);

        cookies.put(U.getId(), generated_cookie);

        return generated_cookie;
    }

    public User validateCookie(Cookie C) {
        for (Map.Entry<Integer, Cookie> pair : cookies.entrySet()) {
            Integer id = pair.getKey();
            Cookie cookie = pair.getValue();

            if (C.getValue().contentEquals(cookie.getValue()))
                return User.fromDB(id);
        }

        return null;
    }

    public boolean invalidateCookie(Cookie C){
        try{
            User U = validateCookie(C);
            cookies.remove(U.getId());
            return true;
        }
        catch (Exception exc){}

        return false;
    }
}
