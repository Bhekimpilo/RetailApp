package legacy.com.retailapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUp extends ActionBarActivity {

    EditText name, email, password;
    Button singUp;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText) findViewById(R.id.nameEdit);
        email = (EditText) findViewById(R.id.emailEdit);
        password = (EditText) findViewById(R.id.passEdit);
        singUp = (Button) findViewById(R.id.button2);

        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);



        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                String mName = name.getText().toString();
                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();

                mName = mName.trim();
                mEmail = mEmail.trim();
                mPassword = mPassword.trim();

                if (mName.isEmpty() || mEmail.isEmpty() || mPassword.isEmpty()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(SignUp.this);
                    alert.setTitle("Error")
                            .setMessage("Empty fields are not allowed")
                            .setNegativeButton("OK", null)
                            .create()
                            .show();
                }else {

                    ParseUser user = new ParseUser();
                    user.setUsername(mName);
                    user.setEmail(mEmail);
                    user.setPassword(mPassword);
                    user.signUpInBackground(new SignUpCallback() {

                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                progressDialog.hide();
                                startActivity(new Intent(SignUp.this, Detail.class));
                                Toast.makeText(SignUp.this, "Success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUp.this, "Error", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }

}
