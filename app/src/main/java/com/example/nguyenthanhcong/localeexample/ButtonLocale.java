package com.example.nguyenthanhcong.localeexample;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nguyen.thanh.cong on 11/15/2016.
 */

public class ButtonLocale extends Button implements LocaleChangeListener{
    private static final String TAG = ButtonLocale.class.getSimpleName();
    private String resourceId;

    public ButtonLocale(Context context) {
        super(context);
    }

    public ButtonLocale(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonLocale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ButtonLocale(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private String getStringResourceByName(String aString) {
        String packageName = getContext().getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getResources().getString(resId);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void setTextResource(String resourceId){
        this.resourceId = resourceId;
        setText(getStringResourceByName(resourceId));
    }

    @Override
    public void onLocaleChange() {
        setText(getStringResourceByName(resourceId));
    }
}
