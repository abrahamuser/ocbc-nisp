INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '9302', 'Account Statement', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='9302');



INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '9302', 'VM.0019', 'Account Statement', 0, 1, 'SB_STMTVWR', 1, 1, '9302' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '9302');
