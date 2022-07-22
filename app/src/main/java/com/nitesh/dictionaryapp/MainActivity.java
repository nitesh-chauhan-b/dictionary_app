package com.nitesh.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.nitesh.dictionaryapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Object DeadObjectException;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String search = binding.searchWord.getText().toString();
                if (!search.equals("")) {

                    //Hides Keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


                    if (!Python.isStarted()) {
                        Python.start(new AndroidPlatform(MainActivity.this));

                        Python python = Python.getInstance();
                        PyObject pyObject = python.getModule("project");
                        try {
                            DeadObjectException = pyObject.callAttr("backEnd", search);
                            binding.textView.setText(DeadObjectException.toString());
                            binding.textView3.setText("Searched : " + search);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Please enter correct spelling", Toast.LENGTH_LONG).show();
                        }


                    } else {
                        try {
                            Python python = Python.getInstance();
                            PyObject pyObject = python.getModule("project");

                            DeadObjectException = pyObject.callAttr("backEnd", search);
                            binding.textView.setText(DeadObjectException.toString());
                            binding.textView3.setText("Searched :" + search);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Please enter correct spelling", Toast.LENGTH_LONG).show();
                        }

                    }


                } else {
                    binding.searchWord.setError("Field is Empty");
                }

            }
        });

    }
}