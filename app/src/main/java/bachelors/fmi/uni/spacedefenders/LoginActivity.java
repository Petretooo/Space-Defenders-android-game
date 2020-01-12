package bachelors.fmi.uni.spacedefenders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView registerTV;
    EditText usernameET;
    EditText passwordET;
    Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerTV = findViewById(R.id.registerTextView);
        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        registerTV.setOnClickListener(onClick);
        loginButton.setOnClickListener(onClick);
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.registerTextView){
                Intent intent = new Intent(
                        LoginActivity.this, RegisterActivty.class);

                startActivity(intent);
            }else{
                SharedPreferences pref =
                        getSharedPreferences("SpaceDefenders", MODE_PRIVATE);

                String username = pref.getString("username", "");
                String password = pref.getString("password", "");

                if(usernameET.getText().toString().equalsIgnoreCase(username)
                        && passwordET.getText().toString().equals(password)){

                    Intent loginIntent = new Intent
                            (LoginActivity.this, MainActivity.class);

                    loginIntent.putExtra("user", username);

                    startActivity(loginIntent);
                }
            }

        }
    };

}
