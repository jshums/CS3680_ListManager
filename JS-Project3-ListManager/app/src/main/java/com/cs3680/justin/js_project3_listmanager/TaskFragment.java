package com.cs3680.justin.js_project3_listmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by justi on 2/12/2017.
 */

public class TaskFragment extends Fragment {
    private static final String ARG_TASK_ID = "crime_id";

    private Task mTask;
    private EditText mTitleField;
    private Button mDueDateButton;
    private Button mCompleteDateButton;
    private CheckBox mCompletedCheckbox;

    public static TaskFragment newinstance(UUID taskId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID, taskId);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskList.get(getActivity()).getTask(taskId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)   {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

        mTitleField = (EditText)v.findViewById(R.id.task_title);
        mTitleField.setText(mTask.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTask.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });

        mDueDateButton = (Button)v.findViewById(R.id.task_due_date);
        mDueDateButton.setText(mTask.getDueDate().toString());
        mDueDateButton.setEnabled(false);

        mCompleteDateButton = (Button)v.findViewById(R.id.task_completed_date);
        mCompleteDateButton.setText(mTask.getCompleteDate().toString());
        mCompleteDateButton.setEnabled(false);

        mCompletedCheckbox = (CheckBox)v.findViewById(R.id.task_completed);
        mCompletedCheckbox.setChecked(mTask.isCompleted());
        mCompletedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTask.setCompleted(isChecked);
            }
        });

        /* ADD CONDITIONAL TEXT FORMATTING HERE
        if ()
        mCompletedCheckbox.setTextColor(android.graphics.Color.RED);
        */

        return v;
    }
}
