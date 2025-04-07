CREATE DATABASE IF NOT EXISTS notes;

USE notes;

CREATE TABLE IF NOT EXISTS note (
  id INT AUTO_INCREMENT PRIMARY KEY,
  value TEXT NOT NULL,
  date_created DATETIME DEFAULT CURRENT_TIMESTAMP
);
-- Log table is for a mock table for the log service (a fake third-party service as per the req's).
CREATE TABLE IF NOT EXISTS log (
  id INT AUTO_INCREMENT PRIMARY KEY,
  value TEXT NOT NULL
);

CREATE USER IF NOT EXISTS 'apiUser'@'%' IDENTIFIED BY 'A$uperSecre+DBP@ssw0rd';
GRANT ALL PRIVILEGES ON notes.* TO 'apiUser'@'%';
FLUSH PRIVILEGES;