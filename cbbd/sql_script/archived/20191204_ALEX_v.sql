INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'SHA', 'Shared', 'FT_TT_CHARGES_METHOD_EN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'FT_TT_CHARGES_METHOD_EN' AND gncode='SHA');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'SHA', 'Shared', 'FT_TT_CHARGES_METHOD_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'FT_TT_CHARGES_METHOD_IN' AND gncode='SHA');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'OUR', 'Ourselves', 'FT_TT_CHARGES_METHOD_EN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'FT_TT_CHARGES_METHOD_EN' AND gncode='OUR');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'OUR', 'Ourselves', 'FT_TT_CHARGES_METHOD_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'FT_TT_CHARGES_METHOD_IN' AND gncode='OUR');

UPDATE mleb_mcb.generalcode SET status = 'A' WHERE gncode_type='menuAccess' and gncode='VM.0005';