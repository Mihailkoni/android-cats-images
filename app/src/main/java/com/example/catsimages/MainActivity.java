package com.example.catsimages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private ImageView imageViewCat;
    private ProgressBar progressBar;
    private Button buttonLoadNextImage;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.loadCatImage();

        observeViewModel();
        setUpClickListeners();
    }

    private void initViews() {
        imageViewCat  = findViewById(R.id.imageViewCat);
        progressBar = findViewById(R.id.progressBar);
        buttonLoadNextImage = findViewById(R.id.buttonLoadNextImage);
    }

    private void observeViewModel() {
        viewModel.getIsError().observe(this, error -> {
            if(error){
                Toast.makeText(
                        MainActivity.this,
                        R.string.error_when_uploading_an_image,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        viewModel.getIsLoading().observe(this, loading -> {
            if(loading){
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getCatImage().observe(this, catImage -> {
            Glide.with(MainActivity.this)
                    .load(catImage.getUrl())
                    .into(imageViewCat);
        });
    }

    private void setUpClickListeners() {
        buttonLoadNextImage.setOnClickListener(view -> viewModel.loadCatImage());
    }
}