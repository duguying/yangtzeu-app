package com.rex.yangtzeu.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.rex.yangtzeu.R;

/**
 * 课表
 * Created by rex on 2014/8/8.
 */
public class Kebiao extends Activity implements android.view.View.OnClickListener  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kebiao);
    }

    @Override
    public void onClick(View view) {

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
}
