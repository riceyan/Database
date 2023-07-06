package sg.edu.rp.c346.id21017809.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    EditText etTask, etDate;
    ListView lvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnGetTasks = findViewById(R.id.buttonGetTasks);
        tvResults = findViewById(R.id.tvResults);
        etTask = findViewById(R.id.etTask);
        etDate = findViewById(R.id.etDate);
        lvResults = findViewById(R.id.lvResults);

        ArrayList<String> alResults = new ArrayList<String>();

        ArrayAdapter aaResults = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alResults);
        lvResults.setAdapter(aaResults);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2021");

                String task = etTask.getText().toString();
                String date = etDate.getText().toString();

                //alColours.add(colour);
                int pos = Integer.parseInt(etTask.getText().toString());
                alResults.add(pos, task);
                aaResults.notifyDataSetChanged();
            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
            }
        });

    }

}
