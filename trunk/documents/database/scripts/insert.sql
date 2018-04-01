use divulgaeditais;

/* Inserts Default */

## Roles ##
INSERT INTO roles (role_id,name,observation) VALUES (1, 'Administrador', 'Fun��o Padr�o do Sistema');

## Users ##
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (1,'Administrador','Root do Sistema','divulga@ifsuldeminas.edu.br','coIFObJIrlnBRt6GiDRllgN4TXo=','12.456.000.0001.34','prefeitura','ME','Saude','44444','37.700-345','Avenida Dirce Pereira Rosa',300,'Campus','Jardim Esperan�a','Po�os de Caldas','MG','3598888-4321','3597777-1234','Administrator','666.555.444.33');
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (2,'Usu�rio 2','Usu�rio Comum do Sistema 2','usuario2@ifsuldeminas.edu.br','coIFObJIrlnBRt6GiDRllgN4TXo=','42.446.000.0003-33','empresa','PE','Financeiro','55555','37.700-000','Avenida Saudade',111,'Casa','Vila 1 ','Po�os de Caldas','MG','3598888-4321','3597777-1234','Mec�nico','111.222.333.44');
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (3,'Usu�rio 3','Usu�rio Comum do Sistema 3','usuario3@ifsuldeminas.edu.br','coIFObJIrlnBRt6GiDRllgN4TXo=','72.556-000.0005-44','prefeitura','GE','RH','66666','37.700-111','Avenida Felicidade',222,'Apto','Rosa 2','Po�os de Caldas','MG','3598888-4321','3597777-1234','Pedreiro','222.333.444.55');
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (4,'Usu�rio 4','Usu�rio Comum do Sistema 4','usuario4@ifsuldeminas.edu.br','coIFObJIrlnBRt6GiDRllgN4TXo=','92.666-000.0006-55','empresa','ME','Expedi��o','77777','37.700-222','Avenida Realiza��o',333,'Edif�cio','Bairro Formoso','Po�os de Caldas','MG','3598888-4321','3597777-1234','Carpinteiro','333.444.555.66');

## User Role ##
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (1,1,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (2,2,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (3,3,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (4,4,1,'2016-01-01','2030-12-31');

## Modalities ##
INSERT INTO modalities (modality_id,acronyms,description) VALUES (1,'PRP','Preg�o Presencial');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (2,'PRP-E','Preg�o Eltr�nico');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (3,'CRC','Concorr�ncia');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (4,'TDP','Tomada de Pre�os');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (5,'CON','Convite');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (6,'CCR','Concurso');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (7,'LEI','Leil�o');

## Company Types ##
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (1,'MEI','Micro Empreendedor Individual');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (2,'ME','Micro Empresa');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (3,'EPP','Empresa de Pequeno Porte');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (4,'SA','Sociedade Anonima');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (5,'LDTA','Sociedade Limitada');

## Notices ##
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (1,1,1,1,'272/17',"FORNECIMENTO DE PEDRA BRITA",'Ativo','2017-01-01','2017-02-01','2017-03-01','pregao1.pdf');
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (2,2,2,2,'328/17',"AQUISI��O DE EQUIPAMENTOS",'Inativo','2018-02-11','2018-03-16','2018-05-13','pregao2.pdf');
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (3,3,3,3,'321/17',"FORNECIMENTO DE MATERIAIS RADIOL�GICOS",'Ativo','2019-03-21','2019-03-29','2019-04-21','pregao3.pdf');
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (4,4,4,4,'325/17',"AQUISI��O DE CAMINH�O",'Inativo','2020-04-30','2020-04-30','2020-06-30','pregao4.pdf');

## Categories ##
INSERT INTO categories (category_id,description) VALUES (1,'ALIMENTA��O');
INSERT INTO categories (category_id,description) VALUES (2,'ASSESSORIAS');
INSERT INTO categories (category_id,description) VALUES (3,'COMBUST�VEIS');
INSERT INTO categories (category_id,description) VALUES (4,'DECORA��O E CONFEC��O');
INSERT INTO categories (category_id,description) VALUES (5,'ELETROELETR�NICOS');
INSERT INTO categories (category_id,description) VALUES (6,'EMBALAGENS E DESCART�VEIS');
INSERT INTO categories (category_id,description) VALUES (7,'ENGENHARIA E CONSTRU��O');
INSERT INTO categories (category_id,description) VALUES (8,'ESCRIT�RIO, GR�FICA E SINALIZA��O');
INSERT INTO categories (category_id,description) VALUES (9,'ESPORTIVOS');
INSERT INTO categories (category_id,description) VALUES (10,'EVENTOS E MUSICAIS');
INSERT INTO categories (category_id,description) VALUES (11,'FERRAMENTAS E EQUIPAMENTOS DIVERSOS');
INSERT INTO categories (category_id,description) VALUES (12,'FUNER�RIOS');
INSERT INTO categories (category_id,description) VALUES (13,'INFORM�TICA');
INSERT INTO categories (category_id,description) VALUES (14,'INSTALA��ES');
INSERT INTO categories (category_id,description) VALUES (15,'LIMPEZA');
INSERT INTO categories (category_id,description) VALUES (16,'LIMPEZA URBANA');
INSERT INTO categories (category_id,description) VALUES (17,'MATERIAIS PARA OBRAS');
INSERT INTO categories (category_id,description) VALUES (18,'MOBILI�RIOS');
INSERT INTO categories (category_id,description) VALUES (19,'PRODUTOS AGR�COLAS');
INSERT INTO categories (category_id,description) VALUES (20,'PRODUTOS QU�MICOS E SIMILARES');
INSERT INTO categories (category_id,description) VALUES (21,'PUBLICIDADE');
INSERT INTO categories (category_id,description) VALUES (22,'SA�DE, HOSPITALARES, ODONTOL�GICOS E SIMILARES');
INSERT INTO categories (category_id,description) VALUES (23,'SEGURAN�A E PROTE��O INDIVIDUAL E COLETIVA � EPI E EPC');
INSERT INTO categories (category_id,description) VALUES (24,'TELECOMUNICA��ES');
INSERT INTO categories (category_id,description) VALUES (25,'TRANSPORTES');
INSERT INTO categories (category_id,description) VALUES (26,'UTENS�LIOS DOM�STICOS');
INSERT INTO categories (category_id,description) VALUES (27,'VE�CULOS');
INSERT INTO categories (category_id,description) VALUES (28,'OUTROS');

## Notices Categories ##
INSERT INTO notices_categories (notice_category_id,notice_id,category_id) VALUES (1,1,1);
INSERT INTO notices_categories (notice_category_id,notice_id,category_id) VALUES (2,2,2);
INSERT INTO notices_categories (notice_category_id,notice_id,category_id) VALUES (3,3,3);
INSERT INTO notices_categories (notice_category_id,notice_id,category_id) VALUES (4,4,4);

## Users Categories ##
INSERT INTO users_categories (user_category_id,user_id,category_id) VALUES (1,1,1);
INSERT INTO users_categories (user_category_id,user_id,category_id) VALUES (2,2,2);
INSERT INTO users_categories (user_category_id,user_id,category_id) VALUES (3,3,3);
INSERT INTO users_categories (user_category_id,user_id,category_id) VALUES (4,4,4);

## Users Notices ##
INSERT INTO users_notices (user_notice_id,user_id,notice_id) VALUES (1,1,1);
INSERT INTO users_notices (user_notice_id,user_id,notice_id) VALUES (2,2,2);
INSERT INTO users_notices (user_notice_id,user_id,notice_id) VALUES (3,3,3);
INSERT INTO users_notices (user_notice_id,user_id,notice_id) VALUES (4,4,4);

/* Fim */

/*
SET innodb_lock_wait_timeout = 200;

INSERT INTO mysql.user (Host, User, Password, Select_priv, max_connections, max_user_connections, ssl_cipher, x509_issuer, x509_subject,
Insert_priv, Update_priv, Delete_priv, Create_priv, Drop_priv, Reload_priv, Shutdown_priv, Process_priv, File_priv, Grant_priv, 
References_priv, Index_priv, Alter_priv, Show_db_priv, Super_priv, Create_tmp_table_priv, Lock_tables_priv, Execute_priv, 
Repl_slave_priv, Repl_client_priv, Create_view_priv, Show_view_priv, Create_routine_priv, Alter_routine_priv, Create_user_priv) 
VALUES ('%', 'root', '', 'Y', 200, 200, '','','',
'Y', 'Y', 'Y', 'N', 'N', 'N', 'N', 'N', 'N', 'N',
'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 
'N', 'N', 'N', 'N', 'N', 'N', 'N');

GRANT ALL ON *.* TO 'root'@'%';

flush privileges ;
*/