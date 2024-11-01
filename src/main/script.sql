-- Database: task_app

-- DROP DATABASE IF EXISTS task_app;

-- CREATE DATABASE task_app
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'English_United Kingdom.1252'
--     LC_CTYPE = 'English_United Kingdom.1252'
--     LOCALE_PROVIDER = 'libc'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1
--     IS_TEMPLATE = False;

-- -- Create the 'users' table
-- CREATE TABLE users (
--     id SERIAL PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE,
--     password VARCHAR(255) NOT NULL,
--     email VARCHAR(100) NOT NULL UNIQUE,
--     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
-- );

-- -- Create the 'categories' table
-- CREATE TABLE categories (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(50) NOT NULL UNIQUE,
--     description TEXT
-- );

-- -- Create the 'task_status' table (lookup table for task statuses)
-- CREATE TABLE task_status (
--     id SERIAL PRIMARY KEY,
--     status VARCHAR(20) NOT NULL UNIQUE
-- );

-- -- Insert predefined status values into 'task_status'
-- INSERT INTO task_status (status) VALUES
--     ('pending'),
--     ('in_progress'),
--     ('completed');

-- -- Create the 'task_priority' table (lookup table for task priorities)
-- CREATE TABLE task_priority (
--     id SERIAL PRIMARY KEY,
--     priority VARCHAR(20) NOT NULL UNIQUE
-- );

-- -- Insert predefined priority values into 'task_priority'
-- INSERT INTO task_priority (priority) VALUES
--     ('low'),
--     ('medium'),
--     ('high');

-- -- Create the 'tasks' table
-- CREATE TABLE tasks (
--     id SERIAL PRIMARY KEY,
--     title VARCHAR(100) NOT NULL,
--     description TEXT,
--     due_date DATE CHECK (due_date >= CURRENT_DATE),
--     user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
--     category_id INTEGER REFERENCES categories(id),
--     status_id INTEGER NOT NULL REFERENCES task_status(id),
--     priority_id INTEGER NOT NULL REFERENCES task_priority(id)
-- );

-- Query to select all users from the 'users' table
SELECT 
    id,
    username,
    email,
    created_at
FROM 
    users;
