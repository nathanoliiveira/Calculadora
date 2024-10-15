package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

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

        Toast.makeText(this, "Boa noite", Toast.LENGTH_LONG).show();
    }

    public void numero(View v){
        Button btn = findViewById(v.getId());

        TextView visor = (TextView) findViewById(R.id.txtVisor);

        if (visor.getText().toString().trim().equals("(0")) {
            if(visor.getText().toString().equals("(0") && !btn.getText().toString().equals(".")) {
                visor.setText("(" + btn.getText().toString());
            } else{
                visor.setText("(0" + btn.getText().toString());
            }
        } else {
        if(visor.getText().toString().equals("0") && !btn.getText().toString().equals(".")) {
            visor.setText(btn.getText().toString());
        } else{
            visor.setText(visor.getText().toString() + btn.getText().toString());
        }
        }
    }

    public void operacao(View v){
        Button op = findViewById(v.getId());

        TextView visor = (TextView) findViewById(R.id.txtVisor);

        visor.setText(visor.getText().toString() + op.getText().toString());
    }
    public void apagar (View v){
        Button deletar = findViewById(v.getId());

        TextView visor = (TextView) findViewById(R.id.txtVisor);

        if (visor.getText().toString().length() == 1) {
            visor.setText("0");
        } else {
            visor.setText(visor.getText().toString().substring(0, visor.getText().toString().length() - 1));
        }
    }
    public void resetar (View v){
        Button voltar = findViewById(v.getId());

        TextView visor = (TextView) findViewById(R.id.txtVisor);

        visor.setText("0");
    }
    public void parenteses (View v){

        TextView visor = (TextView) findViewById(R.id.txtVisor);

        int aberto = visor.getText().toString().length() - visor.getText().toString().replace("(", "").length();
        int fechado = visor.getText().toString().length() - visor.getText().toString().replace(")", "").length();

        if (aberto <= fechado) {
            if(visor.getText().toString().equals("0")){
                visor.setText("(" + visor.getText().toString());
            } else {
            visor.setText(visor.getText().toString() + "(");
            }
        }
        else if (aberto > fechado) {
            visor.setText(visor.getText().toString() + ")");
        }


    }

    public void solve(View v){
        TextView visor = (TextView) findViewById(R.id.txtVisor);

        try {
            String equacao = visor.getText().toString();
            Expression e = new ExpressionBuilder(equacao).build();
            String resultado = String.valueOf(e.evaluate());
            visor.setText(resultado);
        } catch (Exception e){
            visor.setText("Erro");
        }
    }
}