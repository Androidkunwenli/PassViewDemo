package com.example.passviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button mButton;
    private MainActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mi);
        mActivity = this;
        mButton = (Button) findViewById(R.id.bt_fragment);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassFragemnt passFragemnt = new PassFragemnt(mActivity);
                passFragemnt.showPopupWindow(mButton);
            }
        });
    }


}
