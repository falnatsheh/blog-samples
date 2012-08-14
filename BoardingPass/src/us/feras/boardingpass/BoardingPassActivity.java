package us.feras.boardingpass;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BoardingPassActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boarding_pass);
		Button showPassBtn = (Button) findViewById(R.id.showPassBtn);
		final EditText originEditText = (EditText) findViewById(R.id.originEditText);
		final EditText destinationEditText = (EditText) findViewById(R.id.destinationEditText);
		showPassBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (originEditText.getText().length() != 0 && destinationEditText.getText().length() != 0) { 
					showBoardingPassNotif(originEditText.getText().toString(), destinationEditText.getText().toString());
				} else {
					Toast.makeText(BoardingPassActivity.this, "Please fill out TO and FROM fileds", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_boarding_pass, menu);
		return true;
	}

	public void showBoardingPassNotif(String origin, String destination) {
		RemoteViews bpView = new RemoteViews(this.getPackageName(), R.layout.notif_boarding_pass);
		bpView.setTextViewText(R.id.origin_text_view, origin);
		bpView.setTextViewText(R.id.destination_text_view, destination);
		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
		Notification notif = new Notification.Builder(this).setContentTitle("Boarding pass").setContentText("Click for more info").setSmallIcon(R.drawable.ic_launcher).build(); 
		notif.flags |= Notification.FLAG_AUTO_CANCEL;
		notif.bigContentView = bpView;
		nm.notify(0, notif);
	}

}
