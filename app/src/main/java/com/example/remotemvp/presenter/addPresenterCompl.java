package com.example.remotemvp.presenter;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;


import com.example.remotemvp.addActivity;
import com.example.remotemvp.model.DatabaseHelper;
import com.example.remotemvp.model.Employee;
import com.example.remotemvp.model.IModelWithViewOpenDoor;
import com.example.remotemvp.model.ModelWithViewOpenDoor;
import com.example.remotemvp.view.IAddView;

import java.util.List;

public class addPresenterCompl implements IAddPresenter
{
    IAddView iAddView;
    Context context;
    IModelWithViewOpenDoor iModelWithViewOpenDoor;
    public addPresenterCompl(IAddView iAddView,Context context)
    {
      this.iAddView = iAddView;
      this.context = context;
      iModelWithViewOpenDoor = new ModelWithViewOpenDoor(context);
    }


    @Override
    public void refreshList() {
        List<Employee> employees = new DatabaseHelper(context).getListOfEmployees();
        Log.d("aaa","in refreshList() "+employees);

        ListAdapterPresenterCompl adapter = new ListAdapterPresenterCompl(context, employees,iAddView);
        iAddView.setAdapterOfList(adapter);
        //listView.setAdapter(adapter);
    }

    @Override
    public void onAddClicked() {
        iAddView.showAddLayout();
        String currentWIFIsplit;
        String wIFI=iModelWithViewOpenDoor.getWifiName();
        if(wIFI != null)
        {
            currentWIFIsplit= wIFI.substring(1, wIFI.length() - 1).trim();
            iAddView.setTextedtName(currentWIFIsplit);
        }


    }

    @Override
    public void onNewSubmit() {
        iAddView.validateTexts();

//        List<Employee> employees = new DatabaseHelper(context).searchByExactName(edtName.getText().toString().trim());
//        List<Employee> employeesDoorName = new DatabaseHelper(addActivity.this).searchByExactDoor(edtAddress.getText().toString().trim());
    }


}
