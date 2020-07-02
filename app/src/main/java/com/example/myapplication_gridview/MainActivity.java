package com.example.myapplication_gridview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView gv_main;
    private MainAdpter adpter;
    String[] names = new String[]{"手机防盗", "通讯卫士", "软件管理", "流量管理", "进程管理", "手机杀毒", "缓存管理", "高级工具", "设置中心"};
    int[] icons = new int[]{R.drawable.widget01, R.drawable.widget02, R.drawable.widget03, R.drawable.widget04, R.drawable.widget05, R.drawable.widget06, R.drawable.widget07, R.drawable.widget08, R.drawable.widget09};
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv_main = findViewById(R.id.gv_main);
        adpter = new MainAdpter(names, icons, this);
        gv_main.setAdapter(adpter);
        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = names[i];
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
        gv_main.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final TextView textView = view.findViewById(R.id.tv_item_name);
                String name = textView.getText().toString();
                final EditText editText = new EditText(MainActivity.this);
                editText.setHint(name);
                if (i == 0) {
                    new AlertDialog.Builder(MainActivity.this).setTitle("修改名称").setView(editText).setPositiveButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String newName=editText.getText().toString();
                            textView.setText(newName);
                            sp.edit().putString("NAME",newName).commit();
                        }
                    }).setNegativeButton("取消", null).show();
                }
                return true;
            }
        });
        sp=getSharedPreferences("hxj", Context.MODE_PRIVATE);
    }
}
