package com.example.liuzhe.myfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;


public class MessageList extends AppCompatActivity {
    ListViewCompat listView;
    Firebase mRef;
    FirebaseListAdapter firebaseListAdapter;
    AppCompatEditText message;
    AppCompatImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        String toUser = intent.getStringExtra("touser");
        setTitle(toUser);
        listView = (ListViewCompat) findViewById(R.id.messageView);

        message = (AppCompatEditText) findViewById(R.id.message);
        send = (AppCompatImageButton) findViewById(R.id.buttonSend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.message_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRef = MyApp.getFirebase().child("message");

        firebaseListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.list_item, mRef) {
            @Override
            protected void populateView(View view, ChatMessage chatMessage, int i) {
                ((TextView) view.findViewById(R.id.name)).setText(chatMessage.getName());
                ((TextView) view.findViewById(R.id.text)).setText(chatMessage.getMessage());
            }
        };

        listView.setAdapter(firebaseListAdapter);

//        final EditText editText = (EditText) findViewById(R.id.email);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRef.push().setValue(new ChatMessage("liu", message.getText().toString()));
                message.setText("");
            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.message_toolbar);
//        setSupportActionBar(toolbar);

//        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//            }
//        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseListAdapter.cleanup();

    }
}
