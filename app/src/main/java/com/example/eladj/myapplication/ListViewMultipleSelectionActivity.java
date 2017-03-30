package com.example.eladj.myapplication;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewMultipleSelectionActivity extends Activity implements OnClickListener {

    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_multiple_selection);
        findViewsById();

        String[] activities = getResources().getStringArray(R.array.AbroadActivities);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, activities);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        button.setOnClickListener(this);
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);
    }

    public void onClick(View v) {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add activities if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }
        if(selectedItems.size() > 3){
            Toast.makeText(ListViewMultipleSelectionActivity.this, "Please choose up to 3 activities from the list",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            String[] outputStrArr = new String[selectedItems.size()];

            for (int i = 0; i < selectedItems.size(); i++) {
                outputStrArr[i] = selectedItems.get(i);
            }

//        Intent intent = new Intent(getApplicationContext(),
//                ResultActivity.class);

            // Create a bundle object
            Bundle b = new Bundle();
            b.putStringArray("selectedItems", outputStrArr);

            // Add the bundle to the intent.
            //intent.putExtras(b);

            // start the ResultActivity
            //startActivity(intent);
        }
    }
}