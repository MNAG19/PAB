package com.pab.mooneyq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pab.mooneyq.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}