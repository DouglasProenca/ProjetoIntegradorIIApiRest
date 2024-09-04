IF NOT EXISTS(SELECT * 
              FROM INFORMATION_SCHEMA.TABLES
              WHERE UPPER(TABLE_NAME) = 'PERMISSAO'
                AND UPPER(TABLE_SCHEMA) = 'SEGURANCA')
CREATE TABLE seguranca.permissao (
   id INT PRIMARY KEY IDENTITY,
   descricao VARCHAR(255) NOT NULL,
   data_criacao DATE NOT NULL,
   ultima_atualizacao DATE
)    