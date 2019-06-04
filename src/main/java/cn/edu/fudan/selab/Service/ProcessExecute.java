package cn.edu.fudan.selab.Service;

import cn.edu.fudan.selab.DeviceInterface.Airmanager;
import cn.edu.fudan.selab.DeviceInterface.Kettle;
import cn.edu.fudan.selab.DeviceInterface.Weidong;
import cn.edu.fudan.selab.DeviceInterface.Xiaomi;
import cn.edu.fudan.selab.Utils.Encoding;
import cn.edu.fudan.selab.Utils.JsonParse;
import cn.edu.fudan.selab.Utils.Parameters;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import static java.lang.Thread.sleep;

public class ProcessExecute implements Runnable{
        private ProcessDesign processDesign;

        public ProcessExecute(ProcessDesign processDesign) {
            this.processDesign = processDesign;
        }

        public void run() {
            double unknownWeight  = 0;
            String weidongMethod = "getWeight", argsJSONStr = null;
            // TODO: 2019-05-23 提高容错

            while (unknownWeight == 0) {
                String res = processDesign.callWeidong(Weidong.getUnknownWeight.toString(), argsJSONStr);
                try {
                    unknownWeight = Double.valueOf(JsonParse.jsonObjectParse(res, "weight"));
                    System.out.println(unknownWeight);
                } catch (Exception e) {
                    e.getStackTrace();
                    unknownWeight = 0;
                }
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String kettleId = JsonParse.jsonArrayParse(processDesign.callKettle(Kettle.getKettles.toString(),
                    argsJSONStr), 0, "id");
            System.out.println(kettleId);
            int modeId = Integer.valueOf(JsonParse.jsonArrayParse(processDesign.callKettle(Kettle.getModes.toString(),
                    argsJSONStr), 4, "id"));
            System.out.println(modeId);

            String startModeArgs = "{\\\"dev_id\\\":\\\"" + kettleId + "\\\",\\\"mode_id\\\":" + modeId + "}";
            processDesign.callKettle(Kettle.startMode.toString(), startModeArgs);

            String resListSubs = processDesign.callAirmanager(Airmanager.listSubs.toString(), argsJSONStr);
            System.out.println(resListSubs);
            argsJSONStr = "{\\\"dev_id\\\":\\\"" + JsonParse.jsonArrayParse(resListSubs, 0, "dev_id") + "\\\"}";
            String resQueryAirCondition = processDesign.callAirmanager(Airmanager.queryAirCondition.toString(), argsJSONStr);
            int airScore = Integer.valueOf(JsonParse.jsonObjectParse(resQueryAirCondition, "air_score"));
            System.out.println(airScore);
            if (airScore < 90) {

                String xiaomiSetSwitchArgs = "{\\\"status\\\":\\\"" + Parameters.XIAOMI_STATUS + "\\\",\\\"dev_id\\\":\\\""
                        + Parameters.XIAOMI_AIR_PURIFIER_ID + "\\\"}";
                processDesign.callXiaomi(Xiaomi.setSwitch.toString(), xiaomiSetSwitchArgs);
            }

        }


}
