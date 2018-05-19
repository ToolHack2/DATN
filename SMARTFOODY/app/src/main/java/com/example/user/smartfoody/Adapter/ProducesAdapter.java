package com.example.user.smartfoody.Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.smartfoody.Model.Produces;
import com.example.user.smartfoody.R;
import com.example.user.smartfoody.View.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 1/1/2018.
 */

public class ProducesAdapter extends RecyclerView.Adapter<ProducesAdapter.ViewHolder>{

    private List<Produces> listproduce;

    private Context context;
    private OnItemClickListener mlistener;

    //setup event itemClick for RecyclerView
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mlistener = listener;
    }

    // constructor
    public ProducesAdapter(Context context, List<Produces> list)
    {
        this.context = context;
        this.listproduce = list;

    }
    // function oncreateviewholder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ViewHolder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Produces produces = listproduce.get(position);
        String img_url = produces.getImage();
        Picasso.with(context).load(img_url).into(holder.pro_img);
        holder.pro_name.setText(produces.getName());
        holder.pro_val.setText(produces.getPrice());
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return listproduce.size();
    }


    // Class ViewHolder -> setup for all palette in view
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pro_img; // create produce image
        public TextView pro_name, pro_val; // create produce name and value
        public CardView cardView;// declare card view
        public int pcount = 0;




        public ViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);

            pro_img = (ImageView)itemView.findViewById(R.id.imgproduce);
            pro_name = (TextView)itemView.findViewById(R.id.txtname);
            pro_val = (TextView)itemView.findViewById(R.id.txtgia);
            cardView = (CardView)itemView.findViewById(R.id.cardview);


            // event item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null)
                    {
                        if (position != RecyclerView.NO_POSITION)
                        {
                            // change background color for card item when user choose item
                            if (cardView.getCardBackgroundColor().getDefaultColor() == -1)
                            {
                                cardView.setCardBackgroundColor(Color.parseColor("#76FF03"));
                                // grow up count bag
                                pcount++;
                                Pcount(pcount);
                                // get value (price) for item user click -> to sum money in bag produce
                                String name = listproduce.get(position).getName();
                                String val = listproduce.get(position).getPrice().toString();

                                listener.onItemClick(position);
                            }
                            else {
                                cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                            }

                        }
                    }
                }
            });
        }
        public int Pcount(int a)
        {
            return a;
        }

    }


}
