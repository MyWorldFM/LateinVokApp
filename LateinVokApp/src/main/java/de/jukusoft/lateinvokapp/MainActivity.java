package de.jukusoft.lateinvokapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import de.jukusoft.lateinvokapp.database.LateinDataBase;


public class MainActivity extends ActionBarActivity {

    protected ImageButton start_button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.start_button = (ImageButton) this.findViewById(R.id.startButton);

        LateinDataBase database = new LateinDataBase(this);

        //Datenbank laden
        database.loadDataBase();

        //Punktzahl setzen
        this.updatePoints();

        //Service starten
        // use this to start and trigger a service
        Intent i= new Intent(this.getApplicationContext(), LateinVokAppService.class);
        // potentially add data to the intent
        i.putExtra("KEY1", "Value to be used by the service");
        this.getApplicationContext().startService(i);

        //http://www.whatthedroid.de/2011/10/hintergrundprozesse-in-android-teil-1-started-services/

        this.start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_SHORT);
            }
        });
    }

    public void updatePoints () {
        TextView pointsView = (TextView) this.findViewById(R.id.pointsView);
        pointsView.setText(LateinDataBase.getInstance().getPoints() + "");
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
