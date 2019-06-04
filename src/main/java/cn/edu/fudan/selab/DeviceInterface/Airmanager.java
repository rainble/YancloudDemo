package cn.edu.fudan.selab.DeviceInterface;

public enum Airmanager {

    calculateHappyScore("calculateHappyScore"),
    listSubs("listSubs"),
    queryAirCondition("queryAirCondition");

    private String methodName;

    Airmanager(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return methodName;
    }

}
