var welcomeMsg = 'Medic App';
document.querySelector('h1').innerText = welcomeMsg;

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
}

let button = document.querySelector('button');
button.addEventListener('mouseenter', function() {
    button.textContent = "Go!";
})