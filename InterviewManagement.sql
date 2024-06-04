-- Create database
IF NOT EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'InterviewManagement')
BEGIN
    CREATE DATABASE InterviewManagement;
END
GO
USE InterviewManagement;
-- End creating database

-- Member table
CREATE TABLE [Member] (
    member_id           INT            IDENTITY(1, 1) PRIMARY KEY,
    member_full_name    NVARCHAR(255)  NOT NULL,
    member_account      VARCHAR(255)   NOT NULL       UNIQUE,
    member_password     VARCHAR(500)   NOT NULL,
    member_dob          DATE           NOT NULL,
    member_created_time DATETIME       DEFAULT CURRENT_TIMESTAMP,
    member_address      NVARCHAR(255),
    member_email        VARCHAR(255),
    member_phone_number VARCHAR(20),
    member_image        VARCHAR(255),
	member_note         NVARCHAR(1000),
    member_status       NVARCHAR(100)  DEFAULT 'Active',
    member_role         NVARCHAR(100)
);
-- End Member table

-- Candidate table
CREATE TABLE Candidate (
    candidate_id            INT IDENTITY(1, 1) PRIMARY KEY,
    candidate_name          NVARCHAR(255)      NOT NULL,
    candidate_address       NVARCHAR(255),
    candidate_phone_number  VARCHAR(20),
    candidate_email         VARCHAR(255),
    candidate_image         VARCHAR(255),
    candidate_dob           DATE               NOT NULL,
    candidate_cv_attachment VARCHAR(255),
    candidate_status        NVARCHAR(100),
	candidate_note          NVARCHAR(1000)
);
-- End Candidate table

-- Job table 
CREATE TABLE Job (
    job_id          INT            IDENTITY(1, 1) PRIMARY KEY,
    job_title       NVARCHAR(255)  NOT NULL,
    job_description NVARCHAR(1000) NOT NULL,
    job_status      NVARCHAR(100)  NOT NULL,
    job_salary_from FLOAT          NOT NULL,
    job_salary_to   FLOAT          NOT NULL,
);
-- End Job table

-- Interview table 
CREATE TABLE Interview (
    interview_id         INT IDENTITY(1, 1) PRIMARY KEY,
    interview_title      NVARCHAR(255)      NOT NULL,
    interview_start_time DATETIME           DEFAULT CURRENT_TIMESTAMP,
    interview_end_time   DATETIME           DEFAULT CURRENT_TIMESTAMP,
    interview_location   NVARCHAR(255)      NOT NULL,
    interview_status     NVARCHAR(100)      NOT NULL,
    interview_result     NVARCHAR(100)      NOT NULL,
	interview_note       NVARCHAR(1000),
    candidate_id         INT,
	job_id               INT
);
-- End Interview table

-- Interviewer table 
CREATE TABLE Interviewer (
    interview_id INT,
    member_id INT,
	PRIMARY KEY (interview_id, member_id)
);
-- End Interviewer table 

-- Constraint: Interview - Candidate (1 - n)
ALTER TABLE Interview
ADD CONSTRAINT FK_Interview_Candidate
FOREIGN KEY (candidate_id) REFERENCES Candidate(candidate_id) ON UPDATE CASCADE ON DELETE CASCADE
-- End constraint: Interview - Candidate (1 - n)

-- Constraint: Interview - Job (1 - n)
ALTER TABLE	Interview
ADD CONSTRAINT FK_Interview_Job
FOREIGN KEY (job_id) REFERENCES Job(job_id) ON UPDATE CASCADE ON DELETE CASCADE
-- End constraint: Interview - Job (1 - n)

-- Constraint: Interview - Member (n - n)
ALTER TABLE Interviewer
ADD CONSTRAINT FK_Interviewer_Interview
FOREIGN KEY (interview_id) REFERENCES Interview(interview_id) ON UPDATE CASCADE ON DELETE CASCADE

ALTER TABLE Interviewer
ADD CONSTRAINT FK_Interviewer_Member
FOREIGN KEY (member_id) REFERENCES [Member](member_id) ON UPDATE CASCADE ON DELETE CASCADE
-- End constraint: Interview - Member (n - n)

-- Some Candidate templates
INSERT INTO Candidate (candidate_name, candidate_address, candidate_phone_number, candidate_email, candidate_image, candidate_dob, candidate_cv_attachment, candidate_status)
VALUES
    ('John Doe', '123 Main Street, City, Country', '123-456-7890', 'john@example.com', 'john_doe.jpg', '1990-05-15', 'john_doe_cv.docx', 'Qualified'),
    ('Jane Smith', '456 Elm Street, City, Country', '987-654-3210', 'jane@example.com', 'jane_smith.jpg', '1985-08-25', 'jane_smith_cv.docx', 'Unqualified'),
    ('Mike Johnson', '789 Oak Street, City, Country', '555-123-4567', 'mike@example.com', 'mike_johnson.jpg', '1982-11-10', 'mike_johnson_cv.docx', 'Qualified'),
    ('Emily Brown', '101 Pine Street, City, Country', '444-555-6666', 'emily@example.com', 'emily_brown.jpg', '1995-03-20', 'emily_brown_cv.docx', 'Unqualified'),
    ('David Lee', '202 Cedar Street, City, Country', '222-333-4444', 'david@example.com', 'david_lee.jpg', '1988-09-08', 'david_lee_cv.docx', 'Waiting'),
	('Alice Johnson', '321 Pine St, Somewhere', '+9876543210', 'alice.johnson@example.com', 'alice_johnson.jpg', '1993-08-25', 'alice_johnson_cv.docx', 'Qualified'),
    ('Bob Brown', '654 Maple St, Nowhere', '+1122334455', 'bob.brown@example.com', 'bob_brown.jpg', '1988-04-12', 'bob_brown_cv.docx', 'Waiting'),
    ('Emily White', '987 Cedar St, Anywhere', '+8765432109', 'emily.white@example.com', 'emily_white.jpg', '1995-11-18', 'emily_white_cv.docx', 'Unqualified'),
    ('Daniel Lee', '159 Walnut St, Elsewhere', '+9988776655', 'daniel.lee@example.com', 'daniel_lee.jpg', '1991-07-07', 'daniel_lee_cv.docx', 'Qualified'),
    ('Olivia Taylor', '852 Oak St, Overthere', '+6655443322', 'olivia.taylor@example.com', 'olivia_taylor.jpg', '1986-03-30', 'olivia_taylor_cv.docx', 'Waiting'),
	('Sophia Martinez', '753 Cherry St, Everywhere', '+7788990011', 'sophia.martinez@example.com', 'sophia_martinez.jpg', '1994-09-14', 'sophia_martinez_cv.docx', 'Qualified'),
    ('Liam Wilson', '369 Birch St, Anyplace', '+5566778899', 'liam.wilson@example.com', 'liam_wilson.jpg', '1989-06-28', 'liam_wilson_cv.docx', 'Waiting'),
    ('Ava Garcia', '147 Spruce St, Noway', '+1122334455', 'ava.garcia@example.com', 'ava_garcia.jpg', '1996-12-03', 'ava_garcia_cv.docx', 'Unqualified'),
    ('Noah Anderson', '258 Pine St, Somewhy', '+9988776655', 'noah.anderson@example.com', 'noah_anderson.jpg', '1990-05-01', 'noah_anderson_cv.docx', 'Qualified'),
    ('Isabella Hernandez', '741 Cedar St, Anywhichway', '+3344556677', 'isabella.hernandez@example.com', 'isabella_hernandez.jpg', '1987-02-17', 'isabella_hernandez_cv.docx', 'Waiting');
-- End some Candidate templates

-- Some Job templates
INSERT INTO Job (job_title, job_description, job_status, job_salary_from, job_salary_to)
VALUES
    ('Software Engineer', 'Developing software applications', 'Available', 60000, 90000),
    ('Data Scientist', 'Analyzing and interpreting complex data sets', 'Available', 70000, 110000),
    ('Project Manager', 'Leading and managing project teams', 'Unavailable', 80000, 120000),
    ('UI/UX Designer', 'Creating member-friendly interfaces', 'Unavailable', 65000, 100000),
    ('Network Administrator', 'Managing and maintaining network infrastructure', 'Available', 55000, 95000),
	('Database Administrator', 'Responsible for managing and maintaining databases.', 'Available', 50000, 70000),
    ('Frontend Developer', 'Responsible for developing member-facing web applications.', 'Unavailable', 60000, 80000),
    ('Backend Developer', 'Responsible for server-side application logic and integration.', 'Available', 55000, 75000),
    ('Business Analyst', 'Responsible for analyzing and documenting business processes and requirements.', 'Available', 55000, 75000),
    ('Customer Support Specialist', 'Responsible for providing assistance and support to customers.', 'Unavailable', 45000, 65000);
-- End some Job templates

-- Some Member templates
INSERT INTO [Member] (member_full_name, member_account, member_password, member_dob, member_address, member_email, member_phone_number, member_image, member_status, member_role)
VALUES 
    ('John Doe', 'jdoe1', 'p', '1990-05-15', '123 Main St', 'john.doe@example.com', '+1234567890', 'john_doe.jpg', 'Active', 'Administrator'),
    ('Jane Smith', 'jsmith2', 'q', '1985-10-20', '456 Elm St', 'jane.smith@example.com', '+0987654321', 'jane_smith.jpg', 'Inactive', 'HR Manager'),
    ('Michael Johnson', 'mjohn3', 'r', '1992-12-30', '789 Oak St', 'michael.johnson@example.com', '+1122334455', 'michael_johnson.jpg', 'Active', 'Job Manager'),
    ('Alice Johnson', 'ajohn4', 's', '1993-08-25', '321 Pine St', 'alice.johnson@example.com', '+9876543210', 'alice_johnson.jpg', 'Active', 'Member'),
    ('Bob Brown', 'bbrown5', 't', '1988-04-12', '654 Maple St', 'bob.brown@example.com', '+1122334455', 'bob_brown.jpg', 'Inactive', 'Administrator'),
    ('Emily White', 'ewhite6', 'u', '1995-11-18', '987 Cedar St', 'emily.white@example.com', '+8765432109', 'emily_white.jpg', 'Active', 'HR Manager'),
    ('Daniel Lee', 'dlee7', 'v', '1991-07-07', '159 Walnut St', 'daniel.lee@example.com', '+9988776655', 'daniel_lee.jpg', 'Inactive', 'Job Manager'),
    ('Olivia Taylor', 'otaylor8', 'w', '1986-03-30', '852 Oak St', 'olivia.taylor@example.com', '+6655443322', 'olivia_taylor.jpg', 'Active', 'Member'),
    ('Sophia Martinez', 'smart9', 'x', '1994-09-14', '753 Cherry St', 'sophia.martinez@example.com', '+7788990011', 'sophia_martinez.jpg', 'Inactive', 'Administrator'),
    ('Liam Wilson', 'lwil10', 'y', '1989-06-28', '369 Birch St', 'liam.wilson@example.com', '+5566778899', 'liam_wilson.jpg', 'Active', 'HR Manager'),
    ('Ava Garcia', 'agarc11', 'z', '1996-12-03', '147 Spruce St', 'ava.garcia@example.com', '+1122334455', 'ava_garcia.jpg', 'Inactive', 'Job Manager'),
    ('Noah Anderson', 'nander12', '1', '1990-05-01', '258 Pine St', 'noah.anderson@example.com', '+9988776655', 'noah_anderson.jpg', 'Active', 'Member'),
    ('Isabella Hernandez', 'ihern13', '2', '1987-02-17', '741 Cedar St', 'isabella.hernandez@example.com', '+3344556677', 'isabella_hernandez.jpg', 'Inactive', 'Administrator'),
    ('Emma Brown', 'ebrown14', '3', '1992-11-05', '369 Elm St', 'emma.brown@example.com', '+1122334455', 'emma_brown.jpg', 'Active', 'HR Manager'),
    ('James Smith', 'jsmith15', '4', '1993-08-15', '456 Oak St', 'james.smith@example.com', '+9876543210', 'james_smith.jpg', 'Inactive', 'Job Manager'),
	('Ethan Miller', 'emiller16', '5', '1991-04-08', '753 Elm St', 'ethan.miller@example.com', '+1122334455', 'ethan_miller.jpg', 'Active', 'Administrator'),
    ('Mia Wilson', 'mwilson17', '6', '1988-09-25', '951 Maple St', 'mia.wilson@example.com', '+9988776655', 'mia_wilson.jpg', 'Inactive', 'HR Manager'),
    ('Alexander Johnson', 'ajohn18', '7', '1994-06-14', '357 Cedar St', 'alexander.johnson@example.com', '+3344556677', 'alexander_johnson.jpg', 'Active', 'Job Manager'),
    ('Charlotte Brown', 'cbrown19', '8', '1996-12-30', '852 Pine St', 'charlotte.brown@example.com', '+1122334455', 'charlotte_brown.jpg', 'Inactive', 'Member'),
    ('William Garcia', 'wgarc20', '9', '1990-03-15', '456 Oak St', 'william.garcia@example.com', '+9876543210', 'william_garcia.jpg', 'Active', 'Administrator'),
    ('Sophia Martinez', 'smart21', '0', '1987-11-05', '369 Spruce St', 'sophia.martinez@example.com', '+5566778899', 'sophia_martinez.jpg', 'Inactive', 'HR Manager'),
    ('Michael Lee', 'mlee22', '!', '1993-08-15', '147 Walnut St', 'michael.lee@example.com', '+9988776655', 'michael_lee.jpg', 'Active', 'Job Manager'),
    ('Amelia Hernandez', 'ahern23', '@', '1985-05-01', '258 Elm St', 'amelia.hernandez@example.com', '+1122334455', 'amelia_hernandez.jpg', 'Inactive', 'Member'),
    ('Benjamin Smith', 'bsmith24', '#', '1992-02-17', '741 Cherry St', 'benjamin.smith@example.com', '+7788990011', 'benjamin_smith.jpg', 'Active', 'Administrator'),
    ('Evelyn Johnson', 'ejohn25', '$', '1989-11-28', '963 Cedar St', 'evelyn.johnson@example.com', '+1234567890', 'evelyn_johnson.jpg', 'Inactive', 'HR Manager'),
    ('Henry Brown', 'hbrown26', '%', '1996-06-03', '852 Oak St', 'henry.brown@example.com', '+1122334455', 'henry_brown.jpg', 'Active', 'Job Manager'),
    ('Madison Garcia', 'mgarc27', '^', '1991-09-14', '369 Pine St', 'madison.garcia@example.com', '+8765432109', 'madison_garcia.jpg', 'Inactive', 'Member'),
    ('Victoria Martinez', 'vmart28', '&', '1987-04-30', '741 Maple St', 'victoria.martinez@example.com', '+9988776655', 'victoria_martinez.jpg', 'Active', 'Administrator'),
    ('Dylan Wilson', 'dwil29', '*', '1994-03-01', '852 Cedar St', 'dylan.wilson@example.com', '+3344556677', 'dylan_wilson.jpg', 'Inactive', 'HR Manager'),
    ('Sophie Johnson', 'sjohn30', '(', '1993-12-20', '147 Oak St', 'sophie.johnson@example.com', '+1122334455', 'sophie_johnson.jpg', 'Active', 'Job Manager');
-- End some member templates

-- Some Interview templates
INSERT INTO Interview (interview_title, interview_location, interview_status, interview_result, candidate_id, job_id)
VALUES 
    ('Software Engineer Interview', 'New York', 'Finished', 'Passed', 1, 1),
    ('Data Scientist Interview', 'San Francisco', 'Scheduled', 'Waiting', 2, 2),
    ('Project Manager Interview', 'Seattle', 'Finished', 'Failed', 3, 3),
    ('UI/UX Designer Interview', 'Los Angeles', 'Scheduled', 'Waiting', 4, 4),
    ('Network Administrator Interview', 'Chicago', 'Finished', 'Passed', 5, 5),
    ('Database Administrator Interview', 'Boston', 'Scheduled', 'Waiting', 6, 6),
    ('Frontend Developer Interview', 'Austin', 'Finished', 'Failed', 7, 7),
    ('Backend Developer Interview', 'Denver', 'Scheduled', 'Waiting', 8, 8),
    ('Business Analyst Interview', 'Miami', 'Finished', 'Passed', 9, 9),
    ('Customer Support Specialist Interview', 'Dallas', 'Scheduled', 'Waiting', 10, 10),
    ('Database Administrator Interview', 'Houston', 'Finished', 'Failed', 11, 1),
    ('Software Engineer Interview', 'Atlanta', 'Scheduled', 'Waiting', 12, 2),
    ('UI/UX Designer Interview', 'Philadelphia', 'Finished', 'Passed', 13, 3),
    ('Data Scientist Interview', 'Washington D.C.', 'Scheduled', 'Waiting', 14, 4),
    ('Network Administrator Interview', 'San Diego', 'Finished', 'Failed', 15, 5);
-- End some Interview templates

-- Some Interviewer templates
INSERT INTO Interviewer
VALUES
	(1, 1),
	(1, 2),
	(2, 3),
	(2, 4),
	(3, 5),
	(3, 6),
	(4, 7),
	(4, 8),
	(5, 9),
	(5, 10),
	(6, 11),
	(6, 12),
	(7, 13),
	(7, 14),
	(8, 15),
	(8, 16),
	(9, 17),
	(9, 18),
	(10, 19),
	(10, 20),
	(11, 21),
	(11, 22),
	(12, 23),
	(12, 24),
	(13, 25),
	(13, 26),
	(14, 27),
	(14, 28),
	(15, 29),
	(15, 30)
-- End some Interviewer templates






