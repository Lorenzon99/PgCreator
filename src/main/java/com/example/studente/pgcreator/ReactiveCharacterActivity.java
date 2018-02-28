package com.example.studente.pgcreator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscriptionList;

public class ReactiveCharacterActivity extends Activity{

    private EditText editTextNome;
    private EditText editTextRazza;
    private EditText editTextClasse;
    private Button buttonIns;
    private Box<Character> charactersBox;
    private Query<Character> charactersQuery;
    private CharactersAdapter charactersAdapter;
    private DataSubscriptionList subscriptions = new DataSubscriptionList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpViews();

        charactersBox = ((App) getApplication()).getBoxStore().boxFor(Character.class);

        // query all notes, sorted a-z by their text (http://greenrobot.org/objectbox/documentation/queries/)
        charactersQuery = charactersBox.query().order(Character_.nome ).build();

        // Reactive query (http://greenrobot.org/objectbox/documentation/data-observers-reactive-extensions/)
        charactersQuery.subscribe(subscriptions).on(AndroidScheduler.mainThread())
                .observer(new DataObserver<List<Character>>() {
                    @Override
                    public void onData(List<Character> characters) {
                        charactersAdapter.setCharacters(characters);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        subscriptions.cancel();
        super.onDestroy();
    }

    protected void setUpViews() {
        ListView listView = (ListView) findViewById(R.id.listViewCharacters);
        listView.setOnItemClickListener(characterClickListener);

        charactersAdapter = new CharactersAdapter();
        listView.setAdapter(charactersAdapter);

        buttonIns = (Button) findViewById(R.id.buttonIns);
        //addNoteButton.setEnabled(false);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextRazza = (EditText) findViewById(R.id.editTextRazza);
        editTextClasse = (EditText) findViewById(R.id.editTextClasse);

        /*
        editText = (EditText) findViewById(R.id.editTextNote);
        editText.setOnEditorActionListener(new OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNote();
                    return true;
                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enable = s.length() != 0;
                addNoteButton.setEnabled(enable);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        */
    }

    public void onAddButtonClick(View view) {
        addCharacter();
    }

    private void addCharacter() {
        String nome = editTextNome.getText().toString();
        String razza = editTextRazza.getText().toString();
        String classe = editTextClasse.getText().toString();
        editTextNome.setText("");
        editTextRazza.setText("");
        editTextClasse.setText("");

        Character character = new Character(0, nome, razza, classe);
        charactersBox.put(character);
    }

    AdapterView.OnItemClickListener characterClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Character character = charactersAdapter.getItem(position);
            charactersBox.remove(character);
        }
    };
}
