package com.example.oneforall.ui.library;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.oneforall.R;

import java.io.File;
import java.util.ArrayList;

public class ListView extends AppCompatActivity {

    public static android.widget.ListView listView;
    private String songTitles[];
    private ArrayList<File> songs;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        //check permissions
        //checkPermission();

//        listView = (android.widget.ListView) findViewById(R.id.songsList);
        listView = findViewById(R.id.songsList);
//        File path = new File("/sdcard/Download/Linkin Park - Greatest Hits [2013]");
//        ArrayList<File> songs = readSongs(path);

        songs = readSongs(Environment.getExternalStorageDirectory());

        songTitles = new String[songs.size()];

        for (int i = 0; i < songs.size(); ++i) {
            songTitles[i] = songs.get(i).getName().replace(".mp3", "");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.song_layout, R.id.txtSongName, songTitles);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListView.this, MusicActivity.class).putExtra("position", position).putExtra("list", songs);
                startActivity(intent);
            }
        });
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_MEDIA_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                songs = readSongs(Environment.getExternalStorageDirectory());

            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_MEDIA_LOCATION}, 1);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            songs = readSongs(Environment.getExternalStorageDirectory());

        } else {
            checkPermission();
        }
    }

    //Scan Files in Disk
    private ArrayList<File> readSongs(File path){
        ArrayList<File> arrayList = new ArrayList<File>();
        File files[] = path.listFiles(); //get all files in Root
        if (files == null){
            Toast.makeText(getApplicationContext(), path.getPath(), Toast.LENGTH_SHORT).show();
            return arrayList;
        }
        for (File f : files){
            if (f.isDirectory()){
                arrayList.addAll(readSongs(f)); //recurse to read files in Dir
            }else{
                if(f.getName().endsWith(".mp3")){
                    arrayList.add(f);
                }
            }
        }
        return arrayList;
    }
}