package com.github.farhaanaliii.wget;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.graphics.drawable.GradientDrawable;
import android.graphics.Color;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.content.Context;
import com.github.farhaanaliii.wget.R;
import android.content.Intent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.Toast;
import android.app.DownloadManager;
import java.io.File;
import android.net.Uri;
import android.Manifest;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.widget.LinearLayout;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {
    public EditText url;
    public Button get,copy,dl,clear;
    public static ProgressBar progressBar;
    public static TextView response;
    public GradientDrawable background = new GradientDrawable();
    public static LinearLayout bottomLayout;
    private InputMethodManager inputMethodManager;
    long downloadId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        final DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        requestStoragePermissions();
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        
        get.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bottomLayout.setVisibility(View.GONE);
                response.setText("");
                url.clearFocus();
                new WGet(url.getText().toString()).execute();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        copy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Copy(response.getText().toString(), MainActivity.this);
                Toast.makeText(MainActivity.this, "Copied!", Toast.LENGTH_SHORT).show();
            }
        });
        dl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputUrl = url.getText().toString().trim();
                String resText = response.getText().toString().trim();
                if(inputUrl.isEmpty() && resText.isEmpty()){
                    return;
                }
                dl.setEnabled(false);
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(downloadId);
                Cursor cursor = downloadManager.query(query);

                if(cursor != null && cursor.moveToFirst()){
                    int status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                    if(status == DownloadManager.STATUS_SUCCESSFUL){
                        dl.setEnabled(true);
                    }else{
                        Toast.makeText(MainActivity.this, "Already Downloading", Toast.LENGTH_LONG).show();
                    }
                    cursor.close();
                }
                requestStoragePermissions();
                String fileName = "";
                if(inputUrl.endsWith("/")){
                    fileName = inputUrl;
                }else{
                    fileName = inputUrl.substring(inputUrl.lastIndexOf('/') + 1);
                }
                File file = Utils.createDocumentFile(fileName, MainActivity.this);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(inputUrl))
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setDestinationUri(Uri.fromFile(file))
                    .setTitle(fileName)
                    .setDescription("Downloading")
                    .setRequiresCharging(false)
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(true);
                downloadId = downloadManager.enqueue(request);
                Toast.makeText(MainActivity.this, "Downloading Started!", Toast.LENGTH_SHORT).show();
            }
        });
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bottomLayout.setVisibility(View.GONE);
                url.setText("");
                response.setText("");
                Toast.makeText(MainActivity.this, "Cleared!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        url = findViewById(R.id.url);
        get = findViewById(R.id.get);
        copy = findViewById(R.id.copy);
        dl = findViewById(R.id.dl);
        clear = findViewById(R.id.clear);
        progressBar = findViewById(R.id.progressBar);
        response = findViewById(R.id.response);
        bottomLayout = findViewById(R.id.bottomLayout);
        
        background.setColor(Color.parseColor("#3F51B5"));
        background.setCornerRadius(15);
        background.setStroke(3, Color.parseColor("#ffffff"));
    }
    public static void Copy(String text, Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); 
        ClipData clip = ClipData.newPlainText("text", text);
        clipboard.setPrimaryClip(clip);
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private void requestStoragePermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_EXTERNAL_STORAGE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }else{
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.about){
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("WGet 1.0 - Developed by Farhan Ali\ni.farhanali.dev@gmail.com")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dia, int which) {
                    }
                })
                .create();
            dialog.show();
        }
        return true;
    }
}
