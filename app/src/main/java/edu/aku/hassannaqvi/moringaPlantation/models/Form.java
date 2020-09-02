package edu.aku.hassannaqvi.moringaPlantation.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.moringaPlantation.contracts.FormsContract.FormsTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Form extends LiveData<Form> {

    private final String projectName = "moringa_plantation";
    private String _ID = "";
    private String _UID = "";
    private String formtype = "";
    private String username;
    private String sysdate = "";
    private String mf101 = "";
    private String mf102 = "";
    private String mf103 = "";
    private String mf104 = "";
    private String mf105 = "";
    private String mf106 = "";
    private String mf106x = "";
    private String mf107 = "";
    private String mf107x = "";
    private String mf108 = "";
    private String mf108x = "";
    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String gpslat = "";
    private String gpslng = "";
    private String gpsdate = "";
    private String gpsacc = "";
    private String deviceid = "";
    private String tagid = "";
    private String sB = "";

    //For section selection
    private SectionSelection secSelection;


    public Form() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFormtype() {
        return formtype;
    }

    public void setFormtype(String formtype) {
        this.formtype = formtype;
    }


    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }


    public String getMf101() {
        return mf101;
    }

    public void setMf101(String mf101) {
        this.mf101 = mf101;
    }

    public String getMf102() {
        return mf102;
    }

    public void setMf102(String mf102) {
        this.mf102 = mf102;
    }

    public String getMf103() {
        return mf103;
    }

    public void setMf103(String mf103) {
        this.mf103 = mf103;
    }

    public String getMf104() {
        return mf104;
    }

    public void setMf104(String mf104) {
        this.mf104 = mf104;
    }

    public String getMf105() {
        return mf105;
    }

    public void setMf105(String mf105) {
        this.mf105 = mf105;
    }


    public String getMf106() {
        return mf106;
    }

    public void setMf106(String mf106) {
        this.mf106 = mf106;
    }


    public String getMf106x() {
        return mf106x;
    }

    public void setMf106x(String mf106x) {
        this.mf106x = mf106x;
    }


    public String getMf107() {
        return mf107;
    }

    public void setMf107(String mf107) {
        this.mf107 = mf107;
    }


    public String getMf107x() {
        return mf107x;
    }

    public void setMf107x(String mf107x) {
        this.mf107x = mf107x;
    }


    public String getMf108() {
        return mf108;
    }

    public void setMf108(String mf108) {
        this.mf108 = mf108;
    }


    public String getMf108x() {
        return mf108x;
    }

    public void setMf108x(String mf108x) {
        this.mf108x = mf108x;
    }


    public String getsB() {
        return sB;
    }

    public void setsB(String sB) {
        this.sB = sB;
    }


    public String getGpslat() {
        return gpslat;
    }

    public Form setGpslat(String gpslat) {
        this.gpslat = gpslat;
        return this;
    }

    public String getGpslng() {
        return gpslng;
    }

    public Form setGpslng(String gpslng) {
        this.gpslng = gpslng;
        return this;
    }

    public String getGpsdate() {
        return gpsdate;
    }

    public Form setGpsdate(String gpsdate) {
        this.gpsdate = gpsdate;
        return this;
    }

    public String getGpsacc() {
        return gpsacc;
    }

    public Form setGpsacc(String gpsacc) {
        this.gpsacc = gpsacc;
        return this;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public Form setDeviceid(String deviceid) {
        this.deviceid = deviceid;
        return this;
    }

    public String getTagid() {
        return tagid;
    }

    public Form setTagid(String tagid) {
        this.tagid = tagid;
        return this;
    }


    //======================


    //====================


    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public String getProjectName() {
        return projectName;
    }


    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }


    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }


    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }


    public String getIstatus96x() {
        return istatus96x;
    }

    public void setIstatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }


    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }


    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }


    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }


    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }


    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }


    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;

    }


    public Form Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.formtype = jsonObject.getString(FormsTable.COLUMN_FORMTYPE);
        this.username = jsonObject.getString(FormsTable.COLUMN_USERNAME);
        this.sysdate = jsonObject.getString(FormsTable.COLUMN_SYSDATE);
        this.mf101 = jsonObject.getString(FormsTable.COLUMN_MF101);
        this.mf102 = jsonObject.getString(FormsTable.COLUMN_MF102);
        this.mf103 = jsonObject.getString(FormsTable.COLUMN_MF103);
        this.mf104 = jsonObject.getString(FormsTable.COLUMN_MF104);
        this.mf105 = jsonObject.getString(FormsTable.COLUMN_MF105);
        this.mf106 = jsonObject.getString(FormsTable.COLUMN_MF106);
        this.mf106x = jsonObject.getString(FormsTable.COLUMN_MF106x);
        this.mf107 = jsonObject.getString(FormsTable.COLUMN_MF107);
        this.mf107x = jsonObject.getString(FormsTable.COLUMN_MF107x);
        this.mf108 = jsonObject.getString(FormsTable.COLUMN_MF108);
        this.mf108x = jsonObject.getString(FormsTable.COLUMN_MF108x);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsTable.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APPVERSION);
        this.sB = jsonObject.getString(FormsTable.COLUMN_SB);

        return this;

    }

    public Form Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.formtype = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.username = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USERNAME));
        this.sysdate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYSDATE));
        this.mf101 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF101));
        this.mf102 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF102));
        this.mf103 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF103));
        this.mf104 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF104));
        this.mf105 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF105));
        this.mf106 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF106));
        this.mf106x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF106x));
        this.mf107 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF107));
        this.mf107x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF107x));
        this.mf108 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF108));
        this.mf108x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MF108x));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
        sBHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SB)));

        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Form.class);
    }

    public String sBtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("mf101", mf101)
                    /*.put("s2q101x", s2q101x)
                    .put("s2q102", s2q102)
                    .put("s2q102x", s2q102x)
                    .put("s2q103", s2q103)
                    .put("s2q103x", s2q103x)
                    .put("s2q104", s2q104)
                    .put("s2q104x", s2q104x)
                    .put("s2q105", s2q105)
                    .put("s2q105x", s2q105x)
                    .put("s2q106", s2q106)
                    .put("s2q106x", s2q106x)
                    .put("s2q107", s2q107)
                    .put("s2q107x", s2q107x)
                    .put("s2q108", s2q108)
                    .put("s2q108x", s2q108x)
                    .put("s2q109", s2q109)
                    .put("s2q109x", s2q109x)
                    .put("s2q110", s2q110)
                    .put("s2q110x", s2q110x)
                    .put("s2q111", s2q111)
                    .put("s2q111x", s2q111x)
                    .put("s2q112", s2q112)
                    .put("s2q112x", s2q112x)
                    .put("s2q113", s2q113)
                    .put("s2q113x", s2q113x)
                    .put("s2q114", s2q114)
                    .put("s2q114x", s2q114x)
                    .put("s2q115", s2q115)
                    .put("s2q115x", s2q115x)
                    .put("s2q116", s2q116)
                    .put("s2q116x", s2q116x)
                    .put("s2q2", s2q2)
                    .put("s2q3", s2q3)
                    .put("s2q31", s2q31)
                    .put("s2q32", s2q32)
                    .put("s2q33", s2q33)
                    .put("s2q4", s2q4)
                    .put("s2q501", s2q501)
                    .put("s2q502", s2q502)
                    .put("s2q503", s2q503)
                    .put("s2q504", s2q504)
                    .put("s2q505", s2q505)
                    .put("s2q506", s2q506)
                    .put("s2q507", s2q507)
                    .put("s2q508", s2q508)
                    .put("s2q509", s2q509)
                    .put("s2q596", s2q596)
                    .put("s2q596x", s2q596x)
                    .put("s2q6", s2q6)
                    .put("s2q7", s2q7)
                    .put("s2q71", s2q71)
                    .put("s2q72", s2q72)*/
                    .put("gpslat", gpslat)
                    .put("gpslng", gpslng)
                    .put("gpsdate", gpsdate)
                    .put("gpsacc", gpsacc)
                    .put("deviceid", deviceid)
                    .put("tagid", tagid)
                    .put("appversion", appversion);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {
            json.put(FormsTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
            json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
            json.put(FormsTable.COLUMN_FORMTYPE, this.formtype == null ? JSONObject.NULL : this.formtype);
            json.put(FormsTable.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);
            json.put(FormsTable.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            json.put(FormsTable.COLUMN_MF101, this.mf101 == null ? JSONObject.NULL : this.mf101);
            json.put(FormsTable.COLUMN_MF102, this.mf102 == null ? JSONObject.NULL : this.mf102);
            json.put(FormsTable.COLUMN_MF103, this.mf103 == null ? JSONObject.NULL : this.mf103);
            json.put(FormsTable.COLUMN_MF104, this.mf104 == null ? JSONObject.NULL : this.mf104);
            json.put(FormsTable.COLUMN_MF105, this.mf105 == null ? JSONObject.NULL : this.mf105);
            json.put(FormsTable.COLUMN_MF106, this.mf106 == null ? JSONObject.NULL : this.mf106);
            json.put(FormsTable.COLUMN_MF106x, this.mf106x == null ? JSONObject.NULL : this.mf106x);
            json.put(FormsTable.COLUMN_MF107, this.mf107 == null ? JSONObject.NULL : this.mf107);
            json.put(FormsTable.COLUMN_MF107x, this.mf107x == null ? JSONObject.NULL : this.mf107x);
            json.put(FormsTable.COLUMN_MF108, this.mf108 == null ? JSONObject.NULL : this.mf108);
            json.put(FormsTable.COLUMN_MF108x, this.mf108x == null ? JSONObject.NULL : this.mf108x);


            json.put(FormsTable.COLUMN_SB, new JSONObject(sBtoString()));


            if (this.sB != null && !this.sB.equals("")) {
                json.put(FormsTable.COLUMN_SB, new JSONObject(this.sB));
            }

            json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
            json.put(FormsTable.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
            json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
            json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
            json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
            json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
            json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
            json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
            json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
            json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sBHydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                /*this.s2q101 = json.getString("s2q101");
                this.s2q101x = json.getString("s2q101x");
                this.s2q102 = json.getString("s2q102");
                this.s2q102x = json.getString("s2q102x");
                this.s2q103 = json.getString("s2q103");
                this.s2q103x = json.getString("s2q103x");
                this.s2q104 = json.getString("s2q104");
                this.s2q104x = json.getString("s2q104x");
                this.s2q105 = json.getString("s2q105");
                this.s2q105x = json.getString("s2q105x");
                this.s2q106 = json.getString("s2q106");
                this.s2q106x = json.getString("s2q106x");
                this.s2q107 = json.getString("s2q107");
                this.s2q107x = json.getString("s2q107x");
                this.s2q108 = json.getString("s2q108");
                this.s2q108x = json.getString("s2q108x");
                this.s2q109 = json.getString("s2q109");
                this.s2q109x = json.getString("s2q109x");
                this.s2q110 = json.getString("s2q110");
                this.s2q110x = json.getString("s2q110x");
                this.s2q111 = json.getString("s2q111");
                this.s2q111x = json.getString("s2q111x");
                this.s2q112 = json.getString("s2q112");
                this.s2q112x = json.getString("s2q112x");
                this.s2q113 = json.getString("s2q113");
                this.s2q113x = json.getString("s2q113x");
                this.s2q114 = json.getString("s2q114");
                this.s2q114x = json.getString("s2q114x");
                this.s2q115 = json.getString("s2q115");
                this.s2q115x = json.getString("s2q115x");
                this.s2q116 = json.getString("s2q116");
                this.s2q116x = json.getString("s2q116x");
                this.s2q2 = json.getString("s2q2");
                this.s2q3 = json.getString("s2q3");
                this.s2q31 = json.getString("s2q31");
                this.s2q32 = json.getString("s2q32");
                this.s2q33 = json.getString("s2q33");
                this.s2q4 = json.getString("s2q4");
                this.s2q501 = json.getString("s2q501");
                this.s2q502 = json.getString("s2q502");
                this.s2q503 = json.getString("s2q503");
                this.s2q504 = json.getString("s2q504");
                this.s2q505 = json.getString("s2q505");
                this.s2q506 = json.getString("s2q506");
                this.s2q507 = json.getString("s2q507");
                this.s2q508 = json.getString("s2q508");
                this.s2q509 = json.getString("s2q509");
                this.s2q596 = json.getString("s2q596");
                this.s2q596x = json.getString("s2q596x");
                this.s2q6 = json.getString("s2q6");
                this.s2q7 = json.getString("s2q7");
                this.s2q71 = json.getString("s2q71");
                this.s2q72 = json.getString("s2q72");*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
