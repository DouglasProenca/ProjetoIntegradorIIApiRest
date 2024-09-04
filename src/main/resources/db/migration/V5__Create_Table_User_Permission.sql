IF NOT EXISTS (SELECT *
               FROM INFORMATION_SCHEMA.TABLES
               WHERE UPPER(TABLE_NAME) = 'USUARIO_PERMISSAO'
                 AND UPPER(TABLE_SCHEMA) = 'SEGURANCA')
BEGIN

 CREATE TABLE seguranca.usuario_permissao (
    usuario INT NOT NULL,
    permissao INT NOT NULL,
    data_criacao DATE NOT NULL,
    ultima_atualizacao DATE
 )

 ALTER TABLE seguranca.usuario_permissao ADD CONSTRAINT fk_usuario_permissao_usuario FOREIGN KEY (usuario) REFERENCES seguranca.usuario(id)
 ALTER TABLE seguranca.usuario_permissao ADD CONSTRAINT fk_usuario_permissao_permissao FOREIGN KEY (permissao) REFERENCES seguranca.permissao(id)

END