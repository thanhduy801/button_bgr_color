package com.example.buttonmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import static android.R.color.black;

public class MainActivity extends AppCompatActivity{

    Button btncolor,btnsave ;
    TextView txtcolor;
    LinearLayout bgr;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        bgr = (LinearLayout) findViewById(R.id.bgr_color);
        btncolor = (Button) findViewById(R.id.btn_color);
        btnsave = (Button) findViewById(R.id.btn_save);
        txtcolor = (TextView) findViewById(R.id.txt_color);


        initPreferences();  //gọi initPreferences


        String savedData = sharedPreferences.getString("DATA", "");
        txtcolor.setText(savedData);

        //gọi hàm lấy background đã lưu trước đó
        maubgr();

        //Bắt sự kiện khi click nút ĐỔI MÀU
        btncolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = String.valueOf(randomColor()); //random ra màu sắt
                txtcolor.setText(a);                      //gán màu đã random cho textview
                String s=txtcolor.getText().toString();   //lấy dữ liệu từ textview
                int i = Integer.parseInt(s);              //ép kiểu dữ liệu
                bgr.setBackgroundColor(i);
            }
        });


        //Bắt sự kiện khi click nút SAVE
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = txtcolor.getText().toString();
                editor.putString("DATA", data);
                editor.commit();
            }
        });
    }


    //hàm lấy màu background đã save, để hiển thị lên background khi bật app trở lại
    private void maubgr() {
        if(txtcolor.getText() == ""){

        }else {
            String s=txtcolor.getText().toString();   //lấy dữ liệu từ textview
            int i = Integer.parseInt(s);              //ép kiểu dữ liệu
            bgr.setBackgroundColor(i);
        }
    }

    //Hàm random tạo màu background
    public int randomColor()
    {
        Random random= new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256),
                random.nextInt(256));
    }
}