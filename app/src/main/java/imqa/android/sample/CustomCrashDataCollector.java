package imqa.android.sample;

import android.util.Log;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

public class CustomCrashDataCollector implements CustomActivityOnCrash.CustomCrashDataCollector {
    @Override
    public String onCrash() {

        Log.e("CustomCrashDataCollector","error");

        return "error";
    }
}
