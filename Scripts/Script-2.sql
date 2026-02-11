--SELECT * FROM EMPLOYEES;
--SELECT SYSDATE FROM DUAL;
--select COLUMN ....
--	FROM TABLE..
--;
-- 1. 현재 시간을 조회한다.
-- ORACLE 전용 시간(ANSI X)
SELECT SYSDATE -- 예약어 (현재 시간을 가리키는 ORACLE 전용 예약어)
FROM DUAL
;

-- 오늘 기준으로 하루 전의 날짜와 시간 조회
SELECT SYSDATE -1
FROM DUAL
;

-- 현재 시간을 기준으로 3시간 전의 날짜와 시간 조회
SELECT
	SYSDATE - 3 / 24
FROM
	DUAL
;

-- 현재 시간을 기준으로 15분 전의 날짜와 시간 조회
SELECT
	SYSDATE - 15 / 24 / 60
FROM
	DUAL
;

-- 현재 시간을 기준으로 50초 이후의 날짜와 시간 조회
-- 50초 / 24시간 / 60분 / 60초
SELECT
	SYSDATE + 50 / 24 / 60 / 60
	, SYSDATE
FROM
	DUAL
;

-- 1년을 더하던 빼던 12개우러을 써서 하면 됨.
SELECT
	ADD_MONTHS(SYSDATE, -2)
FROM
	DUAL
;

-- 2. 현재 시간을 "연-월-일" 포멧으로 조회한다.
-- TO_CHAR, TO_DATE를 가장 많이 사용
SELECT
	TO_CHAR(SYSDATE, 'YYYY-MM-DD'),
	TO_CHAR(SYSDATE, 'YYYY'),-- 연
	TO_CHAR(SYSDATE, 'MM'), -- 월
	TO_CHAR(SYSDATE, 'DD'), -- 일
	TO_CHAR(SYSDATE, 'HH'), -- 12시간 기준의 시
	TO_CHAR(SYSDATE, 'HH24'), -- 24시간 기준의 시
	TO_CHAR(SYSDATE, 'MI'), -- 분
	TO_CHAR(SYSDATE, 'SS') -- 초
FROM
	DUAL
;

-- 3. 한 시간 전 시간을 "시:분:초" 포멧으로 조회한다.
SELECT
	TO_CHAR(SYSDATE -1 / 24, 'HH24:MI:SS')
FROM
	DUAL
;

-- 2월 12일 연습문제
-- '2026-02-12' 텍스트를 날짜타입으로 변경한다.
-- '2026-02-12' 기준으로 50일 이후 날짜와 시간을 조회한다.
-- '2026-02-13' 기준으로 3시간 이후의 날짜와 시간을 조회한다.
-- '2026-02-12' 기준으로 1일 후의 날짜만 조회한다.
-- '2026-02-12' 기준으로 3일 후의 날짜와 시만을 조회한다.

-- 4. EMPLOYEES 테이블의 모든 정보를 조회한다.
-- *은 실무에서 사용하지 않는다
-- 1. 영향도 검사
-- 2. 사용 x, 컬럼 조사
-- 3. 분석용이
-- 를 위해 사용하지 않기
SELECT
	*
FROM
	EMPLOYEES
;


-- 5. DEPARTMENTS 테이블의 모든 정보를 조회한다.
SELECT
	DEPARTMENT_ID,
	DEPARTMENT_NAME,
	MANAGER_ID,
	LOCATION_ID
FROM
	DEPARTMENTS
;

-- 6. JOBS 테이블의 모든 정보를 조회한다.
SELECT
	JOB_ID,
	JOB_TITLE,
	MIN_SALARY,
	MAX_SALARY
FROM
	jobs
;

-- 7. LOCATIONS 테이블의 모든 정보를 조회한다.
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

-- 8. COUNTRIES 테이블의 모든 정보를 조회한다.
SELECT
	COUNTRY_ID,
	COUNTRY_NAME,
	REGION_ID
FROM
	COUNTRIES
;

-- 9. REGIONS 테이블의 모든 정보를 조회한다.
SELECT
	REGION_ID,
	REGION_NAME
FROM
	REGIONS
;

-- 10. JOB_HISTORY 테이블의 모든 정보를 조회한다.
SELECT
	DEPARTMENT_ID,
	START_DATE,
	END_DATE,
	JOB_ID,
	DEPARTMENT_ID
FROM
	JOB_HISTORY
;

-- 11. 90번 부서에서 근무하는 사원(EMPLOYEES)들의 모든 정보를 조회한다.
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
	DEPARTMENT_ID = 90;
-- WHERE 90 = DEPARTMENT_ID; => 이렇게 작성할 경우 결과의 도출할때 까지의 시간이 완전히 달라서 이렇게 작성 x

-- 12. 90번 또는 100번 부서에서 근무하는 사원들의 모든 정보를 조회한다.
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
	DEPARTMENT_ID = 90
	OR DEPARTMENT_ID = 100;

-- 13. 100번(MANAGER_ID) 상사의 직속 부하직원(DEPARTMENT_ID)의 모든 정보를 조회한다.
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
	MANAGER_ID = 100;


-- 14. 직무 아이디가 AD_VP 인 사원의 모든 정보를 조회한다.
-- ORACLE, MYSQL, MARIADB, ...ALTER COLUMN TYPE => 날짜 = '0226-02-11', 숫자 = 10000, 텍스트 = 'AD_VP'
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
	JOB_ID = 'AD_VP';

-- 15. 월급이 7000 이상인 사원의 모든 정보를 조회한다.
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
	SALARY >= 7000;

-- 16. 2005년 09월에 입사한 사원들의 모든 정보를 조회한다.


-- 17. 111번 사원의 모든 정보를 조회한다.
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
	EMPLOYEE_ID = 111;


-- 18. 인센티브를 안받는 사원들의 모든 정보를 조회한다.
-- IS NOT NULL이나 IS NULL은 TYPE옆에 있는 NOT NULL이나 NULL 인지를 확인하고 사용해야한다.
-- NULL일 경우 사용가능
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
	COMMISSION_PCT IS NULL;

-- 19. 인센티브를 받는 사원들의 모든 정보를 조회한다.
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
	COMMISSION_PCT IS NOT NULL;

-- 20. 이름의 첫 글자가 'D' 인 사원들의 모든 정보를 조회한다.

-- 21. 성의 마지막 글자가 'a' 인 사원들의 모든 정보를 조회한다.
-- 22. 전화번호에 '.124.'이 포함된 사원들의 모든 정보를 조회한다.
-- 23. 직무 아이디가 'PU_CLERK'인 사원 중 월급이 3000 이상인 사원들의 모든 정보를 조회한다.
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
	JOB_ID = 'PU_CLERK'
	AND SALARY >= 3000;

-- 24. 평균 월급보다 많이 받는 사원들의 사원번호, 이름, 성, 월급을 조회한다.
-- 25. 평균 월급보다 적게 받는 사원들의 사원번호, 월급, 부서번호를 조회한다.
-- 26. 가장 많은 월급을 받는 사원의 사원번호, 이름, 월급을 조회한다.
-- 27. 이름이 4글자인 사원의 모든 정보를 조회한다.
-- 28. 'SA_REP' 직무인 직원 중 가장 높은 월급과 가장 낮은 월급을 조회한다.
-- 29. 직원의 입사일자를 '연-월-일' 형태로 조회한다.
-- 30. 가장 늦게 입사한 사원의 모든 정보를 조회한다.
-- 31. 가장 일찍 입사한 사원의 모든 정보를 조회한다.
-- 32. 자신의 상사보다 더 많은 월급을 받는 사원의 모든 정보를 조회한다.
-- 33. 자신의 상사보다 더 일찍 입사한 사원의 모든 정보를 조회한다.
-- 34. 부서아이디별 평균 월급을 조회한다.
-- 35. 직무아이디별 평균 월급, 최고월급, 최저월급을 조회한다.
-- 36. 가장 많은 인센티브를 받는 사원의 모든 정보를 조회한다.
-- 37. 가장 적은 인센티브를 받는 사원의 월급과 인센티브를 조회한다.
-- 38. 직무아이디별 사원의 수를 조회한다.
-- 39. 상사아이디별 부하직원의 수를 조회한다. 단, 부하직원이 2명 이하인 경우는 제외한다.
-- 40. 사원이 속한 부서의 평균월급보다 적게 받는 사원의 모든 정보를 조회한다.
-- 41. 사원이 근무하는 부서명, 이름, 성을 조회한다.
-- 42. 가장 적은 월급을 받는 사원의 부서명, 이름, 성, 월급, 부서장사원번호를 조회한다.
-- 43. 상사사원번호를 중복없이 조회한다.
-- 44. 50번 부서의 부서장의 이름, 성, 월급을 조회한다.
-- 45. 부서명별 사원의 수를 조회한다.
-- 46. 사원의 수가 가장 많은 부서명, 사원의 수를 조회한다.
-- 47. 사원이 없는 부서명을 조회한다.
-- 48. 직무가 변경된 사원의 모든 정보를 조회한다.
-- 49. 직무가 변경된적 없는 사원의 모든 정보를 조회한다.
-- 50. 직무가 변경된 사원의 과거 직무명과 현재 직무명을 조회한다.
-- 51. 직무가 가장 많이 변경된 부서의 이름을 조회한다.
-- 52. 'Seattle' 에서 근무중인 사원의 이름, 성, 월급, 부서명 을 조회한다.
-- 53. 'Seattle' 에서 근무하지 않는 모든 사원의 이름, 성, 월급, 부서명, 도시를 조회한다.
-- 54. 근무중인 사원이 가장 많은 도시와 사원의 수를 조회한다.  
-- 55. 근무중인 사원이 없는 도시를 조회한다.
-- 56. 월급이 7000 에서 12000 사이인 사원이 근무중인 도시를 조회한다.
-- 57. 'Seattle' 에서 근무중인 사원의 직무명을 중복없이 조회한다.
-- 58. 사내의 최고월급과 최저월급의 차이를 조회한다.
-- 59. 이름이 'Renske' 인 사원의 월급과 같은 월급을 받는 사원의 모든 정보를 조회한다. 단, 'Renske' 사원은 조회에서 제외한다.
-- 60. 회사 전체의 평균 월급보다 많이 받는 사원들 중 이름에 'u' 가 포함된 사원과 동일한 부서에서 근무중인 사원들의 모든 정보를 조회한다.
-- 61. 부서가 없는 국가명을 조회한다.
-- 62. 'Europe' 에서 근무중인 사원들의 모든 정보를 조회한다.
-- 63. 'Europe' 에서 가장 많은 사원들이 있는 부서명을 조회한다.
-- 64. 대륙 별 사원의 수를 조회한다.
-- 65. 월급이 2500, 3500, 7000 이 아니며 직업이 SA_REP 이나 ST_CLERK 인 사람들을 조회한다.
SELECT
	JOB_ID,
	SALARY
FROM
	EMPLOYEES
WHERE
	SALARY != 2500
	AND SALARY != 3500
	AND SALARY != 7000
	AND(JOB_ID = 'SA_REP'
		OR JOB_ID = 'ST_CLERK')
;

SELECT
	JOB_ID,
	SALARY
FROM
	EMPLOYEES
WHERE
	SALARY NOT IN (2500, 3500, 7000)
	AND JOB_ID IN('SA_REP', 'ST_CLERK') 
;

-- 65-1. 월급이 2500, 3500, 7000 이 아닌 사원들의 사원 번호와 월급을 조회한다.
-- IN : 각각의 값들을 다 가져온다 IN 2500 OR 3500 OR 7000
-- SALARY != 2500 AND SALARY != 3500 AND SALARY != 7000 => SALARY NOT IN (2500, 3500, 7000) !=를 의미
SELECT
	EMPLOYEE_ID,
	SALARY
FROM
	EMPLOYEES
WHERE
	SALARY NOT IN (2500, 3500, 7000)
;

-- 65-2. 직무아이디가 SA_REP 이거나 ST_CLERK 인 사원들의 사원번호와 직무아이디를 조회한다.
SELECT
	EMPLOYEE_ID,
	JOB_ID
FROM
	EMPLOYEES
WHERE
	JOB_ID IN('SA_REP', 'ST_CLERK') 
;

-- 66. 사원의 사원번호, 이름, 성, 상사의 사원번호, 상사의 이름, 상사의 성을 조회한다.
-- 67. 101번 사원의 모든 부하직원 들의 이름, 성, 상사사원번호, 상사사원명을 조회한다.
-- 68. 114번 직원의 모든 상사들의 이름, 성, 상사사원번호, 상사사원명을 조회한다.
-- 69. 114번 직원의 모든 상사들의 이름, 성, 상사사원번호, 상사사원명을 조회한다. 단, 역순으로 조회한다.
-- 70. 모든 사원(EMPLOYEES)들의 정보를 월급(SALARY) 오름차순 정렬하여 조회한다.
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
ORDER BY
	SALARY ASC 
;

-- 71. 모든 사원(EMPLOYEES)들을 이름(LAST_NAME) 내림차순 정렬하여 조회한다.
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
ORDER BY
	LAST_NAME DESC
;

-- 72. 모든 사원들의 이름, 성, 월급, 부서명을 부서번호로 내림차순 정렬하여 조회한다.
SELECT
    E.LAST_NAME,        -- 성
    E.FIRST_NAME,       -- 이름
    E.SALARY,           -- 월급(주급)
    D.DEPARTMENT_NAME   -- 부서명
FROM
    EMPLOYEES E
INNER JOIN
    DEPARTMENTS D
ON
    E.DEPARTMENT_ID = D.DEPARTMENT_ID
ORDER BY
    D.DEPARTMENT_ID DESC
;

-- 73. 부서명별 월급의 합을 내림차순 정렬하여 조회한다.
-- 74. 직무명별 사원의 수를 오름차순 정렬하여 조회한다.
-- 75. 모든 사원들의 모든 정보를 조회한다. 단, 인센티브를 받는 사원은 "인센티브여부" 컬럼에 "Y"를, 아닌 경우 "N"으로 조회한다.
-- 76. 모든 사원들의 이름을 10자리로 맞추어 조회한다.
-- 77. 2007년에 직무가 변경된 사원들의 현재 직무명, 부서명, 사원번호, 이름, 성을 조회한다.
-- 78. 직무별 최대월급보다 더 많은 월급을 받는 사원의 모든 정보를 조회한다.
-- 79. 사원들의 입사일자 중 이름, 성, 연도만 조회한다.
-- 80. 사원들의 입사일자 중 이름, 성, 연도, 월 만 조회한다.
-- 81. 100번 사원의 모든 부하직원을 계층조회한다. 단, LEVEL이 4인 사원은 제외한다.
-- 82. 많은 월급을 받는 10명을 조회한다.
-- 83. 가장 적은 월급을 받는 사원의 상사명, 부서명을 조회한다.
-- 84. 많은 월급을 받는 사원 중 11번 째 부터 20번째를 조회한다.
-- 85. 가장 적은 월급을 받는 중 90번 째 부터 100번째를 조회한다.
-- 86. 'PU_CLERK' 직무인 2번째 부터 5번째 사원의 부서명, 직무명을 조회한다.
-- 87. 모든 사원의 정보를 직무 오름차순, 월급 내림차순으로 조회한다.
-- 88. 직무별 평균월급을 평균월급순으로 오름차순 정렬하여 조회한다.
-- 89. 부서별 평균월급을 내림차순 정렬하여 조회한다.
-- 90. 이름의 첫 번째 글자별 평균월급을 조회한다.
-- 91. 입사연도별 최소월급을 조회한다.
-- 92. 월별 최대월급 중 2번째 부터 4번째 데이터만 조회한다.
-- 93. 직무명별 최소월급을 조회한다.
-- 94. 부서명별 최대월급을 조회한다.
-- 95. 직무명, 부서명 별 사원 수, 평균월급을 조회한다.
-- 96. 도시별 사원 수를 조회한다.
-- 97. 국가별 사원 수, 최대월급, 최소월급을 조회한다.
-- 98. 대륙별 사원 수를 대륙명으로 오름차순 정렬하여 조회한다.
-- 99. 이름이나 성에 'A' 혹은 'a' 가 포함된 사원의 모든 정보를 조회한다.
-- 100. 국가별로 월급이 5000 이상인 사원의 수를 조회한다.
-- 101. 인센티브를 안받는 사원이 근무하는 도시를 조회한다.
-- 102. 인센티브를 포함한 월급이 10000 이상인 사원의 모든 정보를 조회한다.
-- 103. 가장 많은 부서가 있는 도시를 조회한다.
-- 104. 가장 많은 사원이 있는 부서의 국가명을 조회한다.
-- 105. 우편번호가 5자리인 도시에서 근무하는 사원명, 부서명, 도시명, 우편번호를 조회한다.
-- 106. 우편번호에 공백이 없는 도시에서 근무하는 사원의 이름, 부서명, 우편번호를 조회한다.
-- 107. "주"가 없는 도시에서 근무하는 사원의 이름, 도시를 조회한다.
-- 108. 국가명이 6자리인 국가의 모든 정보를 조회한다.
-- 109. 사원의 이름과 성을 이용해 EMAIL과 같은 값으로 만들어 조회한다.
-- 110. 모든 사원들의 이름을 10자리로 변환해 조회한다. 예> 이름 => "        이름"
-- 111. 모든 사원들의 성을 10자리로 변환해 조회한다. 예> 성 => "성         "
-- 112. 109번 사원의 입사일 부터 1년 내에 입사한 사원의 모든 정보를 조회한다.
-- 113. 가장 먼저 입사한 사원의 입사일로부터 2년 내에 입사한사원의 모든 정보를 조회한다.
-- 114. 가장 늦게 입사한 사원의 입사일 보다 1년 앞서 입사한 사원의 모든 정보를 조회한다.
-- 115. 도시명에 띄어쓰기 " " 가 포함된 도시에서 근무중인 사원들의 부서명, 도시명, 사원명을 조회한다.
-- 116. MOD 함수를 통해 사원번호가 홀수면 남자, 짝수면 여자 로 구분해 조회한다. MOD(값, 나눌값)
-- 117. '20230222' 문자 데이터를 날짜로 변환해 조회한다.(DUAL)
-- 118. '20230222' 문자 데이터를 'YYYY-MM' 으로 변환해 조회한다.(DUAL)
-- 119. '20230222130140' 문자 데이터를 'YYYY-MM-DD HH24:MI:SS' 으로 변환해 조회한다. (DUAL)
-- 120. '20230222' 날짜의 열흘 후의 날짜를 'YYYY-MM-DD' 으로 변환해 조회한다. (DUAL)
-- 121. 사원 이름의 글자수 별 사원의 수를 조회한다.
-- 122. 사원 성의 글자수 별 사원의 수를 조회한다.
-- 123. 사원의 월급이 5000 이하이면 "사원", 7000 이하이면 "대리", 9000 이하이면 "과장", 그 외에는 임원 으로 조회한다.
-- 124. 부서별 사원의 수를 조인을 이용해 다음과 같이 조회한다."부서명 (사원의 수)"
-- 125. 부서별 사원의 수를 스칼라쿼리를 이용해 다음과 같이 조회한다. "부서명 (사원의 수)"
-- 126. 사원의 정보를 다음과 같이 조회한다. "사원번호 번 사원의 이름은 성이름 입니다."
-- 127. 사원의 정보를 스칼라쿼리를 이용해 다음과 같이 조회한다. "사원번호 번 사원의 상사명은 상사명 입니다."
-- 128. 사원의 정보를 조인을 이용해 다음고 같이 조회한다. "사원명 (직무명)"
-- 129. 사원의 정보를 스칼라쿼리를 이용해 다음과 같이 조회한다. "사원명 (직무명)"
-- 130. 부서별 월급 차이(최고월급 - 최저월급)가 가장 큰 부서명을 조회한다.
-- 131. 부서별 월급 차이(최고월급 - 최저월급)가 가장 큰 부서에서 근무하는 사원들의 직무명을 중복없이 조회한다.
-- 132. 부서장이 없는 부서명 중 첫 글자가 'C' 로 시작하는 부서명을 조회한다.
-- 133. 부서장이 있는 부서명 중 첫 글자가 'S' 로 시작하는 부서에서 근무중인 사원의 이름과 직무명, 부서명을 조회한다.
-- 134. 지역변호가 1000 ~ 1999 사이인 지역내 부서의 모든 정보를 조회한다.
-- 135. 90, 60, 100번 부서에서 근무중인 사원의 이름, 성, 부서명을 조회한다.
-- 136. 부서명이 5글자 미만인 부서에서 근무중인 사원의 이름, 부서명을 조회한다.
-- 137. 국가 아이디가 'C'로 시작하는 국가의 지역을 모두 조회한다.
-- 138. 국가 아이디의 첫 글자와 국가명의 첫 글자가 다른 모든 국가를 조회한다.
-- 139. 사원 모든 정보 중 이메일만 모두 소문자로 변경하여 조회한다.
-- 140. 사원의 월급을 TRUNC(소수점 버림) 함수를 사용해 100 단위는 버린채 다음과 같이 조회한다. 예> 3700 -> 3000, 12700 -> 12000
-- 141. 100단위를 버린 사원의 월급 별 사원의 수를 조회한다.
-- 142. 현재 시간으로부터 20년 전 보다 일찍 입사한 사원의 모든 정보를 조회한다.
-- 143. 부서번호별 현재 시간으로부터 15년 전 보다 일찍 입사한 사원의 수를 조회한다.
-- 144. 부서명, 직무명 별 평균 월급을 조회한다.
-- 145. 도시명, 지역명 별 사원의 수를 조회한다.
-- 146. 부서명, 직무명 별 평균 월급 중 가장 작은 평균월급을 받는 부서명, 직무명을 조회한다.
-- 147. 102번 직원의 모든 부하직원의 수를 조회한다.
-- 148. 113번 직원의 모든 부하직원의 수를 조회한다.
-- 149. 부하직원이 없는 사원의 모든 정보를 조회한다.
-- 150. 사원번호가 100번인 사원의 사원번호, 이름과 사원번호로 내림차순 정렬된 사원의 사원번호, 이름 조회한다.
/*조회 예
--------------------
100    Steven
206    William
205    Shelley
204    Hermann
203    Susan
202    Pat
201    Michael
200    Jennifer
199    Douglas
198    Donald
197    Kevin
196    Alana
...
*/
