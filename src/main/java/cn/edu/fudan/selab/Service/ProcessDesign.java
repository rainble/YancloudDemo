package cn.edu.fudan.selab.Service;

import cn.edu.fudan.selab.DeviceInterface.Airmanager;
import cn.edu.fudan.selab.DeviceInterface.Weidong;
import cn.edu.fudan.selab.DeviceInterface.Xiaomi;
import cn.edu.fudan.selab.Utils.Encoding;
import cn.edu.fudan.selab.Utils.HttpRequest;
import cn.edu.fudan.selab.Utils.Parameters;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.awt.image.renderable.ParameterBlock;
import java.io.UnsupportedEncodingException;

public class ProcessDesign {

    /**
     * call xiaomi's interface
     * @param methodName
     * @param argsJSONStr
     * @return java.lang.String
     * @create 2019-05-09 14:03
    **/
    public  String callXiaomi(String methodName, String argsJSONStr) {
        String arg =
                "{\"androidId\":\""+ Parameters.ANDROIDID +"\",\"pkgName\":\""
                        + Parameters.PKG_NAME_XIAOMI + "\",\"methodName\":\""
                        + methodName +"\",\"argsJSONStr\":\"" + argsJSONStr + "\",\"versionName\":\""
                        + Parameters.VERSION_NAME_XIAOMI +"\"}";
        System.out.println(arg);

        return callYJS(arg);
    }


    /**
     * call kettle's interface
     * @param methodName
     * @param argsJSONStr
     * @return java.lang.String
     * @create 2019-05-09 14:04
    **/
    public String callKettle(String methodName, String argsJSONStr) {
        String arg =
                "{\"androidId\":\""+ Parameters.ANDROIDID +"\",\"pkgName\":\""
                        + Parameters.PKG_NAME_KETTLE + "\",\"methodName\":\""
                        + methodName +"\",\"argsJSONStr\":\"" + argsJSONStr + "\",\"versionName\":\""
                        + Parameters.VERSION_NAME_KETTLE +"\"}";
        System.out.println(arg);

        return callYJS(arg);
    }

    /**
     * call scale's interface
     * @param methodName
     * @param argsJSONStr
     * @return java.lang.String
     * @create 2019-05-09 14:08
    **/
    public String callWeidong(String methodName, String argsJSONStr) {
        String arg =
                "{\"androidId\":\""+ Parameters.ANDROIDID +"\",\"pkgName\":\""
                        + Parameters.PKG_NAME_WEIDONG + "\",\"methodName\":\""
                        + methodName +"\",\"argsJSONStr\":\"" + argsJSONStr + "\",\"versionName\":\""
                        + Parameters.VERSION_NAME_WEIDONG +"\"}";
        System.out.println(arg);
        return callYJS(arg);
    }
    
    /**
     * call haier's interface
     * @param methodName 
     * @param argsJSONStr
     * @return java.lang.String
     * @create 2019-05-09 14:11
    **/
    public String callAirmanager(String methodName, String argsJSONStr) {
        String arg =
                "{\"androidId\":\""+ Parameters.ANDROIDID +"\",\"pkgName\":\""
                        + Parameters.PKG_NAME_HAIER + "\",\"methodName\":\""
                        + methodName +"\",\"argsJSONStr\":\"" + argsJSONStr + "\",\"versionName\":\""
                        + Parameters.VERSION_NAME_HAIER +"\"}";
        System.out.println(arg);
        return callYJS(arg);
    }

    /**
     * call yjs to invoke yancloudpub, use the first method in http://47.106.38.23:8080/SCIDE/js/scideapidoc.js
     * @param arg 
     * @return java.lang.String
     * @create 2019-05-09 14:22
    **/
    public String callYJS(String arg) {
        try {
            String encodeArg = Encoding.encodeURLComponent(arg);
            String url =
                    Parameters.YJSURL + "?action=" + Parameters.ACTION + "&contractID="
                            + Parameters.CONTRACT_ID + "&operation=" + Parameters.OPERATION + "&arg=" + encodeArg;
            String res = HttpRequest.HTTPRequestDoGet(url);
            System.out.println(url);
            return res;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "call YJS failed";
    }

}
