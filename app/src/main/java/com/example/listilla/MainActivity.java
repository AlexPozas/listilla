package com.example.listilla;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;
        public String img;

        public Record(int _intents, String _nom, String _img ) {
            intents = _intents;
            nom = _nom;
            img = _img;
        }
    }
    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // Numeros aleatorios
    Random rnd = new Random();
    int apellido = 0;
    int nombre = 0;
    int id = 0;
    int foto = 0;
    String[] apellidos = new String[7];
    String[] fotos = new String[4];
    String[] nombres = new String[7];

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos los arrays de nombres, de apellidos y fotos
        apellidos[0] = "Fernandez";
        apellidos[1] = "Alvarez";
        apellidos[2] = "Jimenez";
        apellidos[3] = "Sanchez";
        apellidos[4] = "Gonzalez";
        apellidos[5] = "Perez";
        apellidos[6] = "Rodriguez";

        nombres[0] = "Gean";
        nombres[1] = "Geanfranco";
        nombres[2] = "Alex";
        nombres[3] = "Anfra";
        nombres[4] = "Edu";
        nombres[5] = "Roberto";
        nombres[6] = "Ean";

        fotos[0] = "rio";
        fotos[1] = "logo";
        fotos[2] = "tree";
        fotos[3] = "girasoles";

        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add( new Record(33,"Marcos", "rio") );
        records.add( new Record(12,"Alex", "logo") );
        records.add( new Record(42,"Laura", "tree") );

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>( this, R.layout.list_item, records )
        {
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }

                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                if (getItem(pos).img.equals("rio")) {
                    ((ImageView) convertView.findViewById(R.id.fotoPerfil)).setImageResource(R.drawable.rio);
                } else if (getItem(pos).img.equals("logo")) {
                    ((ImageView) convertView.findViewById(R.id.fotoPerfil)).setImageResource(R.drawable.descarga);
                } else if (getItem(pos).img.equals("tree")) {
                    ((ImageView) convertView.findViewById(R.id.fotoPerfil)).setImageResource(R.drawable.tree);
                }
                else if (getItem(pos).img.equals("girasoles")) {
                    ((ImageView) convertView.findViewById(R.id.fotoPerfil)).setImageResource(R.drawable.girasoles);
                }

                return convertView;
            }

        };


        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);


        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<3;i++) {
                    nombre = rnd.nextInt(7);
                    apellido = rnd.nextInt(7);
                    id = rnd.nextInt(100);
                    foto = rnd.nextInt(4);
                    records.add(new Record(id, nombres[nombre] + " " + apellidos[apellido], fotos[foto]));
                }

                adapter.notifyDataSetChanged();
            }
        });
        Button ordenar = findViewById(R.id.button2);
        ordenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sort the records ArrayList based on the 'intents' field in ascending order
                Collections.sort(records, new Comparator<Record>() {
                    @Override
                    public int compare(Record record1, Record record2) {
                        return Integer.compare(record1.intents, record2.intents);
                    }
                });


                adapter.notifyDataSetChanged();
            }
        });
    }
}