package se.iths.fu_nsa; 

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SpinnerAdapter;

/*Krav
Appen ska (minst) ha följande delar:
Två textrutor
Textruta 1 för vanlig text
Textruta 2 för krypterad text
Två knappar
En för att kryptera texten, textruta 1 ⇒ textruta 2
En för att dekryptera texten, textruta 2 ⇒ textruta 1
En dropdown-meny
Där man kan välja vilket chiffer som ska användas
Två chiffer
Ett som förskjuter alla bokstäver framåt ett steg
“Hejsan!” ⇒ “Ifktbo!”
Ett som översätter alla bokstäver till siffror, separerade med bindestreck (a=0)
“Hejsan!” ⇒ 7-4-9-18-0-13!”’

Utökning: spara översättning
Användaren ska kunna spara en översättning för att kunna kolla på den senare. Användaren ska då kunna klicka på en knapp för att spara de nuvarande strängarna, och det krypterade samt det okrypterade ska sparas i en lista. Användaren ska sedan kunna klicka fram listan, och klicka på en av de/krypteringar som finns där, för att den ska visas i appen.

Krav:
Knapp för att spara översättning
Det ska finnas en lista med sparade översättningar

Utökning: automatisk sparning
Appen ska spara varje översättning automatiskt, direkt när den görs.
*/



public class MainActivity extends ActionBarActivity {

	private EditText clearText;
	private EditText encryptedText;
	
	private Message message;
	private int cipher;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d( "iths", "onCreate: pre cipher = "+this.cipher  );
		cipher = Message.NUMBERS;
		Log.d( "iths", "onCreate: post cipher = "+this.cipher  );
		initialize();
		Log.d( "iths", "onCreate: post init cipher = "+this.cipher  );

		clearText = (EditText) findViewById( R.id.clearText );
		encryptedText = (EditText) findViewById( R.id.encryptedText );

		message = new Message( "Detta är text som skall krypteras.", cipher );

		clearText.setText( message.getDecryptedMessage( cipher ) );
		encryptedText.setText( message.getEncryptedMessage() );
	}
 
	
	private void initialize() {
        this.cipher = Message.NUMBERS;
 
        ActionBar actionBar = getSupportActionBar();
         
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.code_array, R.layout.spinner_item);
         
        OnNavigationListener mOnNavigationListener = new OnNavigationListener() {
               
              //String[] strings = getResources().getStringArray(R.array.code_array);
 
              @Override
              public boolean onNavigationItemSelected(int position, long itemId) {
                  
                  cipher = position;
                   
                  Log.d("Position:", Integer.toString(cipher));
                  return true;
              }
        };
         
 
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
    }
	
	
	public void encryptBtnClicked( View v ){
		Log.d( "iths", "Klickade för kryptering av text... cipher = "+this.cipher );
		message.encryptMessage( clearText.getText().toString(), this.cipher );
		encryptedText.setText( message.getEncryptedMessage() );
	}
	
	public void decryptBtnClicked( View v ){
		Log.d( "iths", "Klickade för avkryptering av text... cipher = "+this.cipher );
		message.encryptMessage( encryptedText.getText().toString(), Message.CLEARTEXT );
		clearText.setText( message.getDecryptedMessage(this.cipher)  );
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
