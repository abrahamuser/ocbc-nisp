UPDATE MLEB_MCB.GENERALCODE SET GNCODE='VM.0019' WHERE GNCODE_TYPE='menuAccess' AND GNCODE = '9101';

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '9101', 'My Account', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='9101');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'VM.0020', 'Deposit Account', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='VM.0020');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'VM.0021', 'Time Deposit', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='VM.0021');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '9501', 'Unit Trust', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='9501');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '9502', 'Bonds', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='9502');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '9401', 'Loans', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='9401');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '9301', 'SPT', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='9301');

DELETE FROM MLEB_MCB.ACCESS_RESTRICTION WHERE MENU_ID='9101';
INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT 'VM.0019', '0', 'My Account', 0, 1, 'SB_STMTVWR', 0, 1, NULL FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = 'VM.0019');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '9101', '0', 'My Account', 0, 1, 'SB_STMTVWR', 0, 1, NULL FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '9101');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT 'VM.0020', 'VM.0019', 'Deposit Account', 0, 1, 'SB_STMTVWR', 1, 1, '9101' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = 'VM.0020');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT 'VM.0021', 'VM.0019', 'Time Deposit', 0, 1, 'SB_STMTVWR', 1, 1, '9101' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = 'VM.0021');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '9501', 'VM.0019', 'Unit Trust', 0, 1, 'SB_STMTVWR', 1, 1, '9501' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '9501');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '9502', 'VM.0019', 'Bonds', 0, 1, 'SB_STMTVWR', 1, 1, '9502' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '9502');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '9401', 'VM.0019', 'Loans', 0, 1, 'SB_STMTVWR', 1, 1, '9401' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '9401');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '9301', 'VM.0019', 'SPT', 0, 1, 'SB_STMTVWR', 1, 1, '9301' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '9301');