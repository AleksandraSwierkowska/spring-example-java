# Spring Java Example


Environment variables required to connect to database:
```
    DB_URL = <url of db>*
    DB_USER = <user of db>
    DB_PASSWORD = <user's password to db> 
```

\* in case of time zone error you need to append this to db url:
```
    ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
``` 

SQL scripts order:
1. create_script.sql
2. data_script.sql