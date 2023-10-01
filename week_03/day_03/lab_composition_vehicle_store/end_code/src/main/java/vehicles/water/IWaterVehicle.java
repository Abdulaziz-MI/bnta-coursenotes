package vehicles.water;

import vehicles.IVehicle;

public interface IWaterVehicle extends IVehicle {
//    must add hulltype property when implemented in class
    String getHullType();
    void setHullType(String type);
}
