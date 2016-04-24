package com.gmail.tscybernetics.fenilevel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FeNiLevel extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private AppCompatSeekBar mFormerMetalLevelSeekBar;
    private AppCompatSeekBar mChargeLoadedSeekBar;
    private AppCompatSeekBar mNiInChargeSeekBar;
    private AppCompatSeekBar mMetalMassSeekBar;
    private AppCompatSeekBar mNiInMetalSeekBar;

    private TextView mFormerMetalLevelTextView;
    private TextView mNiInChargeTextView;
    private TextView mChargeLoadedTextView;
    private TextView mMetalMassTextView;
    private TextView mNiInMetalTextView;

    private TextView mBottomAreaTextView;
    private TextView mFeNiDensityTextView;
    private TextView mNiExtractionTextView;

    private Calculator calculator;

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feni_level);

        mFormerMetalLevelSeekBar = (AppCompatSeekBar) findViewById(R.id.seekbar_former_metal_level);
        mChargeLoadedSeekBar = (AppCompatSeekBar) findViewById(R.id.seekbar_charge_loaded);
        mNiInChargeSeekBar = (AppCompatSeekBar) findViewById(R.id.seekbar_ni_in_charge);
        mMetalMassSeekBar = (AppCompatSeekBar) findViewById(R.id.seekbar_metal_mass);
        mNiInMetalSeekBar = (AppCompatSeekBar) findViewById(R.id.seekbar_ni_in_metal);

        mFormerMetalLevelTextView = (TextView) findViewById(R.id.textview_former_metal_level);
        mChargeLoadedTextView = (TextView) findViewById(R.id.textview_charge_loaded);
        mNiInChargeTextView = (TextView) findViewById(R.id.textview_ni_in_charge);
        mMetalMassTextView = (TextView) findViewById(R.id.textview_metal_mass);
        mNiInMetalTextView  = (TextView) findViewById(R.id.textview_ni_in_metal);

        mBottomAreaTextView = (TextView) findViewById(R.id.textview_bottom_area);
        mFeNiDensityTextView = (TextView) findViewById(R.id.textview_feni_density);
        mNiExtractionTextView = (TextView) findViewById(R.id.textview_ni_extraction);

        resultTextView = (TextView) findViewById(R.id.result);

        calculator = new Calculator();

    }

    @Override
    protected void onStart() {
        super.onStart();

        setupSeekBars ();


    }

    private void setupSeekBars() {
        mFormerMetalLevelSeekBar.setOnSeekBarChangeListener(this);
        mChargeLoadedSeekBar.setOnSeekBarChangeListener(this);
        mNiInChargeSeekBar.setOnSeekBarChangeListener(this);
        mMetalMassSeekBar.setOnSeekBarChangeListener(this);
        mNiInMetalSeekBar.setOnSeekBarChangeListener(this);

        mFormerMetalLevelSeekBar.setMax( (Const.FORMER_METAL_LEVEL_MAX - Const.FORMER_METAL_LEVEL_MIN)/Const.FORMER_METAL_LEVEL_STEP );
        mChargeLoadedSeekBar.setMax( (Const.CHARGE_LOADED_MAX - Const.CHARGE_LOADED_MIN)/Const.CHARGE_LOADED_STEP );
        mNiInChargeSeekBar.setMax( (int)((Const.NI_IN_CHARGE_MAX - Const.NI_IN_CHARGE_MIN)/Const.NI_IN_CHARGE_STEP) );
        mMetalMassSeekBar.setMax( (Const.METAL_MASS_MAX - Const.METAL_MASS_MIN)/Const.METAL_MASS_STEP );
        mNiInMetalSeekBar.setMax( (int)((Const.NI_IN_METAL_MAX - Const.NI_IN_METAL_MIN)/Const.NI_IN_METAL_STEP) );
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        double bottomArea = Double.parseDouble(sp.getString("bottom_area", getString(R.string.bottom_area_default_value)));
        double feniDensity = Double.parseDouble(sp.getString("feni_density", getString(R.string.feni_density_default_value)));
        double niExtraction = Double.parseDouble(sp.getString("ni_extraction", getString(R.string.ni_extraction_default_value)));

        mBottomAreaTextView.setText(String.valueOf(bottomArea));
        mFeNiDensityTextView.setText(String.valueOf(feniDensity));
        mNiExtractionTextView.setText(String.valueOf(niExtraction));

        calculator.setAdditionalOptions(bottomArea, feniDensity, niExtraction);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.additional_settings:
                startActivity(new Intent(this, AdditionSettings.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == mFormerMetalLevelSeekBar) {
            int newValue = Const.FORMER_METAL_LEVEL_MIN + progress * Const.FORMER_METAL_LEVEL_STEP;
            mFormerMetalLevelTextView.setText(String.valueOf(newValue));
            calculator.setFormerMetalLevel(newValue);
        } else if (seekBar == mChargeLoadedSeekBar) {
            int newValue = Const.CHARGE_LOADED_MIN + progress * Const.CHARGE_LOADED_STEP;
            mChargeLoadedTextView.setText(String.valueOf(newValue));
            calculator.setChargeLoaded(newValue);
        } else if (seekBar == mNiInChargeSeekBar) {
            double newValue = Const.NI_IN_CHARGE_MIN + progress * Const.NI_IN_CHARGE_STEP;
            newValue = new BigDecimal(newValue).setScale(1, RoundingMode.UP).doubleValue();
            mNiInChargeTextView.setText(String.valueOf(newValue));
            calculator.setNiInCharge(newValue);
        } else if (seekBar == mMetalMassSeekBar) {
            int newValue = Const.METAL_MASS_MIN + progress * Const.METAL_MASS_STEP;
            mMetalMassTextView.setText(String.valueOf(newValue));
            calculator.setMetalMass(newValue);
        } else if (seekBar == mNiInMetalSeekBar) {
            double newValue = Const.NI_IN_METAL_MIN + progress * Const.NI_IN_METAL_STEP;
            newValue = new BigDecimal(newValue).setScale(1, RoundingMode.UP).doubleValue();
            mNiInMetalTextView.setText(String.valueOf(newValue));
            calculator.setNiInMetal(newValue);
        }

        resultTextView.setText(String.valueOf(calculator.getMetalLevel()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
