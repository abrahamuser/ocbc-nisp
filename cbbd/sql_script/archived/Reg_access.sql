-- Menu Access

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, 'VM.0024', 'Registration', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='VM.0024');


-- Access Control
INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT 'VM.0024', '0', 'Registration', 0, 0, NULL, 0, 1, NULL FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = 'VM.0024');

