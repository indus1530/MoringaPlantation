package edu.aku.hassannaqvi.moringaPlantation.ui.sections;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.moringaPlantation.R;
import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract;
import edu.aku.hassannaqvi.moringaPlantation.core.DatabaseHelper;
import edu.aku.hassannaqvi.moringaPlantation.core.MainApp;
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionCBinding;
import edu.aku.hassannaqvi.moringaPlantation.models.Form;
import edu.aku.hassannaqvi.moringaPlantation.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;


public class SectionCActivity extends AppCompatActivity {

    ActivitySectionCBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_c);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {
        bi.kcs1q2.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVkcs1q201Title));
        bi.kcs1q3.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVkcs1q3Title));


        //     bi.kcs1q2n.setOnClickListener((radioGroup, i)) Clear.clearAllFields(bi.fldGrpCVkcs1q3);
        /* bi.a06.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.lla07));
        bi.a07.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.lla08));*/
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, SectionDActivity.class));
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


    private void SaveDraft() throws JSONException {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        form.setFormdate(form.getSysdate());
        form.setPid(bi.pid.getText().toString());
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        JSONObject json = new JSONObject();

        json.put("formdate", bi.formdate.getText().toString());

        json.put("pid", bi.pid.getText().toString());

        json.put("kcs1q1", bi.kcs1q1y.isChecked() ? "1"
                : bi.kcs1q1n.isChecked() ? "2"
                : "-1");

        json.put("kcs1q2", bi.kcs1q2y.isChecked() ? "1"
                : bi.kcs1q2n.isChecked() ? "2"
                : "-1");

        json.put("kcs1q201", bi.kcs1q201.isChecked() ? "1" : "-1");

        json.put("kcs1q202", bi.kcs1q202.isChecked() ? "2" : "-1");

        json.put("kcs1q203", bi.kcs1q203.isChecked() ? "3" : "-1");

        json.put("kcs1q204", bi.kcs1q204.isChecked() ? "4" : "-1");

        json.put("kcs1q205", bi.kcs1q205.isChecked() ? "5" : "-1");

        json.put("kcs1q206", bi.kcs1q206.isChecked() ? "6" : "-1");

        json.put("kcs1q207", bi.kcs1q207.isChecked() ? "7" : "-1");

        json.put("kcs1q208", bi.kcs1q208.isChecked() ? "8" : "-1");

        json.put("kcs1q209", bi.kcs1q209.isChecked() ? "9" : "-1");

        json.put("kcs1q2010", bi.kcs1q2010.isChecked() ? "10" : "-1");

        json.put("kcs1q2011", bi.kcs1q2011.isChecked() ? "11" : "-1");

        json.put("kcs1q2012", bi.kcs1q2012.isChecked() ? "12" : "-1");

        json.put("kcs1q2013", bi.kcs1q2013.isChecked() ? "13" : "-1");

        json.put("kcs1q2014", bi.kcs1q2014.isChecked() ? "14" : "-1");

        json.put("kcs1q2015", bi.kcs1q2015.isChecked() ? "15" : "-1");

        json.put("kcs1q2016", bi.kcs1q2016.isChecked() ? "16" : "-1");

        json.put("kcs1q3", bi.kcs1q3y.isChecked() ? "1"
                : bi.kcs1q3n.isChecked() ? "2"
                : "-1");

        json.put("kcs1q301", bi.kcs1q301.isChecked() ? "1" : "-1");

        json.put("kcs1q302", bi.kcs1q302.isChecked() ? "2" : "-1");

        json.put("kcs1q303", bi.kcs1q303.isChecked() ? "3" : "-1");

        json.put("kcs1q304", bi.kcs1q304.isChecked() ? "4" : "-1");

        json.put("kcs1q305", bi.kcs1q305.isChecked() ? "5" : "-1");

        json.put("kcs1q306", bi.kcs1q306.isChecked() ? "6" : "-1");

        json.put("kcs1q307", bi.kcs1q307.isChecked() ? "7" : "-1");

        json.put("kcs1q308", bi.kcs1q308.isChecked() ? "8" : "-1");

        json.put("kcs1q396", bi.kcs1q396.isChecked() ? "96" : "-1");

        json.put("kcs1q396x", bi.kcs1q396x.getText().toString());
        json.put("kcs1q3010", bi.kcs1q3010.isChecked() ? "10" : "-1");


        MainApp.setGPS(this);

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }

}