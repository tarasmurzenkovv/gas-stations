INSERT INTO fuel_type (id, type_name) VALUES (1, 'A-72');
INSERT INTO fuel_type (id, type_name) VALUES (2, 'A-76');
INSERT INTO fuel_type (id, type_name) VALUES (3, 'A-80');
INSERT INTO fuel_type (id, type_name) VALUES (4, 'AI-91');
INSERT INTO fuel_type (id, type_name) VALUES (5, 'AI-93');
INSERT INTO fuel_type (id, type_name) VALUES (6, 'AI-95');

INSERT INTO customer_type (id, type_name) VALUES (1, 'REGULAR');
INSERT INTO customer_type (id, type_name) VALUES (2, 'BUSINESS');


INSERT INTO car_type (id, type_name) VALUES (1, 'Toyota');
INSERT INTO car_type (id, type_name) VALUES (2, 'Mercedes');
INSERT INTO car_type (id, type_name) VALUES (3, 'Volvo');
INSERT INTO car_type (id, type_name) VALUES (4, 'Peugeot');
INSERT INTO car_type (id, type_name) VALUES (5, 'Renault');


INSERT INTO customer (id, date_of_birth, e_mail, enabled, first_name, gender, last_name, login, password, customerType_id) 
VALUES (1, '2016-02-29', 'regular@regular.com', '1', 'Blah', 'MALE', 'Blah', 'Blah', 'password', '1');

INSERT INTO customer (id, date_of_birth, e_mail, enabled, first_name, gender, last_name, login, password, customerType_id) 
VALUES (2, '2016-02-29', 'business@business.com', '1', 'BlahBusiness', 'MALE', 'BlahBusiness', 'BlahBusiness', 'password', '2');


INSERT INTO vehicle (id, deleted, number, volume, cartype, customer) VALUES (1, FALSE, 'AA1111BB', 20, 1, 1);


INSERT INTO gas_station (id, address, trademark, name, customer) VALUES (1, 'Fake address', 'Fake company', 'GS1', 2);
INSERT INTO gas_station (id, address, trademark, name, customer) VALUES (2, 'Fake address', 'Fake company', 'GS2', 2);
INSERT INTO gas_station (id, address, trademark, name, customer) VALUES (3, 'Fake address', 'Fake company', 'GS3', 2);
