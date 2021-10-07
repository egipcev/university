drop table if exists groups cascade;
create table groups (
	group_id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	group_name varchar(5)
);

drop table if exists students cascade;
create table students(
	student_id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	group_id integer REFERENCES groups,
	student_number varchar(100),
	first_name varchar(30),
	last_name varchar(30)
);

drop table if exists courses cascade;
create table courses(
	course_id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	course_name varchar(100),
	course_description varchar(100)
);

drop table if exists students_courses cascade;

drop table if exists teachers cascade;
create table teachers(
    teacher_id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    teacher_number varchar(100),
    first_name varchar(30),
    last_name varchar(30)
);

drop table if exists time_table_items cascade;
create table time_table_items (
    item_id integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    date date,
    start_time time,
    end_time time,
    course_id integer REFERENCES courses,
    group_id integer REFERENCES groups,
    teacher_id integer REFERENCES teachers
);