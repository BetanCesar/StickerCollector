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
import com.cesarbetan.android.stickercollector.pojo.Category;

import java.util.List;

/**
 * Created by Cesar on 23/04/18.
 */

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.ViewHolder> {

    public List<Category> categoryList;
    public Context context;

    public CategoriesListAdapter(Context context, List<Category> categoryList){
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameText.setText(categoryList.get(position).getName());
        holder.quantityText.setText(categoryList.get(position).getOwned() + "/" + categoryList.get(position).getTotal());

        final String category_id = categoryList.get(position).categoryId;

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Category ID: " + category_id, Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(context,DispositivosActivity.class);
                intent.putExtra("id", material_id);
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public TextView nameText, quantityText;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            nameText = (TextView) mView.findViewById(R.id.name_text);
            quantityText = (TextView) mView.findViewById(R.id.quantity_text);
        }
    }
}
