package com.gmail.tscybernetics.fenilevel;


import android.util.Log;

public class Calculator {

    private double bottomArea;
    private double feniDensity;
    private double niExtraction;

    private int formerMetalLevel;
    private double niInCharge;
    private int chargeLoaded;
    private int metalMass;
    private double niInMetal;

    public Calculator() {

        this.bottomArea = Const.BOTTOM_AREA_MIN;
        this.feniDensity = Const.FENI_DENSITY_MIN;
        this.niExtraction = Const.NI_EXTRACTION_MIN;
        this.formerMetalLevel = Const.FORMER_METAL_LEVEL_MIN;
        this.niInCharge = Const.NI_IN_CHARGE_MIN;
        this.chargeLoaded = Const.CHARGE_LOADED_MIN;
        this.metalMass = Const.METAL_MASS_MIN;
        this.niInMetal = Const.NI_IN_METAL_MIN;
    }

    public void setAdditionalOptions (double bottomArea,
                                      double feniDensity,
                                      double niExtraction){
        this.bottomArea = bottomArea;
        this.feniDensity = feniDensity;
        this.niExtraction = niExtraction;
    }

    public void setFormerMetalLevel(int formerMetalLevel) {
        this.formerMetalLevel = formerMetalLevel;
    }

    public void setNiInCharge(double niInCharge) {
        this.niInCharge = niInCharge;
    }

    public void setChargeLoaded(int chargeLoaded) {
        this.chargeLoaded = chargeLoaded;
    }

    public void setMetalMass(int metalMass) {
        this.metalMass = metalMass;
    }

    public void setNiInMetal(double niInMetal) {
        this.niInMetal = niInMetal;
    }

    public int getMetalLevel () {

        double createdMetal = chargeLoaded * (niInCharge/100) * (niExtraction/100) / (niInMetal/100);
        Log.d("myTAG", "createdMetal = " + chargeLoaded + " * " + (niInCharge/100) + " * " + (niExtraction/100) + "//" + (niInMetal/100) + " = " + String.valueOf(createdMetal));
        double metalInFurnace = createdMetal - metalMass;
        Log.d("myTAG", "metalInFurnace" + String.valueOf(metalInFurnace));
        double metalVolumeInFurnace = metalInFurnace / feniDensity;
        Log.d("myTAG", "metalVolumeInFurnace" + String.valueOf(metalVolumeInFurnace));
        int metalLevel = (int) (formerMetalLevel + (metalVolumeInFurnace / bottomArea) * 1000); //1000 - meters to millimeters
        Log.d("myTAG", "metalLevel" + String.valueOf(metalLevel));
        return metalLevel;
    }


}
