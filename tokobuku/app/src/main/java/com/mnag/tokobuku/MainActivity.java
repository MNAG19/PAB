package com.mnag.tokobuku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mnag.tokobuku.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyDatabaseHelper myDB;
    BookAdapter bookAdapter;
    ArrayList<String> arrayId, arrayIsbn, arrayTitle, arrayCategory, arrayDescription, arrayPrice;
    public static int dataPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDB = new MyDatabaseHelper(MainActivity.this);

    }
    public void bukaActivityTambah(View view) {
        startActivity(new Intent(MainActivity.this, InsertActivity.class));
    }

    private void generateArrayList() {
        Cursor cursor = myDB.getAllBook();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                arrayId.add(cursor.getString(0));
                arrayIsbn.add(cursor.getString(1));
                arrayTitle.add(cursor.getString(3));
                arrayCategory.add(cursor.getString(4));
                arrayDescription.add(cursor.getString(5));
                arrayPrice.add(cursor.getString(6));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayId = new ArrayList<>();
        arrayTitle = new ArrayList<>();
        arrayAuthor = new ArrayList<>();
        arrayYear = new ArrayList<>();

        generateArrayList();
        bookAdapter = new BookAdapter(MainActivity.this, arrayId, arrayTitle, arrayAuthor, arrayYear);
        binding.rvBuku.setAdapter(bookAdapter);
        binding.rvBuku.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.rvBuku.smoothScrollToPosition(dataPosition);
    }
}