package com.mnag.sqlitedatabasewithroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mnag.sqlitedatabasewithroom.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int INSERT_LOADER = 123;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validatefields()){
                    insertUser();
                }
            }
        });
    }

    private void insertUser() {
        User user = new User();
        user.setName(binding.etName.getText().toString());
        user.setEmail(binding.etEmail.getText().toString());
        user.setPhone(binding.etPhone.getText().toString());
        showProgressBar();
        Bundle args = new Bundle();
        args.putParcelable("user",user);
        LoaderManager.getInstance(this)
                .restartLoader(INSERT_LOADER, args, new LoaderManager.LoaderCallbacks<Boolean>() {
                    @NonNull
                    @Override
                    public Loader<Boolean> onCreateLoader(int id, @Nullable Bundle args) {
                        return new InsertLoader(MainActivity.this, args.getParcelable("user"));
                    }

                    @Override
                    public void onLoadFinished(@NonNull Loader<Boolean> loader, Boolean data) {
                        hideProgressBar();
                        if(data){
                            userAdded();
                        }
                    }

                    @Override
                    public void onLoaderReset(@NonNull Loader<Boolean> loader) {

                    }
                });
    }

    private void userAdded() {
        binding.etName.setText("");
        binding.etEmail.setText("");
        binding.etPhone.setText("");
        Toast.makeText(this, "User added to database", Toast.LENGTH_SHORT).show();
    }

    private void showProgressBar() {
        binding.progressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        binding.progressbar.setVisibility(View.GONE);
    }

    private boolean validatefields() {
        if(binding.etName.getText().toString().equals("")
                || binding.etEmail.getText().toString().equals("")
                || binding.etPhone.getText().toString().equals("")){
            Toast.makeText(this, "please fill all fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}