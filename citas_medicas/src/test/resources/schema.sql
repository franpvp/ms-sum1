CREATE TABLE paciente (
  id_paciente          INTEGER GENERATED ALWAYS AS IDENTITY,
  rut                  VARCHAR2(12) NOT NULL,
  nombre               VARCHAR2(100) NOT NULL,
  apellido_pat         VARCHAR2(100) NOT NULL,
  apellido_mat         VARCHAR2(100) NOT NULL,
  id_prevision         INTEGER NOT NULL

);

CREATE TABLE medico (
  id_medico            INTEGER GENERATED ALWAYS AS IDENTITY,
  rut                  VARCHAR2(12) NOT NULL,
  nombre               VARCHAR2(100) NOT NULL,
  apellido_pat         VARCHAR2(100) NOT NULL,
  apellido_mat         VARCHAR2(100) NOT NULL,
  id_especialidad      INTEGER NOT NULL

);

CREATE TABLE horario (
  id_horario            INTEGER GENERATED ALWAYS AS IDENTITY,
  fecha_hora            TIMESTAMP NOT NULL,
  disponible            NUMBER(1) NOT NULL CHECK (disponible IN (0, 1))
);

CREATE TABLE citamedica (
  id_cita          INTEGER GENERATED ALWAYS AS IDENTITY,
  id_paciente      INTEGER NOT NULL,
  id_medico        INTEGER NOT NULL,
  id_horario       INTEGER NOT NULL
);
