package edu.aku.hassannaqvi.moringaPlantation.models;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.moringaPlantation.contracts.VillagesContract;


public class Villages {

    private static final String TAG = "Villages_CONTRACT";

    Long id;
    String ucname;
    String villagename;
    String seem_vid;

    public Villages() {
        // Default Constructor
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUcname() {
        return ucname;
    }

    public void setUcname(String ucname) {
        this.ucname = ucname;
    }


    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
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
            json.put(VillagesContract.TableVillage._ID, this.id == null ? JSONObject.NULL : this.id);
            json.put(VillagesContract.TableVillage.COLUMN_UCNAME, this.ucname == null ? JSONObject.NULL : this.ucname);
            json.put(VillagesContract.TableVillage.COLUMN_VILLAGE_NAME, this.villagename == null ? JSONObject.NULL : this.villagename);
            json.put(VillagesContract.TableVillage.COLUMN_SEEM_VID, this.seem_vid == null ? JSONObject.NULL : this.seem_vid);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Villages Sync(JSONObject jsonObject) throws JSONException {
        this.ucname = jsonObject.getString(VillagesContract.TableVillage.COLUMN_UCNAME);
        this.villagename = jsonObject.getString(VillagesContract.TableVillage.COLUMN_VILLAGE_NAME);
        this.seem_vid = jsonObject.getString(VillagesContract.TableVillage.COLUMN_SEEM_VID);
        return this;
    }

    public Villages HydrateUc(Cursor cursor) {
        this.ucname = cursor.getString(cursor.getColumnIndex(VillagesContract.TableVillage.COLUMN_UCNAME));
        return this;
    }

    public Villages HydrateVil(Cursor cursor) {
        this.villagename = cursor.getString(cursor.getColumnIndex(VillagesContract.TableVillage.COLUMN_VILLAGE_NAME));
        return this;
    }
}