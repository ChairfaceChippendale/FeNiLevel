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

public class FeNiLevel extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private double bottomArea = .0;
    private double feniDensity = .0;
    private double niExtraction = .0;

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

        Calculator calculator = new Calculator();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mFormerMetalLevelSeekBar.setOnSeekBarChangeListener(this);
        mChargeLoadedSeekBar.setOnSeekBarChangeListener(this);
        mNiInChargeSeekBar.setOnSeekBarChangeListener(this);
        mMetalMassSeekBar.setOnSeekBarChangeListener(this);
        mNiInMetalSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        bottomArea = Float.parseFloat(sp.getString("bottom_area", "0"));
        feniDensity = Double.parseDouble(sp.getString("feni_density", "0"));
        niExtraction = Double.parseDouble(sp.getString("ni_extraction", "0"));

        setupAdditionalOptions();
    }

    private void setupAdditionalOptions () {
        mBottomAreaTextView.setText(String.valueOf(bottomArea));
        mFeNiDensityTextView.setText(String.valueOf(feniDensity));
        mNiExtractionTextView.setText(String.valueOf(niExtraction));
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
            mFormerMetalLevelTextView.setText(String.valueOf(progress));
        } else if (seekBar == mChargeLoadedSeekBar) {
            mChargeLoadedTextView.setText(String.valueOf(progress));
        } else if (seekBar == mNiInChargeSeekBar) {
            mNiInChargeTextView.setText(String.valueOf(progress));
        } else if (seekBar == mMetalMassSeekBar) {
            mMetalMassTextView.setText(String.valueOf(progress));
        } else if (seekBar == mNiInMetalSeekBar) {
            mNiInMetalTextView.setText(String.valueOf(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
