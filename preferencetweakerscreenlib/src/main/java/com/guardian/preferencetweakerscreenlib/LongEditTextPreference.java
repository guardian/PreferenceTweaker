package com.guardian.preferencetweakerscreenlib;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

public class LongEditTextPreference extends EditTextPreference {

    public LongEditTextPreference(Context context) {
        super(context);
    }

    public LongEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LongEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected String getPersistedString(String defaultReturnValue) {
        return String.valueOf(getPersistedLong(-1));
    }

    @Override
    protected boolean persistString(String value) {
        return persistLong(Long.valueOf(value));
    }
}