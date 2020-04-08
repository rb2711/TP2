package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private EditText etDolar, etEuro;
private RadioButton rbEuroDolar, rbDolarEuro;
private TextView tvResultado, tvError;
    private MainViewModel mv;
    private Button btConvertir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarVista();

        mv= ViewModelProviders.of(this).get(MainViewModel.class);
        mv.getEuroDolar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                rbEuroDolar.setChecked(aBoolean);
            }
        });
        mv.getDolarEuro().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                rbDolarEuro.setChecked(aBoolean);
            }
        });
        mv.getMostrarRes().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvResultado.setText(s);
            }
        });
        mv.getError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                tvError.setEnabled(aBoolean);
            }

        });

    }

    private void iniciarVista(){
        etDolar=findViewById(R.id.etDolar);
        etEuro=findViewById(R.id.etEuro);
        rbDolarEuro=findViewById(R.id.rbDolarEuro);
        rbEuroDolar=findViewById(R.id.rbEuroDolar);
        tvResultado=findViewById(R.id.tvMostrarRes);
        btConvertir = findViewById(R.id.btConvertir);
        tvError=findViewById(R.id.tvError);
        etDolar.setEnabled(false);
        etEuro.setEnabled(false);

        rbEuroDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mv.cambiarEstadoDolarEuro();
                etDolar.setEnabled(false);
                etDolar.setText("");
                etEuro.setEnabled(true);
                tvResultado.setText("");
            }
        });

        rbDolarEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mv.cambiarEstadoEuroDolar();
                etEuro.setEnabled(false);
                etEuro.setText("");
                etDolar.setEnabled(true);
                tvResultado.setText("");
            }
        });

        btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbEuroDolar.isChecked()) {
                    mv.hacerCambio(etEuro.getText().toString(), "ED");
                }
                else{
                    mv.hacerCambio(etDolar.getText().toString(),"DE");
                }
            }

        });
    }


}
