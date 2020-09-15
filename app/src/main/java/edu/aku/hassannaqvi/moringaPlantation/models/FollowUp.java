package edu.aku.hassannaqvi.moringaPlantation.models;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.moringaPlantation.contracts.FollowUpContract;


public class FollowUp {

    private static final String TAG = "FollowUp_CONTRACT";

    Long id;
    String mf101;
    String fsysdate;
    String ftid;

    public FollowUp() {
        // Default Constructor
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMf101() {
        return mf101;
    }

    public void setMf101(String mf101) {
        this.mf101 = mf101;
    }


    public String getFsysdate() {
        return fsysdate;
    }

    public void setFsysdate(String fsysdate) {
        this.fsysdate = fsysdate;
    }


    public String getFtid() {
        return ftid;
    }

    public void setFtid(String ftid) {
        this.ftid = ftid;
    }


    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {
            json.put(FollowUpContract.TableFollowUp._ID, this.id == null ? JSONObject.NULL : this.id);
            json.put(FollowUpContract.TableFollowUp.COLUMN_MP101, this.mf101 == null ? JSONObject.NULL : this.mf101);
            json.put(FollowUpContract.TableFollowUp.COLUMN_FSYSDATE, this.fsysdate == null ? JSONObject.NULL : this.fsysdate);
            json.put(FollowUpContract.TableFollowUp.COLUMN_FTID, this.ftid == null ? JSONObject.NULL : this.ftid);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FollowUp Sync(JSONObject jsonObject) throws JSONException {
        this.mf101 = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN_MP101);
        this.fsysdate = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN_FSYSDATE);
        this.ftid = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN_FTID);

        return this;
    }

    public FollowUp HydrateFP(Cursor cursor) {
        this.mf101 = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN_MP101));
        this.fsysdate = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN_FSYSDATE));
        this.ftid = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN_FTID));

        return this;
    }
}