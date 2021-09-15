package model;

public class WoodType {
    private String WoodTypeID;
    private String WoodName;

    public WoodType() {
    }

    public WoodType(String woodTypeID, String woodName) {
        WoodTypeID = woodTypeID;
        WoodName = woodName;
    }

    public String getWoodTypeID() {
        return WoodTypeID;
    }

    public void setWoodTypeID(String woodTypeID) {
        WoodTypeID = woodTypeID;
    }

    public String getWoodName() {
        return WoodName;
    }

    public void setWoodName(String woodName) {
        WoodName = woodName;
    }
}