package edu.aku.hassannaqvi.moringaPlantation.ui.sections;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.moringaPlantation.R;
import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract;
import edu.aku.hassannaqvi.moringaPlantation.core.DatabaseHelper;
import edu.aku.hassannaqvi.moringaPlantation.core.MainApp;
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionMfBinding;
import edu.aku.hassannaqvi.moringaPlantation.models.Form;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;


public class SectionMFActivity extends AppCompatActivity {

    ActivitySectionMfBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_mf);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {

        bi.mf105.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpCVmf106);
            Clear.clearAllFields(bi.fldGrpCVmf107);
            Clear.clearAllFields(bi.fldGrpCVmf108);
            bi.fldGrpCVmf106.setVisibility(View.GONE);
            bi.fldGrpCVmf107.setVisibility(View.GONE);
            bi.fldGrpCVmf108.setVisibility(View.GONE);

            if (i == bi.mf10501.getId()) {
                bi.fldGrpCVmf107.setVisibility(View.VISIBLE);
            } else if (i == bi.mf10502.getId()) {
                bi.fldGrpCVmf106.setVisibility(View.VISIBLE);
            } else if (i == bi.mf10503.getId()) {
                bi.fldGrpCVmf108.setVisibility(View.VISIBLE);
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
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        form.setFormtype("Follow Up");
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setMf101(bi.mf101.getText().toString().trim().isEmpty() ? "-1" : bi.mf101.getText().toString());

        form.setMf102(bi.mf102.getText().toString().trim().isEmpty() ? "-1" : bi.mf102.getText().toString());

        form.setMf103(bi.mf103.getText().toString().trim().isEmpty() ? "-1" : bi.mf103.getText().toString());

        form.setMf104(bi.mf104.getText().toString().trim().isEmpty() ? "-1" : bi.mf104.getText().toString());

        form.setMf105(bi.mf10501.isChecked() ? "1"
                : bi.mf10502.isChecked() ? "2"
                : bi.mf10503.isChecked() ? "3"
                : bi.mf10504.isChecked() ? "4"
                : bi.mf10505.isChecked() ? "5"
                : bi.mf10506.isChecked() ? "6"
                : "-1");

        form.setMf106(bi.mf10601.isChecked() ? "1"
                : bi.mf10602.isChecked() ? "2"
                : bi.mf10603.isChecked() ? "3"
                : bi.mf10604.isChecked() ? "4"
                : bi.mf10605.isChecked() ? "5"
                : bi.mf10696.isChecked() ? "96"
                : "-1");
        form.setMf106x(bi.mf10696x.getText().toString().trim().isEmpty() ? "-1" : bi.mf10696x.getText().toString());

        form.setMf107(bi.mf107.getText().toString().trim().isEmpty() ? "-1" : bi.mf107.getText().toString());

        form.setMf108(bi.mf10801.isChecked() ? "1"
                : bi.mf10802.isChecked() ? "2"
                : bi.mf10896.isChecked() ? "96"
                : "-1");
        form.setMf108x(bi.mf10896x.getText().toString().trim().isEmpty() ? "-1" : bi.mf10896x.getText().toString());
        MainApp.setGPS(this);


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    /*public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }*/

}