package sl.javad.com.hw2;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by javad on 1/18/2017.
 */

public class myService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public int SUM(int ed1) {
        SharedPreferences sh = getSharedPreferences("javad", MODE_APPEND);
        return (sh.getInt("ED1",0) + ed1);
    }

    int ab;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ab = intent.getIntExtra("int",0);
        EventBus.getDefault().post(new String(String.valueOf(SUM(ab))));
        return START_STICKY;
    }
}
