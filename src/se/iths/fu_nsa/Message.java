package se.iths.fu_nsa;

import android.util.Log;

public class Message {

	public static final int CLEARTEXT  = 0;
	public static final int NUMBERS    = 1;
	public static final int SHIFTRIGHT = 2;
	public static final int XOR        = 3;
	public static final int BASE64     = 4;


	private String msg;

	public Message(){

		this.msg = "";
	}
	
	public Message( String text, int encoding ){

		this.msg = encryptMessage(text, encoding);
	}

	
	public String encryptMessage( String msg, int encoding ){
		Log.d( "iths", "Encrypting... '"+msg+"' with encoding "+encoding );
		String result="";
		switch( encoding ){
		case CLEARTEXT:
			result = msg;
			break;
		case SHIFTRIGHT:
			result = shiftRight(msg);
			break;
		case NUMBERS:
			result = toNumbers(msg);
			break;
		default:
			// return a frikkin error here.
		}
		
		this.msg = result;

		return result;
	}
	
	public String getEncryptedMessage(){
		return this.msg;
	}

	public String getDecryptedMessage( int encoding ){
		Log.d( "iths", "Decrypting... '"+this.msg+"' with encoding "+encoding );

		String result = "";

		switch( encoding ){
		case CLEARTEXT:
			result = this.msg;
			break;
		case SHIFTRIGHT:
			result = shiftLeft( this.msg );
			break;
		case NUMBERS:
			result = fromNumbers( this.msg );
			break;
		default:
			// return a frikkin error here.
		}
		
		return result;
	}


	private String shiftRight( String msg ){
		
		String result="";
		
		for(int i=0; i<msg.length(); i++ ){
			result += (char)(msg.charAt(i)+1);
		}

		return result;
	}
	
	private String shiftLeft( String msg ){
		
		String result="";
		
		for(int i=0; i<msg.length(); i++ ){
			result += (char)(msg.charAt(i)-1);
		}

		return result;
	}

	private String toNumbers( String msg ){
		
		String result = "";
		
		for(int i=0; i<msg.length(); i++ ){
			result += (int)msg.charAt(i)-32 + " ";
		}
		
		Log.d( "iths", "Siffer-kodning = " + result );

		return result;
	}

	
	private String fromNumbers( String msg ){
		
		String result="";
		char ch = 255;
		int   i = 0;

		while(ch != 0 ){
			ch = getCharNumberAt(msg, i++);
			result += ch;
		}
		
		return result;
	}
	
	private String fromXOR( String msg ){
		// TODO: add frikkin code here....
		return "nil";
	}
	
	
	private String toXOR( String msg){
		// TODO: coding doiding moiling....
		return "nil";
	}
	
	private char getCharNumberAt( String msg, int index ){

		int msgIndex  =  0;
		int tmpIndex = 0;
		String number = "";

		// First character...
		if( index == 0){
			number = msg.substring(0, msg.indexOf(' ') );
		// Every number from this point has space on both sides.
		}else{
			// 
			for( int i=0; i<index; i++ ){
				msgIndex = msg.indexOf( ' ', msgIndex)+1;
				if( msgIndex == -1 ) return 0;
			}

			tmpIndex = msg.indexOf( ' ', msgIndex);
			
			if( tmpIndex == -1){
				return 0;
			}else{
				number = msg.substring(msgIndex, tmpIndex);
			}
		}

		try{
			return (char) (Integer.parseInt(number)+32);
		}catch( NumberFormatException e){
			return 0;
		}

	}

}
