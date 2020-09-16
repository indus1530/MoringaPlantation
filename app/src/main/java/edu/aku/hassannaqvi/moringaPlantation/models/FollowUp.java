package edu.aku.hassannaqvi.moringaPlantation.models;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.moringaPlantation.contracts.FollowUpContract;


public class FollowUp {

    private static final String TAG = "FollowUp_CONTRACT";

    Long id;
    String mp101;
    String _luid;
    String mpsysdate;
    String pid;
    String seem_vid;

    public FollowUp() {
        // Default Constructor
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMp101() {
        return mp101;
    }

    public void setMp101(String mp101) {
        this.mp101 = mp101;
    }


    public String get_luid() {
        return _luid;
    }

    public void set_luid(String _luid) {
        this._luid = _luid;
    }


    public String getMpsysdate() {
        return mpsysdate;
    }

    public void setMpsysdate(String mpsysdate) {
        this.mpsysdate = mpsysdate;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getSeem_vid() {
        return seem_vid;
    }

    public void setSeem_vid(String seem_vid) {
        this.seem_vid = seem_vid;
    }


    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();
        try {
            json.put(FollowUpContract.TableFollowUp._ID, this.id == null ? JSONObject.NULL : this.id);
            json.put(FollowUpContract.TableFollowUp.COLUMN_MP101, this.mp101 == null ? JSONObject.NULL : this.mp101);
            json.put(FollowUpContract.TableFollowUp.COLUMN__LUID, this._luid == null ? JSONObject.NULL : this._luid);
            json.put(FollowUpContract.TableFollowUp.COLUMN_MPSYSDATE, this.mpsysdate == null ? JSONObject.NULL : this.mpsysdate);
            json.put(FollowUpContract.TableFollowUp.COLUMN_PID, this.pid == null ? JSONObject.NULL : this.pid);
            json.put(FollowUpContract.TableFollowUp.COLUMN_SEEM_VID, this.seem_vid == null ? JSONObject.NULL : this.seem_vid);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public FollowUp Sync(JSONObject jsonObject) throws JSONException {
        this.mp101 = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN_MP101);
        this._luid = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN__LUID);
        this.mpsysdate = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN_MPSYSDATE);
        this.pid = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN_PID);
        this.seem_vid = jsonObject.getString(FollowUpContract.TableFollowUp.COLUMN_SEEM_VID);

        return this;
    }

    public FollowUp HydrateFP(Cursor cursor) {
        this.mp101 = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN_MP101));
        this._luid = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN__LUID));
        this.mpsysdate = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN_MPSYSDATE));
        this.pid = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN_PID));
        this.seem_vid = cursor.getString(cursor.getColumnIndex(FollowUpContract.TableFollowUp.COLUMN_SEEM_VID));

        return this;
    }
}