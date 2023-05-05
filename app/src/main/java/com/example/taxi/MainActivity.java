package com.example.taxi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle;
    SearchView searchView;
    ImageView imgAdd;
    ListView lstContact;
    ArrayList<Taxi_01> arrayList;
    Adapter_01 adapter_01;
    int id;

    SqliteDB_09 mysql = new SqliteDB_09(this, "Taxi_01", null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.textView);
        searchView = findViewById(R.id.searchView);
        lstContact = findViewById(R.id.lstContact);
        imgAdd = findViewById(R.id.imgAdd);


//        mysql.addContact(new Taxi_01("29D2-283.34", 14.3f, 100.100f, 2));
//        mysql.addContact(new Taxi_01("29M3-857.65", 9.6f, 81.600f, 5));
//        mysql.addContact(new Taxi_01("29T2-648.87", 6.5f, 58.500f, 2));
//        mysql.addContact(new Taxi_01("29T4-746.75", 5f, 42.300f, 1));
//        mysql.addContact(new Taxi_01("30K1-129.84", 5f, 42.300f, 1));
//        mysql.addContact(new Taxi_01("30K1-129.84", 6f, 82.800f, 5));

        arrayList = mysql.getAllContact();
        adapter_01 = new Adapter_01(this, arrayList);
        lstContact.setAdapter(adapter_01);

//        for(Taxi_01 x : arrayList){
//            Toast.makeText(MainActivity.this, x.toString(), Toast.LENGTH_LONG).show();
//        }
        lstContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                id = i;
                registerForContextMenu(view);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuDelete:{
                AlertDialog.Builder checkItem = new AlertDialog.Builder(this);
                checkItem.setMessage("Are you sure want to delete!")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mysql.deleteContact(arrayList.get(id).getId());
                                arrayList.remove(arrayList.get(id));
                                lstContact.setAdapter(adapter_01);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = checkItem.create();
                alert.show();
                break;
            }
            case R.id.mnuEdit:{
                Intent intent = new Intent(MainActivity.this, Edit.class);
                Bundle bundle = new Bundle();

                Taxi_01 x = new Taxi_01(arrayList.get(id).getCarId(), arrayList.get(id).getDistance(),
                        arrayList.get(id).getUnitPrice(), arrayList.get(id).getPromotion());
                bundle.putSerializable("edit", x);
                intent.putExtras(bundle);
                startActivityForResult(intent,150);
                break;
            }

        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        Taxi_01 item = (Taxi_01) bundle.getSerializable("addItem");
        if(requestCode == 150 && resultCode == 250){
            int idToUpdate = arrayList.get(id).getId();
            item.setId(idToUpdate);
            mysql.updateContact(idToUpdate, item);
            arrayList = mysql.getAllContact();
            adapter_01 = new Adapter_01(MainActivity.this, arrayList);
            lstContact.setAdapter(adapter_01);
        }
    }
}