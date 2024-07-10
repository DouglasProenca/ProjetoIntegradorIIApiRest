USE [cr7imports]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_isCPF]    Script Date: 19/11/2023 11:09:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE OR ALTER   FUNCTION [dbo].[fn_isCPF] (@cpf VARCHAR(100))
RETURNS BIT
BEGIN

DECLARE @is_cpf BIT 

-- Primeira Verificação
SELECT @is_cpf = CASE WHEN @cpf = '00000000000' THEN 0
					  WHEN @cpf = '11111111111' THEN 0
					  WHEN @cpf = '22222222222' THEN 0
					  WHEN @cpf = '33333333333' THEN 0
					  WHEN @cpf = '44444444444' THEN 0
					  WHEN @cpf = '55555555555' THEN 0
					  WHEN @cpf = '66666666666' THEN 0
					  WHEN @cpf = '77777777777' THEN 0
					  WHEN @cpf = '88888888888' THEN 0
					  WHEN @cpf = '99999999999' THEN 0
					  WHEN LEN(@cpf) <> 11 THEN 0 ELSE 1 END 

---- segunda vericação
IF @is_cpf = 1 
BEGIN

	DECLARE @dig10 CHAR(1)
		  , @dig11 CHAR(1)
		  , @sm INT
		  , @i INT
		  , @r INT
		  , @num INT
		  , @peso INT 
		  , @contador INT -- para os whiles

	SET @peso = 10
	SET @sm = 0
	SET @contador = 1


	--Calculo do 1o. Digito Verificador
	WHILE @contador < 10 
		BEGIN 

		SET @num = (CAST(SUBSTRING(@cpf, @contador, 1) AS INT)) 
		SET @sm = @sm + (@num * @peso)
		SET @peso = @peso - 1

		SET @contador = @contador + 1

	END

	SET @r = 11 - (@sm % 11)

	IF ((@r = 10) OR (@r = 11)) 
	BEGIN
	    SET @dig10 = '0'
	END
	ELSE 
	BEGIN
	    SET @dig10 = CAST((@r) AS CHAR(1))
	END

	--Calculo do 2o. Digito Verificador
	SET @sm = 0
	SET @peso = 11
	SET @contador = 1

	WHILE @contador < 11
	BEGIN
	    SET @num = (CAST(SUBSTRING(@cpf, @contador, 1) AS INT))
	    SET @sm = @sm + (@num * @peso)
	    SET @peso = @peso - 1

		SET @contador = @contador + 1
	END

	SET @r = 11 - (@sm % 11);
	IF ((@r = 10) OR (@r = 11)) 
	BEGIN
	   SET @dig11 = 0
	END
	ELSE
	BEGIN
	   SET @dig11 = @r
	END

	--Verifica se os digitos calculados conferem com os digitos informados.
	IF ((@dig10 = CAST(SUBSTRING(@cpf, 10, 1) AS char(1))) AND (@dig11 = CAST(SUBSTRING(@cpf, 11, 1) AS char(1)))) 
	BEGIN
	    SET @is_cpf = 1
	END
	ELSE 
	BEGIN
	    SET @is_cpf = 0
	END


END

RETURN @is_cpf
END

