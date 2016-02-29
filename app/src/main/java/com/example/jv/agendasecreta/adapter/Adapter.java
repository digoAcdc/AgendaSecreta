package com.example.jv.agendasecreta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jv.agendasecreta.R;
import com.example.jv.agendasecreta.model.Contatos;

import java.util.List;

/**
 * Created by jv on 01/01/16.
 */
public class Adapter extends BaseAdapter {

    private Context context;
    private List<Contatos> contatos;

    public Adapter(Context context, List<Contatos> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        Contatos contato = contatos.get(position);
        return contato.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.layout_contatos, parent, false);
        TextView txtNome = (TextView) v.findViewById(R.id.txtNome);
        TextView txtContato = (TextView) v.findViewById(R.id.txtContato);

        Contatos contato = contatos.get(position);
        txtNome.setText(contato.getNome().toString());
        txtContato.setText(contato.getContato().toString());


        return v;
    }
}
