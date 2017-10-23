use divulgaeditais;

/* Inserts Default */

## Roles ##
INSERT INTO roles (role_id,name,observation) VALUES (1, 'Administrador', 'Função Padrão do Sistema');

## Users ##
INSERT INTO users (user_id,name,email,password,identification,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible) values (1,'Administrador','sisa@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','12.456-000','37.700-345','Avenida Dirce Pereira Rosa',300,'Campus','Jardim Esperança','Poços de Caldas','MG','3598888-4321','3597777-1234','Marceneiro');
INSERT INTO users (user_id,name,email,password,identification,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible) values (2,'Usuário 2','usuario2@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','42.446-000','37.700-000','Avenida Saudade',111,'Casa','Vila 1 ','Poços de Caldas','MG','3598888-4321','3597777-1234','Mecânico');
INSERT INTO users (user_id,name,email,password,identification,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible) values (3,'Usuário 3','usuario3@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','72.556-000','37.700-111','Avenida Felicidade',222,'Apto','Rosa 2','Poços de Caldas','MG','3598888-4321','3597777-1234','Pedreiro');
INSERT INTO users (user_id,name,email,password,identification,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible) values (4,'Usuário 4','usuario4@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','92.666-000','37.700-222','Avenida Realização',333,'Edifício','Bairro Formoso','Poços de Caldas','MG','3598888-4321','3597777-1234','Carpinteiro');

## User Role ##
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (1,1,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (2,2,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (3,3,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (4,4,1,'2016-01-01','2030-12-31');

## Providers ##
INSERT INTO providers (provider_id,name,url) VALUES (1,'Provedor 1','www.provedor1.com.br');
INSERT INTO providers (provider_id,name,url) VALUES (2,'Provedor 2','www.provedor2.com.br');
INSERT INTO providers (provider_id,name,url) VALUES (3,'Provedor 3','www.provedor3.com.br');
INSERT INTO providers (provider_id,name,url) VALUES (4,'Provedor 4','www.provedor4.com.br');

## Modalities ##

INSERT INTO modalities (modality_id,acronyms,description) VALUES (1,'PRP-E','Descrição PRP-E');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (2,'PRE','Descrição PRE');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (3,'INEX','Descrição INEX');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (4,'PRP','Descrição PRP');


## Notices ##
INSERT INTO notices (notice_id,modality_id,number,object,trading_date,url) VALUES (1,1,'272/17',"FORNECIMENTO DE PEDRA BRITA",'2017-01-01','pregao1.com.br');
INSERT INTO notices (notice_id,modality_id,number,object,trading_date,url) VALUES (2,2,'328/17',"AQUISIÇÃO DE EQUIPAMENTOS",'2018-02-11','pregao2.com.br');
INSERT INTO notices (notice_id,modality_id,number,object,trading_date,url) VALUES (3,3,'321/17',"FORNECIMENTO DE MATERIAIS RADIOLÓGICOS",'2019-03-21','pregao3.com.br');
INSERT INTO notices (notice_id,modality_id,number,object,trading_date,url) VALUES (4,4,'325/17',"AQUISIÇÃO DE CAMINHÃO",'2020-04-30','pregao4.com.br');

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