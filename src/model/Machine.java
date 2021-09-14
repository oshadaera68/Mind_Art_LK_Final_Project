package model;

public class Machine {
    private String MachineID;
    private String MachineName;
    private int MachineQty;
    private String model;

    public Machine() {
    }

    public Machine(String machineID, String machineName, int machineQty, String model) {
        this.MachineID = machineID;
        this.MachineName = machineName;
        this.MachineQty = machineQty;
        this.model = model;
    }

    public String getMachineID() {
        return MachineID;
    }

    public void setMachineID(String machineID) {
        MachineID = machineID;
    }

    public String getMachineName() {
        return MachineName;
    }

    public void setMachineName(String machineName) {
        MachineName = machineName;
    }

    public int getMachineQty() {
        return MachineQty;
    }

    public void setMachineQty(int machineQty) {
        MachineQty = machineQty;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
