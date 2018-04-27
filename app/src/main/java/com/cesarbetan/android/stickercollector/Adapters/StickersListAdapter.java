package com.cesarbetan.android.stickercollector.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cesarbetan.android.stickercollector.R;
import com.cesarbetan.android.stickercollector.pojo.Sticker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Cesar on 26/04/18.
 */

public class StickersListAdapter extends RecyclerView.Adapter<StickersListAdapter.ViewHolder> {
    public List<Sticker> stickerList;
    public Context context;
    private int sticker;
    public String category_selected;
    private FirebaseFirestore mFirestore;
    private DocumentReference docRef;
    private DocumentReference docRef2;

    public StickersListAdapter(Context context, List<Sticker> stickerList, String category_selected){
        this.stickerList = stickerList;
        this.context = context;
        this.category_selected = category_selected;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticker_item, parent, false);
        mFirestore = FirebaseFirestore.getInstance();
        docRef = mFirestore.collection("Categories").document("jFhcYOqYpQBbyeLM5Wmi");
        docRef2 = mFirestore.collection("Categories").document("rV7XosFKhaxZkc5aP7NJ");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String sticker_id = stickerList.get(position).categoryId;
        sticker = Integer.parseInt(sticker_id);

        if(category_selected.equals("rV7XosFKhaxZkc5aP7NJ")){
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "Category ID: " + sticker_id, Toast.LENGTH_SHORT).show();
                    Map<String, Object> stick = new HashMap<>();
                    stick.put("number", sticker_id);
                    stick.put("owned", "true");
                    stick.put("quantity", "1");

                    docRef.collection("stickers").document(sticker_id)
                            .set(stick)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    toastSuccessAdd();
                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                    docRef.collection("stickers")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        int count = 0;
                                                        for (DocumentSnapshot document : task.getResult()) {
                                                            count++;
                                                        }
                                                        int faltan = 682-count;
                                                        String countS = count + "";
                                                        String countF = faltan + "";
                                                        DocumentReference todasRef = mFirestore.collection("Categories").document("jFhcYOqYpQBbyeLM5Wmi");
                                                        DocumentReference faltanRef = mFirestore.collection("Categories").document("rV7XosFKhaxZkc5aP7NJ");
                                                        faltanRef.update("owned", countF)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Log.w(TAG, "Error updating document", e);
                                                                    }
                                                                });
                                                        todasRef.update("owned", countS)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {
                                                                        Log.w(TAG, "Error updating document", e);
                                                                    }
                                                                });
                                                        Log.d(TAG, "Total: "+ count);
                                                    } else {
                                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                                    }
                                                }
                                            });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error writing document", e);
                                }
                            });

                    docRef2.collection("stickers").document(sticker_id)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                    //toastSuccessDelete();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "Error deleting document", e);
                                }
                            });
                }
            });
        }
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
    public void toastSuccessAdd(){
        Toast.makeText(context, "Se agregó el sticker satisfactoriamente", Toast.LENGTH_LONG).show();
    }
}
