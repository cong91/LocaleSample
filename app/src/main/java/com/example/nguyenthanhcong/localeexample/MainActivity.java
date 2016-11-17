package com.example.nguyenthanhcong.localeexample;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ButtonLocale btnEnglish;
    private ButtonLocale btnJapanese;
    private TextViewLocale txtLocale;
    private RelativeLayout rootView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        setContentView(R.layout.activity_main);
        rootView = (RelativeLayout)findViewById(R.id.activity_main);
        btnEnglish = (ButtonLocale) findViewById(R.id.btn_english);
        btnJapanese = (ButtonLocale) findViewById(R.id.btn_japanese);
        txtLocale = (TextViewLocale) findViewById(R.id.txtText);
        setTextResource();

    }


    public void changeToEnglish(View view) {
        updateResources("en");
    }

    public void changeToJapanese(View view) {
        updateResources("ja");
    }

    private void updateResources(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = this.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(configuration.locale);
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        updateLocationChange(rootView);
    }

    private void updateLocationChange(ViewGroup rootView) {
        int childCount = rootView.getChildCount();
        if (childCount == 0)
            return;
        for (int i = 0; i < childCount; i++) {
            View childView = rootView.getChildAt(i);
            if (childView instanceof ViewGroup) {
                updateLocationChange((ViewGroup) childView);
            }
            if (childView instanceof LocaleChangeListener) {
                ((LocaleChangeListener) childView).onLocaleChange();
            }
        }
    }

    private void setTextResource() {
        txtLocale.setTextResource("hello");
        btnJapanese.setTextResource("japanese");
        btnEnglish.setTextResource("english");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
