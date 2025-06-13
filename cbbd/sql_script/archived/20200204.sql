INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, '037', 'Waiting for EOD', 'TRANXSTATUSCODE', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE' AND gncode='037');
INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status)
SELECT mleb_mcb.GeneralCode_id.nextval, '037', 'Menunggu EOD', 'TRANXSTATUSCODE_IN', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'TRANXSTATUSCODE_IN' AND gncode='037');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'VM.0008', 'Task List', 'menuAccess', 'X' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='VM.0008');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate) 
SELECT 'VM.0008', '0', 'Task List', 0, 0, NULL, 0, 1 FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = 'VM.0008');

UPDATE mleb_mcb.generalcode SET STATUS = 'A' WHERE GNCODE_TYPE='menuAccess' AND GNCODE='VM.0007';
