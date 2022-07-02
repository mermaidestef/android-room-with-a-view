package com.example.android.roomwordssample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditWordActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_WORD =
            "com.codinginflow.architectureexample.EXTRA_WORD";


    private EditText editTextWord;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        editTextWord = findViewById(R.id.edit_word);
        getSupportActionBar().setHomeAsUpIndicator(R.layout.activity_new_word);


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editar Word");
            editTextWord.setText(intent.getStringExtra(EXTRA_WORD));
        } else {
            setTitle("Agregar palabra");
        }
    }

    private void saveWord() {
        String word = editTextWord.getText().toString();

        if (word.trim().isEmpty() ) {
            Toast.makeText(this, "Por favor ingrese una palabra", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_WORD, word);

        setResult(RESULT_OK, data);
        finish();
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.content_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_save:
                saveWord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}