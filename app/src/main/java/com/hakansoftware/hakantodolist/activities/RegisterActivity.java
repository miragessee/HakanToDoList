package com.hakansoftware.hakantodolist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hakansoftware.hakantodolist.R;
import com.hakansoftware.hakantodolist.controller.UserController;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.btnRegister)
    FButton btnRegister;

    @BindView(R.id.edtEMail)
    EditText edtEMail;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @BindView(R.id.edtPasswordAgain)
    EditText edtPasswordAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        btnRegister.setButtonColor(getResources().getColor(R.color.fbutton_color_amethyst));
        btnRegister.setShadowColor(getResources().getColor(R.color.fbutton_color_wisteria));
        btnRegister.setShadowEnabled(true);
        btnRegister.setShadowHeight(8);
        btnRegister.setCornerRadius(16);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtPassword.getText().toString().trim().equals(edtPasswordAgain.getText().toString().trim()))
                {
                    UserController userController = UserController.getInstance();
                    userController.register(getApplicationContext(), edtEMail.getText().toString().trim(), edtPassword.getText().toString().trim());
                }
            }
        });
    }
}
