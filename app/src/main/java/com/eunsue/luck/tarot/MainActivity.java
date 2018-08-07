package com.eunsue.luck.tarot;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        EditText editText = (EditText)findViewById(R.id.editText);
        editText.setOnEditorActionListener(this);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        switch(v.getId()){
            case R.id.editText:
                setTarotResult(Integer.valueOf(v.getText().toString()));
        }
        return false;
    }

    public void setTarotResult(int index){
        tarotCard tarot = new tarotCard();

        int imgIndex = tarot.getRandomCardIndex(index);
        String imagePath = "tarot" + String.valueOf(imgIndex) + ".png";
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        try{
            InputStream is = getAssets().open(imagePath);
            Drawable drawable = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(drawable);
            imageView.setVisibility(View.VISIBLE);
        }catch(IOException e){
            e.printStackTrace();
        }

        String result = tarot.getRandomCard(index);

        TextView resultView = (TextView)findViewById(R.id.textView3);
        resultView.setText(result);

    }
}
