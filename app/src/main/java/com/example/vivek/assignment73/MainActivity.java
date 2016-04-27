package com.example.vivek.assignment73;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EmployeeDB employeeDB;

    TextView Name,Age;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Name = (TextView)findViewById(R.id.Name);
        Age = (TextView)findViewById(R.id.Age);
        image = (ImageView)findViewById(R.id.image);

        employeeDB = new EmployeeDB(getApplicationContext());


        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.index);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] img = stream.toByteArray();

        ContentValues employeeValues = new ContentValues();
        employeeValues.put("FIRST_NAME", "Venkat");
        employeeValues.put("LAST_NAME", "Vivek");
        employeeValues.put("AGE", 24);
        employeeValues.put("EMPLOYEE_PHOTO", img);

        employeeDB.addEmployee(employeeValues);

        employeeValues.clear();
        employeeValues = employeeDB.getEmployee();

        img = employeeValues.getAsByteArray("EMPLOYEE_PHOTO");
        Bitmap b1=BitmapFactory.decodeByteArray(img, 0, img.length);

        image.setImageBitmap(b1);

        String txt = employeeValues.getAsString("FIRST_NAME") + employeeValues.getAsString("LAST_NAME");
        Name.setText(txt);
        Age.setText(employeeValues.getAsString("AGE"));

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
