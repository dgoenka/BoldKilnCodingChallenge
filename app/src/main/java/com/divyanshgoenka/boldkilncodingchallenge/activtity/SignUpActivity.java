package com.divyanshgoenka.boldkilncodingchallenge.activtity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.dariopellegrini.formbuilder.FormBuilder;
import com.dariopellegrini.formbuilder.FormButton;
import com.dariopellegrini.formbuilder.FormElement;
import com.dariopellegrini.formbuilder.FormObject;
import com.divyanshgoenka.boldkilncodingchallenge.R;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private FormBuilder formBuilder;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mLinearLayout = findViewById(R.id.root_ll);
        formBuilder = new FormBuilder(this, mLinearLayout);
        List<FormObject> formObjects = new ArrayList<FormObject>();
        formObjects.add(new FormElement().setTag("name").setHint("Name").setType(FormElement.Type.TEXT).setRequired(true));
        formObjects.add(new FormElement().setTag("email").setHint("Email").setType(FormElement.Type.EMAIL).setRequired(true));
        formObjects.add(new FormElement().setTag("password").setHint("Password").setType(FormElement.Type.PASSWORD).setRequired(true));
        formObjects.add(new FormElement().setTag("confirm_password").setHint("Confirm Password").setType(FormElement.Type.PASSWORD).setRequired(true));
        formObjects.add(new FormButton().setTitle("Register").setBackgroundColor(Color.RED).setTextColor(Color.WHITE).setRunnable(this::register));
        formBuilder.build(formObjects);
    }

    public void register(){

    }

}
