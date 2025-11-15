package com.example.nossotcc.view;

// Pacote da sua aplicação

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nossotcc.R;

// Torne a classe abstrata para que ela não possa ser instanciada diretamente
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Este método infla (cria) o menu de opções na AppBar
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
        // Cria uma Intent para voltar para a tela de Login (MainActivity)
        Intent intent = new Intent(this, Pagina01.class);

        // Adiciona flags para limpar o histórico de telas
        // Isso impede que o usuário volte para as telas internas usando o botão "Voltar" do celular
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish(); // Finaliza a atividade atual
    }
}

