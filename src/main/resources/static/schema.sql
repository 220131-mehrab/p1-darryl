CREATE TABLE "patient" (
    "Image" VARBINARY,
    "PatientId" INT PRIMARY KEY NOT NULL,
    "FirstName" VARCHAR NOT NULL,
    "MiddleName" VARCHAR,
    "LastName" VARCHAR
);

CREATE TABLE "patientInfo" (
    "PatientId" INT NOT NULL,
    "ArbitraryId" INT NOT NULL,
    "RegistrationDate" DATE NOT NULL,
    "Birthday" DATE NOT NULL,
    "Age" INT NOT NULL,
    "Gender" VARCHAR,
    "Sex" VARCHAR NOT NULL,
    "Marital Status" VARCHAR NOT NULL,
    "Height" INT NOT NULL,
    "WEIGHT" INT NOT NULL,
    "NATIONALITY" VARCHAR NOT NULL,
    CONSTRAINT "PK_patientInfo" PRIMARY KEY ("ArbitraryId"),
    CONSTRAINT "FK_patientInfoPatientId" FOREIGN KEY ("PatientId") REFERENCES "patient" ("PatientId") ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE "contactInfo" (
    "PatientId" INT NOT NULL,
    "HomeAddress" VARCHAR NOT NULL,
    "Email" VARCHAR,
    "PrimaryPhone" VARCHAR NOT NULL,
    "SecondaryPhone" VARCHAR NOT NULL,
    "1stEmergencyContact1Name" VARCHAR,
    "1stEmergencyContact1Number" INT,
    "2ndEmergencyContactName" VARCHAR,
    "2ndEmergencyContactNumber" INT,
    CONSTRAINT "PK_patientInfo" PRIMARY KEY ("RegistrationDate"),
    CONSTRAINT "FK_patientInfoPatientId" FOREIGN KEY ("PatientId") REFERENCES "patient" ("PatientId") ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE INDEX "IFK_patientInfoPatientId" ON "patientInfo" ("PatientId");
CREATE INDEX "IFK_contactInfoPatientId" ON "contactInfo" ("PatientId");

INSERT INTO "patient" VALUES (1, 'Floppa');
INSERT INTO "patient" VALUES (2, 'Walter');