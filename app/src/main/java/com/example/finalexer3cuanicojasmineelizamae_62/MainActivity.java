package com.example.finalexer3cuanicojasmineelizamae_62;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalexer3cuanicojasmineelizamae_62.TaskAdapter;
import com.example.finalexer3cuanicojasmineelizamae_62.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskAdapter adapter;
    private List<Task> taskList;

    // Handle the result from AddTaskActivity
    private final ActivityResultLauncher<Intent> addTaskLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Task newTask = (Task) result.getData().getSerializableExtra("NEW_TASK");
                    if (newTask != null) {
                        adapter.addTask(newTask);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();
        taskList.add(new Task("Buy Groceries", "Milk, Bread, and Eggs"));
        taskList.add(new Task("Exercise", "Go for a 30-minute run"));
        taskList.add(new Task("Read a Book", "Read 2 chapters of the new novel"));
        setupRecyclerView();

        FloatingActionButton fabAdd = findViewById(R.id.fabAddEvent);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            addTaskLauncher.launch(intent);
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);
    }
}