package com.example.liuzhe.myfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;


public class UserList extends AppCompatActivity {
    ListView listView;
    Firebase mRef;
    FirebaseListAdapter firebaseListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        listView = (ListView) findViewById(R.id.userView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.user_toolbar);
        setSupportActionBar(toolbar);

//        Firebase.setAndroidContext(this);
//        mRef = new Firebase("https://glowing-fire-3217.firebaseio.com/message1");
        mRef = MyApp.getFirebase().child("alluser");

        firebaseListAdapter = new FirebaseListAdapter<UserInfo>(this, UserInfo.class, R.layout.simple_list_item, mRef) {
            @Override
            protected void populateView(View view, UserInfo userInfo, int i) {
                ((TextView) view.findViewById(R.id.spinner_name)).setText(userInfo.getName());
                ((TextView) view.findViewById(R.id.spinner_email)).setText(userInfo.getEmail());
            }
        };
        listView.setAdapter(firebaseListAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseListAdapter.cleanup();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.i("Item id:", String.valueOf(item.getItemId()));

        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(UserList.this, "Click search", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add:
                Toast.makeText(UserList.this, "Click add", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add_friend:
                Toast.makeText(UserList.this, "Click add friend", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
