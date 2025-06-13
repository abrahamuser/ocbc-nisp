INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'V2FLAG', 'true', 'ONFX_SETTINGS', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'ONFX_SETTINGS' AND gncode='V2FLAG');