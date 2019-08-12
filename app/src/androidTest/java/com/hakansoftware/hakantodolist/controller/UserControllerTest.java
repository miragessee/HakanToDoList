package com.hakansoftware.hakantodolist.controller;

import android.content.Context;
import android.util.Log;

import com.hakansoftware.hakantodolist.activities.MainActivity;
import com.hakansoftware.hakantodolist.activities.NavigationDrawerActivity;
import com.hakansoftware.hakantodolist.base.BaseIntent;
import com.hakansoftware.hakantodolist.base.BaseUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import io.swagger.client.ApiClient;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserControllerTest {

    private UserController userController;
    private Context context;
    private UserApi api;

    @Before
    public void setUp() throws Exception {
        userController = UserController.getInstance();
        context = mock(Context.class);
        api = new ApiClient().createService(UserApi.class);
    }

    @Test
    public void register() {
        userController.register(context, "qwe","qwe");

        User user = new User();
        user.setEmail("qwe");
        user.setPassword("qwe");

        Call<Object> call = api.userPost(user);

        try {
            Response<Object> response = call.execute();
            Object body = response.body();

            assertTrue(response.isSuccessful() && body.toString().contains("success"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.body() != null) {
                    Log.d("successTag", response.body().toString());
                }
                else
                {
                    try {
                        Log.d("errorTag", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("failTag", t.getMessage());
            }
        });
    }

    @Test
    public void login() {
        userController.login(context, "qwe", "qwe");

        Call<Object> call = api.userGet("qwe", "qwe");

        try {
            Response<Object> response = call.execute();
            Object body = response.body();

            assertTrue(response.isSuccessful() && body.toString().contains("success"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.body() != null) {
                    Log.d("successTag", response.body().toString());
                }
                else
                {
                    try {
                        Log.d("errorTag", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("failTag", t.getMessage());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
    }
}