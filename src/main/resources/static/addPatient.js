fetch('/patients').then(resp => resp.json()).then(patients => {
        document.querySelector('#patientsDiv').innerHTML = listPatients(patients);
    }
);

function listPatients(json) {
    return `${json.map(listPatient).join('\n')}`;
}
// lists the patients in the database to the screen
let listPatient = function(patient) {
    return '<p>' + patient.patientId + ": " + patient.firstName + ": " + patient.middleName + ": " + patient.lastName + '</p>';
}

function goToButton(goToUrl) {
    window.location.href=goToUrl;
}

function myFunction() {
    document.body.style.backgroundColor = "black";
}

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
}

// Creates the mouse hover event to the add patient/insert button
let button = document.querySelector('button');
    button.addEventListener('mouseover',function() {
            button.textContent = "insert";
    })
    button.addEventListener('mouseout',function() {
        button.textContent="Add Patient";
    })