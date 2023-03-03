// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

const firebaseConfig = {
  apiKey: "AIzaSyAfcl7V90rZZw4DNoPyW89Afgzvijbpf-Q",
  authDomain: "melodome-submit.firebaseapp.com",
  projectId: "melodome-submit",
  storageBucket: "melodome-submit.appspot.com",
  messagingSenderId: "619006557670",
  appId: "1:619006557670:web:158f3aa9384613b9e7aa27",
  measurementId: "G-4G8DL9QKNV"
};

  firebase.initializeApp(firebaseConfig);
  
  const form = document.querySelector('form');
  form.addEventListener('submit', (event) => {
    event.preventDefault();
  
    // Get the form data
    const name = document.querySelector('#name').value;
    const email = document.querySelector('#email').value;
    const workHistory = document.querySelector('#work-history').value;
    const description = document.querySelector('#description').value;
    const app = initializeApp(firebaseConfig);
    const analytics = getAnalytics(app);
    const database = getDatabase(app);
  
    // Create a new record in Firebase
    const submissionsRef = firebase.database().ref('submissions');
    const newSubmissionRef = submissionsRef.push();
    newSubmissionRef.set({
      name: name,
      email: email,
      workHistory: workHistory,
      description: description
    })
      .then(() => {
        console.log('Form submitted successfully!');
        // You can also show a success message to the user here
      })
      .catch((error) => {
        console.error(error);
        // You can also show an error message to the user here
      });
  });
  
