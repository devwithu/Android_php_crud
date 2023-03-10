package com.cavanosa.crudretrofitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cavanosa.crudretrofitapp.interfaces.ApiInterface;
import com.cavanosa.crudretrofitapp.model.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtherActivity extends AppCompatActivity
{
    public static final String TAG = "OtherActivity";

    TextView other_name, other_hobby;
    Button update_btn, delete_btn;
    int id;
    String name, hobby;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        other_name = (TextView) findViewById(R.id.other_name);
        other_hobby = (TextView) findViewById(R.id.other_hobby);
        update_btn = (Button) findViewById(R.id.update_btn);
        delete_btn = (Button) findViewById(R.id.delete_btn);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        hobby = intent.getStringExtra("hobby");
        Log.e(TAG, "인텐트 id : " + id + ", 인텐트 이름 : " + name + ", 인텐트 취미 : " + hobby);
        other_name.setText(name);
        other_hobby.setText(hobby);

        update_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent update_intent = new Intent(OtherActivity.this, UpdateActivity.class);
                update_intent.putExtra("id", id);
                update_intent.putExtra("name", name);
                update_intent.putExtra("hobby", hobby);
                startActivity(update_intent);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // 삭제 메서드
                deletePerson(id);
                Intent delete_intent = new Intent(OtherActivity.this, MainActivity.class);
                startActivity(delete_intent);
            }
        });
    }

    private void deletePerson(int id)
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Person> call = apiInterface.deletePerson(id);
        call.enqueue(new Callback<Person>()
        {
            @Override
            public void onResponse(@NonNull Call<Person> call, @NonNull Response<Person> response)
            {
                //
            }

            @Override
            public void onFailure(@NonNull Call<Person> call, @NonNull Throwable t)
            {
                Log.e("deletePerson()", t.getMessage());
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