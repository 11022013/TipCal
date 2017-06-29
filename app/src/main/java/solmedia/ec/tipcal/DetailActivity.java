package solmedia.ec.tipcal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailBill;
    TextView tvDetailTip;
    TextView tvDetailDate;

    public static final String KEY_BILL = "DetailActivity:total";
    public static final String KEY_TIP = "DetailActivity:tip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailBill = (TextView) findViewById(R.id.tvDetailTotal);
        tvDetailTip = (TextView) findViewById(R.id.tvDetailTip);
        tvDetailDate = (TextView) findViewById(R.id.tvDetailDate);

        Intent intent = getIntent();

        tvDetailBill.setText(String.valueOf(intent.getDoubleExtra(KEY_BILL, 0d)));
        tvDetailTip.setText(String.valueOf(intent.getDoubleExtra(KEY_TIP, 0d)));
    }
}
