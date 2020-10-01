package google.codepath.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    Button btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textView);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String pass =  intent.getStringExtra("pass");
        boolean check = intent.getBooleanExtra("checked",false);
        textView.setText("UserName: "+user+" - Password: "+pass+" - Check: "+check);
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}