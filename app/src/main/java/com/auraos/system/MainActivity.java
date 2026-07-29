package com.auraos.system;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import java.util.List;

public class MainActivity extends Activity {

    private AppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Получаем список приложений
        List<AppInfo> apps = AppManager.INSTANCE.getInstalledApps(this);

        // 2. Настраиваем сетку
        GridView gridView = findViewById(R.id.apps_grid);
        adapter = new AppAdapter(this, apps);
        gridView.setAdapter(adapter);

        // 3. Настраиваем реальный поиск
        EditText searchBox = findViewById(R.id.search_box);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void onBackPressed() {
        // Блокируем кнопку "Назад"
    }
}


