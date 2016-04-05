package legacy.com.retailapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    EditText name, password;
    TextView signUp;
    Button login;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameEdit);
        password = (EditText) findViewById(R.id.passEdit);
        signUp = (TextView) findViewById(R.id.signupText);
        login = (Button) findViewById(R.id.button);

        signUp.setOnClickListener(this);
        login.setOnClickListener(this);

        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        pd.setMessage("Authenticating...");


    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.button:
                pd.show();

                ParseUser.logInInBackground(name.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        pd.hide();

                        if (e == null){
                            Intent intent = new Intent(MainActivity.this, Detail.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                            alert.setTitle("Error")
                                    .setMessage("Your credentials do not match any account")
                                    .setNegativeButton("OK", null)
                                    .create()
                                    .show();
                        }
                    }
                });

                break;
            case R.id.signupText:

                startActivity(new Intent(MainActivity.this, SignUp.class));

                break;
        }

    }
}
