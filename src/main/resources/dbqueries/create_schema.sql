--CREATE SCHEMA tcup;
--commit;
BEGIN
CREATE TABLE companies(ID INT NOT NULL,
                            code VARCHAR (5) NOT NULL,
                            company VARCHAR(20) NOT NULL,
                            primary key (code));
CREATE TABLE projects(ID INT NOT NULL,
                            project VARCHAR(20) NOT NULL,
                            code VARCHAR(5) references companies(code),
                            primary key (project));
CREATE TABLE pages(ID INT NOT NULL,
                        code VARCHAR(5) references companies(code),
                        pageName VARCHAR(30) NOT NULL,
                        elementName VARCHAR(100) NOT NULL,
                        elementIdentifier VARCHAR(400) NOT NULL,
                        elementIdentifierValue VARCHAR(400) NOT NULL,
                        primary key (pageName));

create table actoins(ID INT NOT null, actionName VARCHAR(20) NOT NULL);

CREATE TABLE tests(ID INT NOT NULL,
                        code VARCHAR(5) references companies(code),
                        test VARCHAR(1000) NOT NULL,
                        pageName VARCHAR(30) NOT NULL references pages(pageName),
                        elementName VARCHAR(100),
                        actionName VARCHAR(20) NOT NULL,
                        primary key (test));

commit;
END