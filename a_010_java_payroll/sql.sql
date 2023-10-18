select sysdate from dual;

인사마스터(테이블명:hr_master)
create table hr_master(
	id          char(6) primary key,	
	name        varchar2(30),
	jumin1      char(6),
	jumin2      char(7),
	department  char(4),			//기준-구분(11)
	grade      char(2),			 	//기준-구분(21)
	ho          number(2),			//호봉코드
	status      char(1),			//기준-구분(99)
	date_in     date,
	date_out    date
);

기준적용코드(테이블 명:hr_standard_code)			
create table hr_standard_code(
	std1          char(2),
	std2          char(6),
	name          varchar2(30),
	std_won       number(8,1),
	std_rate1     number(4,1),		//예: 100% -> 1.0, 150%->1.5
	std_rate2     number(4,1),		//예: 100% -> 1.0, 150%->1.5
	std_rate3     number(4,1),		//예: 100% -> 1.0, 150%->1.5
	sys_in        date,				//시스템 등록일자
	primary key(std1,std2)
);

호봉코드(테이블 명:hr_hobong_code)					
create table hr_hobong_code(
	grade     char(2),
	ho        number(2),
	ho_won    number(7),			//금액
	sys_in    date,					//시스템 등록일자
	primary key(grade,ho)
);

월 근태(테이블 명:hr_month_work)
create table hr_month_work(
	w_ym          char(6),
	id            char(6),
	month_std     number(2),		//통상 30일
	month_work    number(2),		//통상 30일
	month_minus   number(3,1),	
	m13           number(1),		//기준-구분(13) std2
	m14           number(1),		//기준-구분(14) std3
	not11_2       number(4,1),		//기준-구분(11) std2
	nnt11_3       number(4,1),		//기준-구분(11) std3
	hol12_1       number(4,1),		//기준-구분(12) std1
	hol12_2       number(4,1),		//기준-구분(12) std2
	hol12_3       number(4,1),		//기준-구분(312) std2	
	sys_in        date,				//시스템 등록일자
	primary key(w_ym,id)
);

급여지급자료(테이블 명:hr_month_payroll)
create table hr_month_payroll(
	pay_ym             char(6),
	id                 char(6),
	name               varchar2(30),
	jumin1             char(6),
	jumin2             char(7),
	department         char(4),		//기준-구분(11)
	grade              char(2),		//기준-구분(12)
	ho                 number(2),	//호봉코드
	status             char(1),		//기준-구분(99)
	month_std          number(2),	//통상 30일
	month_work         number(2),	//통상 30일
	month_minus        number(3,1),
	m13                number(1),	//기준-구분(13) std2
	m14                number(1),	//기준-구분(14) std3
	not11_2            number(4,1),	//기준-구분(11) std2
	nnt11_3            number(4,1),	//기준-구분(11) std3
	hol11_1            number(3,1),	//기준-구분(12) std1
	hol12_2            number(3,1),	//기준-구분(12) std2
	hol13_3            number(3,1),	//기준-구분(12) std2
	pay_ho             number(9),	//계산된 금액 호봉금액
	pay_month_std      number(9),	//계산된 금액 통상 30일
	pay_month_work     number(9),	//계산된 금액 통상 30일
	pay_month_minus    number(7),	//계산된 금액 공제
	pay_m13            number(7),	//계산된 금액 기준-구분(13) std2
	pay_m14            number(7),	//계산된 금액 기준-구분(14) std3
	pay_not11_2        number(7),	//계산된 금액 기준-구분(11) std2
	pay_nnt11_3        number(7),	//계산된 금액 기준-구분(11) std3
	pay_hol12_1        number(7),	//계산된 금액 기준-구분(12) std1
	pay_hol12_2        number(7),	//계산된 금액 기준-구분(12) std2
	pay_hol13_3        number(7),	//계산된 금액 기준-구분(12) std2
	pay_process_tol    number(9),
	pay_process_min    number(9),
	pay_process        number(9),
	sys_in             date,		//시스템 등록일자
	primary key(pay_ym,id)
);

1.급여작업프로젝트 : a_010_java_payroll / 인사마스터(테이블 : hr_master)
insert into hr_master values ('201001','김준비','700101','1037011','2100','10',14,'1','20010101','22991231');
insert into hr_master values ('202001','이태규','710102','1041011','1100','10',11,'1','20020101','22991231');
insert into hr_master values ('202002','김광태','720103','1057012','3100','10',12,'1','20020101','22991231');
insert into hr_master values ('203001','박민수','730201','1073013','1100','11',13,'1','20030101','22991231');
insert into hr_master values ('204001','홍해경','740311','2089014','5100','10',11,'1','20040101','22991231');
insert into hr_master values ('204002','유재두','801101','1105015','5110','21',12,'1','20040801','22991231');
insert into hr_master values ('204003','정나라','800402','2121016','3110','21',13,'1','20040801','22991231');
insert into hr_master values ('204004','손병기','810104','1137017','1120','31',13,'1','20040801','22991231');
insert into hr_master values ('205001','김정희','810811','2153018','5110','21',12,'1','20050501','22991231');
insert into hr_master values ('205002','박선영','821021','2169019','5120','31',13,'1','20050501','22991231');
insert into hr_master values ('205003','이만기','830220','1185020','4100','10',12,'1','20050501','22991231');
insert into hr_master values ('206001','정태준','840205','1201021','1110','11',12,'1','20061101','22991231');
insert into hr_master values ('206002','조승국','840306','1217022','1120','21',13,'1','20061101','22991231');
insert into hr_master values ('206003','최선금','840407','2233023','1110','31',13,'1','20061101','22991231');
insert into hr_master values ('206004','정선희','840508','2249024','5120','31',12,'1','20061101','22991231');
insert into hr_master values ('206005','박태준','840609','1265035','3110','31',14,'1','20061201','22991231');
insert into hr_master values ('206006','한나라','840710','1265005','3120','21',14,'1','20061201','22991231');
insert into hr_master values ('206007','명재남','840811','1275221','1130','31',13,'1','20061201','22991231');
insert into hr_master values ('206008','서민석','840710','1275231','4110','31',14,'1','20061201','22991231');
insert into hr_master values ('206009','김병석','840811','1201205','4120','21',13,'9','20061201','20230630');

2.기준적용코드(테이블 명 : hr_standard_code)
insert into hr_standard_code values ('11','1100','HR팀',          0,0,0,0,sysdate);
insert into hr_standard_code values ('11','1110','HR팀 기획',      0,0,0,0,sysdate);
insert into hr_standard_code values ('11','1120','HR팀 급여',      0,0,0,0,sysdate);
insert into hr_standard_code values ('11','1130','HR팀 교육',      0,0,0,0,sysdate);
insert into hr_standard_code values ('11','2100','기획팀',         0,0,0,0,sysdate);
insert into hr_standard_code values ('11','3100','SI팀',          0,0,0,0,sysdate);
insert into hr_standard_code values ('11','3110','SI팀 공공',      0,0,0,0,sysdate);
insert into hr_standard_code values ('11','3120','SI팀 금융',      0,0,0,0,sysdate);
insert into hr_standard_code values ('11','4100','SM팀',          0,0,0,0,sysdate);
insert into hr_standard_code values ('11','4110','SM팀 솔류션',     0,0,0,0,sysdate);
insert into hr_standard_code values ('11','4120','SM팀 공금/금융',  0,0,0,0,sysdate);
insert into hr_standard_code values ('11','5100','홍보팀',         0,0,0,0,sysdate);
insert into hr_standard_code values ('11','5110','홍보팀 국내',     0,0,0,0,sysdate);
insert into hr_standard_code values ('11','5120','홍보팀 국외',     0,0,0,0,sysdate);
insert into hr_standard_code values ('21','10','팀장',            500000,0,0,0,sysdate);
insert into hr_standard_code values ('21','11','팀원',             0,0,0,0,sysdate);
insert into hr_standard_code values ('21','21','팀원',            100000,0,0,0,sysdate);
insert into hr_standard_code values ('21','31','팀원',            200000,0,0,0,sysdate);
insert into hr_standard_code values ('91','11','평일지급율',        100,150,150,200,sysdate);
insert into hr_standard_code values ('91','12','공휴지급율',        150,150,150,200,sysdate);
insert into hr_standard_code values ('91','13','월차수당',         100,150,0,0,sysdate);
insert into hr_standard_code values ('91','14','년차수당',         100,150,0,0,sysdate);
insert into hr_standard_code values ('91','15','생리수당',         100,100,0,0,sysdate);
insert into hr_standard_code values ('99','1','재직',             0,0,0,0,sysdate);
insert into hr_standard_code values ('99','9','퇴직',             0,0,0,0,sysdate);

insert into hr_standard_code values ('00','00','구분',            0,0,0,0,sysdate);
insert into hr_standard_code values ('00','11','소속',            0,0,0,0,sysdate);
insert into hr_standard_code values ('00','21','직급',            0,0,0,0,sysdate);
insert into hr_standard_code values ('00','91','지급기준',         0,0,0,0,sysdate);
insert into hr_standard_code values ('00','99','재직상태',         0,0,0,0,sysdate);

3.호봉코드(테이블 명:hr_hobong_code)
insert into hr_hobong_code values('10',11,4500000,sysdate);
insert into hr_hobong_code values('10',12,5000000,sysdate);
insert into hr_hobong_code values('10',13,6000000,sysdate);
insert into hr_hobong_code values('10',14,7000000,sysdate);
insert into hr_hobong_code values('11',11,3100000,sysdate);
insert into hr_hobong_code values('11',12,3300000,sysdate);
insert into hr_hobong_code values('11',13,3800000,sysdate);
insert into hr_hobong_code values('11',14,4000000,sysdate);
insert into hr_hobong_code values('21',11,3000000,sysdate);
insert into hr_hobong_code values('21',12,3200000,sysdate);
insert into hr_hobong_code values('21',13,3700000,sysdate);
insert into hr_hobong_code values('21',14,3900000,sysdate);
insert into hr_hobong_code values('31',11,3300000,sysdate);
insert into hr_hobong_code values('31',12,3600000,sysdate);
insert into hr_hobong_code values('31',13,4000000,sysdate);
insert into hr_hobong_code values('31',14,4300000,sysdate);

4.월 근태(테이블 명:hr_month_work)
	4-1) 2023년 1월 월근태
insert into hr_month_work values('202301','201001',30,30,0,0,1,0,0,0,0,0,'20230125');

insert into hr_month_work values('202301','202001',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','202002',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','203001',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','204001',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','204002',30,30,1,0,0,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','204003',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','204004',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','205001',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','205002',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','205003',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206001',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206002',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206003',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206004',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206005',30,30,0,0,1,0,0,0,0,0,'20230125');

insert into hr_month_work values('202301','206006',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206007',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206008',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202301','206009',30,30,0,0,1,0,0,0,0,0,'20230125');

4.월 근태(테이블 명:hr_month_work)
	4-2) 2023년 2월 월근태
insert into hr_month_work values('202302','201001',30,30,0,0,1,0,0,0,0,0,'20230125');

insert into hr_month_work values('202302','202001',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','202002',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','203001',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','204001',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','204002',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','204003',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','204004',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','205001',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','205002',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','205003',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206001',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206002',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206003',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206004',30,30,0,1,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206005',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206006',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206007',30,30,2,0,0,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206008',30,30,0,0,1,0,0,0,0,0,'20230125');
insert into hr_month_work values('202302','206009',30,30,0,0,1,0,0,0,0,0,'20230125');
