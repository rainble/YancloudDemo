package cn.edu.fudan.selab.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Encoding {

    public static String decodeURLComponent(String s) throws UnsupportedEncodingException {
        String res = "";
        if (s == null) {
            return null;
        } else {
            res = URLDecoder.decode(s, "UTF-8");
        }
        return res;
    }

    public static String encodeURLComponent(String s) throws UnsupportedEncodingException {
        String res = null;
        res = URLEncoder.encode(s, "UTF-8")
                .replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
        return res;
    }
}
