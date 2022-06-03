package sg.edu.rp.c346.id21025432.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText taskInput;
    Button add,del,clear;
    Spinner spn;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        add = findViewById(R.id.add);
        del = findViewById(R.id.delete);
        clear = findViewById(R.id.clear);
        spn = findViewById(R.id.spinner);
        lv = findViewById(R.id.lv);

        ArrayList<String> tasks;
        tasks = new ArrayList<String>();

        ArrayAdapter aaTask = new ArrayAdapter(this,android.R.layout.simple_list_item_1,tasks);
        lv.setAdapter(aaTask);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        add.setEnabled(true);
                        del.setEnabled(false);
                        taskInput.setText("");
                        break;
                    case 1:
                        add.setEnabled(false);
                        del.setEnabled(true);
                        taskInput.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTask = taskInput.getText().toString();
                tasks.add(newTask);
                aaTask.notifyDataSetChanged();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deletedTask = taskInput.getText().toString();
                    int deleteIndex = Integer.parseInt(deletedTask);
                    if (tasks.isEmpty()){
                        Toast.makeText(MainActivity.this, "No tasks to remove!", Toast.LENGTH_SHORT).show();
                    } else if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        tasks.remove(deleteIndex);
                        aaTask.notifyDataSetChanged();
                    }
                }

        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.clear();
                aaTask.notifyDataSetChanged();
            }
        });

    }
}