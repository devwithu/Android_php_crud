package com.cavanosa.crudretrofitapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cavanosa.crudretrofitapp.adapter.PersonAdapter;
import com.cavanosa.crudretrofitapp.interfaces.ApiInterface;
import com.cavanosa.crudretrofitapp.model.Person;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";

    EditText edit_name, edit_hobby;
    Button add_btn;
    String name, hobby;
    RecyclerView recyclerView;
    PersonAdapter adapter;
    PersonAdapter.ItemClickListener itemClickListener;
    List<Person> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.person_recyclerview);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_hobby = (EditText) findViewById(R.id.edit_hobby);
        add_btn = (Button) findViewById(R.id.add_btn);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        selectPerson();

        itemClickListener = new PersonAdapter.ItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                int id = list.get(position).getId();
                String name = list.get(position).getName();
                String hobby = list.get(position).getHobby();
                Log.e(TAG, "id : " + id + ", name : " + name + ", hobby : " + hobby);
            }
        };

        add_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name = edit_name.getText().toString();
                hobby = edit_hobby.getText().toString();
                insertPerson(name, hobby);
            }
        });
    }

    private void selectPerson()
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Person>> call = apiInterface.getNameHobby();
        call.enqueue(new Callback<List<Person>>()
        {
            @Override
            public void onResponse(@NonNull Call<List<Person>> call, @NonNull Response<List<Person>> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Person>> call, @NonNull Throwable t)
            {
                Log.e("selectPerson()", "에러 : " + t.getMessage());
            }
        });
    }

    private void onGetResult(List<Person> lists)
    {
        adapter = new PersonAdapter(this, lists, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        list = lists;
    }

    // ↓ 추가된 부분
    private void insertPerson(String name, String hobby)
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Person> call = apiInterface.insertPerson(name, hobby);
        call.enqueue(new Callback<Person>()
        {
            @Override
            public void onResponse(@NonNull Call<Person> call, @NonNull Response<Person> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Boolean success = response.body().getSuccess();
                    if (success)
                    {
                        onSuccess(response.body().getMessage());
                    }
                    else
                    {
                        onError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Person> call, @NonNull Throwable t)
            {
                Log.e("insertPerson()", "에러 : " + t.getMessage());
            }
        });
    }

    private void onError(String message)
    {
        Log.e("insertPerson()", "onResponse() 에러 : " + message);
    }

    private void onSuccess(String message)
    {
        Log.e("insertPerson()", "onResponse() 성공 : " + message);
        setResult(RESULT_OK);
        finish();
    }
}