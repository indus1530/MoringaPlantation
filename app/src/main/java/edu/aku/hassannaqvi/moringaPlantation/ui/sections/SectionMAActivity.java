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

import edu.aku.hassannaqvi.moringaPlantation.CONSTANTS;
import edu.aku.hassannaqvi.moringaPlantation.R;
import edu.aku.hassannaqvi.moringaPlantation.contracts.AssessmentContract;
import edu.aku.hassannaqvi.moringaPlantation.core.DatabaseHelper;
import edu.aku.hassannaqvi.moringaPlantation.core.MainApp;
import edu.aku.hassannaqvi.moringaPlantation.databinding.ActivitySectionMaBinding;
import edu.aku.hassannaqvi.moringaPlantation.models.Assessment;
import edu.aku.hassannaqvi.moringaPlantation.models.Users;
import edu.aku.hassannaqvi.moringaPlantation.ui.other.EndingActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static edu.aku.hassannaqvi.moringaPlantation.CONSTANTS.FORM_MA;
import static edu.aku.hassannaqvi.moringaPlantation.CONSTANTS.FORM_MP;
import static edu.aku.hassannaqvi.moringaPlantation.CONSTANTS.SELECTED_MODEL;
import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.assessment;
import static edu.aku.hassannaqvi.moringaPlantation.core.MainApp.form;


public class SectionMAActivity extends AppCompatActivity {

    ActivitySectionMaBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ma);
        bi.setCallback(this);
        setupSkip();
        populateSpinner(this);
    }


    private void setupSkip() {

       /* bi.ma105.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpCVma106);
            Clear.clearAllFields(bi.fldGrpCVma107);
            Clear.clearAllFields(bi.fldGrpCVma108);
            bi.fldGrpCVma106.setVisibility(View.GONE);
            bi.fldGrpCVma107.setVisibility(View.GONE);
            bi.fldGrpCVma108.setVisibility(View.GONE);

            if (i == bi.ma10501.getId()) {
                bi.fldGrpCVma107.setVisibility(View.VISIBLE);
            } else if (i == bi.ma10502.getId()) {
                bi.fldGrpCVma106.setVisibility(View.VISIBLE);
            } else if (i == bi.ma10503.getId()) {
                bi.fldGrpCVma108.setVisibility(View.VISIBLE);
            }
        });*/

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

        bi.ma102.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, usersFullName));

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndingActivity.class).putExtra("complete", true).putExtra(SELECTED_MODEL, FORM_MA));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addAssessment(assessment);
        assessment.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            assessment.set_ID(assessment.getDeviceid() + assessment.get_ID());
            db.updatesAssessmentColumn(AssessmentContract.TableAssessment._ID, assessment.get_ID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() {

        assessment = new Assessment();

        assessment.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date().getTime()));
        assessment.setFormtype(CONSTANTS.FORM_MA);
        assessment.setUsername(MainApp.userName);
        assessment.setDeviceid(MainApp.appInfo.getDeviceID());

        assessment.setDeviceTagId(MainApp.appInfo.getTagName());


        assessment.setAppversion(MainApp.appInfo.getAppVersion());
        assessment.setSeem_vid(assessment.getSeem_vid());
        assessment.setMasysdate(assessment.getMasysdate());

        assessment.setPid(bi.ma103.getText().toString().trim().isEmpty() ? "-1" : bi.ma103.getText().toString());

        assessment.setMa101(bi.ma101.getText().toString().trim().isEmpty() ? "-1" : bi.ma101.getText().toString());
        assessment.setMa102(bi.ma102.getSelectedItem().toString().trim().isEmpty() ? "-1" : bi.ma102.getSelectedItem().toString());
        assessment.setMa103(bi.ma103.getText().toString().trim().isEmpty() ? "-1" : bi.ma103.getText().toString());

        assessment.setMa104(bi.ma10401.isChecked() ? "1"
                : bi.ma10402.isChecked() ? "2"
                : "-1");

        assessment.setMa105(bi.ma105.getText().toString().trim().isEmpty() ? "-1" : bi.ma105.getText().toString());
        assessment.setMa106(bi.ma106.getText().toString().trim().isEmpty() ? "-1" : bi.ma106.getText().toString());

        MainApp.setGPS(this);
    }


    private void setupFields(int view) {
        bi.GrpName02.setVisibility(view);
        Clear.clearAllFields(bi.GrpName02);
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void ma103OnTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        setupFields(View.GONE);
    }


    public void BtnCheckFUP(View view) {


        if (!Validator.emptyCheckingContainer(this, bi.GrpName02)) return;

        getFupByID(bi.ma103.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Assessment>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Assessment fupContract) {
                        assessment = fupContract;
                        setupFields(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SectionMAActivity.this, "No Assessment up found!!", Toast.LENGTH_SHORT).show();
                        setupFields(View.GONE);
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });


    }

    public void BtnCheckFUP2(View view) {


        if (!Validator.emptyCheckingContainer(this, bi.GrpName02)) return;

        getFupByID(bi.ma103.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Assessment>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Assessment fupContract) {
                        assessment = fupContract;
                        setupFields(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        bi.GrpName02.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });


    }


    private Observable<Assessment> getFupByID(String pid) {
        return Observable.create(emitter -> {
            emitter.onNext(appInfo.getDbHelper().getAssessment(Integer.valueOf(pid).toString()));
            emitter.onComplete();
        });
    }


}