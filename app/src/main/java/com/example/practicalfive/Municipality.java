package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Municipality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_municipality);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve and validate the name passed from MainActivity.
        String municipality = TextProcessor.processMunicipality(
                getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY));

        if (municipality == null) {
            Toast.makeText(this, "No valid municipality was supplied", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Select the colour, image, summary index and website for this municipality.
        int colourRes;
        int imageRes;
        int summaryIndex;
        String website;

        switch (municipality) {
            case "Beijing":
                colourRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                summaryIndex = 0;
                website = "https://english.beijing.gov.cn/";
                break;
            case "Shanghai":
                colourRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                summaryIndex = 1;
                website = "https://english.shanghai.gov.cn/";
                break;
            case "Chongqing":
                colourRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                summaryIndex = 2;
                website = "https://english.cq.gov.cn/";
                break;
            case "Tianjin":
            default:
                colourRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                summaryIndex = 3;
                website = "https://en.tj.gov.cn/";
                break;
        }

        int colour = getColor(colourRes);
        int lightText = getColor(R.color.color_text_light);
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);

        TextView nameView = findViewById(R.id.tvName);
        nameView.setText(municipality);
        nameView.setTextColor(lightText);
        nameView.setBackgroundColor(colour);

        ImageView imageView = findViewById(R.id.ivMunicipality);
        imageView.setImageResource(imageRes);

        TextView infoView = findViewById(R.id.tvInfo);
        infoView.setText(summaries[summaryIndex]);
        infoView.setTextColor(colour);

        Button visitButton = findViewById(R.id.btnVisitSite);
        visitButton.setBackgroundTintList(ColorStateList.valueOf(colour));
        visitButton.setTextColor(lightText);

        final String url = website;
        visitButton.setOnClickListener(v -> openWebsite(url));
    }

    private void openWebsite(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No app is available to open the website", Toast.LENGTH_SHORT).show();
        }
    }
}