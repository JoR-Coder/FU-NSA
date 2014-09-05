package se.iths.fu_nsa;

import java.util.ArrayList;
import java.util.Arrays;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class StoredMessageActivity extends ActionBarActivity {

	private ListView list;
	private ArrayList<String> messages;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stored_message);

		initialize();
	}
 
	
	private void initialize() {

		// SharedPrefs here... NOOOOW.
		SharedPreferences prefs = this.getSharedPreferences( "se.iths.fu_nsa-PREFS", Context.MODE_PRIVATE);

	    messages = new ArrayList<String>( convertToArray(prefs.getString("messages", null)));

	    if ( messages == null ) {
	    	messages = new ArrayList<String>();
	    }

	    if( messages.isEmpty() ){
	    	messages.add("");
	    }

		list =(ListView)findViewById(R.id.message_list);
		
		ListAdapter adapter = new ArrayAdapter<String> (this, R.layout.list_item, R.id.txt, messages);
		list.setAdapter(adapter);
        
		list.setOnItemClickListener(new OnItemClickListener() {
			 
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          
            int  itemValue = position;
             
            Intent intent = new Intent (StoredMessageActivity.this, MainActivity.class);
     		intent.putExtra("value",itemValue);
     		startActivity(intent);
     		
     		finish();
             
            }
		});
		
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stored_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	

	private ArrayList<String> convertToArray(String string) {

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(string.split(",")));
        return list;
    }
}
