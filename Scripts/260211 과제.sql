--1. 모든 사원의 모든 정보를 조회한다. 조회 결과 : 107
SELECT                 
	EMPLOYEE_ID,       
	FIRST_NAME,        
	LAST_NAME,         
	EMAIL,             
	PHONE_NUMBER,      
	HIRE_DATE,         
	JOB_ID,            
	SALARY,            
	COMMISSION_PCT,    
	MANAGER_ID,        
	DEPARTMENT_ID
FROM                   
	EMPLOYEES
;

--2. 부서가 없는 사원의 모든 정보를 조회한다. 조회 결과 : 1
SELECT                 
	EMPLOYEE_ID,       
	FIRST_NAME,        
	LAST_NAME,         
	EMAIL,             
	PHONE_NUMBER,      
	HIRE_DATE,         
	JOB_ID,            
	SALARY,            
	COMMISSION_PCT,    
	MANAGER_ID,        
	DEPARTMENT_ID
FROM                   
	EMPLOYEES
WHERE
	DEPARTMENT_ID IS NULL
;

--3. 직무가 없는 사원의 모든 정보를 조회한다. 조회 결과 : 107
SELECT                 
	EMPLOYEE_ID,       
	FIRST_NAME,        
	LAST_NAME,         
	EMAIL,             
	PHONE_NUMBER,      
	HIRE_DATE,         
	JOB_ID,            
	SALARY,            
	COMMISSION_PCT,    
	MANAGER_ID,        
	DEPARTMENT_ID
FROM                   
	EMPLOYEES
WHERE
	JOB_ID = ''
;

--4. 부서와 직무가 모두 있는 사원의 모든 정보를 조회한다. 조회 결과 : 107
SELECT                 
	EMPLOYEE_ID,       
	FIRST_NAME,        
	LAST_NAME,         
	EMAIL,             
	PHONE_NUMBER,      
	HIRE_DATE,         
	JOB_ID,            
	SALARY,            
	COMMISSION_PCT,    
	MANAGER_ID,        
	DEPARTMENT_ID
FROM                   
	EMPLOYEES
WHERE
	DEPARTMENT_ID IS NOT NULL
	AND JOB_ID IS NOT NULL
;


--5. 부서장이 없는 모든 부서의 모든 정보를 조회한다. 조회 결과 : 16
SELECT
	DEPARTMENT_ID,
	DEPARTMENT_NAME,
	MANAGER_ID,
	LOCATION_ID
FROM                   
	DEPARTMENTS
WHERE
	MANAGER_ID IS NULL
;

--6. 부서장이 있는 모든 부서의 모든 정보를 조회한다. 조회 결과 : 11
SELECT
	DEPARTMENT_ID,
	DEPARTMENT_NAME,
	MANAGER_ID,
	LOCATION_ID
FROM                   
	DEPARTMENTS
WHERE
	MANAGER_ID IS NOT NULL
;

--7. 사원의 이름만 조회한다. 조회 결과 : 107
SELECT                       
	FIRST_NAME
FROM                   
	EMPLOYEES
;
--8. 모든 지역의 모든 정보를 조회한다. 조회 결과 : 23
SELECT
	LOCATION_ID,
	STREET_ADDRESS,
	POSTAL_CODE,
	CITY,
	STATE_PROVINCE,
	COUNTRY_ID
FROM
	LOCATIONS
;

--9. 지역이 없는 모든 부서의 정보를 조회한다. 조회 결과 : 0
SELECT
	DEPARTMENT_ID,
	DEPARTMENT_NAME,
	MANAGER_ID,
	LOCATION_ID
FROM                   
	DEPARTMENTS
WHERE
	LOCATION_ID IS NULL
;
