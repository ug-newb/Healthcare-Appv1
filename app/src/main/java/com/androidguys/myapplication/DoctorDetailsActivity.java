package com.androidguys.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 = {
            {"Doctor name : Dr. Ajit Saste", "hospital Address : Matunga", "Exp : 5yrs", "Contact : 9012345678", "500"},
            {"Doctor name : Dr. Diljeet Singh", "hospital Address : Shimoga", "Exp : 6yrs", "Contact : 9102345678", "700"},
            {"Doctor name : Dr. Piyush Shukla", "hospital Address : Chennai", "Exp : 8yrs", "Contact : 9042345678", "800"},
            {"Doctor name : Dr. Ajay Sharma", "hospital Address : Pune", "Exp : 25yrs", "Contact : 9013425678", "200"},
            {"Doctor name : Dr .Prasad Pant", "hospital Address : Bombay", "Exp : 15yrs", "Contact : 9044335678", "400"}
    };
    private String[][] doctor_details2 = {
            {"Doctor name : Dr. Neha Patel", "Hospital Address : Bandra", "Exp : 8yrs", "Contact : 9876543210", "800"},
            {"Doctor name : Dr. Rajesh Sharma", "Hospital Address : Andheri", "Exp : 12yrs", "Contact : 7890123456", "1000"},
            {"Doctor name : Dr. Sneha Gupta", "Hospital Address : Dadar", "Exp : 6yrs", "Contact : 8765432109", "600"},
            {"Doctor name : Dr. Anil Desai", "Hospital Address : Goregaon", "Exp : 10yrs", "Contact : 7654321098", "900"},
            {"Doctor name : Dr. Pooja Shah", "Hospital Address : Malad", "Exp : 4yrs", "Contact : 6543210987", "400"}
    };
    private String[][] doctor_details3 = {
            {"Doctor name : Dr. Anjali Sharma", "Hospital Address : Ballari", "Exp : 7yrs", "Contact : 4321098765", "700"},
            {"Doctor name : Dr. Rahul Patel", "Hospital Address : Tumakuru", "Exp : 15yrs", "Contact : 3210987654", "1500"},
            {"Doctor name : Dr. Swati Desai", "Hospital Address : Udupi", "Exp : 5yrs", "Contact : 2109876543", "500"},
            {"Doctor name : Dr. Naveen Kumar", "Hospital Address : Shivamogga", "Exp : 11yrs", "Contact : 1098765432", "1100"},
            {"Doctor name : Dr. Aishwarya Reddy", "Hospital Address : Hassan", "Exp : 6yrs", "Contact : 0987654321", "600"}
    };
    private String[][] doctor_details4 = {
            {"Doctor name : Dr. Nisha Shahane", "Hospital Address : Borivali", "Exp : 6yrs", "Contact : 0987654321", "600"},
            {"Doctor name : Dr. Prakash Mathur", "Hospital Address : Panvel", "Exp : 8yrs", "Contact : 9876543210", "800"},
            {"Doctor name : Dr. Vandana Singh", "Hospital Address : Chembur", "Exp : 12yrs", "Contact : 8765432109", "1200"},
            {"Doctor name : Dr. Shruti Patel", "Hospital Address : Thane", "Exp : 7yrs", "Contact : 7654321098", "700"},
            {"Doctor name : Dr. Sameer Sharma", "Hospital Address : Kandivali", "Exp : 5yrs", "Contact : 6543210987", "500"}
    };
    private String[][] doctor_details5 = {
            {"Doctor name : Dr. Kavya Sharma", "Hospital Address : Bagalkot", "Exp : 9yrs", "Contact : 4321098765", "900"},
            {"Doctor name : Dr. Prashant Deshmukh", "Hospital Address : Raichur", "Exp : 14yrs", "Contact : 3210987654", "1400"},
            {"Doctor name : Dr. Neha Singhania", "Hospital Address : Bijapur", "Exp : 5yrs", "Contact : 2109876543", "500"},
            {"Doctor name : Dr. Vivek Khanna", "Hospital Address : Bidar", "Exp : 11yrs", "Contact : 1098765432", "1100"},
            {"Doctor name : Dr. Maya Sharma", "Hospital Address : Hassan", "Exp : 8yrs", "Contact : 0987654321", "800"}
    };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians") == 0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dieticians") == 0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentists") == 0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeons") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0; i<doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons. Fees" + doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}