package com.mnag.sqlitedatabasewithroom;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class InsertLoader extends AsyncTaskLoader<Boolean> {
    private User user;
    private UserDatabase db;

    public InsertLoader (@NonNull Context context, User user){
        super(context);
        this.user = user;
        this.db = UserDatabase.getInstance(context);
    }

    @Nullable
    @Override
    public Boolean loadInBackground(){
        db.userDao().insertUser(user);
        return true;
    }
}
