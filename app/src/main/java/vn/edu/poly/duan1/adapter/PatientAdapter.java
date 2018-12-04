package vn.edu.poly.duan1.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.poly.duan1.R;
import vn.edu.poly.duan1.model.PatientManagement;

public class PatientAdapter extends ArrayAdapter<PatientManagement> {

    List<PatientManagement> managementList;
    Context context;
    int resource;

    public PatientAdapter(@NonNull Context context, int resource, @NonNull List<PatientManagement> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.managementList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_patient, parent, false);

        TextView vCode = view.findViewById(R.id.tvcode);
        TextView vName = view.findViewById(R.id.tvname);

        PatientManagement patientManagement = managementList.get(position);
        vCode.setText(patientManagement.getCode());
        vName.setText(patientManagement.getName());

        return view;
    }
}
