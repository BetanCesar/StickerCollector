package com.cesarbetan.android.stickercollector.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cesarbetan.android.stickercollector.R;
import com.cesarbetan.android.stickercollector.StickersActivity;
import com.cesarbetan.android.stickercollector.pojo.Category;
import com.cesarbetan.android.stickercollector.pojo.Sticker;

import java.util.List;

/**
 * Created by Cesar on 26/04/18.
 */

public class StickersListAdapter extends RecyclerView.Adapter<StickersListAdapter.ViewHolder> {
    public List<Sticker> stickerList;
    public Context context;
    public StickersListAdapter(Context context, List<Sticker> stickerList){
        this.stickerList = stickerList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticker_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String sticker_id = stickerList.get(position).categoryId;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Category ID: " + sticker_id, Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(context, StickersActivity.class);
                intent.putExtra("id", sticker_id);
                context.startActivity(intent);*/
            }
        });
        holder.stickerNumberText.setText(stickerList.get(position).getNumber());
        holder.marcoStickerImage.setImageResource(R.drawable.marco);
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public TextView stickerNumberText;
        public ImageView marcoStickerImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            stickerNumberText = (TextView) mView.findViewById(R.id.stickerNumberText);
            marcoStickerImage = (ImageView) mView.findViewById(R.id.marcoStickerImage);
        }
    }
}
