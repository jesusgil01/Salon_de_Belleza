package com.example.salndebelleza;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salndebelleza.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser userFB = FirebaseAuth.getInstance().getCurrentUser();

    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Agregar animaciones
        Animation animacion_arriba = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion_abajo = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView paoloTextView = findViewById(R.id.PaoloTextView);
        ImageView logoImageView = findViewById(R.id.logoImageView);

        paoloTextView.setAnimation(animacion_abajo);
        logoImageView.setAnimation(animacion_arriba);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userFB != null) {
                    initUser(userFB.getEmail());
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(logoImageView, "logoImageTrans");
                    pairs[1] = new Pair<View, String>(paoloTextView, "textTrans");

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                        finish();
                    }
                }

            }
        }, 4000);
    }

    public void initUser(String email) {
        DocumentReference docRef = db.collection("Users").document(email);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                User user = value.toObject(User.class);
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();
            }
        });
    }
}