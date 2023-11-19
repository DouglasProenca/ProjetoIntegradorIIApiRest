CREATE PROCEDURE sp_insert_rc_user  
    @nome VARCHAR(500)
  , @password VARCHAR(1000)
  , @email VARCHAR(1000)
  , @mailPassword VARCHAR(1000)
  , @data DATETIME
  , @account_non_expired CHAR(5)
  , @account_non_locked CHAR(5)
  , @credentials_non_expired CHAR(5)
  , @enabled CHAR(5)
AS BEGIN

DECLARE @account_non_expiredBit BIT
	  , @account_non_lockedBit BIT
	  , @credentials_non_expiredBit BIT
	  , @enabledBit BIT

SELECT @account_non_expiredBit = IIF(@account_non_expired = 'false', 1,0)
	 , @account_non_lockedBit = IIF(@account_non_locked = 'false', 1,0)
	 , @credentials_non_expiredBit = IIF(@credentials_non_expired = 'false', 1,0)
	 , @enabledBit = IIF(@enabled = 'true', 1,0)

INSERT INTO rc_user VALUES(@nome 
						 , @password
						 , @email 
						 , @mailPassword 
						 , @data 
						 , @account_non_expiredBit 
						 , @account_non_lockedBit
						 , @credentials_non_expiredBit 
						 , @enabledBit)

END
