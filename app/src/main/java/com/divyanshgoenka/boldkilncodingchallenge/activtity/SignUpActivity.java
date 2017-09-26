package com.divyanshgoenka.boldkilncodingchallenge.activtity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dariopellegrini.formbuilder.FormBuilder;
import com.dariopellegrini.formbuilder.FormButton;
import com.dariopellegrini.formbuilder.FormElement;
import com.dariopellegrini.formbuilder.FormObject;
import com.dariopellegrini.formbuilder.FormValidation;
import com.divyanshgoenka.boldkilncodingchallenge.R;
import com.divyanshgoenka.boldkilncodingchallenge.util.Constants;
import com.divyanshgoenka.boldkilncodingchallenge.util.EmailValidator;
import com.divyanshgoenka.boldkilncodingchallenge.util.PasswordValidator;

import org.json.JSONObject;

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
        formObjects.add(new FormElement().setTag("name").setHint(getString(R.string.name_field_hint)).setType(FormElement.Type.TEXT).setRequired(true));
        formObjects.add(new FormElement().setTag("email").setHint(getString(R.string.email_field_hint)).setType(FormElement.Type.EMAIL).setRequired(true).setFormValidation(new FormValidation() {
            @Override
            public boolean validate() {
                return emailFieldValidate();
            }
        }).setErrorMessage(getString(R.string.enter_valid_email_address)));
        formObjects.add(new FormElement().setTag("password").setHint(getString(R.string.password_field_hint)).setType(FormElement.Type.PASSWORD).setRequired(true).setFormValidation(new FormValidation() {
            @Override
            public boolean validate() {
                return passwordFieldValidate();
            }
        }));
        formObjects.add(new FormElement().setTag("confirm_password").setHint(getString(R.string.confirm_password_field_hint)).setType(FormElement.Type.PASSWORD).setRequired(true).setFormValidation(new FormValidation() {
            @Override
            public boolean validate() {
                return confirmPasswordFieldValidate();
            }
        }).setErrorMessage(getString(R.string.password_not_matching)));
        formObjects.add(new FormButton().setTitle(getString(R.string.register_button_label)).setBackgroundColor(Color.RED).setTextColor(Color.WHITE).setRunnable(this::validateAndRegister));
        formBuilder.build(formObjects);
    }

    private boolean emailFieldValidate() {
        CharSequence emailFieldString = getTextFromFormBuilderFormField("email");
        return !TextUtils.isEmpty(emailFieldString) && EmailValidator.validate(emailFieldString);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        formBuilder.validate();
        outState.putSerializable("formMap", new JSONObject(formBuilder.formMap).toString());
        super.onSaveInstanceState(outState);
    }

    public CharSequence getTextFromFormBuilderFormField(String field){
        return ((TextView) formBuilder.viewMap.get(field)).getText();
    }

    public CharSequence getPasswordFieldText(){
        return getTextFromFormBuilderFormField("password");
    }

    public CharSequence getConfirmPasswordFieldText(){
        return getTextFromFormBuilderFormField("confirm_password");
    }

    public boolean passwordFieldValidate(){
        CharSequence passwordFieldText = getPasswordFieldText();
        return passwordFieldText!=null && PasswordValidator.validate(passwordFieldText);
    }

    public boolean confirmPasswordFieldValidate(){
        CharSequence confirmPasswordFieldText = getConfirmPasswordFieldText();
        CharSequence passwordFieldText = getPasswordFieldText();
        return passwordFieldValidate() && passwordFieldText.toString().equals(confirmPasswordFieldText.toString()) ;
    }

    public void validateAndRegister(){
        if(formBuilder.validate()){
            if(confirmPasswordFieldValidate()){
                //TODO: add user logic and move below line to after completing that
                returnToLoginScreen();
            }
        }else{
            Snackbar snackbar = Snackbar
                    .make(mLinearLayout, R.string.check_errors, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void onBackPressed() {
        animateAndfinish();
    }

    public void animateAndfinish(){
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void returnToLoginScreen() {
        setResult(RESULT_OK);
        animateAndfinish();
    }



}
