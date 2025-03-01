package com.example.grocerease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    EditText search;
    ListView listView;

    dbHelper db = new dbHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addButton = findViewById(R.id.addButton);
        // search = findViewById(R.id.search);
        listView = findViewById(R.id.listView);

        showItems();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listModel longClick = (listModel) adapterView.getItemAtPosition(i);
                db.DeleteOne(longClick.getID());
                Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                showItems();
                return true;
            }
        });

    }

    private void showItems(){
        List<listModel> itemList = db.getAll();
        ArrayAdapter itemArrayAdapter = new ArrayAdapter<listModel>(MainActivity.this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(itemArrayAdapter);
    }

}