package com.example.finalexer3cuanicojasmineelizamae_62;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalexer3cuanicojasmineelizamae_62.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTitle = findViewById(R.id.etEventTitle);
        etDescription = findViewById(R.id.etEventDescription);
        Button btnSave = findViewById(R.id.btnSaveEvent);

        btnSave.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Task newTask = new Task(title, description);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("NEW_TASK", newTask);
        setResult(RESULT_OK, resultIntent);
        finish(); // Closes activity and returns to MainActivity
    }
}