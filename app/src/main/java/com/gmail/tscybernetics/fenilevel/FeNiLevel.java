package com.gmail.tscybernetics.fenilevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.TextView;

public class FeNiLevel extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private AppCompatSeekBar mNiInOreSeekBar;
    private AppCompatSeekBar mChargeOreSeekBar;

    private TextView mNiInOreTextView;
    private TextView mChargeOreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feni_level);

        mNiInOreSeekBar = (AppCompatSeekBar) findViewById(R.id.seekbar_ni_in_ore);
        mChargeOreSeekBar = (AppCompatSeekBar) findViewById(R.id.seekbar_charge_ore);

        mNiInOreTextView = (TextView) findViewById(R.id.textview_ni_in_ore);
        mChargeOreTextView = (TextView) findViewById(R.id.textview_charge_ore);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mNiInOreSeekBar.setOnSeekBarChangeListener(this);
        mChargeOreSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == mNiInOreSeekBar) {
            mNiInOreTextView.setText(String.valueOf(progress));
        } else if (seekBar == mChargeOreSeekBar) {
            mChargeOreTextView.setText(String.valueOf(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
