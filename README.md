# Bank-service

Bank application for transactions, than can get any type of request (JSON/XML)

# Installation

Install [postgreSQL](https://www.postgresql.org)

Add your configs at application.properties

# Stack of Technologies

Spring Boot, Spring Data, Hibernate, Swagger

# API
## Account
Post Mapping /account/ - create  
Get Mapping /account/{id} - get  
Delete Mapping /account/{id} - delete  

## Client
Post Mapping /client/ - create  
Get Mapping /client/{id} - get  
Delete Mapping /client/{id} - delete  

## Transaction
Post Mapping /transaction/ - create  
Post Mapping /transaction/list - create list of transaction  
Get Mapping /transaction/{id} - get  
Get Mapping /transaction/by-account/{id} - get transactions by account  
Get Mapping /transaction/ - get transactions by set of any parametrs
