package com.example.myflowerproject.fcm;

import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private UserAPI userAPI;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    // [START on_new_token]
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]

    /**
     * Schedule async work using WorkManager.
     */
    private void scheduleJob() {
        // [START dispatch_job]
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class)
                .build();
        WorkManager.getInstance().beginWith(work).enqueue();
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        userAPI = ApiUtils.getAPIService();

        Gson gson = new Gson();
        SharedPreferences mPrefs = getSharedPreferences( "user", MODE_PRIVATE);
        String json = mPrefs.getString("user", "");
        Users user = gson.fromJson(json, Users.class);
        while (user == null) {
            SystemClock.sleep(2000);
            json = mPrefs.getString("user", "");
            user = gson.fromJson(json, Users.class);
        }

        userAPI.updateTokenFCM(user.getToken(), token).enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                //todo update user
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {

            }
        });
    }

}
