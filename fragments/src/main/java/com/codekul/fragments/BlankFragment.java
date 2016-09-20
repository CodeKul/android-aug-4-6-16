package com.codekul.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        rootView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getUserName(rootView).equals("android") &&  getPassword(rootView).equals("android")){
                    Log.i("@codekul","Login Success");
                    setTextMsg(rootView,"Login Success");
                }
            }
        });

        rootView.findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRegistrationFragment();
            }
        });

        return rootView;
    }

    private String getUserName(View rootView){
        return ((EditText)rootView.findViewById(R.id.edtUserName)).getText().toString();
    }

    private String getPassword(View rootView){
        return ((EditText)rootView.findViewById(R.id.edtPassword)).getText().toString();
    }

    private void setTextMsg(View rootView, String msg){
        ((TextView)rootView.findViewById(R.id.textView)).setText(msg);
    }

    private void loadRegistrationFragment(){

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction txn = manager.beginTransaction();
        txn.replace(R.id.frameFragment,new RegistrationFragment());
        txn.commit();
    }
}
