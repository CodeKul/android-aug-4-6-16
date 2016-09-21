package com.codekul.pushnotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void registerNewAccount(){
        final String userName = ((EditText)findViewById(R.id.edtUserName)).getText().toString();
        final String password = ((EditText)findViewById(R.id.edtUserPassword)).getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {

                XMPPTCPConnectionConfiguration connConfig =
                        XMPPTCPConnectionConfiguration.builder()
                                .setHost("tigase.im")  // Name of your Host
                                .setSecurityMode(ConnectionConfiguration.SecurityMode.ifpossible)
                                .setPort(5222)          // Your Port for accepting c2s connection
                                .setDebuggerEnabled(true)
                                .setServiceName("tigase.im")
                                .build();

                AbstractXMPPConnection connection = new XMPPTCPConnection(connConfig);
                try {
                    connection.connect();
                } catch (SmackException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XMPPException e) {
                    e.printStackTrace();
                }

                AccountManager accountManager = AccountManager.getInstance(connection);
                try {
                    if(accountManager.supportsAccountCreation()) {
                        Log.i("@codekul","Server supports registration");
                        Map<String,String> map = new HashMap<>();
                        map.put("email","aniruddha.kudalkar@gmail.com");
                        accountManager.sensitiveOperationOverInsecureConnection(true);
                        accountManager.createAccount(userName, password,map);
                    }
                    else {
                        Log.i("@codekul","Server does not supports registration");
                    }
                } catch (SmackException.NoResponseException e) {
                    e.printStackTrace();
                } catch (XMPPException.XMPPErrorException e) {
                    e.printStackTrace();
                } catch (SmackException.NotConnectedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
