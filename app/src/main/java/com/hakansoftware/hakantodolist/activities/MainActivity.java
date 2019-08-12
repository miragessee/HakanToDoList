package com.hakansoftware.hakantodolist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hakansoftware.hakantodolist.R;
import com.hakansoftware.hakantodolist.base.BaseIntent;
import com.hakansoftware.hakantodolist.controller.UserController;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    public static String email;
    public static String password;

    @BindView(R.id.btnLogin)
    FButton btnLogin;

    @BindView(R.id.btnRegister)
    FButton btnRegister;

    @BindView(R.id.edtEMail)
    EditText edtEMail;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btnRegister.setButtonColor(getResources().getColor(R.color.fbutton_color_amethyst));
        btnRegister.setShadowColor(getResources().getColor(R.color.fbutton_color_wisteria));
        btnRegister.setShadowEnabled(true);
        btnRegister.setShadowHeight(8);
        btnRegister.setCornerRadius(16);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaseIntent.baseIntent(getApplicationContext(), RegisterActivity.class);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserController userController = UserController.getInstance();
                userController.login(getApplicationContext(), edtEMail.getText().toString().trim(), edtPassword.getText().toString().trim());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        //Toast.makeText(AnaActivity.this, "1", Toast.LENGTH_LONG).show();
        if (MainActivity.email != null && MainActivity.password != null) {
            if (MainActivity.email.length() > 2) {
                edtEMail.setText(MainActivity.email);
                edtPassword.setText(MainActivity.password);
            }
        }
    }
}
