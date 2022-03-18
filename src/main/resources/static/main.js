//var welcomeMsg = 'Medic App';
//document.querySelector('h1').innerText = welcomeMsg;
/*
fetch('/patients').then(resp => resp.json()).then(patients => {
        document.querySelector('#patients').innerHTML = listPatients(patients);
    }
);

function listPatients(json) {
    return `${json.map(listPatient).join('\n')}`;
};

let listPatient = function(patient) {
    return '<p>' + patient.patientId + ": " + patient.firstName + '</p>';
};

function postPatient() {
    let patient = {
        "patientId": document.getElementById("patientId").value,
        "firstName": document.getElementById("firstName").value
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
        if (result.status != 200) {
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
//main.js or rather fetchPatients.js

let searchTerms = window.location.search;

fetchPatients();

function fetchPatients() {
    fetch('/patients' + searchTerms).then(resp => resp.json()).then(patients => {
        document.querySelector('#patients').innerHTML = printPatients(patients);
    });
}

function printPatients(json) {
    return `${json.map(printPatient).join('\n')}`;
}

function printPatient(patient) {
    return '<p>' + patient.patientId + ': ' + patient.firstName + '</p>';
}

//addPatient.js

function startAddPatient() {
    let form = `Patient ID: <input type='number' id='newPatientId'></br>
    First Name: <input type='text' id='newFirstName'></br>
    <button onclick='addPatient()'>Add</button>`
    document.querySelector('#addPatient').innerHTML = form;
}

function addPatient() {
    let newPatientId = document.getElementById('newPatientId').value
    let newPatientName = document.getElementById('newFirstName').value

    let patient = {
        "patientId": newPatientId,
        "firstName": newFirstName
    }

    fetch('/patients', {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patient)
    }).then(result => {
        if (result.status != 200) {
            throw new Error('Bad Server Response')
        }
    }).catch(error => { console.log(error) });

    fetchPatients();
}

//postPatient.js

function postPatient() {
    let patient = {
        "patientId": document.getElementById("patientId").value,
        "firstName": document.getElementById("firstName").value
    }

    fetch('/patient', {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(patient)
    }).then((result) => {
        if (result.status != 200) {
            throw new Error("Bad Server Response")
        }
    }).catch((error) => { console.log(error); })

    fetch('/patient').then(resp => resp.json()).then(patients => {
        document.querySelector('#patients').innerHTML = listPatients(patients);
    })
}
