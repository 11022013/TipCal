package solmedia.ec.tipcal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText inputBill;
    Button bntSubmit;
    EditText inputPercentage;
    TextView tvTip;

    private final int TIP_STEP_CHANGE = 1;
    private final int DEFAULT_TIP_PERCENTAGE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBill = (EditText) findViewById(R.id.inputBill);
        inputPercentage = (EditText) findViewById(R.id.inputPercentage);
        tvTip = (TextView) findViewById(R.id.tvTip);
        bntSubmit = (Button) findViewById(R.id.btnSubmit);

        bntSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strBill = inputBill.getText().toString().trim();
                if(!strBill.isEmpty()) {
                    double bill = Double.parseDouble(strBill);
                    double tip = bill * (getTipPercentage() / 100f);
                    String strTip = String.format(getString(R.string.main_format_tip), tip);
                    tvTip.setVisibility(View.VISIBLE);
                    tvTip.setText(strTip);
                }
            }
        });
    }

    private int getTipPercentage() {
        return DEFAULT_TIP_PERCENTAGE;
    }
}
