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

public class Add extends AppCompatActivity {


    EditText groceryName, groceryPrice, groceryQuan;
    Button AddHere, Back;

    dbHelper db = new dbHelper(Add.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        groceryName = findViewById(R.id.groceryName);
        groceryPrice = findViewById(R.id.groceryPrice);
        groceryQuan = findViewById(R.id.groceryQuan);
        AddHere = findViewById(R.id.AddHere);
        Back = findViewById(R.id.Back);



        Back.setOnClickListener(view -> {
            Intent intent1 = new Intent(Add.this, MainActivity.class);
            startActivity(intent1);
        });

        AddHere.setOnClickListener(view -> {
//            try {
//                listModel listmodel = new listModel(
//                        itemID,
//                        groceryName.getText().toString(),
//                        (int) Float.parseFloat(groceryPrice.getText().toString()),
//                        Integer.parseInt(groceryQuan.getText().toString())
//
//                );
//
//                if (itemID == -1) {
//                    db.AddOne(listmodel);
//                    Toast.makeText(Add.this, "Item Added", Toast.LENGTH_SHORT).show();
//                } else {
//                    db.UpdateOne(listmodel);
//                    Toast.makeText(Add.this, "Item Updated", Toast.LENGTH_SHORT).show();
//                }
//
//                startActivity(new Intent(Add.this, MainActivity.class));
//            } catch (Exception e) {
//                Toast.makeText(Add.this, "Error Saving", Toast.LENGTH_SHORT).show();
//            }

            listModel listmodel;

            try {
                listmodel = new listModel(-1, groceryName.getText().toString(), Integer.parseInt(groceryPrice.getText().toString()),
                        Integer.parseInt(groceryQuan.getText().toString()));
                db.AddOne(listmodel);
                Toast.makeText(Add.this, "Grocery Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);

            } catch (Exception e){
                Toast.makeText(Add.this, "Error Adding", Toast.LENGTH_SHORT).show();
            }
        });



    }
}