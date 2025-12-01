package com.example.nossotcc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nossotcc.R;
import com.example.nossotcc.adapter.PlanilhaAdapter;
import com.example.nossotcc.controller.GastoController;
import com.example.nossotcc.model.Gasto;

public class Planilha extends AppCompatActivity {

    private EditText edtDescricao, edtValor, edtData;
    private Spinner spnCategoria, spnFormaPagamento;
    private Button btnAdicionar, btnIrHome;
    private RecyclerView recyclerView;
    private GastoController gastoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planilha);

        gastoController = new GastoController(this);


        edtDescricao = findViewById(R.id.edtNome);
        edtValor = findViewById(R.id.edtValor);
        edtData = findViewById(R.id.edtData);


        spnCategoria = findViewById(R.id.spnCategoria);
        spnFormaPagamento = findViewById(R.id.spnFormaPagamento);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnIrHome = findViewById(R.id.btnIrHome);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ArrayAdapter<CharSequence> adapterCat = ArrayAdapter.createFromResource(
                this,
                R.array.categorias_array,
                android.R.layout.simple_spinner_item
        );
        adapterCat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapterCat);


        ArrayAdapter<CharSequence> adapterPag = ArrayAdapter.createFromResource(
                this,
                R.array.formas_pagamento_array,
                android.R.layout.simple_spinner_item
        );
        adapterPag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFormaPagamento.setAdapter(adapterPag);


        btnAdicionar.setOnClickListener(v -> {


            if (edtValor.getText().toString().trim().isEmpty()) {
                edtValor.setError("Digite um valor");
                return;
            }

            Gasto g = new Gasto();
            g.setDescricao(edtDescricao.getText().toString());
            g.setValor(Double.parseDouble(edtValor.getText().toString()));
            g.setCategoria(spnCategoria.getSelectedItem().toString());
            g.setData(edtData.getText().toString());
            g.setFormaPagamento(spnFormaPagamento.getSelectedItem().toString());
            g.setRecorrente(false);
            g.setTag(null);

            gastoController.salvar(g);
            carregarLista();
        });

        btnIrHome.setOnClickListener(v -> {
            startActivity(new Intent(Planilha.this, Home.class));
            finish();
        });

        carregarLista();
    }

    private void carregarLista() {
        recyclerView.setAdapter(new PlanilhaAdapter(gastoController.listar()));
    }
}
