create table if not exists  Donors(
    Donor_id integer primary key autoincrement,
    name text not null,
    address text not null,
	phone_num integer,
    email text,
    website text,
    coordinator text,
    description text,
    latitude real,
    longitude real

);