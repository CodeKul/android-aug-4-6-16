package com.codekul.demorecharge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnLogin).setOnClickListener(this::login);
        findViewById(R.id.btnRegister).setOnClickListener(this::register);
    }

    private void register(View view) {
        TextView text = ((TextView)findViewById(R.id.textStatus));
        text.setText("Register");
    }

    private void login(View view) {
        //((TextView)findViewById(R.id.textStatus)).setText("Login"); //recomended

        EditText edtUserName = (EditText) findViewById(R.id.edtUserName);
        String userName = edtUserName.getText().toString();

        EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        String password = edtPassword.getText().toString();

        if((userName.equals("codekul") && password.equals("codekul"))
                || (userName.equals("codekul1") && password.equals("codekul1"))) {
            ((TextView) findViewById(R.id.textStatus)).setText("Success :)");
            startActivity(new Intent(this,RechargeActivity.class));
        }
        else ((TextView)findViewById(R.id.textStatus)).setText("Fail :)");
    }
}
