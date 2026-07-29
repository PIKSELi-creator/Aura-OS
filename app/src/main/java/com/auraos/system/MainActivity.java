package com.auraos.system;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Загружаем список приложений и выводим количество в лог
        List<AppInfo> apps = AppManager.INSTANCE.getInstalledApps(this);
        Log.d("AuraOS", "Найдено приложений: " + apps.size());
    }

    @Override
    public void onBackPressed() {
        // Блокируем выход с рабочего стола
    }
}

