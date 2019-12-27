package com.example.mylab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //private static final String READ_PHONE_STATE_PERMISSION = Manifest.permission.READ_PHONE_STATE;
    private static final int REQUEST_READ_PHONE_STATE = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView version = findViewById(R.id.version);
        version.setText("Current version: " + BuildConfig.VERSION_NAME);

        int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            SetId();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE},
                    REQUEST_READ_PHONE_STATE);
        }

    }

    public void SetId()
    {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String id = "";

        try{
            id = telephonyManager.getDeviceId();
        }
        catch (SecurityException ex)
        {

        }
        TextView text = findViewById(R.id.textview);
        text.setText("Device id is " + id);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    SetId();
                } else {
                    // permission denied
                    TextView message = findViewById(R.id.message);
                    message.setText(R.string.message);
                }
                return;
        }
    }


}
