package com.abacus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.abacus.model.GameType;
import com.abacus.R;
import com.abacus.interfaceCallBack.ItemListener;

import java.util.ArrayList;

public class GameTypeRecyclerAdapter extends RecyclerView.Adapter<GameTypeRecyclerAdapter.ViewHolder>{

    ArrayList<GameType> mValues;
    Context mContext;
    ItemListener mListener;

    public GameTypeRecyclerAdapter(Context context, ArrayList<GameType> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView textView;
        ImageView imageView;
        int position;

        GameType item;

        ViewHolder(View v) {
            super(v);


            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(item,position);
                    }
                }
            });


        }

        void setData(GameType item, int position) {
            this.item = item;
            this.position=position;
            textView.setText(item.text);
            imageView.setImageResource(item.drawable);

        }

    }

    @Override
    public GameTypeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.gametypeitem, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position),position);

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }


}
