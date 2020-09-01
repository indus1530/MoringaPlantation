package edu.aku.hassannaqvi.moringaPlantation.ui.sections;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import edu.aku.hassannaqvi.moringaPlantation.R;
import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract;
import edu.aku.hassannaqvi.moringaPlantation.core.DatabaseHelper;
import edu.aku.hassannaqvi.moringaPlantation.core.MainApp;
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionBBinding;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;
import edu.aku.hassannaqvi.moringaPlantation.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;


public class SectionBActivity extends AppCompatActivity {

    ActivitySectionBBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_b);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {

        bi.s2q3.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVs2q31));

        bi.s2q597.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                bi.s2q5check.setTag("-1");
                Clear.clearAllFields(bi.s2q5check, false);
            } else {
                Clear.clearAllFields(bi.s2q5check, true);
                bi.s2q5check.setTag("0");
            }
        });
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_MF108, form.sBtoString());
        if (updcount > 0) {
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() {

        form.setS2q101(bi.s2q101.isChecked() ? "1" : "-1");
        form.setS2q101x(bi.s2q101x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q101x.getText().toString());

        form.setS2q102(bi.s2q102.isChecked() ? "2" : "-1");
        form.setS2q102x(bi.s2q102x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q102x.getText().toString());

        form.setS2q103(bi.s2q103.isChecked() ? "3" : "-1");
        form.setS2q103x(bi.s2q103x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q103x.getText().toString());

        form.setS2q104(bi.s2q104.isChecked() ? "4" : "-1");
        form.setS2q104x(bi.s2q104x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q104x.getText().toString());

        form.setS2q105(bi.s2q105.isChecked() ? "5" : "-1");
        form.setS2q105x(bi.s2q105x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q105x.getText().toString());

        form.setS2q106(bi.s2q106.isChecked() ? "6" : "-1");
        form.setS2q106x(bi.s2q106x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q106x.getText().toString());

        form.setS2q107(bi.s2q107.isChecked() ? "7" : "-1");
        form.setS2q107x(bi.s2q107x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q107x.getText().toString());

        form.setS2q108(bi.s2q108.isChecked() ? "8" : "-1");
        form.setS2q108x(bi.s2q108x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q108x.getText().toString());

        form.setS2q109(bi.s2q109.isChecked() ? "9" : "-1");
        form.setS2q109x(bi.s2q109x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q109x.getText().toString());

        form.setS2q110(bi.s2q110.isChecked() ? "10" : "-1");
        form.setS2q110x(bi.s2q110x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q110x.getText().toString());

        form.setS2q111(bi.s2q111.isChecked() ? "11" : "-1");
        form.setS2q111x(bi.s2q111x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q111x.getText().toString());

        form.setS2q112(bi.s2q112.isChecked() ? "12" : "-1");
        form.setS2q112x(bi.s2q112x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q112x.getText().toString());

        form.setS2q113(bi.s2q113.isChecked() ? "13" : "-1");
        form.setS2q113x(bi.s2q113x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q113x.getText().toString());

        form.setS2q114(bi.s2q114.isChecked() ? "14" : "-1");
        form.setS2q114x(bi.s2q114x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q114x.getText().toString());

        form.setS2q115(bi.s2q115.isChecked() ? "15" : "-1");
        form.setS2q115x(bi.s2q115x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q115x.getText().toString());

        form.setS2q116(bi.s2q116.isChecked() ? "16" : "-1");
        form.setS2q116x(bi.s2q116x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q116x.getText().toString());


        form.setS2q2(bi.s2q201.isChecked() ? "1"
                : bi.s2q202.isChecked() ? "2"
                : "-1");

        form.setS2q3(bi.s2q301.isChecked() ? "1"
                : bi.s2q302.isChecked() ? "2"
                : "-1");

        form.setS2q31(bi.s2q31.getText().toString().trim().isEmpty() ? "-1" : bi.s2q31.getText().toString());
        form.setS2q32(bi.s2q32.getText().toString().trim().isEmpty() ? "-1" : bi.s2q32.getText().toString());
        form.setS2q33(bi.s2q33.getText().toString().trim().isEmpty() ? "-1" : bi.s2q33.getText().toString());

        form.setS2q4(bi.s2q401.isChecked() ? "1"
                : bi.s2q402.isChecked() ? "2"
                : "-1");

        form.setS2q501(bi.s2q501.isChecked() ? "1" : "-1");
        form.setS2q502(bi.s2q502.isChecked() ? "2" : "-1");
        form.setS2q503(bi.s2q503.isChecked() ? "3" : "-1");
        form.setS2q504(bi.s2q504.isChecked() ? "4" : "-1");
        form.setS2q505(bi.s2q505.isChecked() ? "5" : "-1");
        form.setS2q506(bi.s2q506.isChecked() ? "6" : "-1");
        form.setS2q507(bi.s2q507.isChecked() ? "7" : "-1");
        form.setS2q508(bi.s2q508.isChecked() ? "8" : "-1");
        form.setS2q509(bi.s2q509.isChecked() ? "9" : "-1");
        form.setS2q596(bi.s2q596.isChecked() ? "96" : "-1");
        form.setS2q596x(bi.s2q596x.getText().toString().trim().isEmpty() ? "-1" : bi.s2q596x.getText().toString());
        form.setS2q596(bi.s2q597.isChecked() ? "97" : "-1");

        form.setS2q6(bi.s2q601.isChecked() ? "1"
                : bi.s2q602.isChecked() ? "2"
                : "-1");

        form.setS2q7(bi.s2q701.isChecked() ? "1"
                : bi.s2q702.isChecked() ? "2"
                : "-1");

        /*form.setS2q71(bi.s2q71a.isChecked() ? "1"
                : bi.s2q71b.isChecked() ? "2"
                : "-1");

        form.setS2q72(bi.s2q72.getText().toString().trim().isEmpty() ? "-1" : bi.s2q72.getText().toString());*/


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }

}