package mta.universitate.Utils;

import ch.qos.logback.core.subst.Tokenizer;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ParamsParser {

    public static HashMap<String, Object> parse(String body){

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        String[] tokens = body.split("&");

        for(String token : tokens)
        {
            try{
                String key = URLDecoder.decode(token.split("=")[0], "UTF-8");
                String val = URLDecoder.decode(token.split("=")[1], "UTF-8");

                parameters.put(key, val);
            }
            catch (Exception exc){
                exc.printStackTrace();
            }
        }

        return parameters;
    }
}
