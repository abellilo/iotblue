package live.lilo.iotblue;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.BroadcastReceiver;
import android.widget.Toast;

import com.paypad.initialization.Activation;
import com.paypad.cardreader.facade.PinpadFacade;
import com.paypad.cardreader.facade.PinpadFacade;
import com.paypad.impl.Paypad;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
    PinpadFacade pinpadFacade;
    Handler handler;
    Paypad paypad;
    private MyBroadcastReceiver myBroadcastReceiver;
    private ProgressDialog mProgressDialog;
}

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent != null && intent.getAction() != null) {
            String s = intent.getAction();
            if (s.equals("com.esl.paypadlib")) {

                String result = intent.getStringExtra("response");
                String[] resultarray = intent
                        .getStringArrayExtra("responsearray");
                String reversalResult = intent.getStringExtra("reversalResult");
                String encryptedMessage = intent.getStringExtra("encryptedMessage");

                if (result.equals("activatecomplete")) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Activated Successfully", Toast.LENGTH_LONG)
                            .show();
                } else if (result.equals("invalidcode")) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Invalid Activation Code", Toast.LENGTH_LONG)
                            .show();
                } else if (result.equals("failedactivation")) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Activation not Successful", Toast.LENGTH_LONG)
                            .show();
                } else if (result.equals("connected")) {
//						mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Device is connected", Toast.LENGTH_LONG)
                            .show();
                } else if (result.equals("startInitializeprogress")) {
                    mProgressDialog.setMessage("Initializing...");
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog
                            .setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.show();

                } else if (result.equals("Initializecomplete")) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Initialized Successfully", Toast.LENGTH_LONG)
                            .show();
                } else if (result.equals("initializenotcomplete")) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Initialization not Successful",
                            Toast.LENGTH_LONG).show();

                } else if (result.equals("pinpadProcessing")) {
                    mProgressDialog.setMessage("Please wait...");

                } else if (result.equals("enterPIN")) {
                    mProgressDialog.setMessage("Please, Enter PIN");


                } else if (result.equals("PINentered")) {
                    mProgressDialog.setMessage("PIN Entered");

                } else if (result.equals("nibssProcessing")) {
                    mProgressDialog
                            .setMessage("Please wait...\n Processing Transaction");

                } else if (result.equals("errorTranx")) {
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Critical error occured", Toast.LENGTH_LONG)
                            .show();
                } else if (result.equals("transactionresponse")) {
                    mProgressDialog.dismiss();
                    /*
                     * The returned String array has the following element
                     * with the indices below 0-Response Code 1-Response
                     * 2-Terminal ID 3-PAN 4-Card holder name 5-Date
                     * 6-Amount 7-Transaction ID 8-RRN
                     */
                    for (int i = 0; i < resultarray.length; i++) {
                        Toast.makeText(getApplicationContext(),
                                resultarray[i], Toast.LENGTH_SHORT).show();
                    }
                } else if (result.equals("reversal")) {
                    mProgressDialog.dismiss();

                    Toast.makeText(getApplicationContext(),
                            reversalResult, Toast.LENGTH_SHORT).show();

                } else if (result.equals("fcmbresponse")) {
                    mProgressDialog.dismiss();

//                        byte[] dectryptArray = encryptedMessage.getBytes();
//                        byte[] decarray = Base64.decodeBase64(dectryptArray);
//                        String message = null;
//                        try {
//                            message = new String(decarray,"UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//                        Log.i("Enc:", encryptedMessage);
//                        Log.i("Msg:", message);
//
//                        Toast.makeText(getApplicationContext(),
//                                message, Toast.LENGTH_LONG).show();

                }
            }
        }

    }
}