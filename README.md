# CurrencyChartFX-Java-20-Maven

Maven JavaFX IntelliJ IDEA проект - Java 20, JavaFX, Maven, JasperReports, JDBC (Oracle, MS SQL, Azure SQL, PostgreSQL, MySQL, MariaDB, IBM DB2, IBM Informix, Firebird, SQLite, MongoDB, Amazon Aurora MySQL, Amazon Aurora PostgreSQL, Cassandra).
Створення графіків курсів НБУ по роках для спостереження за тенденціями змін.

- Settings.json - поточний файл з налаштуваннями підключення до DB
- Settings (local-and-docker).json - шаблон для локальних та Docker DB
- Settings (aws).json - шаблон для Amazon RDS DB

Базы данных Docker можно взять из проекта #Docker-Win11 (https://github.com/LiaArtem/Docker-Win11)

Запуск проекту для Windows (bat файл):
..\jdk-20\bin\java" -jar --module-path "%cd%"\javafx-sdk\lib --add-modules=javafx.base,javafx.controls,javafx.fxml,javafx.graphics ,javafx.media,javafx.swing,javafx.web CurrencyChartFXMaven.jar

Первинне налаштування:
---------------------------------------------------------------------------------
- завантажити та встановити IntelliJ IDEA Community
- завантажити та встановити Git
- завантажити та встановити jdk-20_windows-x64_bin.exe (20.*)
- завантажити та встановити SceneBuilder-19.0.0
- завантажити та встановити TIB_js-studiocomm_***_windows_x86_64.exe + запустити та закрити.
- налаштувати Github в IntelliJ IDEA Community (Settings - Version Control - Github)

Розгортання - налаштування:
---------------------------------------------------------------------------------
- Завантажити та розпакувати javafx (20.*) в папку проекту: ./javafx-sdk/

Налаштування JavaFX:
---------------------------------------------------------------------------------
- https://www.jetbrains.com/help/idea/javafx.html#check-plugin
- IntelliJ IDEA -> File -> Settings -> Languages ​​and Frameworks -> JavaFX -> Вказати шлях до SceneBuilder (C:\Users\Admin\AppData\Local\SceneBuilder\SceneBuilder.exe)
- Папку при зміні версії JavaFX не міняти = \javafx-sdk\

Налаштування звітів:
---------------------------------------------------------------------------------
- TIB_js-studiocomm_***_windows_x86_64.exe, запустити TIBCO Jaspersoft Studio
- Скопировать .\JaspersoftWorkspace\ в C:\Users\Admin\JaspersoftWorkspace
- змінити налаштування Datasource, якщо необхідно
    - !!! Під час розробки звітів у Jaspersoft® Studio для MSSQL виникає помилка
      java.lang.UnsatisfiedLinkError: Native Library .\mssql-jdbc_auth-X.X.X.x64.dll already loaded in another classloader) методи лікування в інтернеті не підійшли
      При виконанні в java не з'являється, мабуть проблема Jaspersoft Studio з вбудованою роботою з jre11

---------------------------------------------------------------------------------
Налаштування баз даних (+ JDBC Driver):
---------------------------------------------------------------------------------
- Oracle
  - після встановлення міняємо у глоб. реєстрі:
    - Комп'ютер\HKEY_LOCAL_MACHINE\SOFTWARE\ORACLE\KEY_OraDB21Home1 з AMERICAN_AMERICA.WE8MSWIN1252
      на NLS_LANG = AMERICAN_AMERICA.AL32UTF8 (або AMERICAN_AMERICA.CL8MSWIN1251)

  - Oracle SQL Developer виконуємо скрипти з папки. \sql\oracle\
    - під користувачем SYS (1_CREATE_DATABASE_AND_USER.sql)
    - решта під користувачем TEST_USER
    - !!! Перед завантаженням скриптів потрібно налаштувати обов'язково (експорт таблиць виконано в UTF-8).
    - !!! Налаштовуємо кодування серед Oracle SQL Developer - Tools -> Preferences -> Environment -> Encoding (міняємо на UTF-8).

---------------------------------------------------------------------------------
- MS SQL
  - Microsoft SQL Server Management Studio виконуємо скрипти з папки .\sql\mssql\
    !!!! *.sql в меню (Query -> SQLCMD Mode)

  Для роботи jdbc:
  - You need to Go to Start > Microsoft SQL Server > Configuration Tools > SQL Server Configuration Manager
  - SQL Server Configuration Manager > SQL Server Network Configuration > Protocols for MSSQLSERVER
    - Де ви знайдете протокол TCP/IP, якщо він вимкнений, потім увімкніть його.
    - Натисніть TCP/IP, ви знайдете його властивості.
    - !!! Вкладка Protocol - Enabled - Yes
    - !!! Вкладка IP Addresses - IPXX - Enabled - Yes (там де IP address - 127.0.0.1)
    - !!! Вкладка IP Addresses - IPXX - Enabled - Yes (там де IP address - ::1)
    - У цих властивостях видаліть всі динамічні порти TCP і додайте значення 1433 у всі TCP-порт (якщо вони є, за замовчуванням не було)
    - Перезапустіть служби SQL Server > SQL Server

  Включаємо Windows Aunthentication:
  - завантажити або взяти з mssql-jdbc_auth-X.X.X.x64.dll
  - Файл mssql-jdbc_auth-X.X.X.x64.dll скопіювати у windows\system32 для підключення в java
  - У файлі - DBConnSettings.json значення Security_mode_WA = true

  Включаємо SQL Server Aunthentication:
  - Запускаємо Microsoft SQL Server Management Studio
  - Ім'я сервера (SQL Server .....) -> правою клавішею Properties -> Security -> SQL Server and Windows Aunthentication mode включаємо
  - Перезапускаємо сервер
  - Включаємо користувача sa (Security -> Logins -> Properties)
    - General - Password (встановлюємо пароль = 12345678)
    - General (Default database - TestDB)
    - Status (Login - Enabled)
  - У файлі - DBConnSettings.json значення Security_mode_WA = true

---------------------------------------------------------------------------------
- Azure SQL
  - скачати Download Microsoft JDBC Driver for SQL Server - (sqljdbc_X.X.X.X_rus.zip).
  - Файл mssql-jdbc_auth-X.X.X.x64.dll скопіювати у windows\system32 для підключення в java

  - Microsoft SQL Server Management Studio 19 виконуємо скрипти з папки .\sql\azuredb\
    !!!! *.sql в меню (Query -> SQLCMD Mode)

---------------------------------------------------------------------------------
- PostgreSQL
  - DBeaver виконуємо скрипти з папки .\sql\postgeesql\ (при підключенні вкладка PostgreSQL відображати всі бази даних)
    - відкрити SQL скрипт -> Виконати SQL скрипт (Alt+X) (Файли зі скриптами в UTF8 - база в CP1251, при відкритті можуть бути ієрогліфи,
      тоді просто скопіювати текст і вставити у вікно SQL скрипта та виконати)

---------------------------------------------------------------------------------
- MySQL
  - MySQL Workbench виконуємо скрипти з папки .\sql\mysql\

---------------------------------------------------------------------------------
- MariaDB
  - DBeaver виконуємо скрипти з папки. \sql\mariadb\

---------------------------------------------------------------------------------
- SQLite
  - нічого, створення таблиць, подань та процедур автоматизовано у коді програми

---------------------------------------------------------------------------------
- IBM DB2
  - встановлюємо DB2 Community Edition (логін: db2admin, пароль: 12345678).
  - Створюємо базу даних: SAMPLE (Create sample database)
  - Видаємо адмін. права користувача db2admin:
    - запускаємо з Пуск -> Командне вікно DB2 - Адміністратор
    -> db2 connect to SAMPLE
    -> db2 grant DBADM on DATABASE to user db2admin
    -> db2 terminate
  - запускаємо DBeaver
  - підключаємося до сервера:
    - Тип - DB2 LUW
    - сервер - localhost:25000
    - база даних - SAMPLE
    - Користувач - db2admin
    - пароль - 12345678
  - Виконуємо скрипти .\sql\IBM DB2\

---------------------------------------------------------------------------------
IBM Informix
  - встановлюємо IBM Informix без інсталяції Instance (логін: informix (за замовчуванням), пароль: 12345678)
  - запускаємо Server Instance Manager та створюємо підключення
     - Dynamic Server Name: informix_test
     - Service Name: turbo_test
     - Port number: 9088 (за замовчуванням)
     - Password: 12345678
  - встановлюємо IBM Data Studio Client
    - запускаємо (праворуч -> Огляд джерел даних -> З'єднання бази даних -> New...)
    - Підключення:
      - Jdbc driver: Informix 12.1 - Informix JDBC driver default
      - База даних: sysadmin
      - Хост: localhost
      - Номер порту: 9088
      - Сервер: informix_test
      - Ім'я користувача: informix
      - Пароль: 12345678 ([v] Зберегти пароль)
      - Схема: порожньо
    - перемикаємо <Операція: Управління базами даних> на <Операція: Виконати SQL>
    - виконуємо скрипти .\sql\IBM Informix\1_CREATE_DATABASE.sql
    - відключиться від бази даних sysadmin та змінюємо на sample:
      - права клавіша Властивості -> (Загальна - sample, Властивості драйвера - База даних: sample) + застосувати та закрити
    - виконуємо скрипт .\sql\IBM Informix\3_CREATE TABLE AND VIEW.sql
    - Встановлення процедури:
      - праворуч -> Огляд джерел даних -> З'єднання бази даних -> sample -> informix -> Зберігаючі процедури -> правої New Stored Procedure (Ім'я будь-яке) -> Готово
      - замінюємо текст із файлу .\sql\IBM Informix\4_CREATE PROCEDURE.spsql
      - тиснемо маленьку кнопку - Deploy the routine to the database server -> Готово

---------------------------------------------------------------------------------
- Firebird
     - DBeaver виконуємо скрипти з папки .\sql\firebird\
     - якщо встановлений локально - підключення:
       jdbc:firebirdsql:localhost/3050:C:/Windows/System32/SAMPLEDATABASE.FDB?encoding=ISO8859_1

---------------------------------------------------------------------------------
- MongoDB
     - Структури створюються в Docker за замовчанням
     - Підключення "ConnectionMongoDB":
         - нова логіка - параметри для підключення та роботи через jdbc (mongodb_unityjdbc_free.jar)
           - "JDBCConnection": "jdbc:mongodb://localhost:27017/testDB?authMechanism=SCRAM-SHA-1&authSource=admin"
           - "DBUser": "root"
           - "DBPassword": "!Aa112233"

         - Стара логіка - параметри для підключення та роботи через (com.mongodb.MongoClient).
           Не підтримувалося формування звіту через JasperReports community edition
           - "MongoDBHost": "localhost"
           - "MongoDBPort": 27017
           - "MongoDBDatabase": "testDB"
           - "MongoDBCollection": "Curs"

---------------------------------------------------------------------------------
- Cassandra
     - Структури створюються в Docker за замовчанням або через RazorSQL виконуємо скрипти з папки. \sql\cassandra\
     - Підключення: jdbc:cassandra://localhost:9042/testdb

---------------------------------------------------------------------------------
Складання:
---------------------------------------------------------------------------------
- Build - Build Artifacts.. - Build

Fix:
Exception in thread "main" java.lang.SecurityException: Неправильний signature file digest for Manifest main attributes
- IntelliJ IDEA -> File -> Project Structure -> Add New (Artifacts) -> jar -> З Modules With Dependencies on the Create Jar From Module Window:
- Select you main class
- JAR File from Libraries Select copy to output directory and link via manifest

!!! Для JAR видаляти Artifacts і створювати заново при додаванні нових в maven (IntelliJ IDEA -> File -> Project Structure -> Add New (Artifacts) -> jar).

Fix:
Якщо у протоколі ??????
- в консолі якщо кодування windows (cp1251) - IntelliJ IDEA -> File -> Settings -> Editor -> FileEncodings -> Global Encoding -> windows-1251

---------------------------------------------------------------------------------
Завантаження первинних курсів
---------------------------------------------------------------------------------
- https://bank.gov.ua/control/uk/curmetal/currency/search/form/period
- Вказати період та експорт JSON

--------------------------------------------------------------------------------
Відключення доданого файлу від останнього додавання в commit в IntellJ
--------------------------------------------------------------------------------
- Відкриваємо термінал в IntellJ
- Удаляємо локальний кеш наступної команди:
-> git update-index --skip-worktree FILE_NAME
- Повернути:
-> git update-index --no-skip-worktree FILE_NAME