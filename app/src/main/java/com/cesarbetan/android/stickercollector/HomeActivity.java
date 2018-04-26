package com.cesarbetan.android.stickercollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cesarbetan.android.stickercollector.Adapters.CategoriesListAdapter;
import com.cesarbetan.android.stickercollector.pojo.Category;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {

    private static final String TAG = "Firelog";
    private RecyclerView mMainList;
    private FirebaseFirestore mFirestore;

    private CategoriesListAdapter categoriesListAdapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        categoryList = new ArrayList<>();
        categoriesListAdapter = new CategoriesListAdapter(getApplicationContext(), categoryList);

        mMainList = (RecyclerView) findViewById(R.id.mainList);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(categoriesListAdapter);

        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("Categories").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }

                categoryList.clear();
                for(DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){

                        String category_id = doc.getDocument().getId();

                        Category category = doc.getDocument().toObject(Category.class).withId(category_id);
                        categoryList.add(category);

                        categoriesListAdapter.notifyDataSetChanged();
                    }
                    if(doc.getType() == DocumentChange.Type.MODIFIED){

                        String category_id = doc.getDocument().getId();

                        Category category = doc.getDocument().toObject(Category.class).withId(category_id);
                        categoryList.add(category);

                        categoriesListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
    public void addRemoveSticker(View v){
        switch (v.getId()){
            case R.id.addStickerBtn :
                Intent intent = new Intent(HomeActivity.this, AddRemoveStickerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
