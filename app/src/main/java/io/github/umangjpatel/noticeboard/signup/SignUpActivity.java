package io.github.umangjpatel.noticeboard.signup;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import io.github.umangjpatel.noticeboard.R;
import io.github.umangjpatel.noticeboard.databinding.ActivitySignUpBinding;
import io.github.umangjpatel.noticeboard.home.faculty.FacultyHomeActivity;
import io.github.umangjpatel.noticeboard.home.student.StudentHomeActivity;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding mSignUpBinding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mSignUpBinding.setLifecycleOwner(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(mAuth.getCurrentUser());
    }

    public void startUserLogin(View view) {
        if (checkDataEntered())
            mAuth.signInWithEmailAndPassword(mSignUpBinding.emailAddressEditText.getText().toString(),
                    mSignUpBinding.passwordEditText.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful())
                            updateUI(mAuth.getCurrentUser());
                        else
                            showErrorMessages();
                    });
    }

    public void createUserAccount(View view) {
        if (checkDataEntered()) {
            mAuth.createUserWithEmailAndPassword(mSignUpBinding.emailAddressEditText.getText().toString(),
                    mSignUpBinding.passwordEditText.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful())
                            updateUI(mAuth.getCurrentUser());
                        else
                            showErrorMessages();
                    });
        }
    }

    private boolean checkDataEntered() {
        if (isEmpty(mSignUpBinding.emailAddressEditText.getText().toString())) {
            setEmailInputError(getResources().getString(R.string.empty_input_field,
                    getResources().getString(R.string.email_address_hint)));
            return false;
        }
        if (isEmpty(mSignUpBinding.passwordEditText.getText().toString())) {
            setPasswordInputError(getResources().getString(R.string.empty_input_field,
                    getResources().getString(R.string.password_hint)));
            return false;
        }
        if (!isEmail()) {
            setEmailInputError(getResources().getString(R.string.incorrect_input_field,
                    getResources().getString(R.string.password_hint)));
            return false;
        } else
            return true;
    }

    private void setPasswordInputError(String errorMessage) {
        mSignUpBinding.passwordInputLayout.setError(errorMessage);
    }

    private void setEmailInputError(String errorMessage) {
        mSignUpBinding.emailAddressInputLayout.setError(errorMessage);
    }

    private boolean isEmail() {
        String email = mSignUpBinding.emailAddressEditText.getText().toString();
        return (!isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isEmpty(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence);
    }

    private void showErrorMessages() {
        setEmailInputError(getResources().getString(R.string.error_email));
        setPasswordInputError(getResources().getString(R.string.error_password));
        mSignUpBinding.passwordEditText.setText(null);
    }


    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            if (checkFacultyEmail(Objects.requireNonNull(currentUser.getEmail())))
                startActivity(FacultyHomeActivity.newIntent(this));
            else
                startActivity(StudentHomeActivity.newIntent(this));
            finish();
        }
    }

    private boolean checkFacultyEmail(@NonNull String email) {
        return true;
    }

    @NonNull
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, SignUpActivity.class);
    }
}
