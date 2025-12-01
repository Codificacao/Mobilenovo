package com.example.nossotcc.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nossotcc.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    // Este método é chamado quando um item do menu é selecionado
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Verifica se o item selecionado é o nosso botão de "Sair"
        if (item.getItemId() == R.id.action_logout) {
            // Lógica para deslogar o usuário
            fazerLogout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fazerLogout() {

        Intent intent = new Intent(this, Pagina01.class);


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }
}

