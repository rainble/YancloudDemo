package cn.edu.fudan.selab.DeviceInterface;

public enum Ability {

    DRINK("drink"),
    GET_WEIGHT("getWeight"),
    GET_AIR_CONDITION("getAirCondition"),
    AIR_PURIFIER("ariPurifier");

    private String abiliry;

    Ability(String abiliry) {
        this.abiliry = abiliry;
    }

    @Override
    public String toString() {
        return abiliry;
    }

}
