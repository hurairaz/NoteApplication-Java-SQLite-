package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity implements View.OnClickListener {

    Button Save;
    EditText titleEditText, descEditText;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        setTitle("Add Note");
        titleEditText = findViewById(R.id.title_editText);
        descEditText = findViewById(R.id.desc_editText);
        Save = findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        Save.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        final String title = titleEditText.getText().toString();
        final String desc = descEditText.getText().toString();
        dbManager.insert(title,desc);
        Intent i = new Intent(AddNoteActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}