package com.example.captcha;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class CaptchaValidator extends LinearLayout{

    TextView textView;
    EditText editText;
    Button button;

    String text = "Skriv integern med siffror: ";
    String buttonText;
    AfterValidate afterValidate = null;
    View layout = LayoutInflater.from(getContext()).inflate(R.layout.captcha,(ViewGroup)getParent());

    int code = 100;
    int backgroundColor = Color.BLACK;
    int textColor;
    int captchaCode;
    Random rand = new Random();


    public  CaptchaValidator(Context context) {
        super(context);
        setupUI();
    }
    private void setupUI(){

        textView = layout.findViewById(R.id.textView);
        editText = layout.findViewById(R.id.editText);
        button = layout.findViewById(R.id.button2);

        textView.setBackgroundColor(backgroundColor);
        editText.setBackgroundColor(backgroundColor);
        button.setBackgroundColor(backgroundColor);

        captchaCode = rand.nextInt(code);

        textView.setText(text+Integer.toString(captchaCode));

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = editText.getText().toString();
                validate(current,captchaCode);
            }
        });
    }

    private void validate(String input, int captchaNum){
        CharSequence x = Integer.toString(captchaNum);
        if (input.contentEquals(x)){
            afterValidate.onCheckPassed();
        }
        else{
            afterValidate.onCheckFailed();
        }
    }

    public String getInput(){
        return editText.getText().toString();
    }
    public void setRandom(int code) {
        this.code = code;
        captchaCode = rand.nextInt(code);
        textView.setText(text+captchaCode);
    }
    public int getCode() {
        return code;
    }

    public void setAfterValidate(AfterValidate afterValidate) {
        this.afterValidate = afterValidate;
    }
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        textView.setBackgroundColor(backgroundColor);
        editText.setBackgroundColor(backgroundColor);
        button.setBackgroundColor(backgroundColor);
    }
    public int getBackgroundColor() {
        return backgroundColor;
    }
    public void reset(){
        captchaCode = rand.nextInt(code);
        textView.setText(text+captchaCode);
    }
    public void show(){
        addView(layout);
    }
    public void dismiss(){
        removeAllViews();
    }

    //fixa
    public void setText(String text){
        this.text = text;
        textView.setText(text+captchaCode);
    }
    public void setButtonText(String buttonText){
        this.buttonText = buttonText;
        button.setText(buttonText);
    }
    public void setTextColor(int textColor){
        this.textColor = textColor;
        textView.setTextColor(textColor);
        editText.setTextColor(textColor);
        button.setTextColor(textColor);
    }
}
