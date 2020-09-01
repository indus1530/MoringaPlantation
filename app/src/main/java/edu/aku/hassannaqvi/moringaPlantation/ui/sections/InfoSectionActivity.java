package edu.aku.hassannaqvi.moringaPlantation.ui.sections;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.moringaPlantation.R;
import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract;
import edu.aku.hassannaqvi.moringaPlantation.core.DatabaseHelper;
import edu.aku.hassannaqvi.moringaPlantation.core.MainApp;
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivityInfoSectionBinding;
import edu.aku.hassannaqvi.moringaPlantation.datecollection.AgeModel;
import edu.aku.hassannaqvi.moringaPlantation.datecollection.DateRepository;
import edu.aku.hassannaqvi.moringaPlantation.models.Form;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;
import edu.aku.hassannaqvi.moringaPlantation.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;

public class InfoSectionActivity extends AppCompatActivity {

    /*private static final String TAG = "";
    public static FormsContract fc;
    public List<String> talukaName, ucName, villageName, usersName, teamLeadName, healthFacilityCode;
    public List<String> talukaCode, ucCode, villageCode, usersCode, teamLeadCode, healthFacilityName;*/
    ActivityInfoSectionBinding bi;
    boolean dtFlag = false;
    LocalDate localDate = null;
    LocalDate calculatedDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_info_section);
        bi.setCallback(this);
        setupSkip();
    }


    private void setupSkip() {
        bi.a06.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.lla07));
        bi.a07.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.lla08));
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
            startActivity(new Intent(this, EndingActivity.class));
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

        /*form.setA01(bi.a01.getText().toString());
        form.setA02(bi.a02.getText().toString());
        form.setA03(MainApp.userName);

        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setA04(bi.a0401.isChecked() ? "1"
                : bi.a0402.isChecked() ? "2"
                : bi.a0403.isChecked() ? "3"
                : bi.a0404.isChecked() ? "4"
                : bi.a0405.isChecked() ? "5"
                : bi.a0406.isChecked() ? "6"
                : "-1");

        form.setA05(bi.a05.getText().toString().trim().isEmpty() ? "-1" : bi.a05.getText().toString());

        form.setRefno(bi.a05a.getText().toString().trim().isEmpty() ? "-1" : bi.a05a.getText().toString());

        form.setA06(bi.a0601.isChecked() ? "1"
                : bi.a0602.isChecked() ? "2"
                : bi.a0603.isChecked() ? "3"
                : bi.a0604.isChecked() ? "4"
                : bi.a0605.isChecked() ? "5"
                : bi.a0606.isChecked() ? "6"
                : bi.a0607.isChecked() ? "7"
                : bi.a0696.isChecked() ? "96"
                : "-1");

        form.setA07(bi.a0701.isChecked() ? "1"
                : bi.a0702.isChecked() ? "2"
                : "-1");

        form.setA08(bi.a08.getText().toString());

        form.setA09(bi.a09.getText().toString());

        form.setA10(bi.a10.getText().toString());

        form.setA11(bi.a11.getText().toString());

        form.setA12(bi.a1201.isChecked() ? "1"
                : bi.a1202.isChecked() ? "2"
                : "-1");

        form.setA13dd(bi.a13dd.getText().toString());

        form.setA13mm(bi.a13mm.getText().toString());

        form.setA13yy(bi.a13yy.getText().toString());

        form.setA14mm(bi.a14mm.getText().toString());

        form.setA14yy(bi.a14yy.getText().toString());

        form.setA15(bi.a1501.isChecked() ? "1"
                : bi.a1502.isChecked() ? "2"
                : bi.a1503.isChecked() ? "3"
                : "-1");

        String a16 = bi.a1601.isChecked() ? "1"
                : bi.a1602.isChecked() ? "2"
                : bi.a1603.isChecked() ? "3"
                : bi.a1604.isChecked() ? "4"
                : bi.a1605.isChecked() ? "5"
                : bi.a1606.isChecked() ? "6"
                : bi.a1607.isChecked() ? "7"
                : bi.a1608.isChecked() ? "8"
                : bi.a1609.isChecked() ? "9"
                : bi.a16096.isChecked() ? "96"
                : "-1";
        form.setA16(a16);

        form.setA17(bi.a17.getText().toString());

        form.setA18(bi.a1801.isChecked() ? "1"
                : bi.a1802.isChecked() ? "2"
                : bi.a1803.isChecked() ? "3"
                : bi.a1804.isChecked() ? "4"
                : bi.a18096.isChecked() ? "96"
                : "-1");

        form.setA19(bi.a1901.isChecked() ? "1"
                : bi.a1902.isChecked() ? "2"
                : bi.a1903.isChecked() ? "3"
                : bi.a1904.isChecked() ? "4"
                : bi.a1905.isChecked() ? "5"
                : bi.a1906.isChecked() ? "6"
                : bi.a1907.isChecked() ? "7"
                : bi.a1908.isChecked() ? "8"
                : bi.a1909.isChecked() ? "9"
                : bi.a1910.isChecked() ? "10"
                : bi.a1911.isChecked() ? "11"
                : bi.a19096.isChecked() ? "96"
                : "-1");

        form.setA20(bi.a2001.isChecked() ? "1"
                : bi.a2002.isChecked() ? "2"
                : bi.a2003.isChecked() ? "3"
                : bi.a2004.isChecked() ? "4"
                : bi.a2005.isChecked() ? "5"
                : "-1");

        form.setA21(bi.a2101.isChecked() ? "1"
                : bi.a2102.isChecked() ? "2"
                : bi.a2103.isChecked() ? "3"
                : bi.a2104.isChecked() ? "4"
                : bi.a2105.isChecked() ? "5"
                : bi.a2106.isChecked() ? "6"
                : bi.a2107.isChecked() ? "7"
                : bi.a2108.isChecked() ? "8"
                : bi.a2109.isChecked() ? "9"
                : bi.a2110.isChecked() ? "10"
                : bi.a2111.isChecked() ? "11"
                : bi.a21096.isChecked() ? "96"
                : "-1");

        form.setA22pos(bi.a22pos.getText().toString());

        form.setA22org(bi.a22org.getText().toString());

        form.setA23(bi.a2301.isChecked() ? "1"
                : bi.a2302.isChecked() ? "2"
                : "-1");*/

      /*  JSONObject json = new JSONObject();

        json.put("a01", bi.a01.getText().toString());

        json.put("a02", bi.a02.getText().toString());

        json.put("a06", bi.a0601.isChecked() ? "1"
                : bi.a0602.isChecked() ? "2"
                : bi.a0603.isChecked() ? "3"
                : bi.a0604.isChecked() ? "4"
                : bi.a0605.isChecked() ? "5"
                : bi.a0606.isChecked() ? "6"
                : bi.a0607.isChecked() ? "7"
                : bi.a0696.isChecked() ? "96"
                : "-1");

        json.put("a07", bi.a0701.isChecked() ? "1"
                : bi.a0702.isChecked() ? "2"
                : "-1");

        json.put("a08", bi.a08.getText().toString());

        json.put("a09", bi.a09.getText().toString());

        json.put("a10", bi.a10.getText().toString());

        json.put("a11", bi.a11.getText().toString());

        json.put("a12", bi.a1201.isChecked() ? "1"
                : bi.a1202.isChecked() ? "2"
                : "-1");

        json.put("a13dd", bi.a13dd.getText().toString());

        json.put("a13mm", bi.a13mm.getText().toString());

        json.put("a13yy", bi.a13yy.getText().toString());

        json.put("a14mm", bi.a14mm.getText().toString());

        json.put("a14yy", bi.a14yy.getText().toString());

        json.put("a15", bi.a1501.isChecked() ? "1"
                : bi.a1502.isChecked() ? "2"
                : bi.a1503.isChecked() ? "3"
                : "-1");

        String a16 = bi.a1601.isChecked() ? "1"
                : bi.a1602.isChecked() ? "2"
                : bi.a1603.isChecked() ? "3"
                : bi.a1604.isChecked() ? "4"
                : bi.a1605.isChecked() ? "5"
                : bi.a1606.isChecked() ? "6"
                : bi.a1607.isChecked() ? "7"
                : bi.a1608.isChecked() ? "8"
                : bi.a1609.isChecked() ? "9"
                : bi.a16096.isChecked() ? "96"
                : "-1";
        json.put("a16", a16);

        json.put("a17", bi.a17.getText().toString());

        json.put("a18", bi.a1801.isChecked() ? "1"
                : bi.a1802.isChecked() ? "2"
                : bi.a1803.isChecked() ? "3"
                : bi.a1804.isChecked() ? "4"
                : bi.a18096.isChecked() ? "96"
                : "-1");

        json.put("a19", bi.a1901.isChecked() ? "1"
                : bi.a1902.isChecked() ? "2"
                : bi.a1903.isChecked() ? "3"
                : bi.a1904.isChecked() ? "4"
                : bi.a1905.isChecked() ? "5"
                : bi.a1906.isChecked() ? "6"
                : bi.a1907.isChecked() ? "7"
                : bi.a1908.isChecked() ? "8"
                : bi.a1909.isChecked() ? "9"
                : bi.a1910.isChecked() ? "10"
                : bi.a1911.isChecked() ? "11"
                : bi.a19096.isChecked() ? "96"
                : "-1");

        json.put("a20", bi.a2001.isChecked() ? "1"
                : bi.a2002.isChecked() ? "2"
                : bi.a2003.isChecked() ? "3"
                : bi.a2004.isChecked() ? "4"
                : bi.a2005.isChecked() ? "5"
                : "-1");

        json.put("a21", bi.a2101.isChecked() ? "1"
                : bi.a2102.isChecked() ? "2"
                : bi.a2103.isChecked() ? "3"
                : bi.a2104.isChecked() ? "4"
                : bi.a2105.isChecked() ? "5"
                : bi.a2106.isChecked() ? "6"
                : bi.a2107.isChecked() ? "7"
                : bi.a2108.isChecked() ? "8"
                : bi.a2109.isChecked() ? "9"
                : bi.a2110.isChecked() ? "10"
                : bi.a2111.isChecked() ? "11"
                : bi.a21096.isChecked() ? "96"
                : "-1");

        json.put("a22pos", bi.a22pos.getText().toString());

        json.put("a22org", bi.a22org.getText().toString());

        json.put("a23", bi.a2301.isChecked() ? "1"
                : bi.a2302.isChecked() ? "2"
                : "-1");

        form.setsInfo(json.toString());*/

        //form.setSecSelection(new SectionSelection(calculatedDOB, bi.a14yy.getText().toString(), bi.a1502.isChecked(), a16, bi.a2002.isChecked()));

    }


    private boolean formValidation() {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) return false;
        if (!dtFlag) {
//            Toast.makeText(this, "Invalid date!", Toast.LENGTH_SHORT).show();
            return Validator.emptyCustomTextBox(this, bi.a13yy, "Invalid date!");
        }
        if (bi.a0702.isChecked()) return true;
        return Integer.parseInt(bi.a14mm.getText().toString()) != 0 || Integer.parseInt(bi.a14yy.getText().toString()) != 0;
    }


    public void BtnEnd() {
        AppUtilsKt.openEndActivity(this);
    }


    public void populateSpinner(final Context context) {
        // Spinner Drop down elements
        /*talukaName = new ArrayList<>();
        talukaCode = new ArrayList<>();

        talukaName.add("....");
        talukaCode.add("....");

        Collection<TalukasContract> dc = db.getTalukas();
        for (TalukasContract d : dc) {
            talukaName.add(d.getTaluka());
            talukaCode.add(d.getTalukacode());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, talukaName);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        bi.s1q2.setAdapter(dataAdapter);

        bi.s1q2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                healthFacilityCode = new ArrayList<>();
                healthFacilityName = new ArrayList<>();


                healthFacilityCode.add("....");
                healthFacilityName.add("....");

                Collection<HealthFacilitiesContract> pc = db.getHealthFacilities(talukaCode.get(position));
                for (HealthFacilitiesContract p : pc) {
                    healthFacilityCode.add(p.getFacilityCode());
                    healthFacilityName.add(p.getFacilityName());
                }
                ArrayAdapter<String> psuAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, healthFacilityName);

                psuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.s1q1.setAdapter(psuAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bi.s1q1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                villageCode = new ArrayList<>();
                villageName = new ArrayList<>();


                villageCode.add("....");
                villageName.add("....");

                Collection<VillagesContract> pc = db.getVillages(healthFacilityCode.get(position));
                for (VillagesContract p : pc) {
                    villageCode.add(p.getVillagecode());
                    villageName.add(p.getVillagename());
                }
                ArrayAdapter<String> vilAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, villageName);

                vilAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bi.s1q3.setAdapter(vilAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }


    public void a01OnTextChanged(CharSequence s, int start, int before, int count) {
        //Setting Date
        try {
            Instant instant = org.threeten.bp.Instant.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(bi.a01.getText().toString())) + "T06:24:01Z");
            localDate = org.threeten.bp.LocalDateTime.ofInstant(instant, org.threeten.bp.ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void a13yyOnTextChanged(CharSequence s, int start, int before, int count) {
        bi.a14mm.setEnabled(false);
        bi.a14mm.setText(null);
        bi.a14yy.setEnabled(false);
        bi.a14yy.setText(null);
        calculatedDOB = null;
        dtFlag = true;
        if (TextUtils.isEmpty(bi.a13dd.getText()) || TextUtils.isEmpty(bi.a13mm.getText()) || TextUtils.isEmpty(bi.a13yy.getText()))
            return;
        if (!bi.a13dd.isRangeTextValidate() || !bi.a13mm.isRangeTextValidate() || !bi.a13yy.isRangeTextValidate())
            return;
        if (bi.a13dd.getText().toString().equals("98") && bi.a13mm.getText().toString().equals("98") && bi.a13yy.getText().toString().equals("9998")) {
            bi.a14mm.setEnabled(true);
            bi.a14yy.setEnabled(true);
            dtFlag = true;
            return;
        }
        int day = bi.a13dd.getText().toString().equals("98") ? 15 : Integer.parseInt(bi.a13dd.getText().toString());
        int month = Integer.parseInt(bi.a13mm.getText().toString());
        int year = Integer.parseInt(bi.a13yy.getText().toString());

        AgeModel age;
        if (localDate != null)
            age = DateRepository.Companion.getCalculatedAge(localDate, year, month, day);
        else
            age = DateRepository.Companion.getCalculatedAge(year, month, day);
        if (age == null) {
            bi.a13yy.setError("Invalid date!!");
            dtFlag = false;
            return;
        }
        dtFlag = true;
        bi.a14mm.setText(String.valueOf(age.getMonth()));
        bi.a14yy.setText(String.valueOf(age.getYear()));

        //Setting Date
        try {
            Instant instant = Instant.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(
                    bi.a13dd.getText().toString() + "-" + bi.a13mm.getText().toString() + "-" + bi.a13yy.getText().toString()
            )) + "T06:24:01Z");
            calculatedDOB = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}