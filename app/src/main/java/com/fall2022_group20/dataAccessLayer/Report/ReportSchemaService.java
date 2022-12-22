package com.fall2022_group20.dataAccessLayer.Report;

import android.util.Log;

import com.fall2022_group20.dataAccessLayer.child.ChildSchema;

import io.realm.Realm;
import io.realm.RealmResults;

public class ReportSchemaService {
    private String childName;
    private Realm realm;
    private String childId;
    private String reportId;
    private Integer childScore;

    public ReportSchemaService(Realm realm, String childId, String childName, Integer childScore, String reportId) {

        this.realm = realm;
        this.childName = childName;
        this.childId = childId;
        this.childScore = childScore;
        this.reportId = reportId;
    }

    /*
    * Method to create a new child report
    * Creation of a realm object must be executed within an async realm transaction
    * */
    public void createChildRealmReport (){
        realm.executeTransactionAsync(realm -> {
            ReportSchema newReport = realm.createObject(ReportSchema.class, String.valueOf(reportId));
            newReport.setChildName(childName);
            newReport.setChildScore(childScore);
            newReport.setChildId(childId);
        }, () -> { // Lambda expression
            /* success actions */
            Log.i("Child Report Added", childName + " report object added to realm!");
            //realm.close(); // Not sure if this the correct place to close the realm instance
        }, error -> {
            /* failure actions */
            Log.e("Error", "Something went wrong! " + error);
        });
    }

    /*
     * Method to update the score field of the report
     * Query a child report
     * Execute the update within a async realm transaction
     * */
    public void updateChildReportScore(Integer newScore){
        realm.executeTransactionAsync(realm -> {
            ReportSchema childReport = getChildReport();
            childReport.setChildScore(newScore);
        });
    }

    /*
    * Method to return all instances of reports
    * */
    public RealmResults<ReportSchema> getAllReports() {
        return realm.where(ReportSchema.class).findAll();
    }

    /*
    * Method to return an individual report
    * We are looking for a report by name, it can be changed to look for id as well
    * */
    public ReportSchema getChildReport(){
        return realm.where(ReportSchema.class).equalTo("childName", childName).findFirst();
    }

    /*
    * Method to delete a child report
    * Invoke the get report method
    * Execute the deletion within a transaction
    * Discard the reference
    * */
    public void deleteChildReport(){
        realm.executeTransactionAsync(realm -> {
            ReportSchema childReport = getChildReport();
            childReport.deleteFromRealm();
            childReport = null;
        });
    }
}
