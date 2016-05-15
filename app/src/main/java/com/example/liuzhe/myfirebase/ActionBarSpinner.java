package com.example.liuzhe.myfirebase;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.content.res.Resources.Theme;

import android.widget.TextView;
import android.widget.Toast;

import com.example.liuzhe.myfirebase.tools.LoadImageFromStorage;
import com.example.liuzhe.myfirebase.tools.SaveToInternalStorage;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActionBarSpinner extends AppCompatActivity implements View.OnClickListener {

    private Uri userUri;
    private ImageView user_profile;
    private String userId;
    private Bitmap bitmap = null;
//    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_spinner);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.findViewById(R.id.profile_image).setOnClickListener(this);
        user_profile = (ImageView) toolbar.findViewById(R.id.profile_image);

        try {

            userUri = MyApp.getGoogleSignInAccount().getPhotoUrl();
            userId = MyApp.getGoogleSignInAccount().getId();
        } catch (Error e) {
            e.printStackTrace();
        }
        setImageLogo();

        List<Map<String, String>> items = new ArrayList<Map<String, String>>();

        Map<String, String> item0 = new HashMap<String, String>(2);
        item0.put("text", "Browse aisles...");
        item0.put("subText", "(Upgrade required)");
        items.add(item0);

        Map<String, String> item1 = new HashMap<String, String>(2);
        item1.put("text", "Option 1");
        item1.put("subText", "(sub text 1)");
        items.add(item1);

        Map<String, String> item2 = new HashMap<String, String>(2);
        item2.put("text", "Option 2");
        item2.put("subText", "(sub text 2)");
        items.add(item2);

        // Setup spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        MyAdapter spinner_adapter = new MyAdapter(
                toolbar.getContext(),
                items);
//                new String[]{
//                        "Yiyi",
//                        "Section 2",
//                        "Section 3",
//                }
//        );

//        spinner_adapter.setDropDownViewResource(R.layout.simple_list_item);

        spinner.setAdapter(spinner_adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
        getMenuInflater().inflate(R.menu.menu_action_bar_spinner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            PendingIntent pendingIntent =
                    TaskStackBuilder.create(this)
                            // add all of DetailsActivity's parents to the stack,
                            // followed by DetailsActivity itself
                            .addNextIntentWithParentStack(i)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentIntent(pendingIntent);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                Toast.makeText(this, "点击了头像", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, peoplo.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }


    private static class MyAdapter extends ArrayAdapter<Map<String, String>> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, List<Map<String, String>> objects) {
            super(context, R.layout.simple_list_item, (List<Map<String, String>>) objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            try {

                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = inflater.inflate(R.layout.simple_list_item_1, parent, false);
                TextView textView = (TextView) row.findViewById(R.id.spinner_text);
                textView.setText(getItem(position).get("text"));
                return row;
            } catch (Error e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;


            try {
                if (convertView == null) {
                    // Inflate the drop down using the helper's LayoutInflater
                    LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                    view = inflater.inflate(R.layout.simple_list_item, parent, false);

                } else {
                    view = convertView;
                }
//                return view;

                TextView textView = (TextView) view.findViewById(R.id.spinner_name);
                textView.setText(getItem(position).get("text"));
                return view;
            } catch (Error e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_action_bar_spinner, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    private void setImageLogo() {
        if (userUri != null) {
            String dir = userId + "logo";
            String filename = userUri.toString().replace("/", "_");
            Bitmap loadBitmap = new LoadImageFromStorage(getApplicationContext(), dir, filename).LoadImage();
            if (loadBitmap != null) {
                user_profile.setImageBitmap(loadBitmap);
            } else {
                if (userUri != null) {
                    new LoadImage().execute(userUri);
                }
            }
        }
    }

    private class LoadImage extends AsyncTask<Uri, Void, Bitmap> {
        protected Bitmap doInBackground(Uri... uris) {
            String url = uris[0].toString();
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                bitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true);
//                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uris[0]);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return bitmap;
            }
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {
                user_profile.setImageBitmap(image);
                SaveLogo(image);
            } else {
                Toast.makeText(getApplicationContext(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void SaveLogo(Bitmap image) {
        String dir = userId + "logo";
        String filename = userUri.toString().replace("/", "_");
        SaveToInternalStorage saveLogo = new SaveToInternalStorage(getApplicationContext(), dir, filename, image);
        saveLogo.execute();
    }

}
