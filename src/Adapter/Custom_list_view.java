package Adapter;

import java.util.List;

import StudentsDTO.Students;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import trung.example.testapp1.R;

public class Custom_list_view extends ArrayAdapter<Students> {
	Context context;
	int resource;
	List<Students> objects;

	public Custom_list_view(Context context, int resource, List<Students> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View viewrow = inflater.inflate(R.layout.custom_list_sv, parent, false);

		TextView txtTen = (TextView) viewrow.findViewById(R.id.txtTenSV);
		TextView txtGT = (TextView) viewrow.findViewById(R.id.txtGioitinh);
		TextView txtXL = (TextView) viewrow.findViewById(R.id.txtXepLoai);

		Students student = objects.get(position);

		txtTen.setText(student.getName());
		txtGT.setText(student.getGender());
		txtXL.setText(student.getXeploai());

		return viewrow;
	}

}
