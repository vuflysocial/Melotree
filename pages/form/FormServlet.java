import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@WebServlet("/submit-form")
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DatabaseReference dbRef;

    @Override
    public void init() throws ServletException {
        super.init();

        // Initialize Firebase
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://your-firebase-database-url.firebaseio.com/")
                .setServiceAccount(getServletContext().getResourceAsStream("/WEB-INF/firebase-adminsdk.json"))
                .build();
        FirebaseApp.initializeApp(options);

        // Get a reference to the Firebase database
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String workHistory = request.getParameter("work-history");
        String description = request.getParameter("description");

        // Create a new record in Firebase
        String key = dbRef.child("submissions").push().getKey();
        dbRef.child("submissions").child(key).child("name").setValue(name);
        dbRef.child("submissions").child(key).child("email").setValue(email);
        dbRef.child("submissions").child(key).child("workHistory").setValue(workHistory);
        dbRef.child("submissions").child(key).child("description").setValue(description);

        // Send a response back to the client
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("Form submitted successfully!");
        out.close();
    }
}
