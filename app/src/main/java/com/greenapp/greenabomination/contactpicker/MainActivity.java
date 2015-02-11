package com.greenapp.greenabomination.contactpicker;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String[] from = new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        int[] to = new int[]{R.id.itemTextView};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listitemlayout, c, from, to);
        ListView lv = (ListView) findViewById(R.id.contactListView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                c.moveToPosition(position);

                int rowId = c.getInt(c.getColumnIndexOrThrow("_id"));

                Uri outUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);
                Intent outData = new Intent();
                outData.setData(outUri);
                setResult(Activity.RESULT_OK, outData);
                finish();
            }
        })
    }


}
