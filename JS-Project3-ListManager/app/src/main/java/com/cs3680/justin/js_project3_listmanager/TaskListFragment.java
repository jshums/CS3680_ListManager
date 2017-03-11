package com.cs3680.justin.js_project3_listmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

/**
 * Created by justi on 2/18/2017.
 */

public class TaskListFragment extends Fragment {
    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        super.onViewCreated(view,savedInstanceState);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_task_list, menu);

        MenuItem item = menu.findItem(R.id.menu_priority_spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.priority_sort_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String priority = adapter.getItem(position).toString();

                if (priority.equals("HIGH") || priority.equals("MEDIUM") || priority.equals("LOW"))
                {
                    updateUIFilter(priority);
                } else if (priority.equals("ALL")) {
                    updateUI();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_task:
                Task task = new Task();
                TaskList.get(getActivity()).addTask(task);
                Intent intent = TaskPagerActivity
                        .newIntent(getActivity(), task.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI(){
        TaskList taskList = TaskList.get(getActivity());

        List<Task> tasks = taskList.getTasks();

        if (mAdapter == null) {
            mAdapter = new TaskAdapter(tasks);
            mTaskRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTasks(tasks);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void updateUIFilter(String filter){
        TaskList taskList = TaskList.get(getActivity());

        List<Task> tasks = taskList.getTasksByPriority(filter);

        if (mAdapter == null) {
            mAdapter = new TaskAdapter(tasks);
            mTaskRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTasks(tasks);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mCompDateTextView;
        private TextView mDueDateTextView;
        private CheckBox mCompletedCheckBox;
        private TextView mPriorityTextView;

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
            mPriorityTextView = (TextView) itemView.findViewById(
                    R.id.list_item_task_priority);
        }

        public void bindTask (Task task){
            mTask = task;
            mTitleTextView.setText(mTask.getTitle());
            mDueDateTextView.setText(mTask.getDueDate().toString());
            mCompletedCheckBox.setChecked(mTask.isCompleted());
            mCompletedCheckBox.setEnabled(false);
            if (mTask.getCompleteDate().after(new Date(0))) {
                mCompDateTextView.setText(mTask.getCompleteDate().toString());
            }else{
                mCompDateTextView.setText("");
            }
            if(!mTask.getPriority().equals("[SELECT PRIORITY]")) {
                mPriorityTextView.setText(mTask.getPriority());
            }else{
                mPriorityTextView.setText("");
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = TaskPagerActivity.newIntent(getActivity(), mTask.getId());
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
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }


}