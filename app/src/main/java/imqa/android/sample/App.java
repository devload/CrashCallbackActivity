package imqa.android.sample;

import android.app.Application;
import android.content.SharedPreferences;

import cat.ereza.customactivityoncrash.config.CaocConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences pref = getSharedPreferences("IMQA_SampleConfig", MODE_PRIVATE);
        String serverUrl = pref.getString("serverUrl","http://collector.imqa.io");
        String crashServerUrl = pref.getString("crashServerUrl","http://collector.imqa.io");
        String projectKey = pref.getString("projectKey","<프로젝트 키를 넣어주세요>");



        io.imqa.core.IMQAOption imqaOption = new io.imqa.core.IMQAOption();
        imqaOption.setBuildType(false);
        imqaOption.setUploadPeriod(true);
        imqaOption.setKeepFileAtUploadFail(false);
        imqaOption.setHttpTracing(true);
        imqaOption.setPrintLog(true);
        imqaOption.setBehaviorTracing(true);
        imqaOption.setEventTracing(true);
        imqaOption.setDumpInterval(5000);
        imqaOption.setFileInterval(5);
        imqaOption.setForceHttps(true);

        if(serverUrl != null) {
            imqaOption.setServerUrl(serverUrl);
        }
        if(crashServerUrl != null) {
            imqaOption.setCrashServerUrl(serverUrl);
        }

//        imqaOption.setCrashCollect(false);

        io.imqa.mpm.IMQAMpmAgent.getInstance()
                .setOption(imqaOption) // MPM 의 동작 방식을 정하는 옵션을 설정합니다.
                .setContext(this, "") // Application Context 를 초기화 합니다.
                .setProjectKey(projectKey) // IMQA MPM Client 의 Project Key 를 설정합니다.
                .init();


        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(true) //default: true
                .showErrorDetails(false) //default: true
                .showRestartButton(false) //default: true
                .logErrorOnRestart(false) //default: true
                .trackActivities(true) //default: false
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .errorDrawable(R.drawable.ic_launcher_background) //default: bug image
                .restartActivity(null) //default: null (your app's launch activity)
                .errorActivity(ErrorActivity.class) //default: null (default error activity)
                .eventListener(null) //default: null
                .customCrashDataCollector(new CustomCrashDataCollector()) //default: null
                .apply();


    }
}
