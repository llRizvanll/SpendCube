package com.viznar.spendcube.android;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viznar.spendcube.android.readsms.MessageDataM;
import com.viznar.spendcube.android.readsms.MessagesViewAdapter;
import com.viznar.spendcube.android.readsms.ReadMessageListener;
import com.viznar.spendcube.android.readsms.ReadMessagesPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ReadMessageListener{

    private TextView mTextMessage;
    private final int REQUIRED_PERMISSION_READSMS = 10;
    private ReadMessagesPresenter messagesPresenter;
    private MessagesViewAdapter messagesViewAdapter = null;
    private Activity activity;
    private RecyclerView mRecyclerView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.footer_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.footer_profile);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.footer_analyser);
                    return true;
            }
            return false;
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        mRecyclerView = (RecyclerView) findViewById(R.id.messages_listview);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        checkForReadPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkForReadPermission(){

        //Check for sms permission
        if (checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {Manifest.permission.READ_SMS},
                    REQUIRED_PERMISSION_READSMS);
            return;
        }
        else{
            messagesPresenter = new ReadMessagesPresenter(this,activity);
            messagesPresenter.syncSMSData();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        //checkForReadPermission();
        if (checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUIRED_PERMISSION_READSMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(MainActivity.this, "Access messages Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onSuccess(List<MessageDataM> messageData) {
        Toast.makeText(activity,"Red All Messagess",Toast.LENGTH_SHORT).show();

        if (messagesViewAdapter == null){
            messagesViewAdapter = new MessagesViewAdapter(activity,messageData);
            mRecyclerView.setAdapter(messagesViewAdapter);
        }
    }

    @Override
    public void onError(String message) {

    }
}
