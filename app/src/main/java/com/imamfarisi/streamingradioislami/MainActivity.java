package com.imamfarisi.streamingradioislami;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String  arrayName[]={"Facebook",
                            "Twitter",
                            "Instagram",
                            "Line",
                            "Web",
                            "Credit"};

    private Spinner combo;
    private TextView txtDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        //inisialisasi
        combo = (Spinner) findViewById(R.id.combo);
        txtDetails = (TextView) findViewById(R.id.txtDetails);
        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.ic_add,R.drawable.ic_remove)
                .addSubMenu(Color.parseColor("#3b5998"),R.drawable.ic_facebook)
                .addSubMenu(Color.parseColor("#1da1f2"),R.drawable.ic_twitter)
                .addSubMenu(Color.parseColor("#5851db"),R.drawable.ic_insta)
                .addSubMenu(Color.parseColor("#00c300"),R.drawable.line)
                .addSubMenu(Color.parseColor("#d9e0e2"),R.drawable.web)
                .addSubMenu(Color.parseColor("#00a98f"),R.drawable.ic_credit)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Toast.makeText(MainActivity.this, " You selected "+arrayName[index], Toast.LENGTH_SHORT).show();
                       switch (index){
                           case 0:
                               Intent intent = new Intent(MainActivity.this,FacebookActivity.class);
                               startActivity(intent);
                               break;
                           case 1:
                               Intent intent1 = new Intent(MainActivity.this,TwitterActivity.class);
                               startActivity(intent1);
                               break;
                           case 2:
                               Intent intent2 = new Intent(MainActivity.this,InstagramActivity.class);
                               startActivity(intent2);
                               break;
                           case 3:
                               Intent intent3 = new Intent(MainActivity.this,LineActivity.class);
                               startActivity(intent3);
                               break;
                           case 4:
                               Intent intent4 = new Intent(MainActivity.this,WebActivity.class);
                               startActivity(intent4);
                               break;
                           case 5:
                               Intent intent5 = new Intent(MainActivity.this,CreditActivity.class);
                               startActivity(intent5);
                               break;
                       }
                    }
                });


        //set value to list
        final List<Radio> listRadio = new ArrayList<>();
        Radio radio1 = new Radio("STOP", "http://125.160.17.86:8022/;");
        Radio radio2 = new Radio("SBS FM Radio Streaming", "http://radio-fpp.undip.ac.id:8000/sbs-fpp");
        Radio radio3 = new Radio("Radio Muslim", "http://128.199.156.6/;stream/1");
        Radio radio4 = new Radio("Radio KITA Cirebon", "http://live.radiosunnah.net/;");
        Radio radio5 = new Radio("Radio Hidayah", "http://radio.hidayahfm.com:9988/;stream.mp3");
        listRadio.add(radio1);
        listRadio.add(radio2);
        listRadio.add(radio3);
        listRadio.add(radio4);
        listRadio.add(radio5);

        final String[] radioArr = new String[listRadio.size()];
        for (int i = 0; i < listRadio.size(); i++) {
            radioArr[i] = listRadio.get(i).getName();
        }

        //set value to autocomplete
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, radioArr);
        combo.setAdapter(adapter);
        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                int pos = -1;

                for (int j = 0; j < radioArr.length; j++) {
                    if (radioArr[j].equals(selection)) {
                        pos = j;
                        break;
                    }
                }

                callRadio(listRadio.get(pos).getUrl(), listRadio.get(pos).getName());
                txtDetails.setText(listRadio.get(pos).getName() + " is Now Playing");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void callRadio(String url, String name) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("name", name);
        Intent serviceOn = new Intent(this, StreamingService.class);
        serviceOn.putExtras(bundle);
        startService(serviceOn);
    }

    class Radio {
        private String name, url;

        public Radio() {
        }

        public Radio(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
