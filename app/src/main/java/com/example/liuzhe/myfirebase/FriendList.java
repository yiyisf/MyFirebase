package com.example.liuzhe.myfirebase;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

public class FriendList extends AppCompatActivity {
    RecyclerView recyclerView;
    Firebase mRef;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    EditText message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        message = (EditText) findViewById(R.id.message_r);
        send = (Button) findViewById(R.id.buttonSend_r);

//        Firebase.setAndroidContext(this);

//        mRef = new Firebase("https://glowing-fire-3217.firebaseio.com/message1");

        mRef = MyApp.getFirebase().child("message1");
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ChatMessage, ChatMessageViewHolder>(ChatMessage.class,
                R.layout.recy_item,
                ChatMessageViewHolder.class,
                mRef) {
            @Override
            protected void populateViewHolder(ChatMessageViewHolder chatMessageViewHolder, ChatMessage chatMessage, int i) {
                chatMessageViewHolder.r_name.setText(chatMessage.getName());
                chatMessageViewHolder.r_message.setText(chatMessage.getMessage());
                chatMessageViewHolder.r_name.setGravity(5);
                chatMessageViewHolder.r_message.setGravity(5);
                chatMessageViewHolder.r_name.setTextColor(Color.RED);
                chatMessageViewHolder.r_message.setTextColor(Color.GREEN);

//                recyclerView.scrollTo(0, 0);
//                recyclerView.smoothScrollToPosition(firebaseRecyclerAdapter.getItemCount());


            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.push().setValue(new ChatMessage("zhang", message.getText().toString()));
                message.setText("");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(FriendList.this, "Click search", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add:
                Toast.makeText(FriendList.this, "Click add", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add_friend:
                Toast.makeText(FriendList.this, "Click add friend", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
