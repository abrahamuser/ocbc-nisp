INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'A', 'Rollover with interest compound', 'ROLLOVERTYPE_EN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'ROLLOVERTYPE_EN' AND gncode='A');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'Y', 'Rollover', 'ROLLOVERTYPE_EN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'ROLLOVERTYPE_EN' AND gncode='Y');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'N', 'Non-rollover', 'ROLLOVERTYPE_EN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'ROLLOVERTYPE_EN' AND gncode='N');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'A', 'Rollover with interest compound', 'ROLLOVERTYPE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'ROLLOVERTYPE_IN' AND gncode='A');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'Y', 'Rollover', 'ROLLOVERTYPE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'ROLLOVERTYPE_IN' AND gncode='Y');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, 'N', 'Non-rollover', 'ROLLOVERTYPE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'ROLLOVERTYPE_IN' AND gncode='N');
