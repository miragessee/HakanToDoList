package com.hakansoftware.hakantodolist.controller;

import android.content.Context;
import android.util.Log;

import com.hakansoftware.hakantodolist.activities.MainActivity;
import com.hakansoftware.hakantodolist.activities.NavigationDrawerActivity;
import com.hakansoftware.hakantodolist.base.BaseIntent;
import com.hakansoftware.hakantodolist.base.BaseUtils;

import java.io.IOException;

import io.swagger.client.ApiClient;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController {

    private volatile static UserController instance;

    private UserApi api = new ApiClient().createService(UserApi.class);

    public UserController() {
    }

    public static UserController getInstance() {
        if (instance == null)
        {
            synchronized (UserController.class) {
                if (instance == null) {
                    instance = new UserController();
                }
            }
        }
        return instance;
    }

    public void register(Context context, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Call<Object> call = api.userPost(user);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.body() != null) {
                    Log.d("successTag", response.body().toString());

                    BaseUtils.showToast(context, response.body().toString());

                    MainActivity.email = email;
                    MainActivity.password = password;

                    BaseIntent.baseIntent(context, MainActivity.class);
                }
                else
                {
                    try {
                        Log.d("errorTag", response.errorBody().string());

                        BaseUtils.showToast(context, "This email is already in use.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("failTag", t.getMessage());

                BaseUtils.showToast(context, t.getMessage());
            }
        });
    }

    public void login(Context context, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Call<Object> call = api.userGet(email, password);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.body() != null) {
                    Log.d("successTag", response.body().toString());

                    BaseUtils.showToast(context, response.body().toString());

                    MainActivity.email = email;
                    MainActivity.password = password;

                    BaseIntent.baseIntent(context, NavigationDrawerActivity.class);
                }
                else
                {
                    try {
                        Log.d("errorTag", response.errorBody().string());

                        BaseUtils.showToast(context,"E-mail or password is incorrect.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("failTag", t.getMessage());

                BaseUtils.showToast(context, t.getMessage());
            }
        });
    }
}
