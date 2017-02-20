package com.cs3680.justin.js_project3_listmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by justi on 2/18/2017.
 */

public class TaskListFragment extends Fragment {
    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        mTaskRecyclerView = (RecyclerView) view
                .findViewById(R.id.task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        TaskList taskList = TaskList.get(getActivity());

        List<Task> tasks = taskList.getTasks();

        if (mAdapter == null) {
            mAdapter = new TaskAdapter(tasks);
            mTaskRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mCompDateTextView;
        private TextView mDueDateTextView;
        private CheckBox mCompletedCheckBox;

        private Task mTask;

        public TaskHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(
                    R.id.list_item_task_title_text_view);
            mCompDateTextView = (TextView) itemView.findViewById(
                    R.id.list_item_task_comp_date_text_view);
            mDueDateTextView = (TextView) itemView.findViewById(
                    R.id.list_item_task_due_date_text_view);
            mCompletedCheckBox = (CheckBox) itemView.findViewById(
                    R.id.list_item_task_completed_check_box);
        }

        public void bindTask (Task task){
            mTask = task;
            mTitleTextView.setText(mTask.getTitle());
            mCompDateTextView.setText(mTask.getCompleteDate().toString());
            mDueDateTextView.setText(mTask.getDueDate().toString());
            mCompletedCheckBox.setChecked(mTask.isCompleted());
        }

        @Override
        public void onClick(View v) {
            Intent intent = TaskActivity.newIntent(getActivity(), mTask.getId());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks){
            mTasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_task,parent,false);
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bindTask(task);}

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}