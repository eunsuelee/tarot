package com.eunsue.luck.tarot;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private ArrayList<String> dataSet;
    private Context mainCtx;
    private RecyclerView recycleCtx;
    private EditText editText;

    public MainAdapter(Context mainCtx, RecyclerView recycleCtx, EditText editText, ArrayList<String> dataSet){
        this.mainCtx = mainCtx;
        this.recycleCtx = recycleCtx;
        this.editText = editText;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, final int position) {
        holder.mTitle.setText(dataSet.get(position));
        holder.tarot_back.setImageDrawable(getImage("back.jpg"));
        holder.tarot_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editText.setText(String.valueOf(position));
                editText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                recycleCtx.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView mTitle;
        public ImageView tarot_back;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.title);
            tarot_back = (ImageView)itemView.findViewById(R.id.back);
        }
    }

    public Drawable getImage(String path){
        Drawable drawable = null;
        try{
            InputStream is = mainCtx.getAssets().open(path);
            drawable = Drawable.createFromStream(is, null);
        }catch (IOException e){
            e.printStackTrace();
        }

        return drawable;
    }
}
