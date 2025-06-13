INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, '024', 'Rejected', 'TRANXSTATUSCODE', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE' AND gncode='024');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, '024', 'Ditolak', 'TRANXSTATUSCODE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE_IN' AND gncode='024');