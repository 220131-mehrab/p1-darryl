// lists the patients in the database to the screen
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

let table = function(patient) {
    return '<tr>' + '<th>' + patient.patientId + '</th>' + '<th>' + patient.firstName + '</th>' + '<th>' + patient.middleName + '</th>' + '<th>' + patient.lastName + '</th>' + '</tr>';
}
function goToButton(goToUrl) {
    window.location.href=goToUrl;
}

function myFunction() {
    document.body.style.backgroundColor = "black";
}

//document.body.style.backgroundImage = "linear-gradient(#090930,#000000)";