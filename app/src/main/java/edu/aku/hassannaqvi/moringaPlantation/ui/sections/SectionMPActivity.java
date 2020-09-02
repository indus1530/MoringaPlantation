package edu.aku.hassannaqvi.moringaPlantation.ui.sections;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.moringaPlantation.R;
import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract;
import edu.aku.hassannaqvi.moringaPlantation.core.DatabaseHelper;
import edu.aku.hassannaqvi.moringaPlantation.core.MainApp;
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionMpBinding;
import edu.aku.hassannaqvi.moringaPlantation.models.Form;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;


public class SectionMPActivity extends AppCompatActivity {

    ActivitySectionMpBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_mp);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {
        /*bi.a06.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.lla07));
        bi.a07.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.lla08));*/
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
        form.setFormtype("MP Form");
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setMf101(bi.mp101.getText().toString().trim().isEmpty() ? "-1" : bi.mp101.getText().toString());

        form.setMf102(bi.mp102.getText().toString().trim().isEmpty() ? "-1" : bi.mp102.getText().toString());

        form.setMf103(bi.mp103.getText().toString().trim().isEmpty() ? "-1" : bi.mp103.getText().toString());

        form.setMf104(bi.mp104.getText().toString().trim().isEmpty() ? "-1" : bi.mp104.getText().toString());

        form.setMf105(bi.mp105.getText().toString().trim().isEmpty() ? "-1" : bi.mp105.getText().toString());

        form.setMf106(bi.mp106.getText().toString().trim().isEmpty() ? "-1" : bi.mp106.getText().toString());


        form.setMf107(bi.mp10701.isChecked() ? "1"
                : bi.mp10702.isChecked() ? "2"
                : bi.mp10703.isChecked() ? "3"
                : bi.mp1074.isChecked() ? "4"
                : bi.mp10705.isChecked() ? "5"
                : bi.mp10706.isChecked() ? "6"
                : bi.mp10707.isChecked() ? "7"
                : bi.mp10708.isChecked() ? "8"
                : bi.mp10709.isChecked() ? "9"
                : bi.mp10710.isChecked() ? "10"
                : bi.mp10711.isChecked() ? "11"
                : "-1");
        form.setMf107x(bi.mp10711x.getText().toString().trim().isEmpty() ? "-1" : bi.mp10711x.getText().toString());

        form.setMf108(bi.mp108.getText().toString().trim().isEmpty() ? "-1" : bi.mp108.getText().toString());
        MainApp.setGPS(this);


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    /*public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }*/

}