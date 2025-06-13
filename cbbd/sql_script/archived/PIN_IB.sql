INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'VM.0025', 'PIN IB', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='VM.0025');
