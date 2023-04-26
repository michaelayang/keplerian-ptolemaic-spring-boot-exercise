cd C:\Program Files\PostgreSQL\15\bin
psql -h localhost -p 5432 -U postgres
sudo -u postgres psql

curl -u admin:password --verbose -H "Content-Type:  application/json" -X PUT -d '@keplerian_data_numbered.json' "http://localhost:8080/loadKeplerianRecords"
curl -u admin:password --verbose -H "Content-Type:  application/json" -X PUT -d '@ptolemaic_data_numbered.json' "http://localhost:8080/loadPtolemaicRecords"
curl -u admin:password --verbose -H "Content-Type:  application/json" -X PUT -d '@truth_data_numbered.json' "http://localhost:8080/loadTruthDataRecords"

CREATE TABLE PUBLIC.PTOLEMAIC (id BIGINT PRIMARY KEY NOT NULL, first_epicycle_theta REAL NOT NULL, first_epicycle_radius REAL NOT NULL, second_epicycle_theta REAL NOT NULL, second_epicycle_radius REAL NOT NULL, third_epicycle_theta REAL NOT NULL, third_epicycle_radius REAL NOT NULL, ptolemaic_overall_angle REAL NOT NULL);

copy public.ptolemaic from ptolemaic_data_numbered.csv with (FORMAT csv);
\copy public.ptolemaic from 'ptolemaic_data_numbered.csv' with (FORMAT csv);

CREATE TABLE PUBLIC.KEPLERIAN (id BIGINT PRIMARY KEY NOT NULL, earth_x REAL NOT NULL, earth_y REAL NOT NULL, earth_radius REAL NOT NULL, earth_theta REAL NOT NULL, mars_x REAL NOT NULL, mars_y REAL NOT NULL, mars_radius REAL NOT NULL, mars_theta REAL NOT NULL);

copy public.keplerian from keplerian_data_numbered.csv with (FORMAT csv);
\copy public.keplerian from 'keplerian_data_numbered.csv' with (FORMAT csv);

CREATE TABLE PUBLIC.TRUTH_DATA (id BIGINT PRIMARY KEY NOT NULL, truth_angle REAL NOT NULL);

copy public.truth_data from truth_data_numbered.csv with (FORMAT csv);
\copy public.truth_data from 'truth_data_numbered.csv' with (FORMAT csv);
