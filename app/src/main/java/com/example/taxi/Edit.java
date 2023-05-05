package com.example.taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    EditText editCarId, editDistance, editPrice, editPromotion;
    Button btnEdit, btnCancel;
    Taxi_01 x = new Taxi_01();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editCarId = findViewById(R.id.editCarId);
        editDistance = findViewById(R.id.editDistance);
        editPrice = findViewById(R.id.editPrice);
        editPromotion = findViewById(R.id.editPromotion);

        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            x = (Taxi_01) bundle.getSerializable("edit");
            editCarId.setText(x.getCarId());
            editDistance.setText(String.valueOf(x.getDistance()));
            editPrice.setText(String.valueOf(x.getUnitPrice()));
            editPromotion.setText(String.valueOf(x.getPromotion()));
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Edit.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validate = isValidate(editCarId.getText().toString(), editDistance.getText().toString(),
                        editPrice.getText().toString(), editPromotion.getText().toString());
                if(validate.equals("")){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    Taxi_01 item = new Taxi_01(editCarId.getText().toString(), Float.parseFloat(editDistance.getText().toString()),
                            Float.parseFloat(editPrice.getText().toString()), Integer.parseInt(editPromotion.getText().toString()));
                    bundle.putSerializable("addItem", item);
                    intent.putExtras(bundle);
                    setResult(250, intent);
                    finish();
                }
                else {
                    Toast.makeText(Edit.this, validate, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private String isValidate(String soXe,String donGia,String quangDuong, String khuyenMai){
        if(soXe.equals("")) return "Số xe không được để trống";
        if(donGia.equals("")) return "Đơn giá không được để trống";
        if(quangDuong.equals("")) return  "Quãng đường không được để trống";
        if(khuyenMai.equals("")) return "Khuyến mãi không được để trống";
        return "";
    }
}