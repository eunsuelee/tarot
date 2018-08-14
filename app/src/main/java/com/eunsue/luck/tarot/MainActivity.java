package com.eunsue.luck.tarot;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        dataset = new ArrayList<>();
        for(int i=0; i<22; i++){
            dataset.add("#" + i);
        }

        final EditText editText = (EditText)findViewById(R.id.editText);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(this, recyclerView, editText, dataset);
        recyclerView.setAdapter(adapter);

        final ImageView cardView = (ImageView)findViewById(R.id.imageView);
        cardView.setVisibility(View.INVISIBLE);

        final TextView textView = (TextView)findViewById(R.id.textView3);
        textView.setVisibility(View.INVISIBLE);

        editText.setInputType(0);
        editText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                cardView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                editText.setText("");
                editText.setInputType(1);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        editText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        switch(v.getId()){
            case R.id.editText:
                try {
                    setTarotResult(Integer.valueOf(v.getText().toString()));
                    recyclerView.setVisibility(View.INVISIBLE);
                    v.setInputType(0);
                }
                catch(Exception e){
                    Toast.makeText(this, "숫자를 입력해주세요(0~21)", Toast.LENGTH_SHORT).show();
                }
        }
        return false;
    }

    public void setTarotResult(int index){
        if(0 <= index && index <= 21) {
            tarotCard tarot = new tarotCard();

            int imgIndex = tarot.getRandomCardIndex(index);
            String tarotPath = "tarot" + String.valueOf(imgIndex) + ".png";
            ImageView tarotView = (ImageView) findViewById(R.id.imageView);

            this.setImage(tarotView, tarotPath);
            tarotView.setVisibility(View.VISIBLE);

            String result = tarot.getRandomCard(index);

            TextView resultView = (TextView) findViewById(R.id.textView3);
            resultView.setText(result);
            resultView.setVisibility(View.VISIBLE);
        }
        else{
            Toast.makeText(this, "0부터 21까지의 숫자만 입력해주세요.",Toast.LENGTH_SHORT).show();
        }
    }

    public void setImage(ImageView imageView, String path){
        try{
            InputStream is = getAssets().open(path);
            Drawable drawable = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(drawable);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
