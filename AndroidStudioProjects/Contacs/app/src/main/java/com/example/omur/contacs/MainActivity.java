package com.example.omur.contacs;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final List<PersonalInfo> personalInfo = new ArrayList<PersonalInfo>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton turkcell = (RadioButton) findViewById(R.id.r1);
        final RadioButton vodafone = (RadioButton) findViewById(R.id.r2);
        final RadioButton avea = (RadioButton) findViewById(R.id.r3);
        final RadioButton all = (RadioButton) findViewById(R.id.r4);
        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.Rg);
        Button bup = (Button) findViewById(R.id.backup);
        Button rec = (Button) findViewById(R.id.recovery);

        final Cursor bilgiler = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (bilgiler.moveToNext()) {
            personalInfo.add(new PersonalInfo(
                    bilgiler.getString(bilgiler.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    bilgiler.getString(bilgiler.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)), R.drawable.defaultpic)); //isimler değişkenine rehber bağlantısından kişi isimlerini çektik

        }

        final ListView contacs = (ListView) findViewById(R.id.listView);
        MyListAdapter adapter = new MyListAdapter(this, personalInfo);
        contacs.setAdapter(adapter);

        bup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor nameno = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

                String FILENAME = "rehber";
                try {
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    while (nameno.moveToNext()) {
                        String line = nameno.getString(nameno.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) + " " +
                                nameno.getString(nameno.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                        fos.write(line.getBytes());
                        fos.flush();
                    }
                    Toast.makeText(getApplicationContext(), "Successfully Back-Up", Toast.LENGTH_LONG).show();
                    fos.close();

                } catch (IOException e) {
                    Log.e("Error", e.getMessage(), e);
                }

            }
        });

        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FileInputStream fis = openFileInput("rehber");
                    byte[] buffer = new byte[(int) fis.getChannel().size()];
                    fis.read(buffer);
                    String str = "";
                    for (byte b : buffer) str += (char) b;
                    listeleme();
                    fis.close();
                    Log.i("E", String.format("GOT: [%s]", str));
                } catch (IOException e) {
                    Log.e("Error", e.getMessage(), e);
                }
            }


        });

    }

    public void listeleme()
    {

        final ListView contacs = (ListView) findViewById(R.id.listView);
        MyListAdapter adapter = new MyListAdapter(this, personalInfo);
        contacs.setAdapter(adapter);
    }


    public void phonenoClicked(View view)
    {
        Button numberButtonToText = (Button) view;
        String buttonText = numberButtonToText.getText().toString();

        if(buttonText != null)
        {
            Uri uri = Uri.parse("tel:" + buttonText);
            Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(callIntent);
        }
    }


}
