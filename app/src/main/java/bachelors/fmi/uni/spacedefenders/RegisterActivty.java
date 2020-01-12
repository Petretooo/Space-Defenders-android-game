package bachelors.fmi.uni.spacedefenders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegisterActivty extends AppCompatActivity {

    public static final int REQUEST_CODE = 666;

    ImageView image;
    EditText usernameET;
    EditText passwordET;
    EditText repeatPasswordET;
    RadioGroup radioGroup;
    Button okButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activty);

        image = findViewById(R.id.avatarImageView);
        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        repeatPasswordET = findViewById(R.id.repeatPasswordEditText);
        radioGroup = findViewById(R.id.genderRadioGroup);
        okButton = findViewById(R.id.okButton);
        cancelButton = findViewById(R.id.cancelButton);

        okButton.setOnClickListener(onClick);

        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent cameraIntent =
                        new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent, REQUEST_CODE);

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
           Bitmap img = (Bitmap) data.getExtras().get("data");
           image.setImageBitmap(img);
       }
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = usernameET.getText().toString();
            String pass = passwordET.getText().toString();
            String pass2 = repeatPasswordET.getText().toString();

            if(!username.isEmpty() && !pass.isEmpty() &&
                    pass.equals(pass2)){
                RadioButton checkedButton =
                        findViewById(radioGroup.getCheckedRadioButtonId());

                String gender = checkedButton.getText().toString();

                SharedPreferences.Editor editor =
                        getSharedPreferences("SpaceDefenders", MODE_PRIVATE).edit();

                editor.putString("username", username);
                editor.putString("password", pass);
                editor.putString("gender", gender);

                editor.apply();
                editor.commit();

            }
        }
    };
}
