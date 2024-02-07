package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyNoteActivity extends Activity implements View.OnClickListener {

    EditText titleText, descText;
    Button btnSave, btnDelete;
    private long _id;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);
        dbManager = new DBManager(this);
        dbManager.open();
        titleText = findViewById(R.id.title_editText);
        descText = findViewById(R.id.description_editText);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);
        titleText.setText(title);
        descText.setText(desc);

        btnSave.setOnClickListener(this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

            private void returnHome() {
                Intent home_intent = new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home_intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save) {
            String newTitle = titleText.getText().toString();
            String newDesc = descText.getText().toString();
            dbManager.update(_id, newTitle, newDesc);
            this.returnHome();
        } else if (v.getId() == R.id.btn_delete) {
            dbManager.delete(_id);
            this.returnHome();
        }
    }


    private void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}