package com.example.hotstar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class Pager2Adapter extends RecyclerView.Adapter<Pager2Adapter.MyViewHolder> {

    List<Integer> list;
    ViewPager2 pager2;

    public Pager2Adapter(List<Integer> list, ViewPager2 pager2) {
        this.list = list;
        this.pager2 = pager2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pager2_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position));  //Image View


        //Text View like this(1/5)
        int currentPage = position % 5;
        currentPage++;
        holder.tvCurrentPage.setText((currentPage) + "/5");

        /*
         *If list is about to end
         * Add same list to that list
         * So it behaves like infinite item list
         */
        if (position == list.size() - 2) {
            pager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvCurrentPage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            tvCurrentPage = itemView.findViewById(R.id.tv_current_page);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            list.addAll(list);
            notifyDataSetChanged();
        }
    };
}

