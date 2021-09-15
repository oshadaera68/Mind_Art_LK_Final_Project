package view.Tm;

public class WoodTypeTm {
    private String WoodTypeID;
    private String WoodName;

    public WoodTypeTm() {
    }

    public WoodTypeTm(String woodTypeID, String woodName) {
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

    @Override
    public String toString() {
        return "WoodTypeTm{" +
                "WoodTypeID='" + WoodTypeID + '\'' +
                ", WoodName='" + WoodName + '\'' +
                '}';
    }
}