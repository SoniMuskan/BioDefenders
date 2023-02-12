package com.example.login_app;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class HomePageActivity extends AppCompatActivity {

    static final int REQUEST_ENABLE_BT = 1;
    Button btnBluetooth;
     BluetoothAdapter btAdapter;
     ImageView myImage;
     Button button;


    @SuppressLint("MissingInflated")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnBluetooth = (Button) findViewById(R.id.btnBluetooth);
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check if Bluetooth is available on the device
        if (btAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                // Turn on Bluetooth
                if (!btAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                // Bluetooth is already on
                else {
                    Toast.makeText(HomePageActivity.this, "Bluetooth is already on", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button = (Button) findViewById(R.id.buttonim);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }

            void takePicture() {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data_image){
        super.onActivityResult(requestCode,resultCode,data_image);
        if(resultCode== RESULT_OK){
            Bitmap b=(Bitmap)data_image.getExtras().get("data");
            myImage.setImageBitmap(b);
        }
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Bluetooth turned on", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bluetooth not turned on", Toast.LENGTH_SHORT).show();
            }}
    }



}

