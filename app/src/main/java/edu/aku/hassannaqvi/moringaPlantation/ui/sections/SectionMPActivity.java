package edu.aku.hassannaqvi.moringaPlantation.ui.sections;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.moringaPlantation.R;
import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract;
import edu.aku.hassannaqvi.moringaPlantation.core.DatabaseHelper;
import edu.aku.hassannaqvi.moringaPlantation.core.MainApp;
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionMpBinding;
import edu.aku.hassannaqvi.moringaPlantation.models.Form;
import edu.aku.hassannaqvi.moringaPlantation.models.Users;
import edu.aku.hassannaqvi.moringaPlantation.models.Villages;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;


public class SectionMPActivity extends AppCompatActivity {

    ActivitySectionMpBinding bi;
    private List<String> usersFullName, ucNames, ucCodes, villageNames, villageCodes;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_mp);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);
    }


    private void setupSkip() {
        bi.mp107.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVmp108));
    }


    private void populateSpinner(final Context context) {
        db = MainApp.appInfo.getDbHelper();
        // Spinner Drop down elements
        usersFullName = new ArrayList<String>() {
            {
                add("....");
            }
        };

        Collection<Users> dc = db.getUsers();
        for (Users us : dc) {
            usersFullName.add(us.getFull_name());
        }

        bi.mp102.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, usersFullName));
        bi.mp102.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    bi.mp103.setSelection(0);
                    bi.mp103.setEnabled(false);
                    return;
                }

                bi.mp103.setEnabled(true);
                List<String> user2 = new ArrayList<>();

                for (String names : usersFullName) {
                    if (names.equals(bi.mp102.getSelectedItem().toString())) continue;
                    user2.add(names);
                }

                bi.mp103.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, user2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        bi.mp103.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;
                ucNames = new ArrayList<>();
                ucCodes = new ArrayList<>();
                ucNames.add("....");
                ucCodes.add("....");

                Collection<Villages> pc = db.getVillageUc();
                for (Villages p : pc) {
                    ucNames.add(p.getUcname());
                    ucCodes.add(p.getSeem_vid().substring(0, 1));
                }

                bi.mp105.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, ucNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bi.mp105.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) return;
                villageNames = new ArrayList<>();
                villageCodes = new ArrayList<>();
                villageNames.add("....");
                villageCodes.add("....");

                Collection<Villages> pc = db.getVillageByUc(bi.mp105.getSelectedItem().toString());
                for (Villages p : pc) {
                    villageNames.add(p.getVillagename());
                    villageCodes.add(p.getSeem_vid().substring(p.getSeem_vid().length() - 3));
                }

                bi.mp104.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, villageNames));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime()));
        form.setFormtype("MP");
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setMp101(bi.mp101.getText().toString().trim().isEmpty() ? "-1" : bi.mp101.getText().toString());

        form.setMp102(bi.mp102.getSelectedItem().toString());

        form.setMp103(bi.mp103.getSelectedItem().toString());

        form.setMp104(villageCodes.get(bi.mp104.getSelectedItemPosition()));

        form.setMp105("0" + ucCodes.get(bi.mp105.getSelectedItemPosition()));

        form.setMp106(bi.mp106.getText().toString().trim().isEmpty() ? "-1" : bi.mp106.getText().toString());


        form.setMp107(bi.mp10701.isChecked() ? "1"
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
        form.setMp107x(bi.mp10711x.getText().toString().trim().isEmpty() ? "-1" : bi.mp10711x.getText().toString());

        form.setMp108(bi.mp108.getText().toString().trim().isEmpty() ? "-1" : bi.mp108.getText().toString());

        MainApp.setGPS(this);


    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    /*public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }*/

}