package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    private EditText mTaskTitle;
    private EditText mTaskDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setIntentData();
    }

    /* Viewの初期設定 */
    private void initView() {
        mTaskTitle = findViewById(R.id.task_title);
        mTaskDetail = findViewById(R.id.task_detail);
    }

    /** Intentで受け取った値をViewに反映 */
    private void setIntentData() {
        Intent intent = getIntent();
        //String = 文字
        String title = intent.getStringExtra("title");
        String detail = intent.getStringExtra("detail");
        mTaskTitle.setText(title);
        mTaskDetail.setText(detail);
    }
}
