package com.example.remotemvp;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remotemvp.presenter.IAddPresenter;
import com.example.remotemvp.presenter.ListAdapterPresenterCompl;
import com.example.remotemvp.presenter.addPresenterCompl;
import com.example.remotemvp.view.IAddView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity implements IAddView {
    private Button btnSubmit;
    public static Button btnCancel;
    public  ListView listView;
//    private MyListAdapter adapter;
    private EditText edtSearch, edtName, edtPhone,edtPassword;
    public EditText edtAddress;
    private int seletcedEmployeeId;
    TextView txtRespondSetting,txtSeekbar;
    String commandMain, whButton;
    String addORsave="";

    SeekBar seekBar;

    String currentWIFIsplit;
    Button btnSave,btnShowSearch,btnCloseSearch,btnDoSearch;

    IAddPresenter iAddPresenter;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);

        defineWidgets();

        btnSave.setOnClickListener(onAddClicked);   ////    NEW

        iAddPresenter = new addPresenterCompl(addActivity.this,addActivity.this);

        //refreshList(addActivity.this);

    }

    public View.OnClickListener onAddClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            iAddPresenter.onAddClicked();
        }
    };

    private void defineWidgets()
    {
        btnSave = (Button) findViewById(R.id.btn_save);
        btnShowSearch = (Button) findViewById(R.id.btn_search);
        btnCloseSearch = (Button) findViewById(R.id.btn_close_search);
        btnDoSearch = (Button) findViewById(R.id.btn_do_search);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        edtSearch = (EditText) findViewById(R.id.edt_search);
        edtName = (EditText) findViewById(R.id.edt_employee_name);
        edtPhone = (EditText) findViewById(R.id.edt_employee_phone);
        edtAddress = (EditText) findViewById(R.id.edt_employee_address);
        edtPassword =findViewById(R.id.edt_wifi_password);
        txtRespondSetting =findViewById(R.id.txtRespnseSetting);

        txtSeekbar = findViewById(R.id.txt_seek_time);
        seekBar = findViewById(R.id.seek_time);
        listView = (ListView) findViewById(R.id.listView);
    }


    private View.OnClickListener onNewEmployeeSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            iAddPresenter.onNewSubmit();


            //////////////// IF IT IS REAPETED
            boolean isRepeated=false;
            List<Employee> employees = new DatabaseHelper(addActivity.this).searchByExactName(edtName.getText().toString().trim());
            List<Employee> employeesDoorName = new DatabaseHelper(addActivity.this).searchByExactDoor(edtAddress.getText().toString().trim());

            if(!employees.isEmpty() || !employeesDoorName.isEmpty())
            {
                Toast.makeText(addActivity.this, "فیلد تکراری", Toast.LENGTH_SHORT).show();
                isRepeated = true;
            }


            if(isRepeated==false)
            {
                String networkSSIDFromUser =edtName.getText().toString();
                String keyWifiFromUser=edtPhone.getText().toString();
                String passFromUser= edtPassword.getText().toString();
                String timeFromUser =String.valueOf(seekBar.getProgress());
                addToDatabaseAndList(networkSSIDFromUser, keyWifiFromUser, passFromUser,addActivity.this,timeFromUser);
            }

        }
    };


    @Override
    public void setAdapterOfList(ListAdapterPresenterCompl adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void showAddLayout() {
        findViewById(R.id.lin_add_search).setVisibility(View.GONE);
        findViewById(R.id.add_edit_employee).setVisibility(View.VISIBLE);
        findViewById(R.id.txtRespnseSetting).setVisibility(View.VISIBLE);

        btnSubmit.setOnClickListener(onNewEmployeeSubmit);
    }

    @Override
    public void setTextedtName(String str) {
        edtName.setText(str);
    }

    @Override
    public void validateTexts() {
        if (edtName.getText().length() == 0 || edtPhone.getText().length() == 0 || edtAddress.getText().length() == 0 || edtPassword.getText().length()==0) {
            Toast.makeText(addActivity.this, "لطفا اطلاعات را وارد کنید", Toast.LENGTH_SHORT).show();
            return;
        }

        if(edtName.getText().length() > 30 || edtPhone.getText().length() > 10 || !(edtPassword.getText().length() > 8 && edtPassword.getText().length()<20))
        {
            Toast.makeText(addActivity.this, "تعداد کاراکترها کمتر یا بیشتر از حد مجاز", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    public String getEdtName() {
        String str = edtName.getText().toString().trim();
        return str;
    }

    @Override
    public String getEdtAddress()
    {
        edtAddress.getText().toString().trim()
        return null;
    }
}
