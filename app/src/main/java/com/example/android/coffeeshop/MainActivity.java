package com.example.android.coffeeshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private CheckBox checkBoxWhippedCream, checkBoxChocolate;
    private TextView tvQuantity, tvPrice;
    private Button btnPlus, btnMinus, btnOrder;

    private int quantity, hargaSatuan, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.et_name);
        checkBoxWhippedCream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        checkBoxChocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);
        tvQuantity = (TextView) findViewById(R.id.tv_quantity);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnOrder = (Button) findViewById(R.id.btn_order);

        quantity = 2;
        hargaSatuan = 5000;

        displayQuantity(quantity);

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity--;
                displayQuantity(quantity);
            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                displayQuantity(quantity);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Anda Berhasil memesan dgn Total Rp. " + price,
//                        Toast.LENGTH_SHORT).show();
//            }
                if (etName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Nama Tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(MainActivity.this).setTitle("Konfirmasi")
                            .setMessage("Hallo " + etName.getText().toString() + " Total harga yang harus dibayar Rp. " + price + "\nApakah Anda yakin dengan pesanan anda?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    showSnackbar();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            }


        });
    }

    private void showSnackbar() {
        Snackbar.make(findViewById(android.R.id.content), "Pesanan berhasil ditambahkan!", Snackbar.LENGTH_LONG).show();
    }


    private void displayQuantity(int quantity) {
        tvQuantity.setText(quantity + "");

        displayPrice(quantity);
    }

    private void displayPrice(int quantity) {
        price = quantity * hargaSatuan;

        if (checkBoxWhippedCream.isChecked()) {
            price = price + 2000;
        }

        if (checkBoxChocolate.isChecked()) {
            price = price + 1000;
        }
        tvPrice.setText("Rp. " + price);
    }
}