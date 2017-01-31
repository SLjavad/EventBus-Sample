package sl.javad.com.hw2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends Activity {

    EditText ed1;
    EditText ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ed1 = (EditText) findViewById(R.id.ed1);
         ed2 = (EditText) findViewById(R.id.ed2);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void btn1Click(View v)
     {
         SharedPreferences sh = getSharedPreferences("javad",MODE_APPEND);
         SharedPreferences.Editor e = sh.edit();
         e.putInt("ED1", Integer.parseInt(ed1.getText().toString()) );
         e.commit();
         Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();

     }

    @Subscribe
    public void onMessageEvent(String event)
    {
        ed2.setText(event);
    }

    public  void btn2Click(View v)
    {
        Intent intent = new Intent(getApplicationContext(),myService.class);
        intent.putExtra("int",Integer.parseInt(ed1.getText().toString()));
        startService(intent);
    }

}

