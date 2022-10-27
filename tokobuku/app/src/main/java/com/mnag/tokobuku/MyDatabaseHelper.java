package com.mnag.tokobuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context ctx;
    private static final String DATABASE_NAME = "DB_BUKU";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tbBuku";
    private static final String FIELD_ID = "id";
    private static final String FIELD_ISBN = "isbn";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_CATEGORY = "category";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_PRICE = "price";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_ISBN + " TEXT, " + FIELD_TITLE + " TEXT, " + FIELD_CATEGORY + " TEXT, " +
                FIELD_DESCRIPTION + " TEXT, " + FIELD_PRICE + " DOUBLE ); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addBook(String isbn, String title, String category, String description, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_ISBN, isbn);
        cv.put(FIELD_TITLE, title);
        cv.put(FIELD_CATEGORY, category);
        cv.put(FIELD_DESCRIPTION, description);
        cv.put(FIELD_PRICE, price);

        long result = db.insert(TABLE_NAME, null, cv);
        return result;
    }

    public long editBookById(String id, String isbn, String title, String category, String description, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_ISBN, isbn);
        cv.put(FIELD_TITLE, title);
        cv.put(FIELD_CATEGORY, category);
        cv.put(FIELD_DESCRIPTION, description);
        cv.put(FIELD_PRICE, price);

        long result = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return result;
    }

    public Cursor getAllBook() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public long deleteBookById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"id = ?", new String[]{id});
        return result;
    }

}
