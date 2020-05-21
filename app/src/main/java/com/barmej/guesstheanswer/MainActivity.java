package com.barmej.guesstheanswer;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewQUESTIONS;

    private  String[] QUESTIONS ;
    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            true,
            true,
            false,
            true
    };
    private  String[] ANSWERS_DETAILS ;

    private String mCurrentQUESTIONS, mCurrentAnswers;
    public boolean mAnswersDetlalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewQUESTIONS = findViewById(R.id.textView3);
        QUESTIONS =getResources().getStringArray(R.array.QUESTIONS);
        ANSWERS_DETAILS = getResources().getStringArray(R.array.ANSWERS_DETAILS);

        ShowNewQuestions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater =getMenuInflater();
        Inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.MenuChangeLanguage){
            showlanguagedialog();
        }
        return super.onOptionsItemSelected(item);
    }
    private void showlanguagedialog (){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.Change_lang_Text)
                .setItems(R.array.language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                        }

                        LocaleHelper.setLocale(MainActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }).create();
        alertDialog.show();
    }

    public void ShowNewQuestions () {
        Random random = new Random();
        int RandomQUESTIONS = random.nextInt(QUESTIONS.length);
        mCurrentQUESTIONS = QUESTIONS[RandomQUESTIONS];
        mAnswersDetlalls = ANSWERS[RandomQUESTIONS];
        mCurrentAnswers = ANSWERS_DETAILS[RandomQUESTIONS];
        mTextViewQUESTIONS.setText(mCurrentQUESTIONS);

    }

    public void shwaonClik(View view) {
        ShowNewQuestions();
    }

    public void onTrueClicked(View view) {
        if (mAnswersDetlalls == true) {
            Toast.makeText(this, "اجابة صحيحة ", Toast.LENGTH_LONG).show();
            ShowNewQuestions();
        } else {
            Toast.makeText(this, "اجاية خاطئة", Toast.LENGTH_LONG).show();;
            Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
            intent.putExtra("QUESTIONS_Answer",mCurrentAnswers);
            startActivity(intent);

        }
    }
    public void onFalseClicked(View view) {
        if (mAnswersDetlalls == false) {
            Toast.makeText(this, "اجابة صحيحة ", Toast.LENGTH_LONG).show();
            ShowNewQuestions();
        } else {
            Toast.makeText(this, "اجاية خاطئة", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
            intent.putExtra("QUESTIONS_Answer",mCurrentAnswers);
            startActivity(intent);

        }
    }
    public void SheraAA (View view) {
        Intent intent2 = new Intent(MainActivity.this,ShareActivity.class);
        intent2.putExtra("sxsxsx",mCurrentQUESTIONS);
        startActivity(intent2);
    }
    public void onShareQuestions (View view) {
        Intent intent = new Intent(MainActivity.this,ShareActivity.class);
        intent.putExtra("questions text extra", mCurrentQUESTIONS);
        startActivity(intent);
    }
}