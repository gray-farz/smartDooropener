package com.example.remotemvp.presenter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.remotemvp.R;
import com.example.remotemvp.model.Employee;
import com.example.remotemvp.view.IAddView;

import java.util.List;

public class ListAdapterPresenterCompl extends BaseAdapter {

    private Context context;
    private List<Employee> employees;
    IAddView iAddView;
    public ListAdapterPresenterCompl(Context context, List<Employee> employees, IAddView iAddView){
        this.context = context;
        this.employees = employees;
        this.iAddView = iAddView;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return employees.get(i).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        TextView txtWifiName = (TextView) rowView.findViewById(R.id.txt_employee_name);
        final TextView txtKey = (TextView) rowView.findViewById(R.id.txt_employee_phone);
        final TextView txtDoorName = (TextView) rowView.findViewById(R.id.txt_employee_address);
        final TextView txtPassword = (TextView) rowView.findViewById(R.id.txt_wifi_Password);
        final TextView txttime = (TextView) rowView.findViewById(R.id.txt_on_time_value);
        Button btnEdit = (Button) rowView.findViewById(R.id.btn_edit);
        Button btnDelete = (Button) rowView.findViewById(R.id.btn_delete);
        Button btnSet = (Button) rowView.findViewById(R.id.btn_set);

        txtWifiName.setText(employees.get(position).getName());
//        txtKey.setText(employees.get(position).getPhone());
        txtDoorName.setText(employees.get(position).getAddress());
        Log.d("aaa","password in list "+employees.get(position).getPassword());
        txtPassword.setText(employees.get(position).getPassword());
        txttime.setText(employees.get(position).getTime());

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //((addActivity) context).editEmployee(employees.get(position));
//
//
//
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatabaseHelper(context).deleteEmployee(employees.get(position));
//                employees.remove(position);
//                notifyDataSetChanged();
//            }
//        });
//
//        btnSet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String networkSSIDFromUser =employees.get(position).getName();
//                String keyWifiFromUser=employees.get(position).getPhone();
//                String passFromUser= employees.get(position).getPassword();
//                String timeFromUser =employees.get(position).getTime();
//
//                ((addActivity) context).creatSocket(networkSSIDFromUser,passFromUser,keyWifiFromUser,timeFromUser);
//            }
//        });
//
//        txtKey.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(context, "long", Toast.LENGTH_SHORT).show();
////                txtKey.setText("");
//                txtKey.setText(employees.get(position).getPhone());
//                return false;
//            }
//        });

        return rowView;
    }
}


