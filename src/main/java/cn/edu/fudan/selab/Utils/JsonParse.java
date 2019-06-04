package cn.edu.fudan.selab.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonParse {

    public static String jsonArrayParse(String res, int index, String target) {
        JSONObject jsonObject = JSON.parseObject(res);
        JSONObject data = JSON.parseObject(jsonObject.getString("data"));
        JSONObject result = JSON.parseObject(data.getString("result"));
        JSONObject response = JSON.parseObject(result.getString("response"));
        JSONObject returnJSONStr = JSON.parseArray(response.getString("returnJSONStr")).getJSONObject(index);
        return returnJSONStr.getString(target);
    }

    public static String jsonObjectParse(String res, String target) {
        JSONObject jsonObject = JSON.parseObject(res);
        JSONObject data = JSON.parseObject(jsonObject.getString("data"));
        JSONObject result = JSON.parseObject(data.getString("result"));
        JSONObject response = JSON.parseObject(result.getString("response"));
        JSONObject returnJSONStr = JSON.parseObject(response.getString("returnJSONStr"));
        return returnJSONStr.getString(target);
    }

}
