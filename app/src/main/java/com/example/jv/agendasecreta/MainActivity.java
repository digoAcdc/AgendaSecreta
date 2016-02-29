package com.example.jv.agendasecreta;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.jv.agendasecreta.adapter.Adapter;
import com.example.jv.agendasecreta.dao.ContatoDb;
import com.example.jv.agendasecreta.model.Contatos;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvContatos;
    private List<Contatos> contatos;
    private ContatoDb banco = new ContatoDb(this);
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_contatos);

        String email = recuperaEmail();
        AtulizarLista(email);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Agenda");
        setSupportActionBar(toolbar);


        lvContatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ExcluirContato(id);
                return true;
            }
        });

    }

    public void AtulizarLista(String email){
        lvContatos = (ListView) findViewById(R.id.lvcontatos);
        contatos = banco.listarContatos(email);
        lvContatos.setAdapter(new Adapter(this, contatos));
        lvContatos.setOnItemClickListener(this);
    }

    public void ExcluirContato(final long id){


        DialogInterface.OnClickListener dialiog = new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = recuperaEmail();
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Contatos contato = new Contatos();
                        contato.setId(((int) id));
                        banco.excluir(contato);
                        Toast.makeText(MainActivity.this, "Contato excluido com sucesso! ", Toast.LENGTH_SHORT).show();
                        AtulizarLista(email);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        AtulizarLista(email);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Você realmente deseja excluir este contato?");
        builder.setPositiveButton("Sim", dialiog);
        builder.setNegativeButton("Não", dialiog);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_serach);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(onSearch());


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle = getIntent().getExtras();
        switch (item.getItemId()){
            case R.id.btnSair:
                startActivity(new Intent(MainActivity.this, login_face.class));
                finish();
                break;
            case R.id.btnAdicionar:
                Intent toCadastrarContato = new Intent(this, CadastrarContato.class).putExtras(bundle);
                startActivity(toCadastrarContato);
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Contatos contato = contatos.get(position);
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("Email");
        Intent toEditar = new Intent(this, EditarContato.class);
        toEditar.putExtra("nome", contato.getNome().toString());
        toEditar.putExtra("contato", contato.getContato().toString());
        toEditar.putExtra("Id", contato.getId());
        toEditar.putExtra("Email", email);
        startActivity(toEditar);
       // Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();
    }

    private SearchView.OnQueryTextListener onSearch(){
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String email = recuperaEmail();

                PesquisaAvancada(newText, email);

                return false;
            }
        };
    }

    public void PesquisaAvancada(String texto, String email){
        lvContatos = (ListView) findViewById(R.id.lvcontatos);
        contatos = banco.contatosListado(texto, email);
        lvContatos.setAdapter(new Adapter(this, contatos));
        lvContatos.setOnItemClickListener(this);
        if(contatos.size() == 0){
            AtulizarLista(email);
        }
    }


    public String recuperaEmail(){
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("Email");
        return email;
    }
}
