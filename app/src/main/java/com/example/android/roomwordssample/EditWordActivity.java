package com.example.android.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditWordActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_WORD =
            "com.codinginflow.architectureexample.EXTRA_WORD";

    private EditText editTextWord;
    private Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);
        editTextWord = findViewById(R.id.text_edit_word);
        btn_edit = findViewById(R.id.button_edit);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editar Word");
            editTextWord.setText(intent.getStringExtra(EXTRA_WORD));
        } else {
            setTitle("Agregar palabra");
        }

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWord();
            }
        });
    }

    private void saveWord() {
        String word = editTextWord.getText().toString();
        if (word.trim().isEmpty() ) {
            Toast.makeText(this, "Por favor ingrese una palabra", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_WORD, word);
        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if (id!=-1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, data);

        //Toast.makeText(this, "Course has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
        finish();
    }
}