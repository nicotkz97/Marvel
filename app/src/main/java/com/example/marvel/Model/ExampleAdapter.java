package com.example.marvel.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context context;
    private ArrayList<ExampleItem> mExampleList;
    private onClickItemListener mListener;

    public interface onClickItemListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(onClickItemListener listener) {

        this.mListener = listener;
    }

    public ExampleAdapter(Context context, ArrayList<ExampleItem> mExampleList) {
        this.context = context;
        this.mExampleList = mExampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String url = currentItem.getUrl();
        String nom = currentItem.getNom();

        holder.mTextView.setText(nom);
        Picasso.with(context).load(url).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextView = itemView.findViewById(R.id.text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}

