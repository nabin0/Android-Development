package com.nabin.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
    private boolean headsetPluggedIn = false;

    @Override
    public void onReceive(Context context, Intent intent) { // Receives the System broadcast in this method
        String intentAction = intent.getAction();
        if (intentAction != null) {
            String toastMessage = "Unknown intent action.";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected";
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    int headSetState = intent.getIntExtra("state", -1);

                    if (headsetPluggedIn && headSetState == 0) {
                        toastMessage = "Headset unplugged";
                        headsetPluggedIn = false;
                    } else if (!headsetPluggedIn && headSetState == 1) {
                        toastMessage = "Headset Plugged In";
                        headsetPluggedIn = true;
                    }
                    break;

                case MainActivity.ACTION_CUSTOM_BROADCAST:
                    toastMessage = "Custom broadcast received";
                    break;
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}