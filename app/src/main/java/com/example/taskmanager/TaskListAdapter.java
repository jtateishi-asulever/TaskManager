package com.example.taskmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.taskmanager.data.TaskData;

import java.util.List;

/** タスクリスト用独自アダプタ */
public class TaskListAdapter extends ArrayAdapter<TaskData> {

    private int mResource;
    private List<TaskData> mTasks;
    private LayoutInflater mInflater;

    public TaskListAdapter(Context context, int resource, List<TaskData> tasks) {
        super(context, resource, tasks);

        mResource = resource;
        mTasks = tasks;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        // リストビューに表示する要素を取得
        TaskData task = mTasks.get(position);

        // タイトルを設定
        TextView title = view.findViewById(R.id.task_title);
        title.setText(task.getTitle());

        // 削除ボタンにリスナを指定
        Button deleteButton = view.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            showConfirmDeleteDialog(task);
        });

        return view;
    }

    /** 削除ダイアログの生成 */
    private void showConfirmDeleteDialog(TaskData task) {

        // OKボタン押下時のリスナ
        DialogInterface.OnClickListener listener = (dialog, which) -> {
            // 削除実行
            mTasks.remove(task);
            this.notifyDataSetChanged();
        };

        new AlertDialog.Builder(getContext())
                .setMessage("削除してよろしいですか？")
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .show();
    }
}
