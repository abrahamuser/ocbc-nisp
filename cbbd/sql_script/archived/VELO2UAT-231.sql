INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '026', 'Submitted to Bank', 'TRANXSTATUSCODE', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE' AND gncode='026');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '026', 'Dikirik ke Bank', 'TRANXSTATUSCODE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE_IN' AND gncode='026');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '029', 'Pending COT', 'TRANXSTATUSCODE', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE' AND gncode='029');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '029', 'Tertunda COT', 'TRANXSTATUSCODE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE_IN' AND gncode='029');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '033', 'Rejected', 'TRANXSTATUSCODE', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE' AND gncode='033');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '033', 'Ditolak', 'TRANXSTATUSCODE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE_IN' AND gncode='033');