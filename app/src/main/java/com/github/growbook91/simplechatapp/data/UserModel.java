package com.github.growbook91.simplechatapp.data;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.github.growbook91.simplechatapp.ui.MainActivity;
import com.github.growbook91.simplechatapp.ui.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserModel {
    private GoogleSignInClient mGoogleSignInClient;
    private Context activityContext;
    private static final String TAG = "UserModel";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static GoogleSignInAccount account;
    public static MutableLiveData<String> name = new MutableLiveData<String>();
    public static MutableLiveData<String> phoneNumber = new MutableLiveData<String>();
    public static MutableLiveData<String> status = new MutableLiveData<String>();

    //getter and setter of account
    public static GoogleSignInAccount getAccount() {
        return account;
    }

    public static void setAccount(GoogleSignInAccount account) {
        UserModel.account = account;
    }

    //return UserId of account
    public static String getUserId() {
        return account.getId();
    }

    public static String getUserName() {
        return account.getDisplayName();
    }


    //class Constructor
    public UserModel(Context activityContext) {
        this.activityContext = activityContext;
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
        account = GoogleSignIn.getLastSignedInAccount(activityContext);
        if (account != null)
            doesUserExist();

        return account;
    }

    public void doesUserExist() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        boolean userExist = false;
        db.collection("user")
                .document(getUserId())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot document) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        //TODO: load account information.
                        name.setValue(document.getData().get("name").toString());
                        status.setValue(document.getData().get("status").toString());
                        phoneNumber.setValue(document.getData().get("phoneNumber").toString());
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Create new user db", e);
                        //Add user doc
                        //user info is needed.
                        Intent intent = new Intent(activityContext, SignUpActivity.class);
                        activityContext.startActivity(intent);
                    }
                });
    }

    public void makeNewUser(String name, String phoneNumber, String status) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("status", status);
        user.put("phoneNumber", phoneNumber);

        db.collection("user").document(getUserId())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        Intent intent = new Intent(activityContext, MainActivity.class);
                        activityContext.startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }


}
