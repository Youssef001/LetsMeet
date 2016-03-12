package zemmahi.youssef.letsmeet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by mahdi on 16-02-29.
 */
public class SignUp extends AppCompatActivity {


    // UI references.
    private EditText mEdTxtUserName;
    private EditText mEdTxtEmail;
    private EditText mEdTxtPassword;
    private EditText mEdTxtComfirmPassword;
    private Button btnCreateAccount = null;
    private Button btnBackMainMenu = null;


    // Declare the fields
    double fCurrentLatitude;
    double fCurrentLongitude;
    private String fUserName, fEmail, fPassword, fConfirmPass;
    private Bitmap fPhoto;
    private boolean fUserInsertSuccessful = false;
    private boolean fPassCaseEmpty = false, fEmailCaseEmpty = false, fEmailInvalide = false;
    private boolean fConfirmPassCaseEmpty = false, fUserNameCaseEmpty = false, fPassNotConfirm = false, fPassInvalide = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccountSU);
        btnBackMainMenu = (Button) findViewById(R.id.btnBackToMainMenuSU);
        mEdTxtUserName = (EditText) findViewById(R.id.edtTxtUserNameSU);
        mEdTxtEmail = (EditText) findViewById(R.id.edtTxtEmailSU);
        mEdTxtPassword = (EditText) findViewById(R.id.edtTxtPasswordSU);
        mEdTxtComfirmPassword = (EditText) findViewById(R.id.edtTxtConfirmPasswordSU);
        Bundle latitudeRecu = getIntent().getExtras();
        fCurrentLatitude = latitudeRecu.getDouble("fCurrentLatitude");
        Bundle longitudeRecu = getIntent().getExtras();
        fCurrentLongitude = longitudeRecu.getDouble("fCurrentLongitude");

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this, "Longitude : " + fCurrentLongitude + "Latitude : " + fCurrentLatitude, Toast.LENGTH_LONG).show();
                attemptLogin();
            }
        });
        btnBackMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void attemptLogin() {
        View focusView = null;
        boolean cancel = false;
        fEmailCaseEmpty = fPassCaseEmpty = fPassInvalide = fEmailInvalide  =  fConfirmPassCaseEmpty = fPassNotConfirm = fUserNameCaseEmpty = false ;

        // Reset errors.
        mEdTxtUserName.setError(null);
        mEdTxtEmail.setError(null);
        mEdTxtPassword.setError(null);
        mEdTxtComfirmPassword.setError(null);

        // Store values at the time of the login attempt.
        fUserName = mEdTxtUserName.getText().toString();
        fEmail = mEdTxtEmail.getText().toString();
        fPassword = mEdTxtPassword.getText().toString();
        fConfirmPass = mEdTxtComfirmPassword.getText().toString();

        // Check for a not null password, if the user entered one.
        if (TextUtils.isEmpty(fPassword)) {
            mEdTxtPassword.setError(getString(R.string.error_field_required));
            focusView = mEdTxtPassword;
            cancel = true;
            fPassCaseEmpty = true;
        }
        // Check for a valid password.
        if (!isPasswordValid(fPassword)) {
            mEdTxtPassword.setError(getString(R.string.error_invalid_password));
            focusView = mEdTxtPassword;
            cancel = true;
            fPassInvalide = true;
        }
        // Check for a not null confirm password, if the user entered one.
        if (TextUtils.isEmpty(fConfirmPass)) {
            mEdTxtComfirmPassword.setError(getString(R.string.error_field_required));
            focusView = mEdTxtComfirmPassword;
            cancel = true;
            fConfirmPassCaseEmpty = true;
        }
        // Check for a valid confirm password .
        if (!isPasswordConfirmed(fPassword,fConfirmPass)) {
            mEdTxtComfirmPassword.setError(getString(R.string.error_password_not_matches));
            focusView = mEdTxtComfirmPassword;
            cancel = true;
            fPassNotConfirm = true;
        }
        // Check for a not null email address.
        if (TextUtils.isEmpty(fEmail) && !isEmailValid(fEmail)) {
            mEdTxtEmail.setError(getString(R.string.error_field_required));
            focusView = mEdTxtEmail;
            cancel = true;
            fEmailCaseEmpty = true;
        }
        // Check for a valid email address.
        if (!isEmailValid(fEmail)) {
            mEdTxtEmail.setError(getString(R.string.error_invalid_email));
            focusView = mEdTxtEmail;
            cancel = true;
            fEmailInvalide = true;
        }
        // Check for a not null username.
        if (TextUtils.isEmpty(fUserName)) {
            mEdTxtUserName.setError(getString(R.string.error_field_required));
            focusView = mEdTxtUserName;
            cancel = true;
            fUserNameCaseEmpty = true;
        }
        if (!fPassCaseEmpty && !fPassInvalide && !fConfirmPassCaseEmpty && !fPassNotConfirm
                && !fEmailCaseEmpty && !fEmailInvalide && !fUserNameCaseEmpty) {
            // Calling async task to get json
            try {
                new ConnectionCode().execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
               e.printStackTrace();
            }

            if (fUserInsertSuccessful) {
                    Toast.makeText(getApplicationContext(),
                            "Sign up successful!",
                            Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getBaseContext(), ChooseGroup.class);
                    Bundle emailBundle = new Bundle();
                    emailBundle.putString("mEmail", fEmail); // email
                    i.putExtras(emailBundle);
                    startActivity(i);
                    finish();

            }
            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else if(!fUserInsertSuccessful){
                Toast.makeText(getApplicationContext(),
                        "Registration failed! try again, please!",
                        Toast.LENGTH_LONG).show();
                focusView.requestFocus();
            }
        }

    }

    private boolean isPasswordConfirmed(String password, String confirmPass) {
        return password.matches(confirmPass);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
    /****************************************************************/
    private class ConnectionCode extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            // Créer un nouveau utilisateur pour valider sign in
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setName(fUserName);
            utilisateur.setCourriel(fEmail);
            utilisateur.setPassword(fPassword);
            utilisateur.setPhotoEnBitmap(fPhoto);

            /* Ici on enregistre l'utilisateur et on vérifie si l'opération est réussie */
            fUserInsertSuccessful = utilisateur.InscrireNouveauUtilisateur();
            /* Hardcoder !*/
            fUserInsertSuccessful = true;
            return null;
        }
    }

}
