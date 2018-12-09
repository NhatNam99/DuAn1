package vn.edu.poly.duan1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1.adapter.PatientAdapter;
import vn.edu.poly.duan1.model.PatientManagement;
import vn.edu.poly.duan1.sqlitedao.PatientDAO;

public class PatientManagementActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private LayoutInflater inflater;
    PatientDAO patientDAO;
    List<PatientManagement> list;
    PatientAdapter adapter;
    ListView lsPatient;
    int position, maSV;
    public static final int Request_code_Edit = 1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_management);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<PatientManagement>();
        lsPatient = (ListView) findViewById(R.id.lvPatient);
        registerForContextMenu(lsPatient);

        patientDAO = new PatientDAO(this);

        lsPatient.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                return false;
            }
        });

        loadListView();

        lsPatient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PatientManagementActivity.this, PatientDetailsActivity.class);
                intent.putExtra("CodePatient", list.get(position).getCode());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Functions:");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemUpdate:
                Intent intent = new Intent(PatientManagementActivity.this, UpdatePatientActivity.class);
                int CodePatient = list.get(position).getCode();
                intent.putExtra("CodePatient", CodePatient);
                startActivityForResult(intent, Request_code_Edit);
                break;
            case R.id.itemDelete:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want delete information patient?");
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        PatientManagement patientManagement = list.get(menuInfo.position);
                        patientDAO.deletePatient(patientManagement);
                        adapter.remove(patientManagement);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemAdd){
            Intent intent = new Intent(PatientManagementActivity.this, AddPatientActivity.class);
            startActivity(intent);
        }
        if(id == R.id.itemChange){
            Intent intent = new Intent(PatientManagementActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        }
        if(id == R.id.itemLogout){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Do you want to sign out?");
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(PatientManagementActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Request_code_Edit && resultCode == RESULT_OK){
            loadListView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    public void loadListView(){
        list = new ArrayList<PatientManagement>();
        list = patientDAO.getAllPatient();
        adapter = new PatientAdapter(this, R.layout.content_patient_management, list);
        ListView listView = findViewById(R.id.lvPatient);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        list = patientDAO.searchPatient(newText);
        adapter.notifyDataSetChanged();
        setAdapterListView(list);
        return true;
    }

    private void setAdapterListView(List<PatientManagement> patientManagements){
        adapter = new PatientAdapter(this, R.layout.content_patient_management, list);
        ListView listView = findViewById(R.id.lvPatient);
        listView.setAdapter(adapter);
    }
}
