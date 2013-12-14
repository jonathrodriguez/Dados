package com.d20.dados;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Parcelable;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Resultado_rapido extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_rapido);

        Intent invoker = getIntent();
        String dados_s = invoker.getStringExtra(Tirada_rapida.EXTRA_DADOS);

        if ((dados_s != null) && (dados_s.length() > 0)) {
            String[] tirada = dados_s.split(";");
            if (tirada.length > 0) {
                Integer[][] dados = new Integer[tirada.length][2];
                for (int i=0; i < tirada.length; i++) {
                    String[] tirada_split = tirada[i].split("d");
                    dados[i][0] = Integer.parseInt(tirada_split[0]);
                    dados[i][1] = Integer.parseInt(tirada_split[1]);
                }
                realizarTirada(dados);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resultado_rapido, menu);
        return true;
    }


    private void realizarTirada(Integer[][] dados) {
        // get the listview
        ExpandableListView expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        List<String> listDataHeader = new ArrayList<String>();
        HashMap<String, List<Integer>> listDataChild = new HashMap<String, List<Integer>>();

        //

        Random rnd = new Random();

        for (int i = 0; i < dados.length; i++) {
            Integer cuantos = dados[i][0];
            if (cuantos > 0) {
                Integer tipo = dados[i][1];
                String titulo = cuantos.toString().concat("d").concat(tipo.toString());
                listDataHeader.add(titulo);
                List<Integer> child = new ArrayList<Integer>(cuantos);
                for(int c = 0; c < cuantos; c++) {
                    Integer res = rnd.nextInt(tipo) + 1;
                    child.add(res);
                }
                listDataChild.put(titulo,child);
            }
        }

        //


        TiradaRapidaExpandableList listAdapter = new TiradaRapidaExpandableList(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }
}
