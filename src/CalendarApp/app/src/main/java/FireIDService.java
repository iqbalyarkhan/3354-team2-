import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by anmol on 11/27/2017.
 */

/**
 * This class is to log the token refresh when the client app receives push notifications
 */
public class FireIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String tkn = FirebaseInstanceId.getInstance().getToken();
        // in server send the token when it is refreshed and store it
        Log.d("Not","Token ["+tkn+"]");

    }
}
