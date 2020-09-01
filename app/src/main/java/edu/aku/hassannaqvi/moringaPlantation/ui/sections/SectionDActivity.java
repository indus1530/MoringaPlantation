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
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionDBinding;
import edu.aku.hassannaqvi.moringaPlantation.models.Form;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;
import edu.aku.hassannaqvi.moringaPlantation.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;

public class SectionDActivity extends AppCompatActivity {
    ActivitySectionDBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d);
        bi.setCallback(this);
        setupSkip();

    }

    private void setupSkip() {
        bi.kcs2q4.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVkcs2q4Title));
        bi.kcs2q5.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVkcs2q5Title));

        /*bi.a06.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.lla07));
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

    private void SaveDraft() throws JSONException {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        form.setFormdate(form.getSysdate());
        //form.setPid(bi.pid.getText().toString());
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        JSONObject json = new JSONObject();
        json.put("kcs2q4", bi.kcs2q4y.isChecked() ? "1"
                : bi.kcs2q4n.isChecked() ? "2"
                : "-1");

        json.put("kcs2q401", bi.kcs2q401.isChecked() ? "1" : "-1");

        json.put("kcs2q402", bi.kcs2q402.isChecked() ? "2" : "-1");

        json.put("kcs2q403", bi.kcs2q403.isChecked() ? "3" : "-1");

        json.put("kcs2q404", bi.kcs2q404.isChecked() ? "4" : "-1");

        json.put("kcs2q405", bi.kcs2q405.isChecked() ? "5" : "-1");

        json.put("kcs2q406", bi.kcs2q406.isChecked() ? "6" : "-1");

        json.put("kcs2q407", bi.kcs2q407.isChecked() ? "7" : "-1");

        json.put("kcs2q408", bi.kcs2q408.isChecked() ? "8" : "-1");

        json.put("kcs2q4096", bi.kcs2q4096.isChecked() ? "96" : "-1");

        json.put("kcs2q4096x", bi.kcs2q4096x.getText().toString());
        json.put("kcs2q4010", bi.kcs2q4010.isChecked() ? "10" : "-1");

        json.put("kcs2q4011", bi.kcs2q4011.isChecked() ? "11" : "-1");

        json.put("kcs2q5", bi.kcs2q5y.isChecked() ? "1"
                : bi.kcs2q5n.isChecked() ? "2"
                : "-1");

        json.put("kcs2q501", bi.kcs2q501.isChecked() ? "1" : "-1");

        json.put("kcs2q502", bi.kcs2q502.isChecked() ? "2" : "-1");

        json.put("kcs2q503", bi.kcs2q503.isChecked() ? "3" : "-1");

        json.put("kcs2q504", bi.kcs2q504.isChecked() ? "4" : "-1");

        json.put("kcs2q505", bi.kcs2q505.isChecked() ? "5" : "-1");

        json.put("kcs2q506", bi.kcs2q506.isChecked() ? "6" : "-1");

        json.put("kcs2q507", bi.kcs2q507.isChecked() ? "7" : "-1");

        json.put("kcs2q508", bi.kcs2q508.isChecked() ? "8" : "-1");

        json.put("kcs2q5096", bi.kcs2q5096.isChecked() ? "96" : "-1");

        json.put("kcs2q5096x", bi.kcs2q5096x.getText().toString().trim().isEmpty() ? "-1" : bi.kcs2q5096x.getText().toString());
        json.put("kcs2q5010", bi.kcs2q5010.isChecked() ? "10" : "-1");


        MainApp.setGPS(this);

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }
}