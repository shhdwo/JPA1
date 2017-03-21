insert into department (name, version, created_at, updated_at) values ('Finance', 1, '2017-03-16 12:12:12', '2017-03-16 12:12:12');
insert into department (name, version, created_at, updated_at) values ('Automotive', 1, '2017-03-16 12:12:12', '2017-03-16 12:12:12');
insert into department (name, version, created_at, updated_at) values ('FCMG', 1, '2017-03-16 12:12:12', '2017-03-16 12:12:12');

insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'Czeslaw', 'Niemen', '1989-11-22 00:00:00', 12345678901, 2);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Ryszard', 'Riedel', '1989-11-22 00:00:00', 34298742398, 2);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Marek', 'Piekarczyk', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Janusz', 'Panasewicz', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Piotr', 'Piasecki', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Marek', 'Grechuta', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Robert', 'Gawliński', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Artur', 'Gadowski', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Gienek', 'Loska', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Paweł', 'Kukiz', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Piotr', 'Rogucki', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Irena', 'Jarocka', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Katarzyna', 'Nosowska', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Anna', 'Wyszkoni', '1989-11-22 00:00:00', 34298742398, 1);
insert into employee (version, created_at, updated_at, name, surname, birthdate, pesel, department_id) values (1, '2017-03-16 12:12:12', '2017-03-16 13:13:13', 'Artur', 'Rojek', '1989-11-22 00:00:00', 34298742398, 1);


insert into role (version, created_at, updated_at, name) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'PL'), (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'TCD'), (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'FCD'), (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'DEV');

insert into project_types (version, created_at, updated_at, name) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'INTERNAL'), (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'EXTERNAL');

insert into project (version, created_at, updated_at, name, manager_id, type_id) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'Project1', 2, 1)
insert into project (version, created_at, updated_at, name, manager_id, type_id) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'Project2', 2, 1)
insert into project (version, created_at, updated_at, name, manager_id, type_id) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'Project3', 2, 1)
insert into project (version, created_at, updated_at, name, manager_id, type_id) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 'Project4', 3, 1)

insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 2, 1, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 2, 1, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 1, 3, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 1, 3, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 2, 3, 4, 200.25, '2015-03-16 12:12:12', null);
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 3, 3, 4, 200.25, '2015-03-16 12:12:12', null);
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 4, 3, 4, 200.25, '2015-03-16 12:12:12', '2016-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 5, 3, 4, 200.25, '2015-03-16 12:12:12', '2016-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 6, 3, 4, 200.25, '2015-03-16 12:12:12', '2016-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 7, 3, 4, 200.25, '2015-03-16 12:12:12', '2016-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 8, 3, 4, 200.25, '2015-03-16 12:12:12', '2016-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 9, 3, 4, 200.25, '2015-03-16 12:12:12', '2016-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 10, 3, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 11, 3, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 12, 3, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 2, 2, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 2, 2, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 2, 2, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');
insert into employee_project (version, created_at, updated_at, employee_id, project_id, role_id, salary, start_date, end_date) values (1, '2017-03-16 12:12:12', '2017-03-16 12:12:12', 2, 4, 4, 200.25, '2015-03-16 12:12:12', '2017-03-16 12:12:12');