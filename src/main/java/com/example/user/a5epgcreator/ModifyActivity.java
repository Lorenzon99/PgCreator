package com.example.user.a5epgcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class ModifyActivity extends AppCompatActivity {

    private Spinner spinnerRazze;
    private Spinner spinnerClassi;
    private Button buttonModifica;
    private Button buttonAnnulla;
    private EditText editTextNome;

    Pg pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        spinnerRazze = (Spinner) findViewById(R.id.spinnerRazzeM);
        ArrayAdapter<CharSequence> adapterRazze = ArrayAdapter.createFromResource(this,R.array.Razze, android.R.layout.simple_spinner_item);
        adapterRazze.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRazze.setAdapter(adapterRazze);

        spinnerClassi = (Spinner) findViewById(R.id.spinnerClassiM);
        ArrayAdapter<CharSequence> adapterClassi = ArrayAdapter.createFromResource(this,R.array.Classi, android.R.layout.simple_spinner_item);
        adapterClassi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassi.setAdapter(adapterClassi);

        editTextNome = (EditText) findViewById(R.id.editTextNomeM);

        Bundle extra = getIntent().getExtras();

        List<Pg> list = App.pgBox.query().equal(Pg_.id, (Long) extra.get("pg")).build().find();

        pg = list.get(0);

        editTextNome.setText(pg.getNome());
        spinnerRazze.setSelection(adapterRazze.getPosition(pg.razza.getTarget().getNome()));
        spinnerClassi.setSelection(adapterClassi.getPosition(pg.classe.getTarget().getNome()));


        buttonModifica = (Button) findViewById(R.id.buttonModificaM);
        buttonModifica.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pg.setNome(editTextNome.getText().toString());

                        if(!spinnerRazze.getSelectedItem().toString().equals(pg.razza.getTarget().getNome())) {
                            pg.razza.getTarget().pgs.removeById(pg.id);
                            pg.razza.setTarget(QueryBuilder.findRazza(spinnerRazze.getSelectedItem().toString()));
                            pg.razza.getTarget().pgs.add(pg);
                        }

                        if(!spinnerClassi.getSelectedItem().toString().equals(pg.classe.getTarget().getNome())) {
                            pg.classe.getTarget().pgs.removeById(pg.id);
                            pg.classe.setTarget(QueryBuilder.findClasse(spinnerClassi.getSelectedItem().toString()));
                            pg.classe.getTarget().pgs.add(pg);
                        }

                        App.pgBox.put(pg);

                        changeActivity();
                    }
                }
        );

        buttonAnnulla = (Button) findViewById(R.id.buttonAnnullaM);
        buttonAnnulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });

    }

    public void changeActivity () {
        Intent i = new Intent(ModifyActivity.this, DetailActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onBackPressed () {
        changeActivity();
    }
}
