package solmedia.ec.tipcal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import solmedia.ec.tipcal.fragments.TipHistoriListFragmentListener;
import solmedia.ec.tipcal.fragments.TipHistoryListFragment;
import solmedia.ec.tipcal.models.TipModel;

public class MainActivity extends AppCompatActivity {

    EditText inputBill;
    Button bntSubmit;
    Button bntIncrease;
    Button btnDecrease;
    Button btnClear;
    EditText inputPercentage;
    TextView tvTip;

    private final int TIP_STEP_CHANGE = 1;
    private final int DEFAULT_TIP_PERCENTAGE = 10;
    private TipHistoriListFragmentListener fragmentListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputBill = (EditText) findViewById(R.id.inputBill);
        inputPercentage = (EditText) findViewById(R.id.inputPercentage);
        tvTip = (TextView) findViewById(R.id.tvTip);

        bntSubmit = (Button) findViewById(R.id.btnSubmit);
        bntIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnClear = (Button) findViewById(R.id.btnClear);

        TipHistoryListFragment fragment = (TipHistoryListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fgHistoryTips);
        fragment.setRetainInstance(true);

        fragmentListener = fragment;


        bntSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strBill = inputBill.getText().toString().trim();
                if (!strBill.isEmpty()) {

                    double bill = Double.parseDouble(strBill);
                    double tip = bill * (getTipPercentage() / 100f);
                    String strTip = String.format(getString(R.string.main_format_tip), tip);
                    tvTip.setVisibility(View.VISIBLE);
                    tvTip.setText(strTip);

                    TipModel tipModel = new TipModel();
                    tipModel.setBill(bill);
                    tipModel.setTip(tip);
                    tipModel.setTimeStamp(new Date());

                    fragmentListener.addTip(tipModel);
                }
            }
        });

        bntIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTipChange(TIP_STEP_CHANGE);
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTipChange(-TIP_STEP_CHANGE);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.clearList();
            }
        });
    }

    private void handleTipChange(int change) {
        int tipPorcentage = getTipPercentage();

        tipPorcentage += change;
        if (tipPorcentage > 0) {
            inputPercentage.setText(String.valueOf(tipPorcentage));
        }
    }

    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strPercentage = inputPercentage.getText().toString().trim();
        if (!strPercentage.isEmpty()) {
            tipPercentage = Integer.parseInt(strPercentage);
        }

        return tipPercentage;
    }
}
