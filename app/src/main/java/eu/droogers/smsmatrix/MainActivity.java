package eu.droogers.smsmatrix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    static Matrix mx;
    private SharedPreferences sp;
    private EditText botUsername;
    private EditText botPassword;
    private EditText username;
    private EditText device;
    private EditText hsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
        botUsername = (EditText) findViewById(R.id.editText_botUsername);
        botPassword = (EditText) findViewById(R.id.editText_botpassword);
        username = (EditText) findViewById(R.id.editText_username);
        device = (EditText) findViewById(R.id.editText_device);
        hsUrl = (EditText) findViewById(R.id.editText_hsUrl);

        botUsername.setText(sp.getString("botUsername", ""));
        botPassword.setText(sp.getString("botPassword", ""));
        username.setText(sp.getString("username", ""));
        device.setText(sp.getString("device", ""));
        hsUrl.setText(sp.getString("hsUrl", ""));


        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("botUsername", botUsername.getText().toString());
                editor.putString("botPassword", botPassword.getText().toString());
                editor.putString("username", username.getText().toString());
                editor.putString("device", device.getText().toString());
                editor.putString("hsUrl", hsUrl.getText().toString());
                editor.apply();

                Log.e(TAG, "onClick: " + botUsername.getText().toString() );
                startService();
            }
        });
        startService();
    }

    private void startService() {
        Intent intent = new Intent(this, MatrixService.class);
        startService(intent);
    }
}
