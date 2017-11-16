package james.colors.sample.samplecolors;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import james.colorpickerdialog.dialogs.ColorPickerDialog;
import james.colorpickerdialog.dialogs.PreferenceDialog;

public class MainActivity extends AppCompatActivity {

    private CustomView color1;
    private CustomView color2;
    private FloatingActionButton colorPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        color1 = findViewById(R.id.color1);
        color2 = findViewById(R.id.color2);
        colorPicker = findViewById(R.id.colorPicker);

        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerDialog(MainActivity.this)
                        .setDefaultPreference(color1.getColor())
                        .setListener(new PreferenceDialog.OnPreferenceListener<Integer>() {
                            @Override
                            public void onPreference(PreferenceDialog dialog, Integer preference) {
                                color1.setColor(preference);
                                color2.setColor(Color.rgb(255 - Color.red(preference),
                                        255 - Color.green(preference),
                                        255 -  Color.blue(preference)));
                            }

                            @Override
                            public void onCancel(PreferenceDialog dialog) {
                            }
                        })
                        .show();
            }
        });
    }
}
