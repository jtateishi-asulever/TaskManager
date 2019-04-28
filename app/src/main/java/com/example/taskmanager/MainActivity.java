package com.example.taskmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.taskmanager.data.TaskData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mTaskTitleEdit;
    private EditText mTaskDetailEdit;
    private Button mAddTaskButton;
    private ListView mTaskListView;

    private ArrayAdapter mAdapter;

    private List<TaskData> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTasks = new ArrayList<>();

        initView();
    }

    /** Viewの初期設定 */
    private void initView() {
        mTaskTitleEdit = findViewById(R.id.task_title);
        mTaskDetailEdit = findViewById(R.id.task_detail);
        mAddTaskButton = findViewById(R.id.add_button);
        mTaskListView = findViewById(R.id.task_list);

        // タスク追加リスナをセット
        mAddTaskButton.setOnClickListener(v -> addTaskToList());

        // ListViewにAdapterをセット
        mAdapter = new TaskListAdapter(this, R.layout.task_list_item, mTasks);
        mTaskListView.setAdapter(mAdapter);
    }


    // Mark - methods

    /** タスク追加 */
    private void addTaskToList() {
        String title = mTaskTitleEdit.getText().toString();
        String detail = mTaskDetailEdit.getText().toString();

        if (title.isEmpty()) {
            showMessageDialog("タスク名を入力してください");
            return;
        }

        // タスクデータを作成し、リストに格納
        TaskData taskData = new TaskData(title, detail);
        mTasks.add(taskData);
        mAdapter.notifyDataSetChanged();
    }

    /** 詳細画面へ遷移 */
    private void presentDetailActivity() {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("title", mTaskTitleEdit.getText().toString());
        intent.putExtra("detail", mTaskDetailEdit.getText().toString());
        startActivity(intent);
    }

    /** 通常ダイアログ表示 */
    private void showMessageDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    /** OK/Cancel ダイアログ表示 */
    private void showOkCancelDialog(String message, @Nullable DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .show();
    }
}
