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


public class MessageList extends AppCompatActivity {
    ListView listView;
    Firebase mRef;
    FirebaseListAdapter firebaseListAdapter;
    EditText message;
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.messageView);
        message = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.buttonSend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.message_toolbar);
        setSupportActionBar(toolbar);


//        Firebase.setAndroidContext(this);
//        mRef = new Firebase("https://glowing-fire-3217.firebaseio.com/message1");
        mRef = MyApp.getFirebase().child("message1");

        firebaseListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.list_item, mRef) {
            @Override
            protected void populateView(View view, ChatMessage chatMessage, int i) {
                ((TextView) view.findViewById(R.id.name)).setText(chatMessage.getName());
                ((TextView) view.findViewById(R.id.text)).setText(chatMessage.getMessage());
            }
        };

        listView.setAdapter(firebaseListAdapter);

//        final EditText editText = (EditText) findViewById(R.id.message);
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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mtextViewCondition = (TextView) findViewById(R.id.textViewCondition);
//        bbuttonsonny = (Button) findViewById(R.id.buttonsonny);
//        buttonfoggy = (Button) findViewById(R.id.buttonfoggy);


//        final Firebase DataRef = mRef.child("data");
//
//
//        DataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
////                Log.v("map data is:", maplist.toString());
//
//                String text = dataSnapshot.getValue(String.class);
//                mtextViewCondition.setText(text);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        buttonfoggy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DataRef.setValue("foggy");
//            }
//        });
//
//        bbuttonsonny.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DataRef.setValue("Sonny");
//            }
//        });

//        Messageref.addChildEventListener(new ChildEventListener() {
////            @TargetApi(Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Map<String,String> arrayMap = dataSnapshot.getValue(Map.class);
//
//                arrayMaplist.add(arrayMap);
//                firebaseListAdapter.notifyDataSetChanged();
////                int i = 0;
////                for(i = 0;i< arrayMap.size();i++){
////
////                    Log.i("key" ,String.valueOf(i));
////                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////                        Log.i("is:", arrayMap.get("text"));
////                    }
////                }
//
////                Object messages = dataSnapshot.getChildren();
//
////                long count = dataSnapshot.getChildrenCount();
//
////                Log.v("message is :", messages.toString());
//
////                Log.v("message count is:", String.valueOf(count));
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//    }

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
                Toast.makeText(MessageList.this, "Click search", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add:
                Toast.makeText(MessageList.this, "Click add", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_add_friend:
                Toast.makeText(MessageList.this, "Click add friend", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
