package mr_immortalz.com.stereoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import mr_immortalz.com.stereoview.custom.StereoView;

/**
 * Created by Mr_immortalZ on 2016/7/15.
 * email : mr_immortalz@qq.com
 */
public class SettingActivity extends AppCompatActivity {

    private StereoView stereoView1;
    private StereoView stereoView2;
    private SeekBar sbAngle;
    private TextView tvAngle;
    private Button btnNext;
    private Button btnPre;
    private Button btnSelect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initStereoView();
        sbAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                stereoView1.setAngle(i);
                tvAngle.setText("设置3D旋转页面夹角,当前夹角 " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stereoView1.toPre();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stereoView1.toNext();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stereoView1.setItem(3);
            }
        });
    }

    private void initView() {
        stereoView1 = (StereoView) findViewById(R.id.stereoView1);
        stereoView2 = (StereoView) findViewById(R.id.stereoView2);
        sbAngle = (SeekBar) findViewById(R.id.sb_angle);
        tvAngle = (TextView) findViewById(R.id.tv_angle);
        btnPre = (Button) findViewById(R.id.btn_pre);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnSelect = (Button) findViewById(R.id.btn_select);
    }

    private void initStereoView() {
        stereoView1.setResistance(4f)//设置滑动时阻力
                .setInterpolator(new BounceInterpolator())//设置
                .setStartScreen(2);//设置开始时item，注意不能是开头和结尾
        stereoView2.setCan3D(false);
    }
}
