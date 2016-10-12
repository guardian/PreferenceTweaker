package com.guardian.preferencetweakerscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.guardian.preferencetweakerscreenlib.PreferenceSettingsFragment;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPreferences();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main, PreferenceSettingsFragment.newInstance("prefs"))
                .commit();
    }

    private SharedPreferences getSharedPrefs(){
        return getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    /**
     * Setup a shared preference with values
     */
    private void setupPreferences(){
        for(int i = 0; i<50; i++){
            if(i % 5 == 0){
                getSharedPrefs().edit().putBoolean("Boolean " + i, i % 10 == 0).commit();
            }

            if(i % 3 == 0){
                getSharedPrefs().edit().putInt("Integer " + i, new Random().nextInt(20)).commit();
            }

            if(i % 2 == 0){
                getSharedPrefs().edit().putString("String " + i, UUID.randomUUID().toString()).commit();
            }
        }
    }
}
