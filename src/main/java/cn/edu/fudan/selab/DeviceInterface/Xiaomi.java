package cn.edu.fudan.selab.DeviceInterface;

public enum Xiaomi {

    dispatch("dispatch"),
        
    getAirPurifierData("getAirPurifierData"),
        
    getAirPurifierMode("getAirPurifierMode"),
        
        
    getDevList("getDevList"),
        
        
    getMonitorPlant("getMonitorPlant"),
        
        
    getMonitorStatus("getMonitorStatus"),
        
        
    getPM25Data("getPM25Data"),
        
        
    getPlantData("getPlantData"),
        
        
    getSwitchStatus("getSwitchStatus"),
        
        
    listSubs("listSubs"),
        
        
    sendOrder("sendOrder"),
        
        
    setAirPurifierMode("setAirPurifierMode"),
        
        
    setMonitorPlant("setMonitorPlant"),
        
        
    setSwitch("setSwitch"),
        
        
    setmTemp("setmTemp"),
        
        
    syncStatus("syncStatus");


    private String methodName;

    Xiaomi(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return methodName;
    }

}
