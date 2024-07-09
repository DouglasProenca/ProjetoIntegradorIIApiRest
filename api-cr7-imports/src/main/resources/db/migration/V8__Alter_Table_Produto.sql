IF NOT EXISTS( SELECT * FROM cr7imports.INFORMATION_SCHEMA.COLUMNS 
            WHERE UPPER(TABLE_NAME) = UPPER('rc_produto') 
            AND  UPPER(COLUMN_NAME) = UPPER('ativo'))
 ALTER TABLE rc_produto ADD ativo BIT           