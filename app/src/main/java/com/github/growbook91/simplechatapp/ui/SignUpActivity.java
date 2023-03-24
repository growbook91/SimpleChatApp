package com.github.growbook91.simplechatapp.ui;

import static com.github.growbook91.simplechatapp.data.UserModel.getUserName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.growbook91.simplechatapp.R;
import com.github.growbook91.simplechatapp.data.UserModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText nameTextInputEditText;
    private TextInputEditText phoneNumberTextInputEditText;
    private TextInputEditText statusTextInputEditText;
    private MaterialButton signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setViewId();

        nameTextInputEditText.setText(getUserName());

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberTextInputEditText.getText().toString();
                String name = nameTextInputEditText.getText().toString();
                String status = statusTextInputEditText.getText().toString();
//                Toast.makeText(SignUpActivity.this, phoneNumber, Toast.LENGTH_SHORT).show();

                // Only
                if(phoneNumber.isEmpty() || name.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Please fill the blanks", Toast.LENGTH_SHORT).show();
                }
                else{
                    UserModel userModel = new UserModel(SignUpActivity.this);
                    userModel.makeNewUser(name, phoneNumber, status);
                }

            }
        });
    }

    private void setViewId() {
        nameTextInputEditText = findViewById(R.id.text_input_edit_text_name);
        phoneNumberTextInputEditText = findViewById(R.id.text_input_edit_text_phone_number);
        statusTextInputEditText = findViewById(R.id.text_input_edit_text_status);
        signUpButton = findViewById(R.id.sign_up_button);
    }
}