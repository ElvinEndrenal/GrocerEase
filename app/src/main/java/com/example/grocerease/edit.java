package com.example.grocerease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class edit extends AppCompatActivity {


    EditText editName, editPrice, editQuan;
    Button ChangeHere, BackHere;
    dbHelper db = new dbHelper(edit.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);
        editQuan = findViewById(R.id.editQuan);
        ChangeHere = findViewById(R.id.ChangeHere);
        BackHere = findViewById(R.id.BackHere);

        Intent intent = getIntent();
        int itemID = intent.getIntExtra("ID", -1);
        String name = intent.getStringExtra("Name");
        String price = intent.getStringExtra("Price");
        String quantity = intent.getStringExtra("Quantity");

        editName.setText(name);
        editPrice.setText(price);
        editQuan.setText(quantity);

        ChangeHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    listModel listmodel = new listModel(
                            itemID,
                            editName.getText().toString(),
                            (int) Float.parseFloat(editPrice.getText().toString()),
                            Integer.parseInt(editQuan.getText().toString())

                    );

                    if (itemID == -1) {
                        db.AddOne(listmodel);
                        Toast.makeText(edit.this, "Item Added", Toast.LENGTH_SHORT).show();
                    } else {
                        db.UpdateOne(listmodel);
                        Toast.makeText(edit.this, "Item Updated", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(edit.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(edit.this, "Error Saving", Toast.LENGTH_SHORT).show();
                }
            }
        });


        BackHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edit.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}