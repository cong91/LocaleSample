package com.example.nguyenthanhcong.localeexample;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by nguyen.thanh.cong on 11/15/2016.
 */

public class TextViewLocale extends TextView implements LocaleChangeListener{
    private static final String TAG = TextViewLocale.class.getSimpleName();
    private String resourceId;

    public TextViewLocale(Context context) {
        super(context);
    }

    public TextViewLocale(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewLocale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TextViewLocale(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        Log.i(TAG, "onConfigurationChanged: ");
//        setText(getStringResourceByName(getText().toString()));
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
