import cn.edu.fudan.selab.DeviceInterface.*;
import cn.edu.fudan.selab.Service.ProcessDesign;
import cn.edu.fudan.selab.Utils.JsonParse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


public class TestProcessDesing {

    String argsJSONStr = null;
    ProcessDesign processDesign = new ProcessDesign();

    @Test
    public void testCallXiaomi() {
        String res = (processDesign.callXiaomi(Xiaomi.listSubs.toString(), argsJSONStr));
        JSONObject jsonObject = JSON.parseObject(res);
        JSONObject data = JSON.parseObject(jsonObject.getString("data"));
        JSONObject result = JSON.parseObject(data.getString("result"));
        System.out.println(result.getString("response"));
        System.out.println(data.getString("status"));
        System.out.println(jsonObject.getString("action"));    }

    @Test
    public void testCallKettle() {
        String res = processDesign.callKettle(Kettle.getKettles.toString(), argsJSONStr);
        System.out.println(res);
        String kettleId = JsonParse.jsonArrayParse(res, 0, "id");
        System.out.println(kettleId);
        int modeRes = Integer.valueOf(JsonParse.jsonArrayParse(processDesign.callKettle(Kettle.getModes.toString(), argsJSONStr),
                4, "id"));
        System.out.println(modeRes);

        String startModeArgs = "{\\\"dev_id\\\":\\\"" + kettleId + "\\\",\\\"mode_id\\\":" + modeRes + "}";
        processDesign.callKettle(Kettle.startMode.toString(), startModeArgs);
    }

    @Test
    public void testCallAirmanager() {
        String resListSubs = processDesign.callAirmanager(Airmanager.listSubs.toString(), argsJSONStr);
        System.out.println(resListSubs);
        argsJSONStr = "{\\\"dev_id\\\":\\\"" + JsonParse.jsonArrayParse(resListSubs, 0, "dev_id") + "\\\"}";
        String resQueryAirCondition = processDesign.callAirmanager(Airmanager.queryAirCondition.toString(), argsJSONStr);
        System.out.println(resQueryAirCondition);
        int airScore = Integer.valueOf(JsonParse.jsonObjectParse(resQueryAirCondition, "air_score"));
        System.out.println(airScore);
    }

    @Test
    public void testCallWeidong() {
        String resGetUnknownWeight = processDesign.callWeidong(Weidong.getUnknownWeight.toString(), argsJSONStr);
        double unknowdWeight = Double.valueOf(JsonParse.jsonObjectParse(resGetUnknownWeight, "weight"));
        System.out.println(unknowdWeight);
        String resGetWeight = processDesign.callWeidong(Weidong.getWeight.toString(), argsJSONStr);
        System.out.println(resGetWeight);
    }

}
