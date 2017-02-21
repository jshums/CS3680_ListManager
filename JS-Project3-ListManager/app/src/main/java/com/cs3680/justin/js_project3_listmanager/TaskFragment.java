package com.cs3680.justin.js_project3_listmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by justi on 2/12/2017.
 */

public class TaskFragment extends Fragment {
    private static final String ARG_TASK_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Task mTask;
    private EditText mTitleField;
    private Button mDueDateButton;
    private TextView mCompleteDateText;
    private CheckBox mCompletedCheckbox;
    private Button mDeleteTask;

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
        final UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);

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
        updateDueDate();
        mDueDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mTask.getDueDate());
                dialog.setTargetFragment(TaskFragment.this,REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
        }
        });

        mCompleteDateText = (TextView) v.findViewById(R.id.task_completed_date);
        mCompleteDateText.setText(mTask.getCompleteDateText());
        mCompletedCheckbox = (CheckBox)v.findViewById(R.id.task_completed);
        mCompletedCheckbox.setChecked(mTask.isCompleted());
        mCompletedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTask.setCompleted(isChecked);
                updateCompletedDate(isChecked);
            }
        });

        //need to figure out implementation of Delete button
        mDeleteTask = (Button)v.findViewById(R.id.task_delete);
        mDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskList.get(getActivity()).removeTask(mTask);
                getActivity().finish();
            }
        });

        return v;
    }

    private void updateCompletedDate(Boolean isChecked) {
        if (isChecked == true) {
            mCompleteDateText.setText(mTask.getCompleteDateText());
        } else {
            mCompleteDateText.setText("");
            mTask.setCompleteDate(null);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTask.setmDueDate(date);
            updateDueDate();
        }
    }

    private void updateDueDate() {
        mDueDateButton.setText(mTask.getDueDate().toString());
    }
}
