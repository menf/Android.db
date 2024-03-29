package com.github.menf.androiddb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvProducts;
    TextView pole;
    ImageView podglad;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database db = new Database(this);
        for (Item i : db.getAllItems()) {
            db.deleteItem(i);
        }
        db.addItem(new Item(R.string.brazowa_opis, R.string.brazowa, R.drawable.brazowa));
        db.addItem(new Item(R.string.czerwona_opis, R.string.czerwona, R.drawable.czerwona));
        db.addItem(new Item(R.string.niebieska_opis, R.string.niebieska, R.drawable.niebieska));
        db.addItem(new Item(R.string.zielona_opis, R.string.zielona, R.drawable.zielona));
        db.addItem(new Item(R.string.zolta_opis, R.string.zolta, R.drawable.zolta));
        //
        next = (Button) findViewById(R.id.btnnext);
        podglad = (ImageView) findViewById(R.id.imageView);
        lvProducts = (ListView) findViewById(R.id.list_products);
        pole = (TextView) findViewById(R.id.textView4);
        addListenerOnButton();
        lvProducts.setAdapter(new Adapter(this, db.getAllItems()));
        lvProducts.setClickable(true);
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item product = (Item) lvProducts.getItemAtPosition(position);
                // Toast.makeText(getApplicationContext(),product.getDesc(),Toast.LENGTH_LONG).show();
                pole.setText(product.get_description());
                podglad.setImageResource(product.get_image());
            }
        });
    }

    public void addListenerOnButton() {

        final Context context = this;


        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Main2Activity.class);
                startActivity(intent);

            }

        });

    }
}