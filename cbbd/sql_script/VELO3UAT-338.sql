-- Menu Access

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '10114', 'Auth Trans Noti', 'menuAccess', 'A' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='10114');

-- Access Control

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '10114', 'VM.0023', 'Auth Trans Noti', 0, 0, NULL, 1, 1, '10114' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '10114');

