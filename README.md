# dbmsl

CRUD - operations
_________________________________________________________________

## connect to mysql database
> mysql -h 10.10.12.108 -u t31332 -p

## create table
> create table studentTable(id int primary key, name varchar(30), roll int(5), mobile int(10), branch varchar(30));

## alter column
> alter table studentTable modify column mobile bigint(13);

## insert query in the table
> insert into studentTable values(1, "Sangmeshwar", 31332, 8010965429, "Comp");

## update query
> update studentTable set branch = "Computer" where id = 1;

## alter table to add foreign key
> alter table studentTable add foriegn key(id) references  classStudent(id) on delete cascade;

## delete query
> delete from studentTable where id = 1;

## copy records from one to another table
> insert into stuentTableCopy select * from studentTable;

## truncate - delete all records
> truncate studentTable;

## delete table
> drop studentTable

## read all the records from table
> select * from studentTable;

## read all the records with specific condition
> select * from studentTable where branch = "Computer";


JOINS - all types
_________________________________________________________________

## natural join - records with some common values (no need explicit condition)
> select * from studentTable natural join marksTable;

## inner join - records with common values and requires 'ON' clause
> select * from studentTable s inner join marks m on s.id = m.id;
 
## left join - records from left table + common records
> select * from stduentTable s left join marks m on s.id = m.id;

## right join - records from right table + common records
> select * from stduentTable s right join marks m on s.id = m.id;


VIEWS
_________________________________________________________________

## creating normal view - single base table
> create or replace view v1 as select * from studentTable where branch = "Computer" with check option;

//Insert, update and delete queries are same as simple table queries

## creating complex view - multiple base table
> create or replace view v2 as select * from studentTable s inner join marksTable m on s.id = m.id;

//Insert, update and delete queries for complex view may not be possible


INDEX
_________________________________________________________________

## create index on the table
> create index i1 on studetTable(id);

## showing the indexes
> show indexes from studetTable;


PROCEDURE
_________________________________________________________________

## create a procedure

> delimiter $ <br>
> create procedure calArea(IN radius float) <br>
  -> begin <br>
  -> declare radiusBound condition for sqlstate '45000'; <br>
  -> if(radius<5 || radius>9) <br>
  -> then <br>
  -> signal sqlstate '45000' set message_text = "the radius is not in the range"; <br>
  -> else <br>
  -> insert into areaTable values(radius, pi() * radius * radius); <br>
  -> end if; <br>
  -> end $ <br>

> call calArea(6)$


FUNCTION
_________________________________________________________________

## creating a function 

> delimiter $ <br>
> create function calArea(radius float) <br>
  -> return float(20); <br>
  -> as <br>
  -> begin <br>
  -> declare radiusBound condition for sqlstate '45000'; <br>
  -> if(radius>9 || radius<5) <br>
  -> then  <br>
  -> signal sqlstate '45000' set message_text = "radius is not in the range"; <br>
  -> else insert into areaTable values(radius, pi() * radius * radius); <br>
  -> return pi()*radius*radius; <br>
  -> end if; <br>
  -> end $ <br>
> select calArea(6)$


CURSOR
_________________________________________________________________

## creating a cursor

> delimiter $ <br>
> create procedure clone1(IN rollN int) <br>
  -> begin  <br>
  -> DECLARE c1 CURSOR AS SELECT * FROM studentTable WHERE roll = rollN; <br>
  -> OPEN c1; <br>
  -> FETCH c1 into rollN; <br>
  -> if not exists(select * from studentTable where roll = rollN) <br>
  -> then <br>
  -> insert into newStudentTable values select * from studentTable where roll = rollN;<br>
  -> end if; <br>
  -> end $ <br>
> call clone1(31332)$


TRIGGER
_________________________________________________________________

## creating trigger after update 
> delimiter $ <br>
> create trigger uppdateTrigger after update on studentTable for each row <br>
> begin <br>
> insert into auditStudentTable set name = NEW.name, roll = NEW.roll, branch = NEW.branch, mobile = NEW.mobile; <br>
> end; <br>
> $ <br>



<br><br><br>
ASSIGNMENT ON MONGODB (JDBC)
__________________________________________________________________

1.	Connect to mongo – <br>
MongoClient mongo = new MongoClient(“host”, 27017);

2.	Connect to database – <br>
MongoDatabase db = mongo.getDatabase(“t31332db”);

3.	Get collection – <br>
MongoCollection<Document> collection = db.getCollection(“student”);

4.	Create document – <br>
Document document = new Document(“name”, “Sangmeshwar”).append(“roll”, 31332);

5.	Insert document – <br>
collection.insertOne(document);

6.	Display documents – <br>
FindIterable<Document> iterDoc = collection.find();<br>
Iterator<Document> it = iterDoc.iterator();<br>
while (it.hasNext()) {<br>
	System.out.println(it.next());<br>
}

7.	Update document – <br>
collection.updateOne(Filters.eq("roll", 31332)); <br>
Updates.set("fName", fName));

8.	Delete documet – <br>
collection.deleteOne(Filters.eq(“roll”, 31332));

9.	Delete collection – <br>
collection.drop();

