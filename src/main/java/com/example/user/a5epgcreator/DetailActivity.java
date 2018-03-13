package com.example.user.a5epgcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView nome;
    private TextView razza;
    private TextView classe;
    private TextView featuresRazza;
    private TextView featuresClasse;
    private Button buttonModifica;
    private Button buttonIndietro;

    private Pg pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extra = getIntent().getExtras();

        pg = QueryBuilder.findPg((Long) extra.get("pg"));

        nome = (TextView) findViewById(R.id.textViewShowNomeD);
        nome.setText(pg.getNome());

        razza = (TextView) findViewById(R.id.textViewShowRazzaD);
        razza.setText(pg.razza.getTarget().getNome());

        classe = (TextView) findViewById(R.id.textViewShowClasseD);
        classe.setText(pg.classe.getTarget().getNome());

        featuresRazza = (TextView) findViewById(R.id.textViewShowFeaturesRazzaD);
        String textRazza = "";

        for (Feature feature : pg.razza.getTarget().features) {
            textRazza = textRazza + feature.nome + ": " + feature.descrizione + "\n";
        }

        featuresRazza.setText(textRazza);

        featuresClasse = (TextView) findViewById(R.id.textViewShowFeaturesClasseD);
        String textClasse = "";

        for (Feature feature : pg.classe.getTarget().features) {
            textClasse = textClasse + feature.nome + ": " + feature.descrizione + "\n";
        }

        featuresClasse.setText(textClasse);


        buttonModifica = (Button) findViewById(R.id.buttonModificaD);
        buttonModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailActivity.this, ModifyActivity.class);
                i.putExtra("pg", pg.getId());
                startActivity(i);
                finish();
            }
        });

        buttonIndietro = (Button) findViewById(R.id.buttonIndietroD);
        buttonIndietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });
    }

    public void changeActivity () {
        Intent i = new Intent(DetailActivity.this, ListActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed () {
        changeActivity();
    }
}
