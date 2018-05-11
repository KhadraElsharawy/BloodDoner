package com.example.basmet.blooddoner.UI;

import android.app.AuthenticationRequiredException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.basmet.blooddoner.Firebase.User;
import com.example.basmet.blooddoner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Spinner spinnerCities;
    private Spinner spinnerBloodGroup;
    public static EditText edDateOfBirth;
    private CircleImageView circleImageView;
    private EditText edFirstName, edLastName, edPassword, edConfirmPassword, edEmail, edLocation, edPhone;
    private Button btSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        spinnerCities = findViewById(R.id.spinner_city);
        spinnerBloodGroup = findViewById(R.id.spinner_blood_group);
        edDateOfBirth = findViewById(R.id.date_of_birth);
        circleImageView = findViewById(R.id.profile_image);
        edFirstName = findViewById(R.id.first_name);
        edLastName = findViewById(R.id.last_name);
        edPassword = findViewById(R.id.ed_password);
        edEmail = findViewById(R.id.ed_email);
        edLocation = findViewById(R.id.location);
        edConfirmPassword = findViewById(R.id.confirm_password);
        edPhone = findViewById(R.id.phone);
        btSignUp = findViewById(R.id.button_sign_up);

        // Create an ArrayAdapter using the string array and a default spinner layout
        // this adapter for linking spinner to array cities in strings
        ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCities.setAdapter(adapterCity);

        // this adapter for linking spinner to array blood_group in strings
        ArrayAdapter<CharSequence> adapterBlood =  ArrayAdapter.createFromResource(this,
                R.array.blood_group, android.R.layout.simple_spinner_item);
        adapterBlood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodGroup.setAdapter(adapterBlood);

        //Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {
            //handle the already login user
           // FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        }
    }

    // this method for getting date from DatePickerDialog ... that represent in class DateOfBirthPicker
    public void selectDateOfBirth(View view){
        DateOfBirthPicker dateOfBirthPicker = new DateOfBirthPicker();
        dateOfBirthPicker.show(getFragmentManager(), "date");
    }




    private void signUp() {
        final String fName = edFirstName.getText().toString();
        final String lName = edLastName.getText().toString();
        final String password = edPassword.getText().toString();
        final String email = edEmail.getText().toString();
        final String dateOfBirth = edDateOfBirth.getText().toString();
        final String location = edLocation.getText().toString();
        final String confirmPassword = edConfirmPassword.getText().toString();
        final String phone = edPhone.getText().toString();

        if (fName.isEmpty()) {
            edFirstName.setError(getString(R.string.input_error_fName));
            edFirstName.requestFocus();
        }
        if (lName.isEmpty()) {
            edLastName.setError(getString(R.string.input_error_lName));
            edLastName.requestFocus();
        }
        if (password.isEmpty()) {
            edPassword.setError(getString(R.string.input_error_password));
            edPassword.requestFocus();
        }
        if (!confirmPassword.equals(password)){
            edConfirmPassword.setError(getString(R.string.input_error_not_match_password));
            edConfirmPassword.requestFocus();
        }
        if (email.isEmpty()) {
            edEmail.setError(getString(R.string.input_error_email));
            edEmail.requestFocus();
        }
        if (location.isEmpty()) {
            edLocation.setError(getString(R.string.input_error_location));
            edLocation.requestFocus();

        }
        if (dateOfBirth.isEmpty()) {
            edDateOfBirth.setError(getString(R.string.input_error_date_of_birth));
            edDateOfBirth.requestFocus();
        }
        if (phone.isEmpty()) {
            edPhone.setError(getString(R.string.input_error_phone));
            edPhone.requestFocus();
        }
        if (phone.length() != 11) {
            edPhone.setError(getString(R.string.input_error_phone_incorrect));
            edPhone.requestFocus();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edEmail.setError(getString(R.string.input_error_email_invalidate));
            edEmail.requestFocus();
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    User user = new User( fName, lName, email, phone, password, dateOfBirth, location);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "registration_success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "registration_failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

            }
        }
                });




    }


    //this method for getting image from gallery
    public void selectImageFromGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                circleImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
