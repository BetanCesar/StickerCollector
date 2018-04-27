package com.cesarbetan.android.stickercollector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cesarbetan.android.stickercollector.Adapters.CategoriesListAdapter;
import com.cesarbetan.android.stickercollector.Adapters.StickersListAdapter;
import com.cesarbetan.android.stickercollector.pojo.Sticker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class StickersActivity extends Activity {

    private static final String TAG = "Firelog";
    private RecyclerView mStickersList;
    private FirebaseFirestore mFirestore;
    private StickersListAdapter stickersListAdapter;
    private List<Sticker> stickerList;
    private TextView nombreStickerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickers);

        Intent intent = getIntent();
        final String category_selected = intent.getExtras().getString("id");

        stickerList = new ArrayList<>();
        stickersListAdapter = new StickersListAdapter(getApplicationContext(), stickerList, category_selected);

        mStickersList = (RecyclerView) findViewById(R.id.stickerList);
        mStickersList.setHasFixedSize(true);
        mStickersList.setLayoutManager(new GridLayoutManager(this, 4));
        mStickersList.setAdapter(stickersListAdapter);

        mFirestore = FirebaseFirestore.getInstance();

        DocumentReference docRef = mFirestore.collection("Categories").document(category_selected);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        Sticker sticker = task.getResult().toObject(Sticker.class).withId(category_selected);
                        Log.d(TAG, "DocumentSnapshot data: " + task.getResult().getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        docRef.collection("stickers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }

                for(DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){

                        String sticker_id = doc.getDocument().getId();

                        Sticker sticker = doc.getDocument().toObject(Sticker.class).withId(sticker_id);
                        stickerList.add(sticker);
                        Log.d(TAG, sticker_id);

                        stickersListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
