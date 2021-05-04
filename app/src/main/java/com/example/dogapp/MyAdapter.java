package com.example.dogapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<DogBreed> mDogsList;
    private OnItemClickListener mListener;
    private final int SHOW_MENU = 1;
    private final int HIDE_MENU = 2;

    @Override
    public int getItemViewType(int position) {
        if (mDogsList.get(position).isShowMenu()) {
            return SHOW_MENU;
        } else {
            return HIDE_MENU;
        }
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        mListener = onClickListener;
    }

    public Filter getFilter() {
        return null;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, DogBreed dogBreed);
    }

    public MyAdapter(Context context, ArrayList<DogBreed> dogsList) {
        mContext = context;
        mDogsList = dogsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == SHOW_MENU) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_list, parent, false);
            return new MenuViewHolder(v);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
            return new MyViewHolder(v);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        DogBreed currentItem = mDogsList.get(position);

        if (holder instanceof MyViewHolder) {
            String url = currentItem.getUrl();
            String name = currentItem.getName();
            String bred_for = currentItem.getBred_for();

            Picasso.get().load(url).fit()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(((MyViewHolder) holder).iv);
            ((MyViewHolder) holder).tv1.setText(name);
            ((MyViewHolder) holder).tv2.setText(bred_for);

            ((MyViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(holder.itemView, mDogsList.get(position));
                    }
                }
            });
        }
        if(holder instanceof MenuViewHolder) {
            String name;
            String bred_for;
            String breed_group;
            String temperament;
            String origin;
            String life_span;

            name = currentItem.getName();
            ((MenuViewHolder) holder).tv1_1.setText(name);

            if(currentItem.getBred_for()!=null) {
                bred_for = "Bred for: " + currentItem.getBred_for();
                ((MenuViewHolder) holder).tv2_1.setText(bred_for);
            }
            if(currentItem.getBreed_group() != null) {
                breed_group = "Breed group: " + currentItem.getBreed_group();
                ((MenuViewHolder) holder).tv3.setText(breed_group);
            }
            if(currentItem.getTemperament() != null){
                temperament = "Temperament: " + currentItem.getTemperament();
                ((MenuViewHolder) holder).tv4.setText(temperament);
            }
            if(currentItem.getOrigin()!=null){
                origin = "Origin: " + currentItem.getOrigin();
                ((MenuViewHolder) holder).tv5.setText(origin);
            }
            if(currentItem.getLifeSpan()!=null){
                life_span = "Life span: " +currentItem.getLifeSpan();
                ((MenuViewHolder) holder).tv6.setText(life_span);
            }

        }
    }

    @Override
    public int getItemCount() {
        return mDogsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public TextView tv1;
        public TextView tv2;
        public CardView cardView;

        public CardView getCardView() {
            return cardView;
        }

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_view);
            iv = view.findViewById(R.id.image_view);
            tv1 = view.findViewById(R.id.text_view1);
            tv2 = view.findViewById(R.id.text_view2);

        }
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView2;
        public TextView tv1_1, tv2_1, tv3, tv4, tv5, tv6;

        public CardView getCardView2() {
            return cardView2;
        }

        public MenuViewHolder(View view) {
            super(view);
            cardView2 = view.findViewById(R.id.card_view_2);
            tv1_1 = view.findViewById(R.id.name_1);
            tv2_1 = view.findViewById(R.id.bred_for_1);
            tv3 = view.findViewById(R.id.breed_group_1);
            tv4 = view.findViewById(R.id.temperament_1);
            tv5 = view.findViewById(R.id.origin_1);
            tv6 = view.findViewById(R.id.life_span_1);
        }
    }
    public void showMenu(int position) {
        for(int i=0; i<mDogsList.size(); i++){
            mDogsList.get(i).setShowMenu(false);
        }
        mDogsList.get(position).setShowMenu(true);
        notifyDataSetChanged();
    }

    public boolean isMenuShown() {
        for(int i=0; i<mDogsList.size(); i++){
            if(mDogsList.get(i).isShowMenu()){
                return true;
            }
        }
        return false;
    }

    public void closeMenu() {
        for(int i=0; i<mDogsList.size(); i++){
            mDogsList.get(i).setShowMenu(false);
        }
        notifyDataSetChanged();
    }
}

