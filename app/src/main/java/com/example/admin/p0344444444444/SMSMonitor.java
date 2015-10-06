package com.example.admin.p0344444444444;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMSMonitor extends BroadcastReceiver {
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static String sender = "";
    public static String body = "";
    public static String timeSender = "";
    String rowTimeDate;
    Date d;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        d = new Date();

        if (bundle != null) {

            //retrieve the SMS message received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            rowTimeDate = format1.format(d);
            StringBuilder bodyText = new StringBuilder();

            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str =   msgs[i].getOriginatingAddress();
                sender = str;
                bodyText.append(msgs[i].getMessageBody());
            }

            body = bodyText.toString();
            str = "SMS from " + str;

            //display the new SMS message
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(sender));
            String projection[] = new String[]{ContactsContract.Data.DISPLAY_NAME};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

            if (cursor.moveToFirst()) {
                sender = cursor.getString(0);
                timeSender = "SMS from " + sender + " " + rowTimeDate;
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                ManController.write(context, '"' + sender + '"',
                        '"' + timeSender + '"',
                        '"' + body + '"');
            } else {
                timeSender = "SMS from " + sender + " " + rowTimeDate;
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                ManController.write(context, '"' + sender + '"',
                        '"' + timeSender + '"',
                        '"' + body + '"');
            }
        }
    }
}
