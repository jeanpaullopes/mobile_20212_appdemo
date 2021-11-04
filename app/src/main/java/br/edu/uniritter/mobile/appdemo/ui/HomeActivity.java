package br.edu.uniritter.mobile.appdemo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;

import br.edu.uniritter.mobile.appdemo.R;

public class HomeActivity extends AppCompatActivity {
    private final int  REQUEST_CODE = 9999;


    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (verificaBiometria() > 0) {

            executor = ContextCompat.getMainExecutor(this);
            biometricPrompt = new BiometricPrompt(HomeActivity.this,
                    executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode,
                                                  @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(getApplicationContext(),
                            "Authentication error: " + errString, Toast.LENGTH_SHORT)
                            .show();
                }

                @Override
                public void onAuthenticationSucceeded(
                        @NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(getApplicationContext(),
                            "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(), "Authentication failed",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

            promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Biometric login for my app")
                    .setSubtitle("Log in using your biometric credential")
                    .setNegativeButtonText("Use account password")
                    .build();

            // Prompt appears when user clicks "Log in".
            // Consider integrating with the keystore to unlock cryptographic operations,
            // if needed by your app.
            Button biometricLoginButton = findViewById(R.id.biometric_login);
            biometricLoginButton.setOnClickListener(view -> {
                biometricPrompt.authenticate(promptInfo);
            });



        }
    }

    private int verificaBiometria() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                return 1;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "No biometric features available on this device.");
                return -1;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                return -2;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                return 0;
        }
        return -1;
    }
}