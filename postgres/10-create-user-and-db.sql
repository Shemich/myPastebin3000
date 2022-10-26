-- file: 10-create-user-and-db.sql
CREATE DATABASE pastes;
CREATE ROLE program WITH PASSWORD 'test';
GRANT ALL PRIVILEGES ON DATABASE pastes TO program;
ALTER ROLE program WITH LOGIN;