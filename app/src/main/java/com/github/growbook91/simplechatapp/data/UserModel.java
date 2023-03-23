package com.github.growbook91.simplechatapp.data;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.growbook91.simplechatapp.ui.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserModel {
    private GoogleSignInClient mGoogleSignInClient;
    private Context signInActivityContext;
    private static final String TAG = "UserModel";

    //getter and setter of account
    public static GoogleSignInAccount getAccount() {
        return account;
    }

    public static void setAccount(GoogleSignInAccount account) {
        UserModel.account = account;
    }




    private static GoogleSignInAccount account;

    //return UserId of account
    public static String getUserId() {
        return account.getId();
    }




    //class Constructor
    public UserModel(Context signInActivityContext) {
        this.signInActivityContext = signInActivityContext;
    }

    //set the login option and googleSignInClient
    public void setGoogleSignInClient(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        this.mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }


    //This method returns signInItent
    public Intent getGoogleSignInIntent() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        return signInIntent;
    }

    //check whether user has been signed in.
    public GoogleSignInAccount checkUserAccount() {
        account = GoogleSignIn.getLastSignedInAccount(signInActivityContext);
        firstLogIn();

        return account;
    }

    public void firstLogIn(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("uid", getUserId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Create new user db", task.getException());

                        }
                    }
                });
    }


}
