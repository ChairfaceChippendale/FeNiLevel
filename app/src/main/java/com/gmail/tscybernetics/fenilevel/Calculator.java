package com.gmail.tscybernetics.fenilevel;


public class Calculator {

    private double bottomArea;
    private double feniDensity;
    private double niExtraction;

    private double formerMetalLevel;
    private double niInCharge;
    private double chargeLoaded;
    private double metalMass;
    private double niInMetal;

    public Calculator() {

        this.bottomArea = Const.BOTTOM_AREA_MIN;
        this.feniDensity = Const.FENI_DENSITY_MIN;
        this.niExtraction = Const.NI_EXTRACTION_MIN;
        this.formerMetalLevel = Const.FORMER_LEVEL_MIN;
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

    public void setFormerMetalLevel(double formerMetalLevel) {
        this.formerMetalLevel = formerMetalLevel;
    }

    public void setNiInCharge(double niInCharge) {
        this.niInCharge = niInCharge;
    }

    public void setChargeLoaded(double chargeLoaded) {
        this.chargeLoaded = chargeLoaded;
    }

    public void setMetalMass(double metalMass) {
        this.metalMass = metalMass;
    }

    public void setNiInMetal(double niInMetal) {
        this.niInMetal = niInMetal;
    }

    public double getMetalLevel () {
        return 0;
    }


}
