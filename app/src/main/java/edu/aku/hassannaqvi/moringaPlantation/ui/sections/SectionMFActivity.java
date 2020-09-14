package edu.aku.hassannaqvi.moringaPlantation.ui.sections;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionMfBinding;
import edu.aku.hassannaqvi.moringaPlantation.models.FollowUp;
import edu.aku.hassannaqvi.moringaPlantation.models.Form;
import edu.aku.hassannaqvi.moringaPlantation.models.Users;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;


public class SectionMFActivity extends AppCompatActivity {

    ActivitySectionMfBinding bi;
    FollowUp fup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_mf);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);
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


    private void populateSpinner(final Context context) {
        // Spinner Drop down elements
        List<String> usersFullName = new ArrayList<String>() {
            {
                add("....");
            }
        };

        Collection<Users> dc = MainApp.appInfo.getDbHelper().getUsers();
        for (Users us : dc) {
            usersFullName.add(us.getFull_name());
        }

        bi.mf102.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, usersFullName));

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
        form.setFormtype("MF");
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());
        form.setPid(bi.mf103.getText().toString());
        form.setMf101(bi.mf101.getText().toString().trim().isEmpty() ? "-1" : bi.mf101.getText().toString());
        form.setMf102(bi.mf102.getSelectedItem().toString());
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


    private void setupFields(int view) {
        bi.GrpName02.setVisibility(view);
        Clear.clearAllFields(bi.GrpName02);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void mf103OnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setupFields(View.GONE);
    }


    public void BtnCheckFUP(View view) {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName02)) return;

        Disposable disposable = getFupByID(bi.mf103.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fupcontract -> {
                    fup = fupcontract;
                    setupFields(View.VISIBLE);

                }, error -> {
                    Toast.makeText(this, "No Follow up found!!", Toast.LENGTH_SHORT).show();
                    setupFields(View.GONE);
                });

    }


    private Observable<FollowUp> getFupByID(String pid) {
        return Observable.create(emitter -> {
            emitter.onNext(appInfo.getDbHelper().getFollowUp(pid));
            emitter.onComplete();
        });
    }


}