package edu.upc.dsa.minim2_github;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitialActivity extends AppCompatActivity {

    APIInterface apiInterface;
    EditText uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        uname = (EditText) findViewById(R.id.user);

    }

    public void sendUser(View view){
        String username = uname.getText().toString();
        if (username.equals(""))
            Toast.makeText(getApplicationContext(), "Enter the username", Toast.LENGTH_LONG).show();
        else {
            Call<Usuario> userCall = apiInterface.getUserInfo(username);
            userCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    //progressBar.setVisibility(View.VISIBLE);
                    Log.d("TAG",response.code()+"");
                    if (response.code() == 200) {
                        Usuario user1 = response.body();
                        Call<List<Usuario>> followersCall = apiInterface.getFollowers(username);
                        followersCall.enqueue(new Callback<List<Usuario>>() {
                            @Override
                            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                                user1.setUserFollowers(response.body());
                                instanciaUsuario.getInstance().setUser(user1);
                                //progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<List<Usuario>> call, Throwable t) {

                            }
                        });
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"User does not exist", Toast.LENGTH_LONG);
                        toast.show();

                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    call.cancel();
                    Log.d("Error","Failure");
                }
            });
        }
    }
}