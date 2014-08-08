package com.rex.yangtzeu.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.rex.yangtzeu.R;
import com.rex.yangtzeu.Yangtzeu;
import com.rex.yangtzeu.config.Urls;
import com.rex.yangtzeu.http.YuHttp;
import com.rex.yangtzeu.regex.CetRegex;
import com.rex.yangtzeu.regex.JwcRegex;

import java.net.URLEncoder;

/**
 * Created by rex on 2014/7/30.
 */
public class CetScore extends Activity implements
        android.view.View.OnClickListener{
    private Button get_score_btn;
    private EditText cet_number;
    private EditText stu_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cet_score);

        // 标题栏按钮动作
        get_score_btn = (Button) this.findViewById(R.id.get_score_btn);
        get_score_btn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 更改为按下时的背景图片
                    v.setBackgroundResource(R.drawable.yangtzeu_main_title);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // 改为抬起时的图片
                    v.setBackgroundResource(R.drawable.yangtzeu_title_btn_bg);
                }
                return false;
            }
        });

        get_score_btn.setOnClickListener(this);

        cet_number = (EditText) findViewById(R.id.number);
        stu_name = (EditText) findViewById(R.id.name);
    }

    /**
     * 按键事件
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 后退动画
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.back_left_in,
                    R.anim.back_right_out);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        if (view == get_score_btn) {
            if(15 != cet_number.getText().toString().length()){
                Toast.makeText(Yangtzeu.getInstance(),"准考证号为15位！",Toast.LENGTH_LONG).show();
                return;
            }
            if (stu_name.getText().toString().equals("")){
                Toast.makeText(Yangtzeu.getInstance(),"姓名不能为空！",Toast.LENGTH_LONG).show();
                return;
            }
            new NetTask().execute("cet");
        }

    }

    // Async 查分
    private class NetTask extends AsyncTask<String, Void,String> {
        String optype;
        String cet_result = "";

        protected void onPostExecute(String result) {
            if(this.optype == "cet"){ // 渲染数据
                if (CetRegex.get_error(cet_result)){
                    Toast.makeText(Yangtzeu.getInstance(),"未查到！",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Yangtzeu.getInstance(),"TODO",Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        protected String doInBackground(String... arg0) {
            this.optype = arg0[0];
            if(arg0[0] == "cet"){ // 网络获取数据
                String number = cet_number.getText().toString();
                String name = stu_name.getText().toString();

                try {
                    YuHttp.referer = Urls.cet_score;
                    String url = Urls.cet_score + "query?zkzh=" + number + "&xm=" + URLEncoder.encode(name, "utf-8");
                    cet_result = YuHttp.get(url, "utf-8");
                    // Log.i("CET",url + "\n" + cet_result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}