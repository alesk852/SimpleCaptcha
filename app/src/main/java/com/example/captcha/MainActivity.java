package com.example.captcha;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        CaptchaValidator captcha = new CaptchaValidator(this);

        captcha.setBackgroundColor(Color.DKGRAY);
        captcha.setTextColor(Color.WHITE);
        captcha.setRandom(10);
        captcha.show();


        captcha.setAfterValidate(new AfterValidate() {
            @Override
            public String onCheckPassed() {
                System.out.println("Rätt kod, utför massa kod");
                captcha.dismiss();
                return null;
            }

            @Override
            public String onCheckFailed() {
                System.out.println("Fel kod, utför massa kod");
                captcha.setText("Testa igen: ");
                captcha.reset();
                return null;
            }
        });

        layout.addView(captcha);
        setContentView(layout);

    }
}
