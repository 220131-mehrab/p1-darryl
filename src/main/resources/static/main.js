//main.js is not used, but I plan to keep it and store all js functions here

/*var welcomeMsg = 'Medic App';
document.querySelector('h1').innerText = welcomeMsg;*/

//Display function below. allPatients.js is just this isolated
fetch('/patients').then(resp => resp.json()).then(patients => {
    document.querySelector('#patientsDiv').innerHTML = listPatients(patients);
});
function listPatients(json) {
    return `${json.map(listPatient).join('\n')}`;
}
// lists the patients in the database to the screen
let listPatient = function(patient) {
    return '<p>' + patient.patientId + ": " + patient.firstName + ": " + patient.middleName + ": " + patient.lastName + '</p>';
}
function addPatientButton(goToUrl) {
    window.location.href=goToUrl;
}

//addPatient can be just this isolated, but currently has display function above. May replace it with a 'view patients' button in it that links to /allPatients
function postPatient() {
    let patient = {
            "patientId": document.getElementById("patientId").value,
            "firstName": document.getElementById("firstName").value,
            "middleName": document.getElementById("middleName").value,
            "lastName": document.getElementById("lastName").value
        }
    console.log(patient);
    fetch("/patients", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patient)
    }).then((result) => {
        if (result.status !== 200) {
            throw new Error("Bad Server Response");
        }
        console.log(result.text());
    }).catch((error) => { console.log(error); })
    fetch('/patients').then(resp => resp.json()).then(patients => {
            document.querySelector('#patients').innerHTML = listPatients(patients);
        }
    );
    window.document.location.reload();
}*/
