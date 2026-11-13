package com.tvbooster.centek;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(28, 28, 28, 28);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setBackgroundColor(0xFFF4F6F8);

        TextView title = new TextView(this);
        title.setText("TV BOOSTER");
        title.setTextSize(28);
        title.setTextColor(0xFF12345A);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 10, 0, 12);
        root.addView(title, matchWrap());

        TextView info = new TextView(this);
        info.setText("Помощник по обслуживанию телевизора\n\nВыберите действие ниже. Android не разрешает обычным приложениям автоматически очищать кэш других программ, поэтому очистка выполняется в системных настройках.");
        info.setTextSize(17);
        info.setTextColor(0xFF222222);
        info.setGravity(Gravity.CENTER);
        info.setPadding(4, 8, 4, 24);
        root.addView(info, matchWrap());

        addButton("Открыть хранилище", v -> openSettings(new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS)));
        addButton("Открыть список приложений", v -> openSettings(new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)));
        addButton("Сведения о телевизоре", v -> openSettings(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS)));

        TextView foot = new TextView(this);
        foot.setText("\nПодсказка: в настройках приложения можно очистить кэш или удалить ненужное приложение. Не удаляйте системные компоненты.");
        foot.setTextSize(14);
        foot.setTextColor(0xFF555555);
        foot.setGravity(Gravity.CENTER);
        root.addView(foot, matchWrap());

        setContentView(root);
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
    }

    private void addButton(String label, View.OnClickListener listener) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextSize(17);
        b.setAllCaps(false);
        b.setOnClickListener(listener);
        LinearLayout.LayoutParams p = matchWrap();
        p.setMargins(0, 8, 0, 8);
        root.addView(b, p);
    }

    private void openSettings(Intent intent) {
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Этот раздел настроек недоступен на данной прошивке.", Toast.LENGTH_LONG).show();
            try {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            } catch (Exception ignored) {}
        }
    }
}
