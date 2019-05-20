package org.ipv8.android;

import android.os.Binder;
import android.os.IBinder;
import android.content.Intent;
import android.content.Context;
import org.kivy.android.PythonService;

public class ServiceIpv8 extends PythonService {
	/**
	 * Binder given to clients
	 */
    private final IBinder mBinder = new ServiceIpv8Binder();

    

    

    public static void start(Context ctx, String pythonServiceArgument) {
        String argument = ctx.getFilesDir().getAbsolutePath() + "/app";
        Intent intent = new Intent(ctx, ServiceIpv8.class);
        intent.putExtra("androidPrivate", argument);
        intent.putExtra("androidArgument", argument);
        intent.putExtra("serviceEntrypoint", "Ipv8.py");
        intent.putExtra("serviceTitle", "Ipv8");
        intent.putExtra("serviceDescription", "");
        intent.putExtra("pythonName", "Ipv8");
        intent.putExtra("pythonHome", argument);
        intent.putExtra("androidUnpack", argument);
        intent.putExtra("pythonPath", argument + ":" + argument + "/lib");
        intent.putExtra("pythonServiceArgument", pythonServiceArgument);
        ctx.startService(intent);
    }

    public static void stop(Context ctx) {
        Intent intent = new Intent(ctx, ServiceIpv8.class);
        ctx.stopService(intent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * Class used for the client Binder. Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class ServiceIpv8Binder extends Binder {
    	ServiceIpv8 getService() {
            // Return this instance of ServiceIpv8 so clients can call public methods
            return ServiceIpv8.this;
        }
    }
}