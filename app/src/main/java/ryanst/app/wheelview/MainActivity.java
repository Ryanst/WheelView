package ryanst.app.wheelview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import ryanst.library.WheelView;
import ryanst.library.WheelViewDialog;

public class MainActivity extends AppCompatActivity {

    private WheelView.OnWheelViewListener wheelViewListener;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        final List<String> list = Arrays.asList(
                "小学一年级", "小学二年级", "小学三年级", "小学四年级", "小学五年级",
                "初中1年级", "初中2年级", "初中3年级", "初中4年级", "初中5年级", "初中6年级");

        wheelViewListener = new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                result = item;
            }
        };

        View rootView = getLayoutInflater().inflate(R.layout.dialog_change_grade, null);
        WheelView wheelView = (WheelView) rootView.findViewById(R.id.wheelView);

        final WheelViewDialog dialog = new WheelViewDialog.Builder(this, rootView, wheelView, list)
                .setDefaultIndex(3)
                .setFlingSpeed(3)
                .setOffset(3)
                .setTextPadding(10)
                .setSelectTextColor(getResources().getColor(R.color.text_red))
                .setTextColor(getResources().getColor(R.color.text_gray))
                .setTextSize(13)
                .setWheelViewListener(wheelViewListener)
                .create();

        TextView tvCancel = (TextView) rootView.findViewById(R.id.tv_cancel);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        TextView tvConfirm = (TextView) rootView.findViewById(R.id.tv_confirm);

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                Toast.makeText(MainActivity.this, "您选择了:" + result, Toast.LENGTH_LONG).show();
            }
        });
    }
}
