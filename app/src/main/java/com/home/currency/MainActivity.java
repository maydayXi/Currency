package com.home.currency;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtNTD;
    private TextView txtUS, txtJP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        edtNTD = findViewById(R.id.edtNTD);
        txtJP = findViewById(R.id.txtJP);
        txtUS = findViewById(R.id.txtUS);
    }

    public void exchangeDollar(View view) {
        String ntdString = edtNTD.getText().toString();

        if (ntdString.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.problem))
                    .setMessage(getString(R.string.please_enter_ntd))
                    .setPositiveButton(getString(R.string.ok), null)
                    .show();
        } else {
            double ntdValue = Double.parseDouble(ntdString);

            double USRATE = 30.9;
            double JPRATE = 0.27;

            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.result))
                    .setMessage(getString(R.string.jpy_is) + ntdValue/ JPRATE
                            + "\n" + getString(R.string.usd_is) + ntdValue/ USRATE)
                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edtNTD.setText("");
                        }
                    }).show();

            txtUS.setText(String.format(Locale.getDefault(),
                    "%.2f$",ntdValue/USRATE));

            txtJP.setText(String.format(Locale.getDefault(),
                    "%.2f¥", ntdValue/JPRATE));
        }
    }
}
