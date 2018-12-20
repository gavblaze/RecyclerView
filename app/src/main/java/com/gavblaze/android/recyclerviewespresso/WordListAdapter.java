package com.gavblaze.android.recyclerviewespresso;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {
    private LinkedList<String> mLinkedList;
    private OnItemClickListener mOnItemClickListener;


    WordListAdapter(LinkedList<String> list, OnItemClickListener listener) {
        this.mOnItemClickListener = listener;

        this.mLinkedList = list;
    }

    public interface OnItemClickListener {
       void onItemClick(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String word = mLinkedList.get(position);
        viewHolder.mWordTextView.setText(word);
    }

    @Override
    public int getItemCount() {
        return mLinkedList.size();
    }

    public void swapData(LinkedList<String> newList) {
        mLinkedList = newList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mWordTextView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWordTextView = itemView.findViewById(R.id.word_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int positon = getAdapterPosition();
            mOnItemClickListener.onItemClick(positon);

        }
    }
}
