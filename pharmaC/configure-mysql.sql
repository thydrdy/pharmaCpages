# connect to mysal and run as root user
# Create Databases
CREATE DATABASE taco_cloud;

# Create user
CREATE USER 'taco_user'@'localhost' IDENTIFIED BY 'P@$$w0rd';

# Database Grants
GRANT SELECT ON taco_cloud.* to 'taco_user'@'localhost';
GRANT INSERT ON taco_cloud.* to 'taco_user'@'localhost';
GRANT UPDATE ON taco_cloud.* to 'taco_user'@'localhost';
GRANT DELETE ON taco_cloud.* to 'taco_user'@'localhost';
