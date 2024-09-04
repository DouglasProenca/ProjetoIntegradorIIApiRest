IF NOT EXISTS (SELECT * 
               FROM seguranca.permissao 
               WHERE UPPER(descricao) = 'ADMINISTRADOR')
INSERT INTO seguranca.permissao VALUES (1,'Administrador',GETDATE(),NULL)  