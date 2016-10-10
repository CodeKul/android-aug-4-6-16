package com.codekul.contactlistprovider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_READ_CONTACT = 1235;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMyData();
    }

    private void getMyData(){

        ArrayList<String> contacts = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        ((ListView)findViewById(R.id.listContacts)).setAdapter(adapter);

        Uri uri = Uri.parse("content://com.codekul.my.car");
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cusor =
                getContentResolver().query(uri,projection,selection,selectionArgs,sortOrder);
        while (cusor.moveToNext()){

            String name = cusor.getString(cusor.getColumnIndex("myName"));
            String num = cusor.getString(cusor.getColumnIndex("myPassword"));
            contacts.add(name + "\n"+ num);
            adapter.notifyDataSetChanged();
        }
    }

    private void readContacts(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Permission")
                        .setMessage("We need to read contacts for showing in list, please grant read permission")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_CONTACTS},
                                        PERMISSION_READ_CONTACT);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return;

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        PERMISSION_READ_CONTACT);
            }
        }
        accessContacts();
    }

    @RequiresPermission(value = Manifest.permission.READ_CONTACTS)
    private void accessContacts(){

        ArrayList<String> contacts = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        ((ListView)findViewById(R.id.listContacts)).setAdapter(adapter);

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cusor =
                getContentResolver().query(uri,projection,selection,selectionArgs,sortOrder);
        while (cusor.moveToNext()){

            String name = cusor.getString(cusor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = cusor.getString(cusor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(name + "\n"+ num);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_READ_CONTACT){
            if(permissions.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readContacts();
            }
        }
    }
}
