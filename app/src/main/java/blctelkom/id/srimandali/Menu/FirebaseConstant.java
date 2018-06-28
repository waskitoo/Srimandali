package blctelkom.id.srimandali.Menu;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by waski on 02/06/2018.
 */

public class FirebaseConstant {
    public static FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    public static FirebaseUser mFirebaseUser;
    private DatabaseReference mFirebaseDatabaseReference;
}
