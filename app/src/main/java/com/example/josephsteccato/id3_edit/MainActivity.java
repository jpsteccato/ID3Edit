package com.example.josephsteccato.id3_edit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hendrawd.storageutil.library.StorageUtil;

public class MainActivity extends AppCompatActivity {

    String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

    }

    public void openEditTags(View view){
        openFileBrowser(0);
    }

    public void openInferTags(View view){
        openFileBrowser(1);
    }

    //
    // openFileBrowser
    //      On "Edit Tags" button click start next SelectFiles activity
    //
    public void openFileBrowser(int editMode) {
        Intent intent = new Intent(this, SelectFilesActivity.class);
        intent.putExtra("EDIT_MODE",editMode);
        startActivity(intent);
    }

    // openSettings
    public void openSettings(View view) {
        // settings
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    // openAbout
    public void openAbout(View view) {
        // about
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    //
    // checkPermissions
    //
    public boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    //
    // onRequestPermissionsResult
    //
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if ( (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED) ){
                Toast toast = Toast.makeText(getApplicationContext(), "ALL PERMISSIONS GRANTED", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "PERMISSIONS DENIED", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}




