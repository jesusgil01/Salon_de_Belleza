package com.example.salndebelleza;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salndebelleza.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class  UserActivity extends AppCompatActivity {

    TextView nombreUsuarioBienvenidoTextView;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser userFB = FirebaseAuth.getInstance().getCurrentUser();

    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user = (User)getIntent().getExtras().getSerializable("User");

        nombreUsuarioBienvenidoTextView = findViewById(R.id.nombreUsuarioBienvenidoTextView);

        nombreUsuarioBienvenidoTextView.setText(user.getFirstName());


    }


}