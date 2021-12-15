//Hadasa Golkin 322807694
//Ateret Rabi 315285304

package com.example.app2.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app2.Constants;
import com.example.app2.Entities.Travel;
import com.example.app2.MyBroadCastReceiver;
import com.example.app2.R;
import com.example.app2.UI.ViewModel.TravelViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TravelViewModel ViewModel=new TravelViewModel();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    SharedPreferences sharedPreferences;
    EditText pass;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        registerReceiver(new MyBroadCastReceiver(),new IntentFilter(Constants.CUSTOM_INTENT));
        setContentView(R.layout.activity_login);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
         pass=findViewById(R.id.password);
         email=findViewById(R.id.email);

         email.setText(sharedPreferences.getString("email",""));
         pass.setText(sharedPreferences.getString("password",""));
        Button loginbtn=findViewById(R.id.login_btn);

        Intent i = new Intent(MainActivity.this, NavigationActivity.class);

        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String passstring=pass.getText().toString();
                String emailstring=email.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(emailstring,passstring).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                ArrayList<Travel> mytravels=new ArrayList<Travel>();
                                if(task.isSuccessful()){
                                    mytravels= ViewModel.GetTravelOfUser(emailstring);
                                    i.putExtra("MY_MAIL",emailstring);
                                    startActivity(i);}
                                else
                                    Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();



                            }
                        }
                );
            }
        });


        Button signupbtn=findViewById(R.id.sign_up_btn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passstring=pass.getText().toString();
                String emailstring=email.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(emailstring,passstring).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                                Toast.makeText(MainActivity.this,"הרישום הצליח.",Toast.LENGTH_LONG).show();
                                            else
                                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                        }
                                    });

                                else
                                    Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }

                );


            }
        });


    }

    public void savePref(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email.getText().toString());
        editor.putString("password",pass.getText().toString());
        editor.commit();
    }
}