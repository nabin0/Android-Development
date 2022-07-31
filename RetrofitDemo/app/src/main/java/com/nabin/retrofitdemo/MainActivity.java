package com.nabin.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionScene;

import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Vars
    private MyApi api;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.viewDataTv);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(MyApi.class);

        // getPosts();
        // createPost();
        putPatchPost();
        //deletePost();
    }

    private void getPosts() {
        //Default Get call
        // Call<List<ModelClass>> call = api.getPosts();

        //Static parameters Get call
        // Call<List<ModelClass>> call = api.getPosts(4, null, "id","desc");

        //Dynamic Get Method call

        /*
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "4");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");
        Call<List<ModelClass>> call = api.getPosts(parameters);
        */

        //Change path at runtime
        Call<List<ModelClass>> call = api.getPostWithCustomPath("posts");


        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }
                List<ModelClass> posts = response.body();

                for (ModelClass m : posts) {
                    String content = "";
                    content += "Id : " + m.getId() + '\n';
                    content += "userid : " + m.getUserId() + '\n';
                    content += "Title: " + m.getTitle() + "\n";
                    content += "Text: " + m.getText() + "\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void createPost(){
        //Post Method with model parameter

        /*
        ModelClass model = new ModelClass(4, "new Title", "this is body ");
        Call<ModelClass> call = api.createPost(model);
         */

        //Post method call with static parameter values
        // Call<ModelClass> call = api.createPost(4,"this is new title", "this is body of the content");

        //Post Method call with dynamic Map (parameter)

        Map<String, String> parameter = new HashMap<>();
        parameter.put("userId", "23");
        parameter.put("title", "Dynamic title");
        parameter.put("body", "dynamic body");
        Call<ModelClass> call = api.createPost(parameter);

        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                if(!response.isSuccessful()){
                    textView.setText( "Code: "+response.code());
                    return;
                }
                ModelClass obj = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Id: " + obj.getId()+ "\n";
                content += "userId: " + obj.getUserId()+ "\n";
                content += "Title: " + obj.getTitle()+ "\n";
                content += "Text: " + obj.getText()+ "\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void putPatchPost(){
        ModelClass model = new ModelClass(115, null, "this is new Text");

        //Call<ModelClass> call =  api.putPost(2, model);
        Call<ModelClass> call =  api.patchPost(2, model);

        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                if(!response.isSuccessful()){
                    textView.setText( "Code: "+response.code());
                    return;
                }
                ModelClass obj = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Id: " + obj.getId()+ "\n";
                content += "userId: " + obj.getUserId()+ "\n";
                content += "Title: " + obj.getTitle()+ "\n";
                content += "Text: " + obj.getText()+ "\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


    private void deletePost(){
        Call<ModelClass> call =  api.deletePost(2);

        call.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                if(!response.isSuccessful()){
                    textView.setText( "Code: "+response.code());
                    return;
                }
                ModelClass obj = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "Id: " + obj.getId()+ "\n";
                content += "userId: " + obj.getUserId()+ "\n";
                content += "Title: " + obj.getTitle()+ "\n";
                content += "Text: " + obj.getText()+ "\n";

                textView.setText(content);
            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}