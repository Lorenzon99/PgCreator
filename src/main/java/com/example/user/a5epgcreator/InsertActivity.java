package com.example.user.a5epgcreator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class InsertActivity extends AppCompatActivity {

    private Spinner spinnerRazze;
    private Spinner spinnerClassi;
    private Button buttonInserisci;
    private EditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        spinnerRazze = (Spinner) findViewById(R.id.spinnerRazzeI);
        ArrayAdapter<CharSequence> adapterRazze = ArrayAdapter.createFromResource(this,R.array.Razze, android.R.layout.simple_spinner_item);
        adapterRazze.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRazze.setAdapter(adapterRazze);

        spinnerClassi = (Spinner) findViewById(R.id.spinnerClassiI);
        ArrayAdapter<CharSequence> adapterClassi = ArrayAdapter.createFromResource(this,R.array.Classi, android.R.layout.simple_spinner_item);
        adapterClassi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClassi.setAdapter(adapterClassi);

        editTextNome = (EditText) findViewById(R.id.editTextNomeI);

        buttonInserisci = (Button) findViewById(R.id.buttonInserisciI);
        buttonInserisci.setOnClickListener(
                new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       addPg();
                       changeActivity();
                   }
               }
        );
    }

    public void addPg () {
        if (editTextNome.getText().toString() != "") {
            Pg pg = new Pg(0, editTextNome.getText().toString());
            pg.razza.setTarget(QueryBuilder.findRazza(spinnerRazze.getSelectedItem().toString()));
            pg.classe.setTarget(QueryBuilder.findClasse(spinnerClassi.getSelectedItem().toString()));
            App.pgBox.put(pg);

            pg.razza.getTarget().pgs.add(pg);
            pg.classe.getTarget().pgs.add(pg);
        }
    }

    public void changeActivity () {
        Intent i = new Intent(InsertActivity.this, ListActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed () {
        changeActivity();
    }
}
