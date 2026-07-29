package com.auraos.system;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Получаем список всех установленных приложений
        List<AppInfo> apps = AppManager.INSTANCE.getInstalledApps(this);

        // 2. Находим сетку на экране
        GridView gridView = findViewById(R.id.apps_grid);

        // 3. Передаем список в наш адаптер и выводим на экран
        AppAdapter adapter = new AppAdapter(this, apps);
        gridView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        // Блокируем кнопку "Назад", чтобы пользователь не мог "выйти" из лончера
    }
}

