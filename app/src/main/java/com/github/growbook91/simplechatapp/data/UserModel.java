package com.github.growbook91.simplechatapp.data;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.github.growbook91.simplechatapp.ui.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class UserModel {
    private GoogleSignInClient mGoogleSignInClient;
    private Context signInActivityContext;
    public static GoogleSignInAccount account;

    public UserModel(Context signInActivityContext) {
        this.signInActivityContext = signInActivityContext;
    }

    public void setGoogleSignInClient(Activity activity) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        this.mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
    }


    public Intent getGoogleSignInIntent(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        return signInIntent;
    }

    public GoogleSignInAccount checkUserAccount(){
        account = GoogleSignIn.getLastSignedInAccount(signInActivityContext);
        return account;
    }
}
