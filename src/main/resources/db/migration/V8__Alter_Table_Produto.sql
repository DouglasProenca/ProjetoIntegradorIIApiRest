IF NOT EXISTS( SELECT * FROM cr7imports.INFORMATION_SCHEMA.COLUMNS 
            WHERE UPPER(TABLE_NAME) = UPPER('produto') 
            AND  UPPER(COLUMN_NAME) = UPPER('ativo'))
 ALTER TABLE produto ADD ativo BIT           