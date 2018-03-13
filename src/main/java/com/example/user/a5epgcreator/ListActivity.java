package com.example.user.a5epgcreator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import io.objectbox.query.Query;

public class ListActivity extends AppCompatActivity {

    private Button buttonNuovo;
    private ListView listViewPg;

    private PgAdapter pgAdapter;
    private Query<Pg> pgsQuery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list);

        buttonNuovo = findViewById(R.id.buttonInsL);
        buttonNuovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this, InsertActivity.class);
                startActivity(i);
                finish();
            }
        });

        listViewPg = findViewById(R.id.listViewPgL);
        listViewPg.setOnItemClickListener(pgClickListener);

        pgAdapter = new PgAdapter();
        listViewPg.setAdapter(pgAdapter);

        pgsQuery = App.pgBox.query().order(Pg_.nome).build();

        registerForContextMenu(listViewPg);

        updateList();
    }

    public void updateList() {
        List<Pg> pgs = pgsQuery.find();
        pgAdapter.setPgList(pgs);
    }

    AdapterView.OnItemClickListener pgClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(ListActivity.this, DetailActivity.class);
            Pg pg = (Pg) pgAdapter.getItem(position);
            i.putExtra("pg", pg.getId());

            startActivity(i);
            finish();
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listViewPgL) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.list_activity_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Pg pg = (Pg) pgAdapter.getItem(((AdapterView.AdapterContextMenuInfo)info).position);

        switch (item.getItemId()) {
            case R.id.elimina :
                pg.razza.getTarget().pgs.removeById(pg.id);
                pg.classe.getTarget().pgs.removeById(pg.id);

                App.pgBox.remove(pg);
                updateList();

                break;

            case R.id.modifica :
                Intent i = new Intent(ListActivity.this, ModifyActivity.class);
                i.putExtra("pg", pg.getId());

                startActivity(i);
                finish();

                break;
        }
        return true;
    }
}