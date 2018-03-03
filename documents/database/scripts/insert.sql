use divulgaeditais;

/* Inserts Default */

## Roles ##
INSERT INTO roles (role_id,name,observation) VALUES (1, 'Administrador', 'Função Padrão do Sistema');

## Users ##
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (1,'Administrador','Root do Sistema','sisa@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','12.456.000.0001.34','prefeitura','ME','Saude','44444','37.700-345','Avenida Dirce Pereira Rosa',300,'Campus','Jardim Esperança','Poços de Caldas','MG','3598888-4321','3597777-1234','Administrator','666.555.444.33');
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (2,'Usuário 2','Usuário Comum do Sistema 2','usuario2@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','42.446.000.0003-33','empresa','PE','Financeiro','55555','37.700-000','Avenida Saudade',111,'Casa','Vila 1 ','Poços de Caldas','MG','3598888-4321','3597777-1234','Mecânico','111.222.333.44');
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (3,'Usuário 3','Usuário Comum do Sistema 3','usuario3@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','72.556-000.0005-44','prefeitura','GE','RH','66666','37.700-111','Avenida Felicidade',222,'Apto','Rosa 2','Poços de Caldas','MG','3598888-4321','3597777-1234','Pedreiro','222.333.444.55');
INSERT INTO users(user_id,social_name,fantasy_name,email,password,cnpj,type,company_type,branch,cnae,zip_code,address,number,complement,neighborhood,city,state,phone_primary,phone_secondary,responsible_name,responsible_cpf) values (4,'Usuário 4','Usuário Comum do Sistema 4','usuario4@ifsuldeminas.edu.br','1VsR4a3oE9aV9rpHFeIoyxp2Kdc=','92.666-000.0006-55','empresa','ME','Expedição','77777','37.700-222','Avenida Realização',333,'Edifício','Bairro Formoso','Poços de Caldas','MG','3598888-4321','3597777-1234','Carpinteiro','333.444.555.66');

## User Role ##
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (1,1,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (2,2,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (3,3,1,'2016-01-01','2030-12-31');
INSERT INTO users_roles (user_role_id,user_id,role_id,start_date,end_date) VALUES (4,4,1,'2016-01-01','2030-12-31');

## Modalities ##
INSERT INTO modalities (modality_id,acronyms,description) VALUES (1,'PRP','Pregão Presencial');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (2,'PRP-E','Pregão Eltrônico');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (3,'CRC','Concorrência');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (4,'TDP','Tomada de Preços');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (5,'CON','Convite');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (6,'CCR','Concurso');
INSERT INTO modalities (modality_id,acronyms,description) VALUES (7,'LEI','Leilão');

## Company Types ##
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (1,'MEI','Micro Empreendedor Individual');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (2,'ME','Micro Empresa');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (3,'EPP','Empresa de Pequeno Porte');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (4,'SA','Sociedade Anonima');
INSERT INTO company_types (company_type_id,acronyms,description) VALUES (5,'LDTA','Sociedade Limitada');

## Notices ##
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (1,1,1,1,'272/17',"FORNECIMENTO DE PEDRA BRITA",'Ativo','2017-01-01','2017-02-01','2017-03-01','pregao1.pdf');
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (2,2,2,2,'328/17',"AQUISIÇÃO DE EQUIPAMENTOS",'Inativo','2018-02-11','2018-03-16','2018-05-13','pregao2.pdf');
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (3,3,3,3,'321/17',"FORNECIMENTO DE MATERIAIS RADIOLÓGICOS",'Ativo','2019-03-21','2019-03-29','2019-04-21','pregao3.pdf');
INSERT INTO notices (notice_id,modality_id,inserted_by,company_type_id,number,object,status,trading_date,publishing_date,closing_date,file_name) VALUES (4,4,4,4,'325/17',"AQUISIÇÃO DE CAMINHÃO",'Inativo','2020-04-30','2020-04-30','2020-06-30','pregao4.pdf');

## Categories ##
INSERT INTO categories (category_id,description) VALUES (1,'Limpeza');
INSERT INTO categories (category_id,description) VALUES (2,'Obras');
INSERT INTO categories (category_id,description) VALUES (3,'Escritório');
INSERT INTO categories (category_id,description) VALUES (4,'Alimentos');

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