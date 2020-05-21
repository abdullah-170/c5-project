package com.barmej.guesstheanswer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {
     public EditText sa;
     private String Share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        sa = findViewById(R.id.editTextShareTitle);
        Share =getIntent().getStringExtra("questions text extra");
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String questionTitle = sharedPreferences.getString("share_title","");
        sa.setText(questionTitle);

    }
    public void ggdg (View view){
        String questionTitle = sa.getText().toString();
        Intent ShareIntetn = new Intent();
        ShareIntetn.setAction(Intent.ACTION_SEND);
        ShareIntetn.putExtra(Intent.EXTRA_TEXT,questionTitle + "id/" + Share);
        ShareIntetn.setType("text/plain");
        startActivity(ShareIntetn);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share_title", questionTitle);
        editor.apply();

    }

    public void ih (View view) {
        finish();
    }
}
