drop database dbdep;

revoke all privileges, grant option from 'dbdep'@'localhost';

drop user 'dbdep'@'localhost';