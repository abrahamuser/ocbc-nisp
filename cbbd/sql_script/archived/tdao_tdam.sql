INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '5601', 'TDAO', 'menuAccess', 'X' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='5601');

INSERT INTO mleb_mcb.generalcode (row_id,gncode,gncode_description,gncode_type,status) 
SELECT mleb_mcb.GeneralCode_id.nextval, '5602', 'TDAM', 'menuAccess', 'X' FROM MLEB_MCB.generalcode 
WHERE ROWNUM=1 AND NOT EXISTS(SELECT gncode FROM MLEB_MCB.generalcode WHERE gncode_type = 'menuAccess' AND gncode='5602');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '5601', 'VM.0002', 'TDAO', 0, 0, NULL, 1, 1, '5601' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '5601');

INSERT INTO mleb_mcb.access_restriction (menu_id, menu_parent_id,menu_description, access_validate, role_validate, role_type, menu_validate, block_validate, menu_validate_id) 
SELECT '5602', 'VM.0002', 'TDAM', 0, 0, NULL, 1, 1, '5602' FROM mleb_mcb.access_restriction 
WHERE ROWNUM=1 AND NOT EXISTS (SELECT menu_id FROM mleb_mcb.access_restriction WHERE menu_id = '5602');

DELETE FROM MLEB_MCB.TNC WHERE PRODUCT_KEY = 'TDAO';
INSERT INTO MLEB_MCB.TNC (ROW_ID, PRODUCT_KEY, TNC_URL, FOOTER, LOCALE) VALUES (MLEB_MCB.TNC_ID.nextval, 'TDAO', '/home/oracle/ocbc/apps/tnc/TDAO.html', '', 'EN');
INSERT INTO MLEB_MCB.TNC (ROW_ID, PRODUCT_KEY, TNC_URL, FOOTER, LOCALE) VALUES (MLEB_MCB.TNC_ID.nextval, 'TDAO', '/home/oracle/ocbc/apps/tnc/TDAO_IN.html', '', 'IN');