package cn.edu.fudan.selab.Service;

import cn.edu.fudan.selab.DeviceInterface.Ability;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AbilityReasoning {

    private List<String> needAbilities = new ArrayList<String>();


    public List<String> abilitySearch(String location) {
        List<String> result = new ArrayList<String>();
//        find abilities from KG
        if (location.equals("402")) {
            //mock for test
            result.add(Ability.DRINK.toString());
            result.add(Ability.AIR_PURIFIER.toString());
            result.add(Ability.GET_AIR_CONDITION.toString());
            result.add(Ability.GET_WEIGHT.toString());

        }

        return result;
    }

    public String abilityMatch(List<String> abilities) {

        boolean flag1 = false;
        boolean flag2 = true;
        needAbilities.add(Ability.DRINK.toString());
        needAbilities.add(Ability.AIR_PURIFIER.toString());
        needAbilities.add(Ability.GET_AIR_CONDITION.toString());
        needAbilities.add(Ability.GET_WEIGHT.toString());
        JSONObject result = new JSONObject();

        for (String needAbility : needAbilities) {
            for (String ability : abilities) {
                if (ability.equals(needAbility)) {
                    flag1 = true;
                    result.put(needAbility, true);
                    break;
                }
            }
            if (flag1 == false) {
                result.put(needAbility, false);
            }
            flag2 = flag2 & flag1;
        }
        result.put("result", flag2);

        return result.toJSONString();
    }


}
