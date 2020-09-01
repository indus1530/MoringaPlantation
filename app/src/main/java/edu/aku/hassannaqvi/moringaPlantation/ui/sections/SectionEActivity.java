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
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionEBinding;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;
import edu.aku.hassannaqvi.moringaPlantation.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;

public class SectionEActivity extends AppCompatActivity {

    ActivitySectionEBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {

        bi.fus1q3.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.fus1q302.getId()) {
                Clear.clearAllFields(bi.fldGrpfus1q3);
            }
        }));

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

        /*JSONObject json = new JSONObject();

        json.put("formdate", bi.formdate.getText().toString());

        json.put("pid", bi.pid.getText().toString());

        json.put("fus1q1", bi.fus1q1.getText().toString());

        json.put("fus1q2", bi.fus1q201.isChecked() ? "1"
                : bi.fus1q202.isChecked() ? "2"
                :  "-1");

        json.put("fus1q3", bi.fus1q301.isChecked() ? "1"
                : bi.fus1q302.isChecked() ? "2"
                :  "-1");

        json.put("fus1q4", bi.fus1q401.isChecked() ? "1"
                : bi.fus1q402.isChecked() ? "2"
                :  "-1");

        json.put("fus1q5", bi.fus1q5.getText().toString());*/


        form.setFormDate(bi.formdate.getText().toString());

        form.setPid(bi.pid.getText().toString());

        form.setFUS1Q1(bi.fus1q1.getText().toString());

        form.setFUS1Q2(bi.fus1q201.isChecked() ? "1"
                : bi.fus1q202.isChecked() ? "2"
                : "-1");

        form.setFUS1Q3(bi.fus1q301.isChecked() ? "1"
                : bi.fus1q302.isChecked() ? "2"
                : "-1");

        form.setFUS1Q4(bi.fus1q401.isChecked() ? "1"
                : bi.fus1q402.isChecked() ? "2"
                : "-1");

        form.setFUS1Q5(bi.fus1q5.getText().toString());


    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


}