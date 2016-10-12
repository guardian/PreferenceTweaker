package com.guardian.preferencetweakerscreenlib;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.widget.EditText;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

@SuppressWarnings("deprecation")
public class PreferenceSettingsFragment extends PreferenceFragment {

    private static final String MAP_KEY = "map_key";

    public static PreferenceSettingsFragment newInstance(String preferenceKey) {
        PreferenceSettingsFragment fragment = new PreferenceSettingsFragment();
        Bundle args = new Bundle();
        args.putString(MAP_KEY, preferenceKey);
        fragment.setArguments(args);
        return fragment;
    }

    private String getKey(){
        return getArguments().getString(MAP_KEY);
    }

    private Map getPreferenceMap(){
        return getActivity().getSharedPreferences(getKey(), Context.MODE_PRIVATE).getAll();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getPreferenceScreen() != null){
            getPreferenceScreen().removeAll();
        }

        setupDebugPrefs();
    }

    private void setupDebugPrefs() {
        addPreferencesFromResource(R.xml.settings_preferences);

        Map map = getPreferenceMap();
        SortedSet<String> keySet = new TreeSet<String>(map.keySet());

        Log.d("dd", keySet.toString());

        for(String key : keySet){
            Object obj = map.get(key);

            Preference preference = new Preference(getActivity());

            if(obj instanceof String){
                Log.d("String", key + " || " + obj.toString());
                preference = new EditTextPreference(getActivity());
            } else if(obj instanceof Boolean){
                Log.d("Boolean", key + " || " + obj.toString());
                preference = new CheckBoxPreference(getActivity());
            } else if(obj instanceof Integer){
                Log.d("Integer", key + " || " + obj.toString());
                preference = setEditTextToDigitsOnly(new IntEditTextPreference(getActivity()));
            } else if(obj instanceof Long){
                Log.d("Long", key + " || " + obj.toString());
                preference = setEditTextToDigitsOnly(new LongEditTextPreference(getActivity()));
            } else if(obj instanceof Float){
                Log.d("Float", key + " || " + obj.toString());
                preference = setEditTextToDigitsOnly(new FloatEditTextPreference(getActivity()));
            }

            preference.setDefaultValue(obj);
            preference.setKey(key);
            preference.setTitle(key);
            preference.setSummary("Current: " + obj);

            getPreferenceScreen().addPreference(preference);
        }
    }

    private EditTextPreference setEditTextToDigitsOnly(EditTextPreference preference){
        EditText et = preference.getEditText();
        et.setKeyListener(DigitsKeyListener.getInstance());
        return preference;
    }

}
