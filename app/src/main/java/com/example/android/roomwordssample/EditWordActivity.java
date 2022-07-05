package com.example.android.roomwordssample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditWordActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_WORD =
            "com.codinginflow.architectureexample.EXTRA_WORD";

    private EditText editTextWord;
    private Button btn_edit, btn_cancel, btn_load;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Dialog dialog = new Dialog(EditWordActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_edit_word);
        editTextWord = dialog.findViewById(R.id.text_edit_word);
        btn_edit = dialog.findViewById(R.id.button_edit);
        btn_cancel = dialog.findViewById(R.id.button_cancel);
        btn_load=dialog.findViewById(R.id.btn_image);
        image = (ImageView) findViewById(R.id.image);

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
                dialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditWordActivity.this, MainActivity.class));
                dialog.dismiss();
            }
        });

        /*btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
                dialog.dismiss();
            }
        });*/
        dialog.dismiss();
        dialog.show();
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


    private void cargarImagen(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione"),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path = data.getData();
            image.setImageURI(path);
        }
    }
}