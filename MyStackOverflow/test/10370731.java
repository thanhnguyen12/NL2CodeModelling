public class ABC {
	public void DEF() {
	   String smsBody="Sms Body";
	Intent sendIntent = new Intent(Intent.ACTION_VIEW);
	sendIntent.putExtra("sms_body", smsBody); 
	sendIntent.setType("vnd.android-dir/mms-sms");
	startActivity(sendIntent);
	}
	
	public void sendSms(final String receiverNumber, final String smsBody) {
    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendTextMessage(receiverNumber, null, smsBody, null, null);        
}
	
	public void FGH() {
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    sharingIntent.setType("text/plain");
    String shareBody = "Here is the share content body";
    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
    // what's wrong with this, same method is called 2 times => AST cannot parsed
//    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
	}
}