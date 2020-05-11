package com.example.graphs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


    //Объявим переменные компонентов
    Button button;
    TextView textView;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    ImageView imageView;
    String namePhoto = "cko";

    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        /*//Найдем компоненты в XML разметке
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        //Пропишем обработчик клика кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = "";

                Cursor cursor = mDb.rawQuery("SELECT * FROM clients", null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(1) + " | ";
                    cursor.moveToNext();
                }
                cursor.close();

                textView.setText(product);
            }
        });*/


        /*//Список клиентов
        ArrayList<HashMap<String, Object>> clients = new ArrayList<HashMap<String, Object>>();

//Список параметров конкретного клиента
        HashMap<String, Object> client;*/

//Отправляем запрос в БД
        //Cursor cursor = mDb.rawQuery("SELECT * FROM my_table", null);
        Army DataBaseInfo = mDBHelper.exMainList(0);

//Пробегаем по всем клиентам

        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        imageView = findViewById(R.id.photo);


        //String linksPhoto = DataBaseInfo.getAllImage();
        //List<String> photoLinks = GetLinkImages(linksPhoto);
        //final String photoLinksStr[] = photoLinks.toArray(new String[0]);

        textView.setText(DataBaseInfo.getTitle());
        textView3.setText(DataBaseInfo.getSubtitle());
        textView4.setText(DataBaseInfo.getDescription());
        textView5.setText(DataBaseInfo.getGroupName());


        String path = Environment.getExternalStorageDirectory().toString();
        String imagePath = "/storage/emulated/0/AudioArmy/PhotoForDB/ppu.jpg";
        imageView.setImageURI(Uri.parse(imagePath));
    }
}


