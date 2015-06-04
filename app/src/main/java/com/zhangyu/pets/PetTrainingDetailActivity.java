package com.zhangyu.pets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zhangyu.pets.fragment.PetTrainingDetailActivityFragment;
import com.zhangyu.pets.utils.Consts;


public class PetTrainingDetailActivity extends ActionBarActivity {

    private String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pet_training_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        courseId = bundle.getString(Consts.INTENT_URL);
        String []tmp = null;
        tmp = courseId.split("/");
        for (int i = 0; i < tmp.length; i++) {
            courseId = tmp[i];
        }
        PetTrainingDetailActivityFragment fragment =
                PetTrainingDetailActivityFragment.newInstance(
                        this.getApplication().getApplicationContext(), courseId);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment).commit();
    }

    public String getCourseId() {
        return courseId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pet_training_detail, menu);
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
