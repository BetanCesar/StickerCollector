package com.cesarbetan.android.stickercollector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class AddRemoveStickerActivity extends Activity {

    private static final String TAG = "Firelog";
    private boolean filled;
    private String sticker;
    private int stickerCounter;
    private TextView stickerSelectedText;

    private FirebaseFirestore mFirestore;
    private DocumentReference docRef;
    private DocumentReference docRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove_sticker);
        filled = false;
        sticker = "#";
        stickerCounter = 0;
        stickerSelectedText = (TextView) findViewById(R.id.stickerSelectedText);

        mFirestore = FirebaseFirestore.getInstance();
        docRef = mFirestore.collection("Categories").document("jFhcYOqYpQBbyeLM5Wmi");
        docRef2 = mFirestore.collection("Categories").document("rV7XosFKhaxZkc5aP7NJ");
    }

    public void numberSticker(View v){
        switch (v.getId()){
            case R.id.btn0 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "0";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn1 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "1";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn2 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "2";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn3 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "3";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn4 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "4";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn5 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "5";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn6 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "6";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn7 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "7";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn8 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "8";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
            case R.id.btn9 :
                if(counterIsZero()){
                    sticker = "";
                }
                if(stickerCounter < 3){
                    sticker += "9";
                    stickerCounter ++;
                    stickerSelectedText.setText(sticker);
                }
                break;
        }
    }
    public void restartSticker(View v) {
        sticker = "#";
        stickerCounter = 0;
        stickerSelectedText.setText(sticker);
    }
    public void addSticker(View v){
        int stickerNumber;
        if(!sticker.equals("#")){
            stickerNumber = Integer.parseInt(sticker);
            if(stickerNumber < 682){
                Map<String, Object> stick = new HashMap<>();
                stick.put("number", sticker);
                stick.put("owned", "true");
                stick.put("quantity", "1");

                docRef.collection("stickers").document(sticker)
                        .set(stick)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                toastSuccessAdd();
                                sticker = "#";
                                stickerCounter = 0;
                                stickerSelectedText.setText(sticker);
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

                docRef2.collection("stickers").document(sticker)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                //toastSuccessDelete();
                                sticker = "#";
                                stickerCounter = 0;
                                stickerSelectedText.setText(sticker);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "Error deleting document", e);
                            }
                        });

            }else{
                sticker = "#";
                stickerCounter = 0;
                stickerSelectedText.setText(sticker);
                Toast.makeText(this, "Dude! Los stickers van del 00 al 681", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Ingresa un número", Toast.LENGTH_LONG).show();
        }
    }
    public void removeSticker(View v){
        int stickerNumber;
        if(!sticker.equals("#")){
            stickerNumber = Integer.parseInt(sticker);
            if(stickerNumber < 682){
                Map<String, Object> stick = new HashMap<>();
                stick.put("number", sticker);
                stick.put("owned", "false");
                stick.put("quantity", "0");
                docRef.collection("stickers").document(sticker)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                toastSuccessDelete();
                                //sticker = "#";
                                stickerCounter = 0;
                                stickerSelectedText.setText(sticker);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "Error deleting document", e);
                            }
                        });
                docRef2.collection("stickers").document(sticker)
                        .set(stick)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                                sticker = "#";
                                stickerCounter = 0;
                                stickerSelectedText.setText(sticker);
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

            }else{
                sticker = "#";
                stickerCounter = 0;
                stickerSelectedText.setText(sticker);
                Toast.makeText(this, "Dude! Los stickers van del 0 al 681", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Ingresa un número", Toast.LENGTH_LONG).show();
        }
    }


    public boolean counterIsZero(){
        if(stickerCounter == 0){
            return true;
        }else{
            return false;
        }
    }
    public void toastSuccessAdd(){
        Toast.makeText(this, "Se agregó el sticker: " + sticker, Toast.LENGTH_LONG).show();
    }
    public void toastSuccessDelete(){
        Toast.makeText(this, "Se eliminó el sticker: " + sticker, Toast.LENGTH_LONG).show();
    }
}
