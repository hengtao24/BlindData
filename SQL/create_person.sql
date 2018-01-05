create table blinddata.person(
	StudentId varchar(10) primary key,
    Name varchar(8),
    Sex char(2),
	Age SMAllINT,
    Height DEC(3,2),
    Weight DEC(5,2),
    hobby varchar(100),
    foreign key(StudentId) references blinddata.user(StudentId) on delete cascade
)