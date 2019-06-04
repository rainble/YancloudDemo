package cn.edu.fudan.selab.DeviceInterface;

public enum Kettle {

    getKettles("getKettles"),
    getModes("getModes"),
    startMode("startMode");


    private String methodName;

    Kettle(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return methodName;
    }
}
