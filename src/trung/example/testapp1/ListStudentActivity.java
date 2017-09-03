package trung.example.testapp1;

import java.util.ArrayList;
import java.util.List;

import Adapter.Custom_list_view;
import Databasehelper.DataDAO;
import Databasehelper.DbHelper;
import StudentsDTO.Students;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import android.view.ContextMenu.ContextMenuInfo;

public class ListStudentActivity extends Activity {
	List<Students> list;
	ListView lv;
	LinearLayout linear;
	DataDAO db;
	Custom_list_view adapter;
	public static int resulUpdate = 100;
	int vitri, id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layout_listview_sv);
		super.onCreate(savedInstanceState);

		linear = (LinearLayout) findViewById(R.id.layoutSV);
		db = new DataDAO(this);
		list = new ArrayList<Students>();

		LoadStudents();

		registerForContextMenu(linear);
		registerForContextMenu(lv);

		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				vitri = position;
				return false;
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(ListStudentActivity.this, DetailStudentsActivity.class);
				intent.putExtra("SV", list.get(position).getIdsv());
				startActivity(intent);

			}
		});
		SearchView search = (SearchView) findViewById(R.id.searchSV);
		search.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Search(newText.trim());
				return false;
			}
		});

	}

	public void LoadStudents() {
		list = db.loadStudents();
		adapter = new Custom_list_view(this, R.layout.custom_list_sv, list);
		lv = (ListView) findViewById(R.id.listSV);
		lv.setAdapter(adapter);
	}

	public void Search(String query) {
		list = db.searchStudents(query);
		adapter = new Custom_list_view(this, R.layout.custom_list_sv, list);
		lv = (ListView) findViewById(R.id.listSV);
		lv.setAdapter(adapter);
	}

	public void DeleteStudents() {
		id = list.get(vitri).getIdsv();
		if (db.deleteStudents(id) != -1) {
			Toast.makeText(getApplicationContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
			
		} else {
			Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
			
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu_function, menu);
		if (v.getId() == R.id.listSV) {
			menu.getItem(0).setVisible(false);
			menu.getItem(1).setVisible(false);
			menu.getItem(2).setVisible(false);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menuThem) {
			Intent intent = new Intent(ListStudentActivity.this, NewStudentsActivity.class);
			startActivity(intent);
		}
		if (id == R.id.menuXoa) {
			DeleteStudents();
			LoadStudents();
		}
		if (id == R.id.menuSua) {
			Intent intent = new Intent(ListStudentActivity.this, UpdateStudentsActivity.class);
			int idint = list.get(vitri).getIdsv();
			intent.putExtra("SV", idint);
			startActivityForResult(intent, resulUpdate);
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == resulUpdate && resultCode == RESULT_OK) {
			LoadStudents();
		}
	}
}
