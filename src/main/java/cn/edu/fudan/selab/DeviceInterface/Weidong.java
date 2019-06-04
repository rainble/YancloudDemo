package cn.edu.fudan.selab.DeviceInterface;

public enum Weidong {

    getUnknownWeight("getUnknownWeight"),
    getWeight("getWeight");

    private String methodName;

    Weidong(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return methodName;
    }
}
